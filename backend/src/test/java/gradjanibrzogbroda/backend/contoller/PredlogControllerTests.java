package gradjanibrzogbroda.backend.contoller;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.config.TestWebSecurity;
import gradjanibrzogbroda.backend.constants.PredlogConstants;
import gradjanibrzogbroda.backend.domain.Predlog;
import gradjanibrzogbroda.backend.domain.PredlogStatus;
import gradjanibrzogbroda.backend.dto.PredlogDTO;
import gradjanibrzogbroda.backend.exceptions.JeloNotFoundException;
import gradjanibrzogbroda.backend.exceptions.PredlogWrongFormatException;

@Import(TestWebSecurity.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PredlogControllerTests extends AbstractTestNGSpringContextTests {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
    public void testDodajPredlogWrongFormatIzmena() throws JSONException {
		
		PredlogDTO dto=new PredlogDTO();
		dto.setId(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_ID);
		dto.setNovoJelo(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_NOVO_JELO);
		dto.setStaroJeloId(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STARO_JELO_ID);
		dto.setStatus(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STATUS);
		dto.setTipIzmene(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_TIP_IZMENE);
	    
        ResponseEntity<PredlogDTO> responseEntity = restTemplate.postForEntity("/predlog",dto,PredlogDTO.class);
        
        
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
	
	@Test
    public void testDodajPredlogWrongFormatBrisanje() throws JSONException {
		
		PredlogDTO dto=new PredlogDTO();
		dto.setId(PredlogConstants.WRONT_FORMAT_PREDLOG_DTO_ID_BRISANJE);
		dto.setNovoJelo(PredlogConstants.WRONT_FORMAT_PREDLOG_DTO_NOVO_JELO_BRISANJE);
		dto.setStaroJeloId(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STARO_JELO_ID_BRISANJE);
		dto.setStatus(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STATUS_BRISANJE);
		dto.setTipIzmene(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_TIP_IZMENE_BRISANJE);
	    
        ResponseEntity<PredlogDTO> responseEntity = restTemplate.postForEntity("/predlog",dto,PredlogDTO.class);
        
        
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
	
	@Test
    public void testDodajPredlogWrongFormatDodavanje() throws JSONException {
		
		PredlogDTO dto=new PredlogDTO();
		dto.setId(PredlogConstants.WRONT_FORMAT_PREDLOG_DTO_ID_DODAVANJE);
		dto.setNovoJelo(PredlogConstants.WRONT_FORMAT_PREDLOG_DTO_NOVO_JELO_DODAVANJE);
		dto.setStaroJeloId(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STARO_JELO_ID_DODAVANJE);
		dto.setStatus(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STATUS_DODAVANJE);
		dto.setTipIzmene(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_TIP_IZMENE_DODAVANJE);
	    
        ResponseEntity<PredlogDTO> responseEntity = restTemplate.postForEntity("/predlog",dto,PredlogDTO.class);
        
        
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
	
	@Test
    public void testDodajPredlogNonExistingJeloId() throws JSONException {
		
		PredlogDTO dto=new PredlogDTO();
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_BRISANJE);
		dto.setStaroJeloId(PredlogConstants.NON_EXISTING_STARO_JELO_ID_DB);
		dto.setStatus(PredlogConstants.PREDLOG_DTO_STATUS_BRISANJE);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_BRISANJE);
	    
        ResponseEntity<PredlogDTO> responseEntity = restTemplate.postForEntity("/predlog",dto,PredlogDTO.class);
        
        
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
	
	@Test(priority = 1)
	public void testAddPredlogTipIzmena() throws PredlogWrongFormatException, JeloNotFoundException, JSONException {
		
		JSONObject jeloJSONObject=new JSONObject();
		jeloJSONObject.put("naziv", PredlogConstants.PREDLOG_DTO_NOVO_JELO_DTO_IZMENA.getNaziv());
		jeloJSONObject.put("opis", PredlogConstants.PREDLOG_DTO_NOVO_JELO_DTO_IZMENA.getOpis());
		jeloJSONObject.put("trenutnaCena", PredlogConstants.PREDLOG_DTO_NOVO_JELO_DTO_IZMENA.getTrenutnaCena());
		jeloJSONObject.put("vremePripremeMils", PredlogConstants.PREDLOG_DTO_NOVO_JELO_DTO_IZMENA.getVremePripremeMils());
		jeloJSONObject.put("kategorijaJela", PredlogConstants.PREDLOG_DTO_NOVO_JELO_DTO_IZMENA.getKategorijaJela());
		jeloJSONObject.put("tipJela", PredlogConstants.PREDLOG_DTO_NOVO_JELO_DTO_IZMENA.getTipJela());
		jeloJSONObject.put("picBase64","");
		
		JSONObject predlogJsonObject = new JSONObject();
		predlogJsonObject.put("novoJelo", jeloJSONObject);
		predlogJsonObject.put("staroJeloId", PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_IZMENA);
		predlogJsonObject.put("status", PredlogConstants.PREDLOG_DTO_STATUS_IZMENA);
		predlogJsonObject.put("tipIzmene", PredlogConstants.PREDLOG_DTO_TIP_IZMENE_IZMENA);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    HttpEntity<String> request = 
	    	      new HttpEntity<String>(predlogJsonObject.toString(), headers);
		
		PredlogDTO responseEntity = restTemplate.postForObject("/predlog",request,PredlogDTO.class);
		
		Assert.assertEquals(responseEntity.getId(), Integer.valueOf(1));
        
	}
	
	@Test(priority = 2)
    public void testDodajPredlogTipDodavanje() throws JSONException {
		
		PredlogDTO dto=new PredlogDTO();
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_DTO_DODAVANJE);
		dto.setStaroJeloId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_DODAVANJE);
		dto.setStatus(PredlogStatus.NOV);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_DODAVANJE);
	    
        ResponseEntity<PredlogDTO> responseEntity = restTemplate.postForEntity("/predlog",dto,PredlogDTO.class);
        
        Assert.assertEquals(responseEntity.getBody().getId(),  Integer.valueOf(2));
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }
	
	
	@Test(priority = 3)
    public void testDodajPredlogTipBrisanje() throws JSONException {
		
		PredlogDTO dto=new PredlogDTO();
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_BRISANJE);
		dto.setStaroJeloId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_BRISANJE);
		dto.setStatus(PredlogStatus.NOV);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_BRISANJE);
	    
        ResponseEntity<PredlogDTO> responseEntity = restTemplate.postForEntity("/predlog",dto,PredlogDTO.class);
        
        Assert.assertEquals(responseEntity.getBody().getId(),  Integer.valueOf(3));
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }
	
	@Test(priority = 5)
	public void testIzmeniPredlogTipBrisanjeUpdateStatus() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setId(3);
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_BRISANJE);
		dto.setStaroJeloId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_BRISANJE);
		dto.setStatus(PredlogStatus.ODBIJEN);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_BRISANJE);
		
		HttpEntity<PredlogDTO> requestUpdate = new HttpEntity<>(dto);
		
		ResponseEntity<PredlogDTO> responseEntity = restTemplate.exchange("/predlog",HttpMethod.PUT,requestUpdate,PredlogDTO.class);
		
		Assert.assertEquals(responseEntity.getBody().getId(), Integer.valueOf(3));
		Assert.assertEquals(responseEntity.getBody().getStatus(), PredlogStatus.ODBIJEN);
		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test(priority = -1)
    public void testGetAllPredloziBeforeAdd() {
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("/predlog?page="+0+"&size="+5,
        		Map.class);

        Map<String, Object> actual = responseEntity.getBody();
        
        Assert.assertEquals(actual.get("totalItems"), 0);

    }
	
	@Test(priority = 4)
    public void testGetAllPredloziAfterAdd() {
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("/predlog?page="+0+"&size="+5,
        		Map.class);

        Map<String, Object> actual = responseEntity.getBody();
        
        Assert.assertEquals(actual.get("totalItems"), 3);

    }
	
	

}
