package pl.tau.db.dao;

/**
 * Created by Owner on 25/05/2019.
 */
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.awt.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class ConcertTest {
    @Test
    public void createObjectTest() {
        Concert p = new Concert();
        assertNotNull(p);
    }


}
