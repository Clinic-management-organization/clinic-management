package com.clinic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import java.time.LocalDate;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
//@SpringBootApplication
public class ClinicManagmentApplication {	

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(ClinicManagmentApplication.class, args);
		
		// Create and save sample Medecin objects
		Medecin medecin1 = new Medecin() ;
				medecin1.setNom("John");
				medecin1.setPrenom("Doe");
				medecin1.setDateNaissance(new Date());
                medecin1.setSexe(GenderType.H);
                medecin1.setAdresse("123 Main St");
                medecin1.setTel("123456789");
                medecin1.setEmail("john.doe@example.com");
                medecin1.setRole(RoleType.MEDECIN);
                medecin1.setSpecialite(SpecialiteType.DERMATOLOGIE);
                medecin1.setHoraires(null);  // Set the list of horaires
                medecin1.setRDVs(null);   // Set the list of RDVs
        

        Medecin medecin2 = new Medecin();
				medecin2.setNom("Angela");
				medecin2.setPrenom("Smith");
				medecin2.setDateNaissance(new Date());
		        medecin2.setSexe(GenderType.F);
		        medecin2.setAdresse("123 Main St");
		        medecin2.setTel("123456789");
		        medecin2.setEmail("john.doe@example.com");
		        medecin2.setRole(RoleType.MEDECIN);
		        medecin2.setSpecialite(SpecialiteType.GYNECOLOGIE);
		        medecin2.setHoraires(null);  // Set the list of horaires
		        medecin2.setRDVs(null);   // Set the list of RDVs
		        
        // Create and save sample Patient objects
		Patient patient1 = new Patient() ;
				patient1.setNom("Ameni");
				patient1.setPrenom("Selmi");
				patient1.setDateNaissance(new Date());
				patient1.setSexe(GenderType.F);
				patient1.setAdresse("123oued ellil St");
				patient1.setTel("123456789");
				patient1.setEmail("john.doe@example.com");
				patient1.setRole(RoleType.PATIENT);
				patient1.setSituationFamilliale("single");
				patient1.setRDVs(null);   // Set the list of RDVs
        

        Patient patient2 = new Patient();
        		patient2.setNom("Angela");
        		patient2.setPrenom("Smith");
        		patient2.setDateNaissance(new Date());
        		patient2.setSexe(GenderType.F);
        		patient2.setAdresse("123 Main St");
        		patient2.setTel("123456789");
        		patient2.setEmail("john.doe@example.com");
        		patient2.setRole(RoleType.MEDECIN);
				patient2.setSituationFamilliale("married");
        		patient2.setRDVs(null);   // Set the list of RDVs
        		
        		
        		

        RendezVous rdv = new RendezVous();
        		rdv.setDateRendezVous(new Date());
        		rdv.setEtatRendezVous(EtatRDV.RAPPORTEE);
        		rdv.setMedecin(medecin1);
        		rdv.setPatient(patient1);
       RendezVous rdv2 = new RendezVous();
         		rdv2.setDateRendezVous(new Date());
         		rdv2.setEtatRendezVous(EtatRDV.RAPPORTEE);
         		rdv2.setMedecin(medecin1);
         		rdv2.setPatient(patient2);
       RendezVous rdv3 = new RendezVous();
		       rdv3.setDateRendezVous(new Date());
		       rdv3.setEtatRendezVous(EtatRDV.RAPPORTEE);
		       rdv3.setMedecin(medecin2);
		       rdv3.setPatient(patient2);
		       
		       
		       RendezVous rdv4 = new RendezVous();
		       rdv4.setDateRendezVous(new Date());
		       rdv4.setEtatRendezVous(EtatRDV.RAPPORTEE);
		       rdv4.setMedecin(medecin1);
		       rdv4.setPatient(patient1);

		       RendezVous rdv5 = new RendezVous();
		       rdv5.setDateRendezVous(new Date());
		       rdv5.setEtatRendezVous(EtatRDV.RAPPORTEE);
		       rdv5.setMedecin(medecin2);
		       rdv5.setPatient(patient2);

		       
       DossierMedical dm1 = new DossierMedical();
		       dm1.setConsultations(null);
		       dm1.setDateCreation(new Date());
		       dm1.setDateMiseAJour(new Date());
		       dm1.setObservation("Test dossier 1");
		       
