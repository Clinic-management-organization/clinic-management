package com.clinic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.clinic.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

/**
 * Configuration de la sécurité de l'application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RSAKeyProperties keys;

    /**
     * Bean pour encoder les mots de passe avec BCrypt.
     * @return Encoder BCrypt.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean pour gérer l'authentification des utilisateurs.
     * @param detailsService Service pour récupérer les détails de l'utilisateur.
     * @return Gestionnaire d'authentification.
     */
    @Bean
    public AuthenticationManager authManager(UserDetailsService detailsService) {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(detailsService);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoProvider);
    }

    /**
     * Constructeur pour injecter les propriétés des clés RSA.
     * @param keys Propriétés des clés RSA.
     */
    public SecurityConfig(RSAKeyProperties keys) {
        this.keys = keys;
    }

    private static final String[] ALLOWED_PATHS_USER = {
            "/h2-console/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
    };

    private static final String[] ALLOWED_PATHS_ADMIN = {
            "/api/medecins/**",
            "/api/diagnostics/**",
            "/api/patients/**",
            "/api/dossiersMedicaux/**",
            "/api/traitements/**",
            "/api/consultations/**",
            "/api/rendezvous/**",
            "/h2-console/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
    };

    /**
     * Configuration des filtres de sécurité.
     * @param httpConfigurer Configuration HTTP.
     * @return Configuration de la chaîne de sécurité.
     * @throws Exception En cas d'erreur lors de la configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpConfigurer) throws Exception {
        httpConfigurer
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                        .disable())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(ALLOWED_PATHS_USER).hasRole("USER");
                    authorize.requestMatchers(ALLOWED_PATHS_ADMIN).hasRole("ADMIN");
                    authorize.requestMatchers("/auth/**").permitAll();
                    authorize.requestMatchers("/admin/**").hasRole("ADMIN");
                    authorize.requestMatchers("/user/**").hasAnyRole("ADMIN", "USER");
                    authorize.anyRequest().authenticated();
                })
                .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpConfigurer.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
        );

        return httpConfigurer.build();
    }

    /**
     * Configuration du décodeur JWT.
     * @return Décodeur JWT.
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }

    /**
     * Configuration de l'encodeur JWT.
     * @return Encodeur JWT.
     */
    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    /**
     * Configuration du convertisseur d'authentification JWT.
     * @return Convertisseur d'authentification JWT.
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtConverter;
    }
}
