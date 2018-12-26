package be.heh.petclinic.component.database;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import be.heh.petclinic.component.owner.*;
import be.heh.petclinic.component.vet.*;
import be.heh.petclinic.component.visit.*;
import be.heh.petclinic.component.pet.*;
import be.heh.petclinic.domain.*;

import java.sql.ResultSet;
import java.util.*;

import javax.sql.*;

public class JdbcDao {
    private DataSource dataSource;
    public JdbcDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    //Get From database
    public List<Owner> getOwnerData(){
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT * FROM owners;", new OwnerRowMapper());
    }
    public List<PetType> getTypeData(){
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT * FROM types;", new TypeRowMapper());
    }
    public List<Pet> getPetData() {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT p.id, p.name, p.birth_date, t.name, o.* FROM pets AS p JOIN types AS t ON (p.type_id = t.id) JOIN owners AS o ON (p.owner_id = o.id) ORDER BY p.id;", new PetRowMapper());
    }
    public List<Vet> getVetData() {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select v.id, v.first_name, v.last_name,v.description, s.name from vets as v left join vet_specialties as vs on v.id = vs.vet_id left join specialties as s on specialty_id = s.id;", new VetRowMapper());
    }
    public List<Visit> getVisitData() {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT v.id,v.visit_date, v.description,t.name, p.*,o.* FROM visits AS v JOIN pets AS p ON (v.pet_id = p.id) JOIN owners AS o ON (p.owner_id = o.id) JOIN types as t ON (p.type_id = t.id);", new VisitRowMapper());
    }

    //Save into database
    public void saveOwnerToDB(Owner owner){
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.execute("INSERT INTO owners(first_name, last_name, address, city, telephone) VALUES(\""+owner.getFirstName()+"\",\""+owner.getLastName()+"\",\""+owner.getAddress()+"\",\""+owner.getCity()+"\",\""+owner.getTelephone()+"\")");

    }
    public void saveVetToDB(Vet vet){
        JdbcTemplate insert = new JdbcTemplate(dataSource);      
        insert.execute("INSERT INTO vets(first_name, last_name, description) VALUES(\""+vet.getFirstname()+"\",\""+vet.getLastname()+"\",\""+vet.getDescription()+"\")");
        List<Vet> vets = this.getVetData();
        int lastId = vets.get(vets.size()-1).getId();
        for (int i = 0; i< vet.getSpecialty().size(); i++) {
            String query ="";
            int id =0;
            switch(vet.getSpecialty().get(i)){
                case "radiology":
                    id=1;
                break;
                case "surgery":
                    id=2;
                break;
                case "general":
                    id=4;
                break;
                case "dentistry":
                    id=3;
                break;
            }
            query ="INSERT INTO vet_specialties(vet_id, specialty_id) VALUES("+lastId+","+id+");";
            insert.execute(query);
        }
    }
    public void savePetToDB(Pet pet){
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        int petTypeId = this.getTypeId(pet);
        
        insert.execute("INSERT INTO pets(name, birth_date, type_id, owner_id) VALUES(\""+pet.getName()+"\",\""+pet.getBirthDate()+"\",\""+petTypeId+"\",\""+pet.getOwner().getId()+"\");");
    }
    public void saveVisitToDB(Visit visit){
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.execute("INSERT INTO visits(pet_id, visit_date, description) VALUES(\""+visit.getPet().getId()+"\",\""+visit.getDate()+"\",\""+visit.getDescription()+"\")");
    }

    // Update db

    public void updateOwnerToDB(Owner owner){
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE owners SET first_name=\""+owner.getFirstName()+"\",last_name=\""+owner.getLastName()+"\",address=\""+owner.getAddress()+"\",city=\""+owner.getCity()+"\",telephone=\""+owner.getTelephone()+"\" WHERE id="+owner.getId()+";");
    }

    public void updateVetToDB(Vet vet){
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE vets SET first_name=\""+vet.getFirstname()+"\",last_name=\""+vet.getFirstname()+"\",description=\""+vet.getDescription()+"\" WHERE id="+vet.getId()+";");
        update.update("DELETE FROM vet_specialties WHERE vet_id=\""+vet.getId()+"\";");
        ArrayList<String> currentSpecialties = vet.getSpecialty();
        for (String spe : currentSpecialties){
            int id = 0;
            switch(spe){
                case "radiology":
                    id=1;
                break;
                case "surgery":
                    id=2;
                break;
                case "general":
                    id=4;
                break;
                case "dentistry":
                    id=3;
                break;
            }
            update.update("INSERT INTO vet_specialties(vet_id, specialty_id) VALUES(\""+vet.getId()+"\",\""+id+"\");");
        }
    }

    public void updatePetToDB(Pet pet){
        JdbcTemplate update = new JdbcTemplate(dataSource);
        int typeId = this.getTypeId(pet);
        update.update("UPDATE pets SET name=\""+pet.getName()+"\" ,birth_date=\""+pet.getBirthDate()+"\" ,type_id=\""+typeId+"\" ,owner_id=\""+pet.getOwner().getId()+"\" WHERE id="+pet.getId()+";");
    }

    public void updateVisitToDB(Visit visit){
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE visits SET pet_id="+visit.getPet().getId()+", visit_date=\""+visit.getDate()+"\", description=\""+visit.getDescription()+"\" WHERE id="+visit.getId()+";");
    }
    private int getTypeId(Pet pet){
        int id = 0;
        for (PetType var : this.getTypeData()) {
            if(pet.getType().equals(var.getName())){
                id= var.getId();
                break;
            }
        }
        return id;
    }

    // DELETE FUNCTION

    public void deletePetFromDB(Pet pet){
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM pets WHERE id="+pet.getId()+";");
    }
    public void deleteVetFromDB(Vet vet){
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM vet_specialties WHERE vet_id="+vet.getId()+";");
        delete.update("DELETE FROM vets WHERE id="+vet.getId()+";");
    }
    public void deleteVisitsFromDB(Visit visit){
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM visits WHERE id="+visit.getId()+";");
    }
    public void deleteOwnerFromDB(Owner owner){
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM pets WHERE owner_id="+owner.getId()+";");
        delete.update("DELETE FROM owners WHERE id="+owner.getId()+";");
    }

}

