package be.heh.petclinic.domain;

public class PetType{
    private int id;
    private String name;
    public PetType(){
        
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }   
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}