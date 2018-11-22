package be.heh.petclinic.component.visit;

import org.springframework.jdbc.core.JdbcTemplate;


import be.heh.petclinic.domain.Visit;
import java.util.List;

import javax.sql.DataSource;

public class JdbcVisitDao {

    private DataSource dataSource;

    public JdbcVisitDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Visit> getEvents() {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT v.id,v.visit_date, v.description,t.name, p.*,o.* FROM visits AS v JOIN pets AS p ON (v.pet_id = p.id) JOIN owners AS o ON (p.owner_id = o.id) JOIN types as t ON (p.type_id = t.id);", new VisitRowMapper());
    }

}