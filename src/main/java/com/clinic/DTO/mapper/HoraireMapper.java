package com.clinic.DTO.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.clinic.DTO.HoraireDTO;
import com.clinic.entity.Horaire;

public class HoraireMapper {

    public static HoraireDTO toDTO(Horaire horaire) {
        HoraireDTO dto = new HoraireDTO();
        dto.setId(horaire.getId());
        dto.setStartTime((horaire.getStartTime()).toString());
        dto.setEndTime((horaire.getEndTime()).toString());
        dto.setDayOfWeek(horaire.getDayOfWeek());
        // Other mappings as needed
        return dto;
    }

    public static Horaire toEntity(HoraireDTO dto) {
        Horaire horaire = new Horaire();
        horaire.setId(dto.getId());
        //horaire.setStartTime((dto.getStartTime()));
        //horaire.setEndTime(dto.getEndTime());
        horaire.setDayOfWeek(dto.getDayOfWeek());
        // Other mappings as needed
        return horaire;
    }

    // Additional methods for mapping lists if needed
    public static List<HoraireDTO> toDTOList(List<Horaire> horaireList) {
        return horaireList.stream().map(HoraireMapper::toDTO).collect(Collectors.toList());
    }

    public static List<Horaire> toEntityList(List<HoraireDTO> horaireDTOList) {
        return horaireDTOList.stream().map(HoraireMapper::toEntity).collect(Collectors.toList());
    }
}
