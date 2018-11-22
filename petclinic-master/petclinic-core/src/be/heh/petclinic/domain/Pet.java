package be.heh.petclinic.domain;
import java.util.Date;
public class Pet{
    private int id;
    private String name;
    private String birthDate;
    private String type;
    private Owner owner;

    public Pet(){
        
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
    public String getBirthDate() {
        return this.birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Owner getOwner() {
        return this.owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}