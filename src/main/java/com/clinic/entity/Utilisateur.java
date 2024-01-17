package com.clinic.entity;

import java.io.Serializable;
import java.util.Date;

import com.clinic.entity.Enum.GenderType;
import com.clinic.entity.Enum.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide.")
    @Size(min = 2, max = 50, message = "Le nom doit avoir entre 2 et 50 caractères.")
    @Column(nullable = false)
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide.")
    @Size(min = 2, max = 50, message = "Le prénom doit avoir entre 2 et 50 caractères.")
    @Column(nullable = false)
    private String prenom;

    @Past(message = "La date de naissance doit être dans le passé.")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Enumerated(EnumType.STRING)
    private GenderType sexe;

    @Size(max = 255, message = "L'adresse doit avoir moins de 255 caractères.")
    private String adresse;

    @Size(max = 15, message = "Le numéro de téléphone doit avoir moins de 15 caractères.")
    private String tel;

    @Email(message = "L'adresse e-mail doit être valide.")
    private String email;

    private RoleType role;
}
