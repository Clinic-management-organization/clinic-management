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
	
    // Stocke les propriétés des clés RSA (utilisées pour JWT)
	private final RSAKeyProperties keys;

	// Crée un encodeur de mot de passe BCrypt (utilisé pour hacher les mots de passe)
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	// Configure un gestionnaire d'authentification avec un fournisseur DAO
	@Bean
	public AuthenticationManager authManager(UserDetailsService detailsService){
	    DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
	    daoProvider.setUserDetailsService(detailsService); // Utilise UserDetailsService pour charger les infos utilisateur
	    daoProvider.setPasswordEncoder(passwordEncoder()); // Utilise BCrypt pour hacher les mots de passe
	    return new ProviderManager(daoProvider); // Crée et retourne le ProviderManager avec le DaoAuthenticationProvider
	}
	
	// Constructeur qui initialise les propriétés des clés RSA
	public SecurityConfig(RSAKeyProperties keys) {
		System.out.println("keys"+keys);
		this.keys = keys;
	}
	
	// Chemins d'accès autorisés pour tout le monde
	private static final String[] ALLOWED_PATHS_ANY = {
	        "/h2-console/**",
	        "/swagger-ui/**",
	        "/v3/api-docs/**",	   
	        "/auth/**",
	};
	
	// Chemins d'accès autorisés pour les utilisateurs et les admins
	private static final String[] ALLOWED_PATHS_USER = {
	            "/api/rendezvous/**",
	            "/api/dossiersMedicaux/**",
	            "/api/medecins/**",
	};
	
	// Chemins d'accès autorisés seulement pour les admins
	private static final String[] ALLOWED_PATHS_ADMIN = {
	        "/api/medecins/**",
	        "/api/diagnostics/**",
	        "/api/patients/**",
	        "/api/dossiersMedicaux/**",
	        "/api/traitements/**",
	        "/api/consultations/**",
	        "/api/rendezvous/**",
	};

	// Configure la chaîne de filtres de sécurité pour les requêtes HTTP
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf
	    		.ignoringRequestMatchers("/h2-console/**") // Désactive la protection CSRF pour /h2-console
	    		.disable())
	        .authorizeHttpRequests(authorize ->{
        		authorize.requestMatchers(ALLOWED_PATHS_USER)
        		.hasAnyRole("USER", "ADMIN"); // Autorise les utilisateurs et admins pour certains chemins
        		authorize.requestMatchers(ALLOWED_PATHS_ADMIN)
        		.hasRole("ADMIN"); // Autorise seulement les admins pour certains chemins
//        		.permitAll();
        		authorize.requestMatchers(ALLOWED_PATHS_ANY)
        		.permitAll(); // Autorise tout le monde pour certains chemins
        		authorize.anyRequest().authenticated(); // Toutes les autres requêtes nécessitent une authentification
	        })
	        
	        .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable)) // Désactive les options de frame (pour H2 console)
	        .sessionManagement((session) -> session
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));// Configure les sessions pour être sans état (STATELESS)
	        
	        http.oauth2ResourceServer(oauth2 -> oauth2
	                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())) // Configure l'utilisation de JWT
	        	);
	    
	    return http.build(); // Construit et retourne la chaîne de filtres de sécurité
	}
	
	// Crée un décodeur JWT avec la clé publique RSA
	@Bean
	public JwtDecoder jwtDecoder(){
	    return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
	}

	// Crée un encodeur JWT avec la clé RSA
	@Bean
	public JwtEncoder jwtEncoder(){
	    JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
	    JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
	    return new NimbusJwtEncoder(jwks);
	}

	// Configure la manière dont les autorités (rôles) sont extraites des tokens JWT
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter(){
	    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
	    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles"); // Spécifie le nom de la revendication contenant les rôles
	    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_"); // Préfixe des autorités
	    JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
	    jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
	    return jwtConverter;
	}
}
