package pl.tau.db.dao;

import org.junit.After;
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

        connection = new DbConnect();
        initialDatabaseState = new LinkedList();
        try {
            connection.getCon().createStatement()
                    .executeUpdate("CREATE TABLE IF NOT EXISTS "  +
                            "Concert(id bigint GENERATED BY DEFAULT AS IDENTITY, "
                            + "artist varchar(20) NOT NULL, event_date varchar(20) NOT NULL) , location varchar(20) NOT NULL");

        } catch (Exception e) {
        }

        try {

            PreparedStatement insert = connection.getCon().prepareStatement(
                    "INSERT INTO Concert (artist, event_date, location) VALUES (?, ?,?) ",
                    Statement.RETURN_GENERATED_KEYS);
            PreparedStatement getAllConcertsPreparedStatement = connection.getCon().prepareStatement("SELECT id, artist, event_date, location FROM Concert ORDER By id");
            PreparedStatement getConcertByIdPreparedStatement = connection.getCon().prepareStatement("SELECT id, artist, event_date, location FROM Concert WHERE id = ?");
            PreparedStatement updateConcertPreparedStatement = connection.getCon().prepareStatement("UPDATE Concert SET artist, event_date, location  WHERE id = ?");
            PreparedStatement deleteConcertPreparedStatement = connection.getCon().prepareStatement("DELETE FROM Concert WHERE id =  ?");
            for (int i = 0; i < 10; i++) {
                Concert concert = new Concert();
                concert.setArtist("janek");
                concert.setEvent_date("25.06.2018" );
                concert.setLocation("Gdansk" );
                insert.setString(1, concert.getArtist());
                insert.setString(2, concert.getEvent_date());
                insert.setString(3, concert.getLocation());
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
    @After
    public void clean() {
        try {
            PreparedStatement deleteConcertPreparedStatement = connection.getCon().prepareStatement("DELETE FROM concert");
            deleteConcertPreparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        @Test
        public void gettingAllTest() {

            DAO concertDAO = new ConcertDao(connection.getCon());

            assertThat(concertDAO.getAllConcerts().size(), equalTo(initialDatabaseState.size()));
        }
    @Test
    public void updatingTest() throws SQLException {
        Concert concert = new Concert(initialDatabaseState.get(1));
        DAO concertDAO = new ConcertDao(connection.getCon());
        concert.setArtist("Organek");
        concert.setLocation("Gdynia");
        concert.setEvent_date("04.05.2019");
        concert.setId(initialDatabaseState.get(1).getId());
        initialDatabaseState.set(2, concert);
        assertEquals(1, concertDAO.updateConcert(concert));

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
     @Test
     public void testGet() {
         DAO concertDAO = new ConcertDao(connection.getCon());
         Concert concert = (Concert) concertDAO.getConcert(initialDatabaseState.get(1).getId()).get();
         assertEquals(initialDatabaseState.get(1).getArtist(), concert.getArtist());
         assertEquals(initialDatabaseState.get(1).getEvent_date(), concert.getEvent_date());
         assertEquals(initialDatabaseState.get(1).getLocation(),concert.getLocation());

     }
     @Test
     public void checkDeleting() throws SQLException {
         Concert concert = new Concert(initialDatabaseState.get(3));
         DAO concertDAO = new ConcertDao(connection.getCon());
         initialDatabaseState.remove(3);

         assertEquals(1,concertDAO.deleteConcert(concert));
         assertThat(concertDAO.getAllConcerts().size(), equalTo(initialDatabaseState.size()));
        // assertNull(concertDAO.getConcert(concert.getId()));

     }


}
