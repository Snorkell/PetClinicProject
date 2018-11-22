package be.heh.petclinic.component.vet;

import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.ResultSet;

import be.heh.petclinic.domain.Vet;

public class VetRowMapper implements RowMapper<Vet> {

    @Override
    public Vet mapRow(ResultSet rs,int i) throws SQLException {
        Vet vet = new Vet();
        vet.setId(rs.getInt("id"));
        vet.setFirstname(rs.getString("v.first_name"));
        vet.setLastname(rs.getString("v.last_name"));
        vet.addSpecialty(rs.getString("s.name"));
        vet.setDescription(rs.getString("v.description"));
        return vet;
    }


}