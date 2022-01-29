package gradjanibrzogbroda.backend.contoller;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.constants.PredlogConstants;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PredlogControllerTests extends AbstractTestNGSpringContextTests {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test(priority = -1)
    public void testGetAllZaposleniBeforeAdd() {
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("/predlog?page="+0+"&size="+5,
        		Map.class);

        Map<String, Object> actual = responseEntity.getBody();
        
        Assert.assertEquals(actual.get("totalItems"), 0);

    }
	
	@Test(priority = 10)
    public void testGetAllZaposleniAfterAdd() {
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("/predlog?page="+0+"&size="+5,
        		Map.class);

        Map<String, Object> actual = responseEntity.getBody();
        
        Assert.assertEquals(actual.get("totalItems"), 0);

    }

}
