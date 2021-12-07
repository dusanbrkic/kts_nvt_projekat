package gradjanibrzogbroda.backend.contoller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.constants.ZaposleniConstants;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.dto.PlataDTO;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;
import gradjanibrzogbroda.backend.service.ZaposleniService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ZaposleniControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
	private TestRestTemplate restTemplate;

    @Autowired
    private ZaposleniService zaposleniService;
    
    @Test(priority = -1)
    public void testGetAllZaposleni(){
        ResponseEntity<ZaposleniDTO[]> responseEntity = restTemplate.getForEntity("/zaposleni/all", ZaposleniDTO[].class);

        ZaposleniDTO[] actual = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ZaposleniConstants.DB_ZAPOSLENI_COUNT, actual.length);
        assertEquals(ZaposleniConstants.DB_ZAPOSLENI_ID, actual[1].getId());
        assertEquals(ZaposleniConstants.DB_ZAPOSLENI_IME, actual[1].getIme());
        assertEquals(ZaposleniConstants.DB_ZAPOSLENI_PREZIME, actual[1].getPrezime());
        
    }

    @Test
    public void testGetZaposleniById(){
        ResponseEntity<ZaposleniDTO> responseEntity = restTemplate.getForEntity("/zaposleni/id/" + ZaposleniConstants.DB_ZAPOSLENI_ID, ZaposleniDTO.class);

        ZaposleniDTO actual = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ZaposleniConstants.DB_ZAPOSLENI_ID, actual.getId());
        assertEquals(ZaposleniConstants.DB_ZAPOSLENI_IME, actual.getIme());
        assertEquals(ZaposleniConstants.DB_ZAPOSLENI_PREZIME, actual.getPrezime());
        
    }

    @Test
    public void testUpdateZaposleni(){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
            "/zaposleni/update?id=" + ZaposleniConstants.UPDATED_ZAPOSLENI_ID + "&ime=" + ZaposleniConstants.UPDATED_ZAPOSLENI_IME + 
            "&prezime=" + ZaposleniConstants.UPDATED_ZAPOSLENI_PREZIME + "&pol=MUSKI&datumRodjenja=1999-11-10&trenutnaPlata=90000.0&tipZaposlenja=MENADZER&slikaString",
             "", String.class);

        Zaposleni actual = zaposleniService.findOneById(ZaposleniConstants.UPDATED_ZAPOSLENI_ID);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ZaposleniConstants.UPDATED_ZAPOSLENI_ID, actual.getId());
        assertEquals(ZaposleniConstants.UPDATED_ZAPOSLENI_IME, actual.getIme());
        assertEquals(ZaposleniConstants.UPDATED_ZAPOSLENI_PREZIME, actual.getPrezime());
        
    }

    @Test
    public void testAddZaposleni(){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
            "/zaposleni/add?id=" + ZaposleniConstants.NEW_ZAPOSLENI_ID + "&ime=" + ZaposleniConstants.NEW_ZAPOSLENI_IME + "&prezime="
             + ZaposleniConstants.NEW_ZAPOSLENI_PREZIME + "&pol=MUSKI&datumRodjenja=1967-08-27&trenutnaPlata=46000&tipZaposlenja=KONOBAR&slikaString",
             "", String.class);

        Zaposleni actual = zaposleniService.findOneById(ZaposleniConstants.NEW_ZAPOSLENI_ID);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ZaposleniConstants.NEW_ZAPOSLENI_ID, actual.getId());
        assertEquals(ZaposleniConstants.NEW_ZAPOSLENI_IME, actual.getIme());
        assertEquals(ZaposleniConstants.NEW_ZAPOSLENI_PREZIME, actual.getPrezime());
        
    }

    @Test
    public void testDeleteZaposleni(){
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/zaposleni/delete/" + ZaposleniConstants.DELETED_ZAPOSLENI_ID, 
        HttpMethod.DELETE, new HttpEntity<Object>(null), Object.class);

        Zaposleni actual = zaposleniService.findOneById(ZaposleniConstants.DELETED_ZAPOSLENI_ID);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(null, actual);
        
    }

    @Test
    public void testIzmeniPlatu(){
        ResponseEntity<ZaposleniDTO> responseEntity = restTemplate.exchange(
            "/zaposleni/izmeni-platu", HttpMethod.PUT, new HttpEntity<PlataDTO>(ZaposleniConstants.NEW_PLATA_DTO), ZaposleniDTO.class);

        Zaposleni actual = zaposleniService.findOneById(ZaposleniConstants.NEW_PLATA_DTO.getZaposleniId());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ZaposleniConstants.NEW_VISINA_PLATE, actual.getTrenutnaPlata());
        
    }
}
