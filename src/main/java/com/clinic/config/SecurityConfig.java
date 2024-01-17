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

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	   private final RSAKeyProperties keys;
	
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	      
		@Bean
	    public AuthenticationManager authManager(UserDetailsService detailsService){
	        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
	        daoProvider.setUserDetailsService(detailsService);
	        daoProvider.setPasswordEncoder(passwordEncoder());
	        return new ProviderManager(daoProvider);
	    }
	    
	public SecurityConfig(RSAKeyProperties keys) {
		System.out.println("keys"+keys);
			this.keys = keys;
		}
	private static final String[] ALLOWED_PATHS_ANY = {
            "/h2-console/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",	   
            "/auth/**",
    };

	private static final String[] ALLOWED_PATHS_USER = {
	            "/api/rendezvous/**",
	            "/api/dossiersMedicaux/**",
	    };
	private static final String[] ALLOWED_PATHS_ADMIN = {
            "/api/medecins/**",
            "/api/diagnostics/**",
            "/api/patients/**",
            "/api/dossiersMedicaux/**",
            "/api/traitements/**",
            "/api/consultations/**",
            "/api/rendezvous/**",
    };

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
        		.ignoringRequestMatchers("/h2-console/**")
        		.disable())
                .authorizeHttpRequests(authorize ->{
	            		authorize.requestMatchers(ALLOWED_PATHS_USER)
	            		.hasAnyRole("USER", "ADMIN");
	            		authorize.requestMatchers(ALLOWED_PATHS_ADMIN)
	            		.hasRole("ADMIN");
//                		.permitAll();
                		authorize.requestMatchers(ALLOWED_PATHS_ANY)
                		.permitAll();
                		authorize.anyRequest().authenticated();
		        })
                
		        .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) ;
        
		        http.oauth2ResourceServer(oauth2 -> oauth2
		                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
		        	);
        
        return http.build();
    }
	 @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }

  @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtConverter;
    }
}