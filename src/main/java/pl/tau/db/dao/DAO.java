package pl.tau.db.dao;

import pl.tau.db.domain.Concert;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Owner on 07/06/2019.
 */
public interface DAO<T> {
    List<T> getAllConcerts();
    int updateConcert(Concert concert) throws SQLException;
    Optional<T> getConcert(long id);
    int deleteConcert(T t) throws SQLException;
     T save(T t);

}
