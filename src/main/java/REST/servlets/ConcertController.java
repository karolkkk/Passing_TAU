package REST.servlets;

/**
 * Created by Owner on 08/06/2019.
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.tau.db.DbConnect;
import pl.tau.db.dao.ConcertDao;
import pl.tau.db.domain.Concert;

import java.awt.print.Book;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;


import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8081/deleteConcert"})
@Controller
public class ConcertController {

    ConcertDao concertDao = new ConcertDao(DbConnect.getCon());

    @RequestMapping("/")
    public @ResponseBody
    String greeting() {
        return "Hello World";
    }
//getting all concerts
    @RequestMapping(value = "/concerts", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Concert> getAllConcerts() {
        List<Concert> concerts = new ArrayList<>();
        for (Concert p : concertDao.getAllConcerts()) {
            concerts.add(p);
        }
        return concerts;
    }
    //getting one concert
    @RequestMapping(value = "/concert/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Concert getConcert(@PathVariable("id") Long id) {
        return concertDao.getConcert(id).get();
    }

    //deleting concert
    @RequestMapping(value = "/concerts/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteConcert(@PathVariable("id") Long id) throws SQLException {

        concertDao.deleteConcert(getConcert(id));
        return "OK";
    }
    /*@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletePerson(@PathVariable("id") Long id) throws SQLException {
        libraryManager.deleteClient(libraryManager.findClientById(id));
        return "OK";
    }*/
    //creating concert
    @RequestMapping(value = "/concert",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public Concert saveC(@RequestBody Concert concert) {
       Concert concert1 = concertDao.save(concert);

        return concert1;
    }
}