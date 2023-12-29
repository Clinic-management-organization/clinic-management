package com.clinic.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	 private static final String[] ALLOWED_PATHS = {
	            "/api/medecins/**",
	            "/api/diagnostics/**",
	            "/api/patients/**",
	            "/api/dossiersMedicaux/**",
	            "/api/traitements/**",
	            "/api/consultations/**",
	            "/api/rendezvous/**",
	            "/h2-console/**",
	            "/swagger-ui/**",
	            "/v3/api-docs/**"
	    };
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
        		.ignoringRequestMatchers("/h2-console/**")
        		.disable())
                //.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
		        .authorizeHttpRequests(authorize -> authorize
                		.requestMatchers(ALLOWED_PATHS)
                		.permitAll()
                		.anyRequest()
                		.authenticated()
                )
		        .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) ;
;
// To swagger  authorization
       http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("*"));
            configuration.setAllowedMethods(Arrays.asList("*"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
            return configuration;
        }));
        return http.build();
    }
}