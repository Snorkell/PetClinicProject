package be.heh.petclinic.component.owner;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.ResultSet;

import be.heh.petclinic.domain.Owner;

public class OwnerRowMapper implements RowMapper<Owner>{
    @Override 
        public Owner mapRow(ResultSet rs, int i) throws SQLException{
        Owner owner = new Owner();
        owner.setId(rs.getInt("id"));
        owner.setFirstName(rs.getString("first_name"));
        owner.setLastName(rs.getString("last_name"));
        owner.setAddress(rs.getString("address"));
        owner.setCity(rs.getString("city"));
        owner.setTelephone(rs.getString("telephone"));
        return owner;
    }
}