       DossierMedical dm2 = new DossierMedical();
		       dm2.setConsultations(null);
		       dm2.setDateCreation(new Date());
		       dm2.setDateMiseAJour(new Date());
		       dm2.setObservation("Test dossier 2");
		       DossierMedical dossierMedical3 = new DossierMedical();
		       dossierMedical3.setConsultations(null);
		       dossierMedical3.setDateCreation(new Date());
		       dossierMedical3.setDateMiseAJour(new Date());
		       dossierMedical3.setObservation("Test dossier 3");


		       Consultation c1 = new Consultation();
		        c1.setDossierMedical(dm1);
		        c1.setPrix(50);
		        c1.setDateCreation(parseDate("2023-06-15"));
		        c1.setSynthese("consultation terminée");

		        Consultation consultation2 = new Consultation();
		        consultation2.setDossierMedical(dm1);
		        consultation2.setPrix(60);
		        consultation2.setDateCreation(parseDate("2023-03-16")); 
		        consultation2.setSynthese("Consultation en cours");

		        Consultation consultation3 = new Consultation();
		        consultation3.setDossierMedical(dm2);
		        consultation3.setPrix(70);
		        consultation3.setDateCreation(parseDate("2023-12-17")); 
		        consultation3.setSynthese("Consultation prévue");


		       

	  Traitement t1 =  new Traitement() ;
				t1.setConsultation(c1);
				t1.setDosage("3 doses");
				t1.setMedicament("Panadol");
				t1.setStartDate(LocalDate.now());
				t1.setEndDate(LocalDate.now());
				// Création et sauvegarde d'autres objets Traitement
				Traitement traitement2 = new Traitement();
				traitement2.setConsultation(consultation2);
				traitement2.setDosage("2 doses");
				traitement2.setMedicament("Aspirine");
				traitement2.setStartDate(LocalDate.now());
				traitement2.setEndDate(LocalDate.now().plusDays(7));

				Traitement traitement3 = new Traitement();
				traitement3.setConsultation(consultation3);
				traitement3.setDosage("1 dose");
				traitement3.setMedicament("Vitamine C");
				traitement3.setStartDate(LocalDate.now());
				traitement3.setEndDate(LocalDate.now().plusDays(5));

	  			
	            Diagnostic d1 =  new Diagnostic() ;
				d1.setConsultation(c1);
				d1.setDescription("Radiologie");
				
				
				// Création et sauvegarde d'autres objets Diagnostic
				Diagnostic diagnostic2 = new Diagnostic();
				diagnostic2.setConsultation(consultation2);
				diagnostic2.setDescription("Échographie");

				Diagnostic diagnostic3 = new Diagnostic();
				diagnostic3.setConsultation(consultation3);
				diagnostic3.setDescription("Bilan sanguin");

				
	  			
		       rdv.setDossierMedical(dm2);
		       rdv2.setDossierMedical(dm1);
		       rdv3.setDossierMedical(dm1);
		    // Création et sauvegarde d'autres objets Medecin
		       Medecin medecin3 = new Medecin();
		       medecin3.setNom("Marie");
		       medecin3.setPrenom("Dupont");
		       medecin3.setDateNaissance(new Date());
		       medecin3.setSexe(GenderType.F);
		       medecin3.setAdresse("789 Rue Principale");
		       medecin3.setTel("987654321");
		       medecin3.setEmail("marie.dupont@example.com");
		       medecin3.setRole(RoleType.MEDECIN);
		       medecin3.setSpecialite(SpecialiteType.DERMATOLOGIE);

		       // Création et sauvegarde d'autres objets Patient
		       Patient patient3 = new Patient();
		       patient3.setNom("Jean");
		       patient3.setPrenom("Martin");
		       patient3.setDateNaissance(new Date());
		       patient3.setSexe(GenderType.H);
		       patient3.setAdresse("456 Rue Secondaire");
		       patient3.setTel("123456789");
		       patient3.setEmail("jean.martin@example.com");
		       patient3.setRole(RoleType.PATIENT);
		       patient3.setSituationFamilliale("divorced");

		       // Création et sauvegarde d'autres objets DossierMedical
		       DossierMedical dossierMedical4 = new DossierMedical();
		       dossierMedical4.setConsultations(null);
		       dossierMedical4.setDateCreation(new Date());
		       dossierMedical4.setDateMiseAJour(new Date());
		       dossierMedical4.setObservation("Test dossier 4");

