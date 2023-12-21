package com.clinic.entity.Embedded;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horraire {
    private LocalTime startTime; // Heure de début
    private LocalTime endTime; // Heure de fin
    private DayOfWeek dayOfWeek; // Jour de la semaine
}
