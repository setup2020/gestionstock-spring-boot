package cm.aupas.gestionstock.controller;


import cm.aupas.gestionstock.dto.LoginDto;
import cm.aupas.gestionstock.dto.UserDto;
import cm.aupas.gestionstock.dto.auth.AuthenticationRequest;
import cm.aupas.gestionstock.exceptions.EntityNotFoundException;
import cm.aupas.gestionstock.services.UserService;
import cm.aupas.gestionstock.services.impl.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@Slf4j
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtDecoder jwtDecoder;

    private final UserService userService;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtDecoder jwtDecoder, UserService userService, TokenService tokenService) {

        this.authenticationManager = authenticationManager;
        this.jwtDecoder = jwtDecoder;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping( "/token")

    public ResponseEntity<Map<String, String>> requestForToken(AuthenticationRequest authenticationRequest
    ) {
        Map<String, String> response;
         if (authenticationRequest.getGrantType().equals("password")) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );

            response = tokenService.generateJwtToken(authentication.getName(), authentication.getAuthorities(), authenticationRequest.isWithRefreshToken());
            return ResponseEntity.ok(response);
        } else if (authenticationRequest.getGrantType().equals("refreshToken")) {
            String refreshToken = authenticationRequest.getRefreshToken();
            if (refreshToken == null) {
                return new ResponseEntity<>(Map.of("error", "RefreshToken Not present"), HttpStatus.UNAUTHORIZED);
            }

            Jwt decodedJwt = jwtDecoder.decode(refreshToken);
            String username = decodedJwt.getSubject();
            LoginDto userDto = userService.findByUserName(username);
            Collection<GrantedAuthority> authorities = userDto.getRoles().stream().map(roleDto -> new SimpleGrantedAuthority(roleDto.getName())).collect(Collectors.toList());

            response = tokenService.generateJwtToken(userDto.getUsername(), authorities, authenticationRequest.isWithRefreshToken());
            return ResponseEntity.ok(response);

        }
        return new ResponseEntity<>(Map.of("error",String.format("grantType <<%s>> not supported ",authenticationRequest.getGrantType())),HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/infos")
    // @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<UserDto> infoUser(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            Jwt decodedJwt = jwtDecoder.decode(authHeader.substring(7));
            String username = decodedJwt.getSubject();
            UserDto appUser = userService.findByUserName1(username);
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        } else {
            throw new EntityNotFoundException("Invalid token");
        }


    }


}
