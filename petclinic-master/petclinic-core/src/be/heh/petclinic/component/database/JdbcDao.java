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
        List<Vet> vets = this.getVetData();
        int lastId = vets.get(vets.size()-1).getId()+1;
        insert.execute("INSERT INTO vets(first_name, last_name, description) VALUES(\""+vet.getFirstname()+"\",\""+vet.getLastname()+"\",\""+vet.getDescription()+"\")");
        String query ="";
        int id =0;
        for (int i = 0; i< vet.getSpecialty().size(); i++) {
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
            insert.update(query);
        }
    }
    public void savePetToDB(Pet pet){
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        int petTypeId = 0;
        for (PetType var : this.getTypeData()) {
            if(pet.getType().equals(var.getName())){
                petTypeId = var.getId();
                break;
            }
        }
        insert.execute("INSERT INTO pets(name, birth_date, type_id, owner_id) VALUES(\""+pet.getName()+"\",\""+pet.getBirthDate()+"\",\""+petTypeId+"\",\""+pet.getOwner().getId()+"\");");
    }
    public void saveVisitToDB(Visit visit){
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.execute("");

    }

}

