package com.tourism.service;

import com.tourism.service.Equipment;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentRepository repository;

    @RequestMapping(path="", method = RequestMethod.POST)
    public @ResponseBody
    Equipment add(@RequestBody Equipment equipment) {
        return  repository.save(equipment);
    }

    @RequestMapping(path="", method = RequestMethod.GET)
    public @ResponseBody Iterable<Equipment> getAll() {
        return repository.findAll();
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody Equipment getById(@PathVariable int id) throws CustomException {
        return repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
    }

    @RequestMapping(path="/find/{name}", method = RequestMethod.GET)
    public @ResponseBody Equipment getIdByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @RequestMapping(path="/filter/{type}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Equipment> getByFilter(@PathVariable String type) {
        return repository.findByType(type);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.PUT)
    public @ResponseBody Equipment update(@PathVariable Integer id,
                                          @RequestBody Equipment equipment) throws CustomException {
        repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
        repository.customUpdate(id, equipment.getName(),
                equipment.getPrice(), equipment.getType(),
                equipment.getDescription(), equipment.getRating());
        equipment.setId(id);
        return equipment;
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException {
        Equipment existing = repository.findById(id).orElseThrow(() -> new CustomException("Item wasn't found"));
        repository.delete(existing);
        return "Deleted";
    }
}
