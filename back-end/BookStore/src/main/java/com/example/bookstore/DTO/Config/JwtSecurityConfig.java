package com.example.bookstore.DTO.Config;



import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class JwtSecurityConfig {
    private JwtDecoder jwtDecoder;

    private final UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .cors(Customizer.withDefaults())
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/authenticate/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//
//                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER", "MANAGER");

                    authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(oAuth2 -> oAuth2
                        .jwt(jwt -> jwt
                                .decoder(jwtDecoder)
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())));
        return http.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter =
                new JwtGrantedAuthoritiesConverter();

        grantedAuthoritiesConverter.setAuthoritiesClaimName("scope");
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        // vì token của bạn là ROLE_USER

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return converter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        return keyPairGenerator.generateKeyPair();
    }

    @Bean
    public RSAKey rsaKey() throws Exception {
        return new RSAKey.Builder((RSAPublicKey) keyPair().getPublic())
                .privateKey((RSAPrivateKey) keyPair().getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }


    @Bean
    public JWKSource<SecurityContext> jwkSource(com.nimbusds.jose.jwk.RSAKey rsaKey) throws JOSEException {

        //JWKSet jwkSet = new JWKSet(rsaKey);

        return ((jwkSelector, securityContext) -> jwkSelector.select(new JWKSet(rsaKey)));
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {

        return NimbusJwtDecoder
                .withPublicKey(rsaKey.toRSAPublicKey())
                .build();
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {

        return new NimbusJwtEncoder(jwkSource);
    }
}
