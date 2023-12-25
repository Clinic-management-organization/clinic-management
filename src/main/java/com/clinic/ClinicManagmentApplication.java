package com.clinic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.clinic.dao.MedecinDAO;
import com.clinic.dao.PatientDAO;
import com.clinic.entity.Medecin;
import com.clinic.entity.Patient;
import com.clinic.entity.Enum.GenderType;
import com.clinic.entity.Enum.RoleType;
import com.clinic.entity.Enum.SpecialiteType;

@SpringBootApplication
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

        MedecinDAO medecinDAO=ctx.getBean(MedecinDAO.class);
        PatientDAO patientDAO=ctx.getBean(PatientDAO.class);
		medecinDAO.save(medecin1);
        medecinDAO.save(medecin2);
        patientDAO.save(patient1);
        patientDAO.save(patient2);
	}	
	
}
