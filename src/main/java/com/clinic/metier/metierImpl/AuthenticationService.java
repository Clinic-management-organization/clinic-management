package com.clinic.metier.metierImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinic.dao.RoleDAO;
import com.clinic.dao.UserDAO;
import com.clinic.dto.LoginResponseDTO;
import com.clinic.entity.ApplicationUser;
import com.clinic.entity.Role;

@Service
@Transactional
public class AuthenticationService {

    // Injection des dépendances
    @Autowired
    private UserDAO userDAO; // DAO pour les opérations liées aux utilisateurs
    @Autowired
    private RoleDAO roleDAO; // DAO pour les opérations liées aux rôles
    @Autowired
    private PasswordEncoder passwordEncoder; // Encodeur de mot de passe pour hacher les mots de passe
    @Autowired
    private AuthenticationManager authenticationManager; // Gestionnaire d'authentification pour traiter les tentatives de connexion
    @Autowired
    private TokenService tokenService; // Service pour générer des JWT

    // Méthode pour enregistrer un nouvel utilisateur
    public ApplicationUser registerUser(String username, String password){

        String encodedPassword = passwordEncoder.encode(password); // Hachage du mot de passe
        Role userRole = roleDAO.findByAuthority("USER").get(); // Récupération du rôle utilisateur

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole); // Ajout du rôle à l'utilisateur

        // Enregistrement et retour de l'utilisateur dans la base de données
        return userDAO.save(new ApplicationUser(0, username, encodedPassword, authorities));
    }

    // Méthode pour connecter un utilisateur
    public LoginResponseDTO loginUser(String username, String password){

        try{
            // Authentification de l'utilisateur
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            // Génération d'un JWT pour l'utilisateur authentifié
            String token = tokenService.generateJwt(auth);

            // Retour d'une réponse de connexion contenant l'utilisateur et le token JWT
            return new LoginResponseDTO(userDAO.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            // Retour d'une réponse vide en cas d'échec de l'authentification
            return new LoginResponseDTO(null, "");
        }
    }

}
