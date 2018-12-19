package be.heh.petclinic.component.pet;

import be.heh.petclinic.domain.Pet;

import java.util.List;

import java.util.Collection;

public interface PetComponent{

    Collection<Pet> getPets();
    Collection<Pet> getPetsByKey(String key, Object value);
    void saveToDB(Pet pet);
    void updateToDB(Pet pet);
    void deleteFromDB(Pet pet);
}