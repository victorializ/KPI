package com.tourism.Tourist;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TouristRowMapper implements RowMapper<TouristModel> {
    public TouristModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        TouristModel tourist = new TouristModel();
        tourist.setId(resultSet.getLong("ID"));
        tourist.setName(resultSet.getString("NAME"));
        tourist.setEmail(resultSet.getString("EMAIL"));
        return tourist;
    }
}
