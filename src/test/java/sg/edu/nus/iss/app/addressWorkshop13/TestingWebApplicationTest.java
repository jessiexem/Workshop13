package sg.edu.nus.iss.app.addressWorkshop13;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TestingWebApplicationTest {

    private Logger logger = Logger.getLogger(TestingWebApplicationTest.class.getName());

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveContactTest() throws Exception {
        Map<String,String> input = new HashMap<>();
        input.put("name","test2");
        input.put("email","test2@google.com");
        input.put("phoneNumber", "3243242");
        logger.log(Level.INFO, "" + objectMapper.writeValueAsString(input));
        mockMvc.perform(post("/contact2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
