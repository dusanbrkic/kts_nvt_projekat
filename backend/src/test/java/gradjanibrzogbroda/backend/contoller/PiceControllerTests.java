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

import gradjanibrzogbroda.backend.constants.PiceConstants;
import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.dto.PiceDTO;
import gradjanibrzogbroda.backend.service.PiceService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PiceControllerTests extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PiceService piceService;
	
	@Test
	public void testGetPiceById() {
		ResponseEntity<PiceDTO> responseEntity = restTemplate.getForEntity("/pice/id/"+PiceConstants.DB_PICE_ID, PiceDTO.class);
		
		PiceDTO actual = responseEntity.getBody();
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(actual.getId(),PiceConstants.DB_PICE_ID);
		assertEquals(actual.getTrenutnaCena(),PiceConstants.DB_PICE_CENA);
		assertEquals(actual.getNaziv(),PiceConstants.DB_PICE_NAZIV);
	}
	
	@Test
	public void testGetAllPice() {
		ResponseEntity<PiceDTO[]> responseEntity = restTemplate.getForEntity("/pice/all", PiceDTO[].class);
		
		PiceDTO[] actual = responseEntity.getBody();
		
		assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
		assertEquals(actual[0].getId(),PiceConstants.DB_PICE_ID);
		assertEquals(actual[0].getTrenutnaCena(),PiceConstants.DB_PICE_CENA);
		assertEquals(actual[0].getNaziv(),PiceConstants.DB_PICE_NAZIV);
	}
	
	@Test
	public void testAddPice() {
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("/pice", PiceConstants.NEW_PICE_DTO, String.class);
		
		Pice actual = piceService.findOne(PiceConstants.NEW_PICE_ID);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(actual.getId(), PiceConstants.NEW_PICE_ID);
		assertEquals(actual.getNaziv(), PiceConstants.NEW_PICE_NAZIV);
		assertEquals(actual.getTrenutnaCena(), PiceConstants.NEW_PICE_CENA);
	}
	
	@Test
	public void testIzmeniPice() {
		ResponseEntity<PiceDTO> responseEntity = restTemplate.postForEntity("/pice", PiceConstants.UPDATED_PICE_DTO, PiceDTO.class);
		
		PiceDTO actual = responseEntity.getBody();
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(actual.getId(), PiceConstants.UPDATED_PICE_ID);
		assertEquals(actual.getNaziv(), PiceConstants.UPDATED_PICE_NAZIV);
		assertEquals(actual.getTrenutnaCena(), PiceConstants.UPDATED_PICE_CENA);
		
	}
	
	@Test(priority = 2)
	public void testDeletePice() {
		ResponseEntity<Object> responseEntity = restTemplate.exchange("/pice/"+PiceConstants.DELETED_PICE_ID, HttpMethod.DELETE, new HttpEntity<Object>(null), Object.class);
		
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
}
