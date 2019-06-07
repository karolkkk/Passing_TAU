package pl.tau.db.dao;

/**
 * Created by Owner on 07/06/2019.
 */

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.junit.runners.JUnit4;
import pl.tau.db.DbConnect;

@RunWith(JUnit4.class)
public class DbConnectTest {
    private static final Logger LOGGER = Logger.getLogger(DbConnectTest.class.getCanonicalName());

    @Rule
    public Timeout globalTimeout = new Timeout(1000);


    @Test
    public void createConnectionDbTest() {
        DbConnect connection = new DbConnect();
        assertNotNull(connection);
    }

    @Test
    public void ConnectionTest() {
        DbConnect connection = new DbConnect();
        try {
            assertNotNull(connection.getCon());
        }
        catch(Exception e) {
            LOGGER.log(Level.FINEST,"Failed connection");
        }
    }
}
