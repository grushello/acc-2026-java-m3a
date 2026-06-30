package org.example.shelter;

import org.example.model.Animal;

import java.util.List;

public class ShelterInfo {
    public static <T extends Animal> T oldestAnimal(Shelter<T> shelter)
    {
        List<T> animals = shelter.getAllAnimals();
        if (animals.isEmpty()) {
            return null;
        }

        T oldestAnimal = animals.get(0);
        for(T animal : animals)
        {
            if(animal.getAge() > oldestAnimal.getAge())
                oldestAnimal = animal;
        }
        return oldestAnimal;
    }
    public static <T extends Animal> float averageAge(Shelter<T> shelter)
    {
        List<T> animals = shelter.getAllAnimals();
        if (animals.isEmpty()) {
            throw new RuntimeException("Non-empty list expected");
        }

        float totalAge = 0;
        for(T animal : animals) {
            totalAge += animal.getAge();
        }
        return totalAge/animals.size();
    }
}
