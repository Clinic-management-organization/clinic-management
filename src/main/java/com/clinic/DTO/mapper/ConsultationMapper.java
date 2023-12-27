package com.clinic.DTO.mapper;

import com.clinic.DTO.ConsultationDTO;
import com.clinic.entity.Consultation;

public class ConsultationMapper {
    public static ConsultationDTO toDTO(Consultation consultation) {
        return EntityMapper.mapToDTO(consultation, ConsultationDTO.class);
    }

    public static Consultation toEntity(ConsultationDTO consultationDTO) {
        return EntityMapper.mapToEntity(consultationDTO, Consultation.class);
    }
}

