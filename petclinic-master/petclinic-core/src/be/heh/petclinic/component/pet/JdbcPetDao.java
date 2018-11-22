package be.heh.petclinic.component.pet;

import org.springframework.jdbc.core.JdbcTemplate;


import be.heh.petclinic.domain.Pet;
import java.util.List;

import javax.sql.DataSource;

public class JdbcPetDao {

    private DataSource dataSource;

    public JdbcPetDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Pet> getEvents() {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT p.id, p.name, p.birth_date, t.name, o.* FROM pets AS p JOIN types AS t ON (p.type_id = t.id) JOIN owners AS o ON (p.owner_id = o.id) ORDER BY p.id;", new PetRowMapper());
    }

}

