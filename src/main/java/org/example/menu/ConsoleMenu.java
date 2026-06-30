package org.example.menu;

import org.example.model.*;
import org.example.shelter.Shelter;

import java.util.List;
import java.util.Scanner;

import lombok.RequiredArgsConstructor;
import org.example.shelter.ShelterInfo;

@RequiredArgsConstructor
public class ConsoleMenu {
    private final Shelter<Animal> shelter;
    private final Scanner scanner =  new Scanner(System.in);

    public void start(){
        boolean shouldExit = false;
        int input = -1;
        while(!shouldExit) {
            printMenu();
            input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    listAllAnimals();
                    break;
                case 3:
                    findBySpecies();
                    break;
                case 4:
                    listAvailableAnimals();
                    break;
                case 5:
                    markAsAdopted();
                    break;
                case 6:
                    sortByAge();
                    break;
                case 7:
                    showOldestAnimal();
                    break;
                case 8:
                    showAverageAge();
                    break;
                case 0:
                    shouldExit = true;
                    break;
                default:
                    System.out.println("Invalid option");
            }

        }
    }

    private void printMenu(){
        System.out.println("""
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                6. Sort by age
                7. Oldest animal
                8. Average age
                0. Exit
                """);
    }

    private void addAnimal() {
        System.out.println("Enter animal features in format: <ID> <Name> <Age> <Species>");

        String id = scanner.next();
        String name = scanner.next();
        int age = scanner.nextInt();
        String species = scanner.next();
        scanner.nextLine();

        Animal animal = createAnimal(id, name, age, species);
        shelter.addAnimal(animal);

        System.out.println("Animal added.");
    }

    private void listAllAnimals() {
        List<Animal> animals = shelter.getAllAnimals();

        if (animals.isEmpty()) {
            System.out.println("No animals in shelter.");
            return;
        }

        System.out.println("Animals in shelter:");
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }

    private void findBySpecies() {
        System.out.println("Enter species:");
        String species = scanner.nextLine();
        List<Animal> result = shelter.findBySpecies(species);
        if (result.isEmpty()) {
            System.out.println("No animals found.");
            return;
        }
        for (Animal animal : result) {
            System.out.println(animal);
        }
    }

    private void listAvailableAnimals() {
        List<Animal> result = shelter.findAvailableAnimals();
        if (result.isEmpty()) {
            System.out.println("No available animals found.");
            return;
        }
        for (Animal animal : result) {
            System.out.println(animal);
        }
    }

    private void markAsAdopted() {
        System.out.println("Enter animal ID:");
        String id = scanner.nextLine();

        shelter.markAsAdopted(id);
        System.out.println("Animal was marked as adopted.");
    }
    private void sortByAge() {
        List<Animal> sorted = shelter.sortByAge();

        for (Animal a : sorted) {
            System.out.println(a.toString());
        }
    }
    private void showOldestAnimal() {
        Animal oldest = ShelterInfo.oldestAnimal(shelter);

        if (oldest == null) {
            System.out.println("No animals in shelter.");
            return;
        }
        System.out.println(oldest);
    }
    private void showAverageAge() {
        try {
            float avg = ShelterInfo.averageAge(shelter);
            System.out.println("Average age: " + avg);
        } catch (RuntimeException e) {
            System.out.println("No animals in shelter.");
        }
    }

    private Animal createAnimal(String id, String name, int age, String species) {

        Animal animal;

        switch (species.toLowerCase()) {
            case "dog":
                animal = new Dog(new AnimalId(id), name, age);
                break;

            case "cat":
                animal = new Cat(new AnimalId(id), name, age);
                break;

            case "bird":
                animal = new Bird(new AnimalId(id), name, age);
                break;

            case "bear":
                animal = new Bear(new AnimalId(id), name, age);
                break;

            default:
                throw new RuntimeException("Unknown species provided");
        }

        return animal;
    }
}
