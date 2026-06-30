package org.example.model;

public final class Bear extends Animal{
    public Bear(AnimalId id, String name, int age){
        super(id,name,age);
    }

    @Override
    public String getSpecies(){
        return "Bear";
    }
}
