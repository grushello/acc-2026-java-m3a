package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@ToString

public final class AdoptionHistory {
    private final AnimalId animalId;
    private final LocalDate adoptionDate;
    private final String adopterName;
}
