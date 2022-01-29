package gradjanibrzogbroda.backend.contoller;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.config.TestWebSecurity;

@Import(TestWebSecurity.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ReportControllerTests extends AbstractTestNGSpringContextTests{

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testGetFinancialReport() {
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("/report?start=2022-01-09T13:30:00.000Z&end=2022-01-27T13:30:00.000Z", Object.class);
		
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testGetFinancialReportLastYear() {
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("/report?start=2021-01-09T13:30:00.000Z&end=2021-01-27T13:30:00.000Z", Object.class);
		
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
}
