package be.heh.petclinic.component.vet;

import be.heh.petclinic.domain.Vet;

import java.util.List;

import java.util.Collection;

public interface VetComponent{

    Collection<Vet> getVets();
    Collection<Vet> getVetsByKey(String key, Object value);
    void saveToDB(Vet vet);
    void updateToDB(Vet vet);
    void deleteFomDB(Vet vet);
}