package be.heh.petclinic.domain;
import java.util.ArrayList;
public class Vet {
    private int id;
    private String lastname;
    private String firstname;
    private String description;
    private ArrayList<String> specialty = new ArrayList<>();

    public Vet(){
        
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public void addSpecialty(String specialty){
        this.specialty.add(specialty);
    }
    public void removeSpecialty(String specialty){
        this.specialty.remove(specialty);
    }

    public String getLastname(){
        return this.lastname;
    }

    public String getFirstname(){
        return this.firstname;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ArrayList<String> getSpecialty(){
        return this.specialty;
    }

}