package com.tourism.Tourist;

import com.tourism.CrudDao;

import java.util.List;

public interface TouristDao extends CrudDao<TouristModel> {
    String add(TouristModel order);
    void delete(TouristModel order);
    void update(TouristModel order);

    List<TouristModel> getAll();
    TouristModel getById(Long id);
}
