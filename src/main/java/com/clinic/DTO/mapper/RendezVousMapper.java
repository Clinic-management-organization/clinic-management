package com.clinic.DTO.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.clinic.DTO.RendezVousDTO;
import com.clinic.entity.RendezVous;
import com.clinic.entity.Enum.EtatRDV;

public class RendezVousMapper {

    public static RendezVousDTO toDTO(RendezVous rendezVous) {
        RendezVousDTO dto = new RendezVousDTO();
        dto.setId(rendezVous.getId());
        dto.setEtatRendezVous(rendezVous.getEtatRendezVous());
        dto.setDateRendezVous(rendezVous.getDateRendezVous());
        dto.setRemarques(rendezVous.getRemarques());
        dto.setMotif(rendezVous.getMotif());
        dto.setPatient(PatientMapper.toDTO(rendezVous.getPatient()));
        dto.setMedecin(MedecinMapper.toDTO(rendezVous.getMedecin()));
        // Other mappings as needed
        return dto;
    }

    public static RendezVous toEntity(RendezVousDTO dto) {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setId(dto.getId());
        rendezVous.setEtatRendezVous(dto.getEtatRendezVous());
        rendezVous.setDateRendezVous(dto.getDateRendezVous());
        rendezVous.setRemarques(dto.getRemarques());
        rendezVous.setMotif(dto.getMotif());
        rendezVous.setPatient(PatientMapper.toEntity(dto.getPatient()));
        rendezVous.setMedecin(MedecinMapper.toEntity(dto.getMedecin()));
        // Other mappings as needed
        return rendezVous;
    }

    // Additional methods for mapping lists if needed
    public static List<RendezVousDTO> toDTOList(List<RendezVous> rendezVousList) {
        return rendezVousList.stream().map(RendezVousMapper::toDTO).collect(Collectors.toList());
    }

    public static List<RendezVous> toEntityList(List<RendezVousDTO> rendezVousDTOList) {
        return rendezVousDTOList.stream().map(RendezVousMapper::toEntity).collect(Collectors.toList());
    }
}
