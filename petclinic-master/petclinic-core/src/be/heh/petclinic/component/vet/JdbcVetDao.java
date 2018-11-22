package be.heh.petclinic.component.vet;

import org.springframework.jdbc.core.JdbcTemplate;


import be.heh.petclinic.domain.Vet;
import java.util.List;

import javax.sql.DataSource;

public class JdbcVetDao {

    private DataSource dataSource;

    public JdbcVetDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Vet> getEvents() {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select v.id, v.first_name, v.last_name,v.description, s.name from vets as v left join vet_specialties as vs on v.id = vs.vet_id left join specialties as s on specialty_id = s.id;", new VetRowMapper());
    }

}