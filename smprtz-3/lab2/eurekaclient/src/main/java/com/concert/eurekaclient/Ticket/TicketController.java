package com.concert.eurekaclient.Ticket;

import com.concert.eurekaclient.GetResponse;
import com.concert.eurekaclient.Ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/ticket")
public class TicketController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Autowired
    private TicketRepository repository;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @RequestMapping(path="/list", method = RequestMethod.GET)
    public @ResponseBody
    GetResponse<Iterable<Ticket>> getAll() {
        return new GetResponse<Iterable<Ticket>>(repository.findAll(), instanceId);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    public @ResponseBody GetResponse<Optional<Ticket>> getById(@PathVariable int id) {
        return new GetResponse<Optional<Ticket>>(repository.findById(id), instanceId);
    }

    @RequestMapping(path="/find/{event}", method = RequestMethod.GET)
    public @ResponseBody GetResponse<Ticket> getByEvent(@PathVariable String event) {
        return  new GetResponse<Ticket>(repository.findByEvent(event), instanceId);
    }

    @RequestMapping(path="/new", method = RequestMethod.POST)
    public @ResponseBody String add (@RequestParam String event,
                                     @RequestParam String date,
                                     @RequestParam String location,
                                     @RequestParam String zone,
                                     @RequestParam Integer place,
                                     @RequestParam Float price) throws ParseException {
        Ticket instance = new Ticket();
        Date purchaseDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        instance.setEvent(event);
        instance.setDate(formatter.parse(date));
        instance.setLocation(location);
        instance.setZone(zone);
        instance.setPlace(place);
        instance.setPrice(price);
        instance.setPurchaseDate(purchaseDate);
        repository.save(instance);
        return "Saved";
    }

    @RequestMapping(path="/delete", method = RequestMethod.POST)
    public @ResponseBody void delete(@PathVariable Integer id) {
        Ticket instance  = repository.find(id);
        repository.delete(instance);
    }


    @RequestMapping(path="/update", method = RequestMethod.POST)
    public @ResponseBody String update(@RequestParam String event,
                                        @RequestParam String date,
                                        @RequestParam String location,
                                        @RequestParam String zone,
                                        @RequestParam Integer place,
                                        @RequestParam Float price) throws ParseException {
        repository.customUpdate(event, formatter.parse(date), location, zone,  place, price);
        return "Updated";
    }
}
