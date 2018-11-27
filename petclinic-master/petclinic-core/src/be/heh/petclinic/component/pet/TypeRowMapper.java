package be.heh.petclinic.component.pet;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import be.heh.petclinic.domain.PetType;

public class TypeRowMapper implements RowMapper<PetType>{
    @Override
    public PetType mapRow(ResultSet rs,int i) throws SQLException{
        PetType petType = new PetType();
        petType.setId(rs.getInt("id"));
        petType.setName(rs.getString("name"));
        return petType;
    }
}