		       // Création et sauvegarde d'autres objets Medecin
		       Medecin medecin4 = new Medecin();
		       medecin4.setNom("Philippe");
		       medecin4.setPrenom("Lefevre");
		       medecin4.setDateNaissance(new Date());
		       medecin4.setSexe(GenderType.H);
		       medecin4.setAdresse("987 Rue Tertiaire");
		       medecin4.setTel("123456789");
		       medecin4.setEmail("philippe.lefevre@example.com");
		       medecin4.setRole(RoleType.MEDECIN);
		       medecin4.setSpecialite(SpecialiteType.DERMATOLOGIE);

		       // Création et sauvegarde d'autres objets Patient
		       Patient patient4 = new Patient();
		       patient4.setNom("Sophie");
		       patient4.setPrenom("Dufresne");
		       patient4.setDateNaissance(new Date());
		       patient4.setSexe(GenderType.F);
		       patient4.setAdresse("789 Rue Quaternaire");
		       patient4.setTel("987654321");
		       patient4.setEmail("sophie.dufresne@example.com");
		       patient4.setRole(RoleType.PATIENT);
		       patient4.setSituationFamilliale("single");

		       // Création et sauvegarde d'autres objets DossierMedical
		       DossierMedical dossierMedical5 = new DossierMedical();
		       dossierMedical5.setConsultations(null);
		       dossierMedical5.setDateCreation(new Date());
		       dossierMedical5.setDateMiseAJour(new Date());
		       dossierMedical5.setObservation("Test dossier 5");

		       // Sauvegarde des objets en utilisant les DAOs
		     
       
      
        MedecinDAO medecinDAO=ctx.getBean(MedecinDAO.class);
        PatientDAO patientDAO=ctx.getBean(PatientDAO.class);
        RendezVousDAO rdvDAO=ctx.getBean(RendezVousDAO.class);
        TraitementDAO tDAO=ctx.getBean(TraitementDAO.class);
        DiagnosticDAO dDAO=ctx.getBean(DiagnosticDAO.class);
        DossierMedicalDAO dmDAO=ctx.getBean(DossierMedicalDAO.class);
        ConsultationDAO cDAO=ctx.getBean(ConsultationDAO.class);
        
        
    
      
		medecinDAO.save(medecin1);
        medecinDAO.save(medecin2);
        patientDAO.save(patient1);
        patientDAO.save(patient2);
        medecinDAO.save(medecin3);
	    medecinDAO.save(medecin4);
	    patientDAO.save(patient3);
	    patientDAO.save(patient4);
	    dmDAO.save(dossierMedical4);
	    dmDAO.save(dossierMedical5);
        dmDAO.save(dm1) ;
        dmDAO.save(dm2) ;
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
        dDAO.save(diagnostic3);}
        
        private static Date parseDate(String dateString) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace(); 
                return null;
            }
        
        
	}
       
	
	@Bean
	CommandLineRunner run(RoleDAO roleDAO, UserDAO userDAO,PatientDAO patientDAO, PasswordEncoder passwordEncode){
		return args ->{
			if(roleDAO.findByAuthority("ADMIN").isPresent()) return;
			
			Role adminRole = roleDAO.save(new Role("ADMIN"));
			Role userRole = roleDAO.save(new Role("USER"));
			
			Set<Role> adminRoles = new HashSet<>();
			adminRoles.add(adminRole);
			Set<Role> userRoles = new HashSet<>();
			userRoles.add(userRole);
			
			Patient patient1 = new Patient() ;
				patient1.setNom("Ameni");
				patient1.setPrenom("Selmi");
				patient1.setDateNaissance(new Date());
				patient1.setSexe(GenderType.F);
				patient1.setAdresse("123oued ellil St");
				patient1.setTel("123456789");
				patient1.setEmail("john.doe@example.com");
				patient1.setRole(RoleType.PATIENT);
				patient1.setSituationFamilliale("single");
				patient1.setRDVs(null);   // Set the list of RDVs
	        
			ApplicationUser admin = new ApplicationUser(1, "admin@gmail.com", passwordEncode.encode("password"), adminRoles);
			ApplicationUser user = new ApplicationUser(2, "user@gmail.com", passwordEncode.encode("password"), userRoles );
			user.setPatient(patient1);
			patientDAO.save(patient1);
			userDAO.save(admin);
			userDAO.save(user);
		};
	}

	
}
