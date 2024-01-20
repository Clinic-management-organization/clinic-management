package com.clinic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.clinic.dao.ConsultationDAO;
import com.clinic.dao.DiagnosticDAO;
import com.clinic.dao.DossierMedicalDAO;
import com.clinic.dao.MedecinDAO;
import com.clinic.dao.PatientDAO;
import com.clinic.dao.RendezVousDAO;
import com.clinic.dao.RoleDAO;
import com.clinic.dao.TraitementDAO;
import com.clinic.dao.UserDAO;

import com.clinic.entity.ApplicationUser;
import com.clinic.entity.Consultation;
import com.clinic.entity.Diagnostic;
import com.clinic.entity.DossierMedical;
import com.clinic.entity.Medecin;
import com.clinic.entity.Patient;
import com.clinic.entity.RendezVous;
import com.clinic.entity.Role;
import com.clinic.entity.Traitement;
import com.clinic.entity.Enum.EtatRDV;
import com.clinic.entity.Enum.GenderType;
import com.clinic.entity.Enum.RoleType;
import com.clinic.entity.Enum.SpecialiteType;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class ClinicManagmentApplication {	

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ClinicManagmentApplication.class, args);

        // Create and save sample Medecin objects
        Medecin medecin1 = createMedecin("John", "Doe", GenderType.H, "123 Main St", "123456789", "john.doe@example.com", RoleType.MEDECIN, SpecialiteType.DERMATOLOGIE);
        Medecin medecin2 = createMedecin("Angela", "Smith", GenderType.F, "123 Main St", "123456789", "angela.smith@example.com", RoleType.MEDECIN, SpecialiteType.GYNECOLOGIE);

        // Create and save sample Patient objects
        Patient patient1 = createPatient("Ameni", "Selmi", GenderType.F, "123oued ellil St", "123456789", "amini.selmi@example.com", RoleType.PATIENT, "single");
        Patient patient2 = createPatient("Angela", "Smith", GenderType.F, "123 Main St", "123456789", "angela.smith@example.com", RoleType.PATIENT, "married");

        // Create and save sample DossierMedical objects
        DossierMedical dm1 = createDossierMedical("Test dossier 1");
        DossierMedical dm2 = createDossierMedical("Test dossier 2");

        // Define the specific dates for Consultation objects
        Date specificDate1 = parseDate("2024-06-15");
        Date specificDate2 = parseDate("2024-03-16");
        Date specificDate3 = parseDate("2023-12-17");
        Date specificDate4 = parseDate("2022-12-17");
        Date specificDate5 = parseDate("2022-12-19");

        // Create and save sample Consultation objects with specific dates
        Consultation c1 = createConsultation(dm1, specificDate1, "consultation terminée", 50);
        Consultation consultation2 = createConsultation(dm1, specificDate2, "Consultation en cours", 60);
        Consultation consultation3 = createConsultation(dm2, specificDate3, "Consultation prévue", 70);

        // Create and save sample Traitement objects
        Traitement t1 = createTraitement(c1, "3 doses", "Panadol", LocalDate.now(), LocalDate.now());
        Traitement traitement2 = createTraitement(consultation2, "2 doses", "Aspirine", LocalDate.now(), LocalDate.now().plusDays(7));
        Traitement traitement3 = createTraitement(consultation3, "1 dose", "Vitamine C", LocalDate.now(), LocalDate.now().plusDays(5));

        // Create and save sample Diagnostic objects
        Diagnostic d1 = createDiagnostic(c1, "Radiologie");
        Diagnostic diagnostic2 = createDiagnostic(consultation2, "Échographie");
        Diagnostic diagnostic3 = createDiagnostic(consultation3, "Bilan sanguin");

        // Create and save sample RendezVous objects
        RendezVous rdv = createRendezVous(specificDate1, EtatRDV.CONFIRMEE, medecin1, patient1, dm2);
        RendezVous rdv2 = createRendezVous(specificDate2, EtatRDV.ANNULEE, medecin1, patient2, dm1);
        RendezVous rdv3 = createRendezVous(specificDate3, EtatRDV.ANNULEE, medecin2, patient2, dm1);
        RendezVous rdv4 = createRendezVous(specificDate4, EtatRDV.CONFIRMEE, medecin1, patient1, dm2);
        RendezVous rdv5 = createRendezVous(specificDate5, EtatRDV.ANNULEE, medecin2, patient2, dm2);

        // Save objects to the database using DAOs
        MedecinDAO medecinDAO = ctx.getBean(MedecinDAO.class);
        PatientDAO patientDAO = ctx.getBean(PatientDAO.class);
        RendezVousDAO rdvDAO = ctx.getBean(RendezVousDAO.class);
        TraitementDAO tDAO = ctx.getBean(TraitementDAO.class);
        DiagnosticDAO dDAO = ctx.getBean(DiagnosticDAO.class);
        DossierMedicalDAO dmDAO = ctx.getBean(DossierMedicalDAO.class);
        ConsultationDAO cDAO = ctx.getBean(ConsultationDAO.class);

        medecinDAO.save(medecin1);
        medecinDAO.save(medecin2);
        patientDAO.save(patient1);
        patientDAO.save(patient2);
        dmDAO.save(dm1);
        dmDAO.save(dm2);
        rdvDAO.save(rdv);
        rdvDAO.save(rdv2);
        rdvDAO.save(rdv3);
        cDAO.save(c1);
        tDAO.save(t1);
        dDAO.save(d1);
        rdvDAO.save(rdv4);
        rdvDAO.save(rdv5);
        cDAO.save(consultation2);
        cDAO.save(consultation3);
        tDAO.save(traitement2);
        tDAO.save(traitement3);
        dDAO.save(diagnostic2);
        dDAO.save(diagnostic3);
    }

    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Medecin createMedecin(String nom, String prenom, GenderType sexe, String adresse, String tel, String email, RoleType role, SpecialiteType specialite) {
        Medecin medecin = new Medecin();
        medecin.setNom(nom);
        medecin.setPrenom(prenom);
        medecin.setDateNaissance(new Date());
        medecin.setSexe(sexe);
        medecin.setAdresse(adresse);
        medecin.setTel(tel);
        medecin.setEmail(email);
        medecin.setRole(role);
        medecin.setSpecialite(specialite);
        medecin.setHoraires(null);  // Set the list of horaires
        medecin.setRDVs(null);   // Set the list of RDVs
        return medecin;
    }

    private static Patient createPatient(String nom, String prenom, GenderType sexe, String adresse, String tel, String email, RoleType role, String situationFamilliale) {
        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setDateNaissance(new Date());
        patient.setSexe(sexe);
        patient.setAdresse(adresse);
        patient.setTel(tel);
        patient.setEmail(email);
        patient.setRole(role);
        patient.setSituationFamilliale(situationFamilliale);
        patient.setRDVs(null);   // Set the list of RDVs
        return patient;
    }

    private static DossierMedical createDossierMedical(String observation) {
        DossierMedical dm = new DossierMedical();
        dm.setConsultations(null);
        dm.setDateCreation(new Date());
        dm.setDateMiseAJour(new Date());
        dm.setObservation(observation);
        return dm;
    }

    private static Consultation createConsultation(DossierMedical dossierMedical, Date specificDate, String synthese, int prix) {
        Consultation consultation = new Consultation();
        consultation.setDossierMedical(dossierMedical);
        consultation.setPrix(prix);
        
        // Set the specific date for dateCreation
        consultation.setDateCreation(specificDate);

        consultation.setSynthese(synthese);
        return consultation;
    }


    private static Traitement createTraitement(Consultation consultation, String dosage, String medicament, LocalDate startDate, LocalDate endDate) {
        Traitement traitement = new Traitement();
        traitement.setConsultation(consultation);
        traitement.setDosage(dosage);
        traitement.setMedicament(medicament);
        
        // Use the specific dates from the Consultation object
        traitement.setStartDate(consultation.getDateCreation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        traitement.setEndDate(traitement.getStartDate().plusDays(7)); // Adjust this based on your requirement

        return traitement;
    }

    private static Diagnostic createDiagnostic(Consultation consultation, String description) {
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setConsultation(consultation);
        diagnostic.setDescription(description);
        return diagnostic;
    }

    private static RendezVous createRendezVous(Date specificDate, EtatRDV etatRendezVous, Medecin medecin, Patient patient, DossierMedical dossierMedical) {
        RendezVous rdv = new RendezVous();
        rdv.setDateRendezVous(specificDate);
        rdv.setEtatRendezVous(etatRendezVous);
        rdv.setMedecin(medecin);
        rdv.setPatient(patient);
        rdv.setDossierMedical(dossierMedical);
        return rdv;
    }

    @Bean
    CommandLineRunner run(RoleDAO roleDAO, UserDAO userDAO, DossierMedicalDAO dmDAO, RendezVousDAO rdvDAO, MedecinDAO medecinDAO, PatientDAO patientDAO, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleDAO.findByAuthority("ADMIN").isPresent()) return;

            Role adminRole = roleDAO.save(new Role("ADMIN"));
            Role userRole = roleDAO.save(new Role("USER"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            Patient patient1 = createPatient("Ameni", "Selmi", GenderType.F, "123oued ellil St", "123456789", "amini.selmi@example.com", RoleType.PATIENT, "single");
            Medecin medecin1 = createMedecin("wrimi", "siwar", GenderType.H, "123 Main St", "123456789", "john.doe@example.com", RoleType.MEDECIN, SpecialiteType.DERMATOLOGIE);
            DossierMedical dossierMedical3 = createDossierMedical("Test dossier 3");

            RendezVous rdv4 = createRendezVous(new Date(), EtatRDV.CONFIRMEE, medecin1, patient1, dossierMedical3);
            RendezVous rdv5 = createRendezVous(new Date(), EtatRDV.CONFIRMEE, medecin1, patient1, dossierMedical3);

            ApplicationUser admin = new ApplicationUser(1, "admin@gmail.com", passwordEncoder.encode("password"), adminRoles);
            ApplicationUser user = new ApplicationUser(2, "user@gmail.com", passwordEncoder.encode("password"), userRoles);
            user.setPatient(patient1);
            patientDAO.save(patient1);
            userDAO.save(admin);
            userDAO.save(user);
            medecinDAO.save(medecin1);
            dmDAO.save(dossierMedical3);
            rdvDAO.save(rdv4);
            rdvDAO.save(rdv5);
        };
    }
}
