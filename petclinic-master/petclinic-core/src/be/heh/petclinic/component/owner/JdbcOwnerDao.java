package be.heh.petclinic.component.owner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import be.heh.petclinic.domain.Owner;
import java.util.*;

import javax.sql.*;

public class JdbcOwnerDao {
    private DataSource dataSource;
    public JdbcOwnerDao(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public List<Owner> getEvents(){
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT * FROM owners;", new OwnerRowMapper());
    }
    public void saveOwnerToDB(Owner owner){
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.execute("INSERT INTO owners(first_name, last_name, address, city, telephone) VALUES('"+owner.getFirstName()+"','"+owner.getLastName()+"','"+owner.getAddress()+"','"+owner.getCity()+"','"+owner.getTelephone()+"')");

    }
}

