package com.tourism.Equipment;

import org.springframework.beans.factory.annotation.Autowired;
import com.tourism.Config.AppConfig;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.List;

public class Equipment implements EquipmentDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public List<EquipmentModel> getAll() throws SQLException {
        String query = "SELECT ID, NAME, PRICE, TYPE FROM EQUIPMENT";
        return jdbcTemplate.query(query, new EquipmentRowMapper());
    }

    public String add(EquipmentModel equipment) {
        String query = "INSERT INTO EQUIPMENT (ID, NAME, PRICE, TYPE) VALUES('" + equipment.getId() + "," + equipment.getName() + "," + equipment.getPrice() + "," + equipment.getType() + "')";
        jdbcTemplate.update(query);
        return "Inserted!";
    }

    public EquipmentModel getById(Long id) {
        String query = "SELECT * FROM EQUIPMENT WHERE ID=?";
        return this.jdbcTemplate.queryForObject(query, new Object[]{id}, new EquipmentRowMapper());
    }

    public void update(EquipmentModel equipment) {
        String query = String.format("UPDATE EQUIPMENT SET NAME=%s, PRICE=%f, TYPE=%s", equipment.getName(), equipment.getType(), equipment.getPrice());
        this.jdbcTemplate.update(query);
    }

    public void delete(EquipmentModel equipment) {
        String query = String.format("DELETE FROM EQUIPMENT WHERE ID=%f", equipment.getId());
        this.jdbcTemplate.update(query);
    }

    public Equipment() {
        jdbcTemplate = new AppConfig().jdbcTemplate();
    } // ?
}