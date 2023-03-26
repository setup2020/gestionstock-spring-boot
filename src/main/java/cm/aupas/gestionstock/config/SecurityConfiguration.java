package cm.aupas.gestionstock.config;

import cm.aupas.gestionstock.dto.LoginDto;
import cm.aupas.gestionstock.dto.UserDto;
import cm.aupas.gestionstock.services.UserService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.stream.Collectors;

import static cm.aupas.gestionstock.utils.Constants.APP_ROOT;


@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private final RsaKeysConfig rsaKeysConfig;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(RsaKeysConfig rsaKeysConfig,PasswordEncoder passwordEncoder1) {

        this.rsaKeysConfig = rsaKeysConfig;
        this.passwordEncoder = passwordEncoder1;
    }


    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(authenticationProvider);
    }




    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Autowired
            private UserService accountService;
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                LoginDto appUser=accountService.findByUserName(username);
                log.warn("-------------------------------user---------------------------- {}",appUser);
                if (appUser==null) throw new UsernameNotFoundException("User not found");
                //Collection<GrantedAuthority> authorities= List.of(new SimpleGrantedAuthority("USER"));
                Collection<GrantedAuthority> authorities=appUser.getRoles().stream().map(r->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());

                return new User(username,appUser.getPassword(),authorities);
            }
        };
    }










  // @Bean
  WebSecurityCustomizer webSecurityCustomizer(){
      return (web -> web.ignoring().antMatchers("/**"));
  }



    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(csrf->csrf.disable()) //(1)
                .authorizeRequests(auth->auth.antMatchers("/"+APP_ROOT+"/token/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**"
                        ).permitAll() )

                   .authorizeRequests(auth->auth.anyRequest().authenticated()) // (2)

                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // (3)
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
               // .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsaKeysConfig.publicKey()).build();
        //return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
    }
    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk=new RSAKey.Builder(rsaKeysConfig.publicKey()).privateKey(rsaKeysConfig.privateKey()).build();
        //JWK jwk=new RSAKey.Builder((RSAPublicKey)keyPair.getPublic()).privateKey(keyPair.getPrivate()).build();
        JWKSource<SecurityContext> jwkSource=new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }




}
