package pl.tau.db.dao;

import pl.tau.db.domain.Concert;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Owner on 07/06/2019.
 */
public interface DAO {
    List<Concert> getAllConcerts();
    int updateConcert(Concert concert) throws SQLException;
}
