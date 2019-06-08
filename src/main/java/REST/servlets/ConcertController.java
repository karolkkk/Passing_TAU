package REST.servlets;

/**
 * Created by Owner on 08/06/2019.
 */
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.tau.db.DbConnect;
import pl.tau.db.dao.ConcertDao;
import pl.tau.db.domain.Concert;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;


import org.springframework.web.bind.annotation.*;


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
    @RequestMapping(value = "/concert/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletePerson(@PathVariable("id") Long id) throws SQLException {
        concertDao.deleteConcert(concertDao.getConcert(id).get());
        return "OK";
    }
    //creating concert
    @RequestMapping(value = "/concert",method = RequestMethod.POST)
    public Concert save(@RequestBody Concert nconcert) {
        nconcert.setId(concertDao.save(nconcert));
        return nconcert;
    }
}