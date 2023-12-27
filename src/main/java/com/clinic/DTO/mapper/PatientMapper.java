package com.clinic.DTO.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.clinic.DTO.PatientDTO;
import com.clinic.entity.Patient;
import com.clinic.entity.Enum.GenderType;

public class PatientMapper {

    public static PatientDTO toDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setNom(patient.getNom());
        dto.setPrenom(patient.getPrenom());
        dto.setDateNaissance(patient.getDateNaissance());
        dto.setSexe(patient.getSexe());
        dto.setAdresse(patient.getAdresse());
        dto.setTel(patient.getTel());
        dto.setEmail(patient.getEmail());
        dto.setSituationFamilliale(patient.getSituationFamilliale());
        dto.setAssuranceMedicale(patient.getAssuranceMedicale());
        return dto;
    }

    public static Patient toEntity(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setNom(dto.getNom());
        patient.setPrenom(dto.getPrenom());
        patient.setDateNaissance(dto.getDateNaissance());
        patient.setSexe(dto.getSexe());
        patient.setAdresse(dto.getAdresse());
        patient.setTel(dto.getTel());
        patient.setEmail(dto.getEmail());
        patient.setSituationFamilliale(dto.getSituationFamilliale());
        patient.setAssuranceMedicale(dto.getAssuranceMedicale());
        // Other mappings as needed
        return patient;
    }

    // Additional methods for mapping lists if needed
    public static List<PatientDTO> toDTOList(List<Patient> patientList) {
        return patientList.stream().map(PatientMapper::toDTO).collect(Collectors.toList());
    }

    public static List<Patient> toEntityList(List<PatientDTO> patientDTOList) {
        return patientDTOList.stream().map(PatientMapper::toEntity).collect(Collectors.toList());
    }
}
