package com.tourism.Equipment;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipmentRowMapper  implements RowMapper<EquipmentModel>{
    public EquipmentModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        EquipmentModel equipment = new EquipmentModel();
        equipment.setId(resultSet.getLong("ID"));
        equipment.setName(resultSet.getString("NAME"));
        equipment.setPrice(resultSet.getFloat("PRICE"));
        equipment.setType(resultSet.getString("TYPE"));
        return equipment;
    }
}