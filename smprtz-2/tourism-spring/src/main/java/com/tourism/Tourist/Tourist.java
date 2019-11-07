package com.tourism.Tourist;

import com.tourism.Config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Tourist implements TouristDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public String  add(TouristModel tourist) {

        String query = String.format("INSERT INTO TOURIST (ID, name, email) VALUES(%f,%s,%s)", tourist.getId(), tourist.getName(), tourist.getEmail());
        jdbcTemplate.update(query);
        return "Added!";
    }

    public List<TouristModel> getAll() {
        String query = "SELECT ID, NAME, EMAIL FROM TOURIST";
        return jdbcTemplate.query(query, new TouristRowMapper());
    }

    public TouristModel getById(Long id) {
        String query = "SELECT * FROM TOURIST WHERE ID=?";
        return this.jdbcTemplate.queryForObject(query, new Object[]{id}, new TouristRowMapper());
    }


    public void update(TouristModel tourist) {
        String query = String.format("UPDATE TOURIST SET NAME=%s,EMAIL=%s", tourist.getName(), tourist.getEmail());
        jdbcTemplate.update(query);
    }

    public void delete(TouristModel tourist) {
        String query =  String.format("DELETE FROM TOURIST WHERE ID=%f", tourist.getId());
        jdbcTemplate.update(query);
    }

    public Tourist() {
        jdbcTemplate = new AppConfig().jdbcTemplate();
    }
}
