package be.heh.petclinic.component.visit;

import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.ResultSet;

import be.heh.petclinic.domain.*;

public class VisitRowMapper implements RowMapper<Visit> {

    @Override
    public Visit mapRow(ResultSet rs,int i) throws SQLException {
        Visit visit = new Visit();
        visit.setId(rs.getInt("v.id"));
        visit.setDate(rs.getDate("v.visit_date").toString());
        visit.setDescription(rs.getString("v.description"));
        Pet pet = new Pet();
        pet.setId(rs.getInt("p.id"));
        pet.setName(rs.getString("p.name"));
        pet.setBirthDate(rs.getDate("p.birth_date").toString());
        pet.setType(rs.getString("t.name"));
        Owner owner = new Owner();
        owner.setId(rs.getInt("o.id"));
        owner.setFirstName(rs.getString("o.first_name"));
        owner.setLastName(rs.getString("o.last_name"));
        owner.setAddress(rs.getString("o.address"));
        owner.setCity(rs.getString("o.city"));
        owner.setTelephone(rs.getString("o.telephone"));
        pet.setOwner(owner);
        visit.setPet(pet);
        return visit;
    }
}