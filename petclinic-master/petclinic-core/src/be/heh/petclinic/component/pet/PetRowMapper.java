package be.heh.petclinic.component.pet;

import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.util.Collection;
import java.sql.ResultSet;

import be.heh.petclinic.component.owner.*;
import be.heh.petclinic.domain.*;

public class PetRowMapper implements RowMapper<Pet> {
    @Override
    public Pet mapRow(ResultSet rs,int i) throws SQLException {

        Pet pet = new Pet();
        pet.setId(rs.getInt("p.id"));
        pet.setBirthDate(rs.getDate("p.birth_Date").toString());
        pet.setName(rs.getString("p.name"));
        pet.setType(rs.getString("t.name"));
        Owner owner = new Owner();
        owner.setId(rs.getInt("o.id"));
        owner.setFirstName(rs.getString("o.first_name"));
        owner.setLastName(rs.getString("o.last_name"));
        owner.setAddress(rs.getString("o.address"));
        owner.setCity(rs.getString("o.city"));
        owner.setTelephone(rs.getString("o.telephone"));
        pet.setOwner(owner);
        return pet;
    }

}