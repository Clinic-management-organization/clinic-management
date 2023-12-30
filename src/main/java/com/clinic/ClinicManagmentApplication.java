package com.clinic;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.clinic.dao.ConsultationDAO;
import com.clinic.dao.DiagnosticDAO;
import com.clinic.dao.DossierMedicalDAO;
import com.clinic.dao.MedecinDAO;
import com.clinic.dao.PatientDAO;
import com.clinic.dao.RendezVousDAO;
import com.clinic.dao.TraitementDAO;
import com.clinic.entity.Consultation;
import com.clinic.entity.Diagnostic;
import com.clinic.entity.DossierMedical;
import com.clinic.entity.Medecin;
import com.clinic.entity.Patient;
import com.clinic.entity.RendezVous;
import com.clinic.entity.Traitement;
import com.clinic.entity.Enum.EtatRDV;
import com.clinic.entity.Enum.GenderType;
import com.clinic.entity.Enum.RoleType;
import com.clinic.entity.Enum.SpecialiteType;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
                medecin1.setLogin("john.doe");
                medecin1.setMotDePasse("password");
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
		        medecin2.setLogin("john.doe");
		        medecin2.setMotDePasse("password");
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
				patient1.setLogin("john.doe");
				patient1.setMotDePasse("password");
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
        		patient2.setLogin("john.doe");
        		patient2.setMotDePasse("password");
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
	  Consultation c1 = new Consultation() ;
	  			c1.setDossierMedical(dm1);
	  			c1.setPrix(50);
	  			c1.setSynthese("consultation terminé");
	  Traitement t1 =  new Traitement() ;
				t1.setConsultation(c1);
				t1.setDosage("3 doses");
				t1.setMedicament("Panadol");
				t1.setStartDate(LocalDate.now());
				t1.setEndDate(LocalDate.now());
	  			
	  Diagnostic d1 =  new Diagnostic() ;
				d1.setConsultation(c1);
				d1.setDescription("Radiologie");
				
	  			
		       rdv.setDossierMedical(dm2);
		       rdv2.setDossierMedical(dm1);
		       rdv3.setDossierMedical(dm1);
		       
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
        dmDAO.save(dm1) ;
        dmDAO.save(dm2) ;
        rdvDAO.save(rdv); 
        rdvDAO.save(rdv2); 
        rdvDAO.save(rdv3);
        cDAO.save(c1);
        tDAO.save(t1);
        dDAO.save(d1);
	}	
	
}
