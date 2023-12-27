package com.clinic.DTO.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.clinic.DTO.MedecinDTO;
import com.clinic.entity.Medecin;
import com.clinic.entity.Enum.SpecialiteType;

public class MedecinMapper {

    public static MedecinDTO toDTO(Medecin medecin) {
        MedecinDTO dto = new MedecinDTO();
        dto.setId(medecin.getId());
        dto.setNom(medecin.getNom());
        dto.setPrenom(medecin.getPrenom());
        dto.setDateNaissance(medecin.getDateNaissance());
        dto.setSexe(medecin.getSexe());
        dto.setAdresse(medecin.getAdresse());
        dto.setTel(medecin.getTel());
        dto.setEmail(medecin.getEmail());
        dto.setSpecialite(medecin.getSpecialite());
        dto.setHoraires(HoraireMapper.toDTOList(medecin.getHoraires()));
        return dto;
    }

    public static Medecin toEntity(MedecinDTO dto) {
        Medecin medecin = new Medecin();
        medecin.setId(dto.getId());
        medecin.setNom(dto.getNom());
        medecin.setPrenom(dto.getPrenom());
        medecin.setDateNaissance(dto.getDateNaissance());
        medecin.setSexe(dto.getSexe());
        medecin.setAdresse(dto.getAdresse());
        medecin.setTel(dto.getTel());
        medecin.setEmail(dto.getEmail());
        medecin.setSpecialite(dto.getSpecialite());
        medecin.setHoraires(HoraireMapper.toEntityList(dto.getHoraires()));
        // Other mappings as needed
        return medecin;
    }

    // Additional methods for mapping lists if needed
    public static List<MedecinDTO> toDTOList(List<Medecin> medecinList) {
        return medecinList.stream().map(MedecinMapper::toDTO).collect(Collectors.toList());
    }

    public static List<Medecin> toEntityList(List<MedecinDTO> medecinDTOList) {
        return medecinDTOList.stream().map(MedecinMapper::toEntity).collect(Collectors.toList());
    }
}
