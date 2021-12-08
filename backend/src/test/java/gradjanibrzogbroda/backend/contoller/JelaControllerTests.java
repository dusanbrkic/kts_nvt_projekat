package gradjanibrzogbroda.backend.contoller;

import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.constants.JeloConstants;
import gradjanibrzogbroda.backend.dto.JeloDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class JelaControllerTests extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test(priority = -1)
	public void testGetAllJela() {
		ResponseEntity<JeloDTO[]> responseEntity = restTemplate.getForEntity("/jela/all", JeloDTO[].class);
		
		JeloDTO[] jela = responseEntity.getBody();
		
		assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
		assertEquals(JeloConstants.FIND_ALL_NUMBER_OF_ITEMS,jela.length);
	}
	
	@Test
	public void testGetJeloById() {
		ResponseEntity<JeloDTO> responseEntity = restTemplate.getForEntity("/jela/id/"+JeloConstants.DB_JELO_ID, JeloDTO.class);
		
		JeloDTO actual = responseEntity.getBody();
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(actual.getId(),JeloConstants.DB_JELO_ID);
		assertEquals(actual.getTrenutnaCena(),JeloConstants.DB_JELO_CENA);
		assertEquals(actual.getNaziv(),JeloConstants.DB_JELO_NAZIV);
	}
	
	@Test
	public void testGetJeloByNaziv() {
		ResponseEntity<JeloDTO> responseEntity = restTemplate.getForEntity("/jela/naziv/"+JeloConstants.DB_JELO_NAZIV, JeloDTO.class);
		
		JeloDTO actual = responseEntity.getBody();
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(actual.getId(),JeloConstants.DB_JELO_ID);
		assertEquals(actual.getTrenutnaCena(),JeloConstants.DB_JELO_CENA);
		assertEquals(actual.getNaziv(),JeloConstants.DB_JELO_NAZIV);
	}
	
	@Test
	public void testAddJelo() {
		ResponseEntity<JeloDTO> responseEntity = restTemplate.postForEntity("/jela", JeloConstants.NEW_JELO_DTO, JeloDTO.class);
		
		JeloDTO actual = responseEntity.getBody();
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(actual.getId(),JeloConstants.NEW_JELO_ID);
		assertEquals(actual.getTrenutnaCena(),JeloConstants.NEW_JELO_CENA);
		assertEquals(actual.getNaziv(),JeloConstants.NEW_JELO_NAZIV);
	}
	
	@Test
	public void testUpdateJelo() {
		ResponseEntity<JeloDTO> responseEntity = restTemplate.postForEntity("/jela", JeloConstants.UPDATED_JELO_DTO, JeloDTO.class);
		
		JeloDTO actual = responseEntity.getBody();
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(actual.getId(),JeloConstants.UPDATED_JELO_ID);
		assertEquals(actual.getTrenutnaCena(),JeloConstants.UPDATED_JELO_CENA);
		assertEquals(actual.getNaziv(),JeloConstants.UPDATED_JELO_NAZIV);
	}
	
	@Test
	public void testDeleteJelo() {
		ResponseEntity<Object> responseEntity = restTemplate.exchange("/jela/"+JeloConstants.DELETED_JELO_ID, HttpMethod.DELETE, new HttpEntity<Object>(null), Object.class);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	/*@Test
	public void testIzmeniCenu() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Double> requestUpdate=new HttpEntity<>(JeloConstants.PRICE_CHANGE_JELO_CENA, headers);
		
		ResponseEntity<JeloDTO> responseEntity=restTemplate.exchange("/jela/izmeni-cenu/"+JeloConstants.PRICE_CHANGE_JELO_ID, HttpMethod.PUT,requestUpdate,JeloDTO.class);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}*/
	

}
