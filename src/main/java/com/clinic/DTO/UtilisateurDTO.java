package com.clinic.DTO;

import lombok.Data;
import java.util.Date;

import com.clinic.entity.Enum.GenderType;
import com.clinic.entity.Enum.RoleType;

@Data

public class UtilisateurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private GenderType sexe;
    private String adresse;
    private String tel;
    private String email;
    private String login;
    private String motDePasse;
    private RoleType role;
}
