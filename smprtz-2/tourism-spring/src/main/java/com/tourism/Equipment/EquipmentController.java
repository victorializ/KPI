package com.tourism.Equipment;

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
public class EquipmentController {

    public Equipment dao;

    public EquipmentController() {
        this.dao = new Equipment();
    }

    @RequestMapping(path = "/equipment", method = RequestMethod.GET)
    public List<EquipmentModel> GetAll() throws SQLException {
        return this.dao.getAll();
    }

    @RequestMapping(path = "/equipment/{id}", method = RequestMethod.GET)
    public EquipmentModel GetById(@PathVariable long id) throws SQLException {
        return this.dao.getById(id);
    }
}