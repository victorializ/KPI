package com.tourism.database;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> {

    void newTable();
    void deleteTable();

    void add(T t) throws SQLException;
    void delete(T order) throws SQLException;
    void update(T order) throws SQLException;

    List<T> getAll() throws SQLException;
    T getById(Long id) throws SQLException;
}