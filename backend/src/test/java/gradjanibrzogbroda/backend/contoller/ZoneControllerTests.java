package gradjanibrzogbroda.backend.contoller;

import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;

import org.h2.command.dml.Set;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.config.TestWebSecurity;
import gradjanibrzogbroda.backend.constants.JeloConstants;
import gradjanibrzogbroda.backend.dto.JeloDTO;
import gradjanibrzogbroda.backend.dto.StoDTO;
import gradjanibrzogbroda.backend.dto.ZoneDTO;

@Import(TestWebSecurity.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ZoneControllerTests extends AbstractTestNGSpringContextTests{

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testGetJeloById() {
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("/zone", Object.class);
		
		List<ZoneDTO> actual = (List<ZoneDTO>) responseEntity.getBody();
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(actual.size(), 2);
	}
	
	@Test
	public void testUpdateZoneNeaktivna() {
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("/zone", new ZoneDTO("podrum", "", "12345", new HashSet<StoDTO>()), String.class);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testUpdateZoneNeAktivna2() {
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("/zone", new ZoneDTO("podrum", "", "56789", new HashSet<StoDTO>()), String.class);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test(priority = 10)
	public void testDeleteZone() {
		ResponseEntity<String> responseEntity = restTemplate.exchange("/zone/"+"12345", HttpMethod.DELETE,new HttpEntity<Object>(null), String.class);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	
	
}
