package be.heh.petclinic.domain;

import java.util.Date;

public class Visit{
    private int id; 
    private Pet pet;
    private String date;
    private String description;

    public Visit(){
        
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Pet getPet() {
        return this.pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }
}