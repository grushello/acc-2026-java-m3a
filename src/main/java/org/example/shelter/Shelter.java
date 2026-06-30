package org.example.shelter;

import org.example.model.AdoptionStatus;
import org.example.model.Animal;
import org.example.model.AnimalId;

import java.util.ArrayList;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();

    public void addAnimal(T animal){
        animals.add(animal);
    }

    public List<T> getAllAnimals(){
        return new ArrayList<>(animals);
    }

    public List<T> findBySpecies(String species){
        ArrayList<T> filteredAnimals = new ArrayList<>();
        for(T animal : animals)
        {
            if(animal.getSpecies().equalsIgnoreCase(species))
                filteredAnimals.add(animal);
        }
        return filteredAnimals;
    }

    public List<T> findAvailableAnimals(){
        ArrayList<T> filteredAnimals = new ArrayList<>();
        for(T animal : animals)
        {
            if(animal.getAdoptionStatus().equals(AdoptionStatus.AVAILABLE))
                filteredAnimals.add(animal);
        }
        return filteredAnimals;
    }

    public List<T> sortByAge(){
        ArrayList<T> sorted = new ArrayList<T>(animals);
        sorted.sort((a1, a2) -> Integer.compare(a1.getAge(), a2.getAge()));
        return sorted;
    }
    public void markAsAdopted(String id){
        for(T animal : animals)
        {
            if(animal.getId().toString().equals(id))
                animal.markAsAdopted();
        }
    }
}
