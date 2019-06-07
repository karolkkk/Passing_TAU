package pl.tau.db.dao;

import pl.tau.db.DbConnect;
import pl.tau.db.domain.Concert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Owner on 07/06/2019.
 */

public class ConcertDao implements DAO{
    DbConnect connection;

    PreparedStatement updateConcertPreparedStatement;
    PreparedStatement getAllConcertsPreparedStatement;

    public ConcertDao (Connection con) {
        try {
            updateConcertPreparedStatement = con.prepareStatement("UPDATE concert SET artist = ?, event_date = ?, location = ?  WHERE id = ?");
            getAllConcertsPreparedStatement = con.prepareStatement("SELECT * FROM concert ORDER By id");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public List<Concert> getAllConcerts () {
        try {
            List<Concert> ret = new LinkedList();
            ResultSet result = getAllConcertsPreparedStatement.executeQuery();
            while (result.next()) {
                Concert p = new Concert();
                p.setId(result.getLong("id"));
                p.setArtist(result.getString("artist"));
                p.setEvent_date(result.getString("event_date"));
                p.setLocation(result.getString("location"));
                ret.add(p);
            }
            return ret;
        }catch (Exception e) {
            return null;
        }
    }


    public int updateConcert(Concert concert) throws SQLException {
        int count;
        try {
            updateConcertPreparedStatement.setString(1, concert.getArtist());
            updateConcertPreparedStatement.setString(2,concert.getEvent_date());
            updateConcertPreparedStatement.setString(3,concert.getLocation());

            if (concert.getId() != null) {
                updateConcertPreparedStatement.setLong(4, concert.getId());
            } else {
                updateConcertPreparedStatement.setLong(4, -1);
            }
            count = updateConcertPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        if (count == 1)
            return count;
        else
            throw new SQLException("Concert not found");
    }

}
