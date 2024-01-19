package com.clinic.metier.metierImpl;
import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    
    // Injecte automatiquement une instance de JwtEncoder par Spring.
    @Autowired
    private JwtEncoder jwtEncoder;

    // Injecte automatiquement une instance de JwtDecoder par Spring.
    @Autowired
    private JwtDecoder jwtDecoder;

    // Méthode pour générer un JWT (JSON Web Token).
    public String generateJwt(Authentication auth){

        // Obtient l'instant actuel pour définir le temps d'émission du JWT.
        Instant now = Instant.now();

        // Convertit les autorités (rôles) de l'utilisateur authentifié en une chaîne de caractères séparée par des espaces.
        String scope = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        // Construit l'ensemble des revendications (claims) à inclure dans le JWT.
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self") // Définit l'émetteur du token.
            .issuedAt(now) // Définit le moment de création du token.
            .subject(auth.getName()) // Définit le sujet du token (généralement le nom d'utilisateur).
            .claim("roles", scope) // Ajoute une revendication personnalisée pour les rôles de l'utilisateur.
            .build();

        // Encode les revendications en un JWT et retourne la valeur du token.
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
