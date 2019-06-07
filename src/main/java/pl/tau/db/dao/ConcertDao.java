package pl.tau.db.dao;

import pl.tau.db.DbConnect;
import pl.tau.db.domain.Concert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Owner on 07/06/2019.
 */

public class ConcertDao implements DAO<Concert>{
    DbConnect connection;

    PreparedStatement updateConcertPreparedStatement;
    PreparedStatement getAllConcertsPreparedStatement;
    PreparedStatement getOneConcert;
    PreparedStatement deleteOneConcert;
    ResultSet rs = null;
    public ConcertDao (Connection con) {
        try {
            getOneConcert = con.prepareStatement("SELECT * FROM concert Where id = ?");
            updateConcertPreparedStatement = con.prepareStatement("UPDATE concert SET artist = ?, event_date = ?, location = ?  WHERE id = ?");
            getAllConcertsPreparedStatement = con.prepareStatement("SELECT * FROM concert ORDER By id");
            deleteOneConcert = con.prepareStatement("DELETE FROM concert WHERE id=?");
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

    public Optional<Concert> getConcert(long id) {
      Concert concert = new Concert();

        try {
            getOneConcert.setLong(1,id);
            rs = getOneConcert.executeQuery();
            while (rs.next()) {
                concert = new Concert(rs.getLong("id"), rs.getString("artist"), rs.getString("event_date"),
                        rs.getString("location"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return Optional.of(concert);

    }

    public int deleteConcert(Concert concert)  throws SQLException{
        int count;
        try {
            if (concert.getId() != null) {
                deleteOneConcert.setLong(1, concert.getId());


            } else {
                deleteOneConcert.setLong(1, -1);
            }
            count = deleteOneConcert.executeUpdate();

        }catch(SQLException e){
            throw new SQLException(e.getMessage());
            }
        if (count == 1)
            return count;
        else
            throw new SQLException("Concert not found");
    }
    }


