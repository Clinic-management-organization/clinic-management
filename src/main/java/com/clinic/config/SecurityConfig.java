package com.clinic.config;


import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	 private final UserDetailsService userDetailsService;
	    private final BCryptPasswordEncoder passwordEncoder;
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
	    
	    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
	        this.userDetailsService = userDetailsService;
	        this.passwordEncoder = passwordEncoder;
	    }
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
        return http.build();
    }

}