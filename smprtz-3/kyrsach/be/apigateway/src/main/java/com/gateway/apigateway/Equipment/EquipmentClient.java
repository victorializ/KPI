package com.gateway.apigateway.Equipment;

import com.gateway.apigateway.CustomException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="equipment-service")
public interface EquipmentClient {
    @RequestMapping(path="/equipment", method = RequestMethod.POST)
    public @ResponseBody Equipment add(@RequestBody Equipment equipment);

    @RequestMapping(path="/equipment", method = RequestMethod.GET)
    public @ResponseBody Iterable<Equipment> getAll();

    @RequestMapping(path="/equipment/{id}", method = RequestMethod.GET)
    public @ResponseBody Equipment getById(@PathVariable int id) throws CustomException;

    @RequestMapping(path="/equipment/find/{name}", method = RequestMethod.GET)
    public @ResponseBody Equipment getIdByName(@PathVariable String name);

    @RequestMapping(path="/equipment/filter/{type}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Equipment> getByFilter(@PathVariable String type);

    @RequestMapping(path="/equipment/{id}", method = RequestMethod.PUT)
    public @ResponseBody Equipment update(@PathVariable Integer id,
                                          @RequestBody Equipment equipment) throws CustomException;

    @RequestMapping(path="/equipment/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) throws CustomException;
}