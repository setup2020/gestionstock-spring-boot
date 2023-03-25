package cm.aupas.gestionstock.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;

@RestController
public class AuthController {

    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;
    private  final JwtDecoder jwtDecoder;
    private final UserDetailsService userDetailsService;

    public AuthController(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager, JwtDecoder jwtDecoder, UserDetailsService userDetailsService) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtDecoder = jwtDecoder;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(APP_ROOT+"/token")

    public ResponseEntity<Map<String,String>> jwtToken(String grantType,
                                   String username,
                                   String password,
                                   boolean withRefreshToken,
                                   String refreshToken
                                       ){
        String subject=null;
        String scope=null;

        if(grantType.equals("password")){

         Authentication  authentication  = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
         subject=authentication.getName();
         scope= authentication.getAuthorities().stream().map(aut -> aut.getAuthority()).collect(Collectors.joining(" "));


        }else if(grantType.equals("refreshToken")){
            if(refreshToken==null){
                return new ResponseEntity<>(Map.of("ErrorMessage","Refresh token is required"),HttpStatus.UNAUTHORIZED);
            }

            Jwt decode =null;
            try {
             decode=   jwtDecoder.decode(refreshToken);
            } catch (JwtException e){
                return new ResponseEntity<>(Map.of("ErrorMessage",e.getMessage()),HttpStatus.UNAUTHORIZED);
            }

            subject=decode.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
             scope=authorities.stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));




        }



        Map<String,String> idToken=new HashMap<>();

        Instant instant=Instant
                .now();


        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(withRefreshToken?5:30, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope",scope)
                .build();

        String jwtAccessToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

        idToken.put("accessToken",jwtAccessToken);
        if(withRefreshToken){
            JwtClaimsSet jwtClaimsSetRefresh=JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                    .issuer("security-service")
                    .build();

            String jwtRefreshToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();

            idToken.put("refreshToken",jwtRefreshToken);
        }

        return new ResponseEntity<>(idToken,HttpStatus.OK);

    }

    @GetMapping("/infos")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public Map<String,Object> dataTest( Authentication authentication){
        return Map.of("name","Compuer","price",7600,"authorities",authentication.getAuthorities());
    }
}
