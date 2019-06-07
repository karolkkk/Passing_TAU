package pl.tau.db.dao;

import pl.tau.db.dao.ConcertDao;
import org.junit.Before;
import org.junit.Test;
import pl.tau.db.DbConnect;
import pl.tau.db.domain.Concert;

/**
 * Created by Owner on 07/06/2019.
 */
import java.sql.*;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class ConcertDaoTest {
    DbConnect connection;
    Random random;
    List<Concert> initialDatabaseState;

    @Before
    public void setup() throws SQLException {
        random = new Random();
        //connection = DriverManager.getConnection(connection);
        DbConnect con = new DbConnect();
        initialDatabaseState = new LinkedList();
        try {
            con.getCon().createStatement()
                    .executeUpdate("CREATE TABLE " +
                            "Concert(id bigint GENERATED BY DEFAULT AS IDENTITY, "
                            + "artist varchar(20) NOT NULL, event_date varchar(20) NOT NULL) , location varchar(20) NOT NULL");

        } catch (Exception e) {
        }

        try {
            PreparedStatement insert = con.getCon().prepareStatement(
                    "INSERT INTO Concert (artist, event_date, location) VALUES (?, ?,?) ",
                    Statement.RETURN_GENERATED_KEYS);
            PreparedStatement getAllConcertsPreparedStatement = con.getCon().prepareStatement("SELECT id, artist, event_date, location FROM Concert ORDER By id");
            PreparedStatement getConcertByIdPreparedStatement = con.getCon().prepareStatement("SELECT id, artist, event_date, location FROM Concert WHERE id = ?");
            PreparedStatement updateConcertPreparedStatement = con.getCon().prepareStatement("UPDATE Concert SET mileage = ? WHERE id = ?");
            PreparedStatement deleteConcertPreparedStatement = con.getCon().prepareStatement("DELETE FROM Concert WHERE id =  ?");
            for (int i = 0; i < 10; i++) {
                Concert concert = new Concert();
                concert.setArtist("janek" + random.nextInt(1000));
                concert.setEvent_date("25.06.2018" + random.nextInt(1000));
                concert.setLocation("Gdansk" + random.nextInt(1000));
                insert.setString(1, concert.getArtist());
                insert.setString(2, concert.getEvent_date());
                insert.setString(3, concert.getArtist());
                insert.executeUpdate();
                ResultSet keys = insert.getGeneratedKeys();
                if (keys.next()) {
                    concert.setId(keys.getLong(1));
                }

                initialDatabaseState.add(concert);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Test
        public void gettingAllTest() {
            Concert concert = new Concert();
            List<Concert> retrievedConcerts = concertDao.getAllConcerts();
            assertThat(retrievedConcerts, equalTo(initialDatabaseState));
        }
    @Test
    public void updatingTest() throws SQLException {
        Concert concert = new Concert(initialDatabaseState.get(2));
        DAO concertDAO = new ConcertDao();
        concert.setArtist("Organek");
        concert.setLocation("Gdynia");
        concert.setEvent_date("04.05.2019");
        initialDatabaseState.set(2, concert);
        assertEquals(1, concertDAO.updateConcert(concert));
        assertThat(concertDAO.getAllConcerts(), equalTo(initialDatabaseState));
    }
     /*   @Test
        public void addingTest() {
            Concert concert = new Concert();
            concert.setArtist("Organek");
            concert.setLocation("Gdynia");
            concert.setEvent_date("04.05.2019");

            assertEquals(1, concert.addConcert(concert));
            initialDatabaseState.add(concert);
            assertThat(concert.getAllConcerts(), equalTo(initialDatabaseState));
        }*/
}}
