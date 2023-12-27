package com.clinic.DTO.mapper;

import java.util.stream.Collectors;

import com.clinic.DTO.DossierMedicalDTO;
import com.clinic.entity.DossierMedical;

public class DossierMedicalMapper {

    public static DossierMedicalDTO toDTO(DossierMedical dossierMedical) {
        DossierMedicalDTO dto = new DossierMedicalDTO();
        dto.setId(dossierMedical.getId());
        dto.setDateCreation(dossierMedical.getDateCreation());
        dto.setDateMiseAJour(dossierMedical.getDateMiseAJour());
        dto.setObservation(dossierMedical.getObservation());

        // Map consultations
        if (dossierMedical.getConsultations() != null) {
            dto.setConsultations(
                    dossierMedical.getConsultations().stream()
                            .map(ConsultationMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        // Map rdvs
        if (dossierMedical.getRdvs() != null) {
            dto.setRdvs(
                    dossierMedical.getRdvs().stream()
                            .map(RendezVousMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static DossierMedical toEntity(DossierMedicalDTO dossierMedicalDTO) {
        return EntityMapper.mapToEntity(dossierMedicalDTO, DossierMedical.class);
    }

}
