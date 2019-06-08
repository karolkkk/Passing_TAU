package REST.servlets; /**
 * Created by Owner on 08/06/2019.
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.tau.db.dao.ConcertDao;
import pl.tau.db.domain.Concert;
import org.springframework.test.context.ContextConfiguration;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ConcertControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ConcertDao service;
    @Test
    public void contextLoads() throws Exception {
        assertNotNull(mockMvc);
    }
    @Test
    public void greetingShouldReturnHelloMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }
    @Test
    public void getAllShouldReturnSomeResults() throws Exception {
        List<Concert> expectedResult = new LinkedList<Concert>();
        Concert np = new Concert();
        np.setId(123l);
        np.setArtist("Waclawa");
        np.setEvent_date("23.09.2019");
        np.setLocation("Borkowo");
        expectedResult.add(np);
        when(service.getAllConcerts()).thenReturn(expectedResult);
        this.mockMvc.perform(get("/concerts"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":123,\"artist\":\"Waclawa,\"event_date\":23.09.2019,\"location\":Borkowo}]"));
    }
}

