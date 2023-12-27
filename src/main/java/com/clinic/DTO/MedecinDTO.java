package com.clinic.DTO;

import java.util.List;

import com.clinic.entity.Enum.SpecialiteType;

import lombok.Data;

@Data
public class MedecinDTO extends UtilisateurDTO {
    private SpecialiteType specialite;
    private List<HoraireDTO> horaires;
}
