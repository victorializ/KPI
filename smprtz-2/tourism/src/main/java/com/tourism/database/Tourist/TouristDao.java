package com.tourism.database.Tourist;

import com.tourism.database.CrudDao;

import java.util.List;

public interface TouristDao extends CrudDao<TouristModel> {
    void newTable();
    void deleteTable();

    void add(TouristModel order);
    void delete(TouristModel order);
    void update(TouristModel order);

    List<TouristModel> getAll();
    TouristModel getById(Long id);
}
