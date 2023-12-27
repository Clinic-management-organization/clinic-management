package com.clinic.DTO;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Data;

@Data
public class HoraireDTO {
    private Long id;
    private String startTime;
    private String endTime;
    private DayOfWeek dayOfWeek;
}
