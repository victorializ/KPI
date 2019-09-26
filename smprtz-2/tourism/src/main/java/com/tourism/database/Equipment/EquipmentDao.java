package com.tourism.database.Equipment;

import com.tourism.database.CrudDao;

import java.util.List;

public interface EquipmentDao extends CrudDao<EquipmentModel> {

    void newTable();
    void deleteTable();

    void add(EquipmentModel order);
    void delete(EquipmentModel order);
    void update(EquipmentModel order);

    List<EquipmentModel> getAll();
    EquipmentModel getById(Long id);
}
