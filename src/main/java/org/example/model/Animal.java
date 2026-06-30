package org.example.model;

import lombok.Getter;
import lombok.ToString;

@Getter

public sealed abstract class Animal permits Dog,Cat,Bird,Bear {
    private final AnimalId id;
    private final String name;
    private final int age;
    private AdoptionStatus adoptionStatus;

    protected Animal(AnimalId id, String name, int age) {
        if(name == null || name.isEmpty())
            throw new RuntimeException("Animal name cannot be empty");
        if(age < 0)
            throw new RuntimeException("Age can not be negative");
        this.id = id;
        this.name = name;
        this.age = age;
        this.adoptionStatus = AdoptionStatus.AVAILABLE;
    }

    public void markAsAdopted(){
        this.adoptionStatus = AdoptionStatus.ADOPTED;
    }

    public abstract String getSpecies();

    @Override
    public String toString(){
        return id + " | " + name + " | " + age + " years old | " + getSpecies() + " | " + adoptionStatus;
    }
}
