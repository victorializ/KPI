package com.tourism.Tourist;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.tourism.Equipment.EquipmentModel;
import com.tourism.Equipment.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;


@RestController
public class TouristController {
    public Tourist dao;

    public TouristController() {
        this.dao = new Tourist();
    }

    @RequestMapping(path = "/tourist", method = RequestMethod.GET)
    public List<TouristModel> GetAll() throws SQLException {
        return this.dao.getAll();
    }

    @RequestMapping(path = "/tourist/{id}", method = RequestMethod.GET)
    public TouristModel GetById(@PathVariable long id) throws SQLException {
        return this.dao.getById(id);
    }
}

