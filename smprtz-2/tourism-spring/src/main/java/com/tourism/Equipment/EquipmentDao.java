package com.tourism.Equipment;

import com.tourism.CrudDao;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentDao extends CrudDao<EquipmentModel> {
    String  add(EquipmentModel order);
    void delete(EquipmentModel order);
    void update(EquipmentModel order);

    List<EquipmentModel> getAll() throws SQLException;
    EquipmentModel getById(Long id);
}
