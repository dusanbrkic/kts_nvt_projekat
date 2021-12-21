package gradjanibrzogbroda.backend.contoller;

import gradjanibrzogbroda.backend.constants.JeloPorudzbineConstants;
import gradjanibrzogbroda.backend.constants.PorudzbinaConstants;
import gradjanibrzogbroda.backend.domain.JeloPorudzbine;
import gradjanibrzogbroda.backend.domain.StatusJela;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PorudzbinaDTO;
import gradjanibrzogbroda.backend.exceptions.JeloPorudzbineNotFoundException;
import gradjanibrzogbroda.backend.service.JeloPorudzbineService;
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

import static org.testng.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class JeloPorudzbineControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    JeloPorudzbineService jeloPorudzbineService;

    @Test
    public void testDodajJeloPorudzbine(){
        ResponseEntity<JeloPorudzbineDTO> responseEntity = restTemplate.postForEntity("/jelo-porudzbine",
                JeloPorudzbineConstants.NEW_JELO_PORUDZBINE, JeloPorudzbineDTO.class);
        JeloPorudzbineDTO actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);

        assertEquals(actual.getId(), JeloPorudzbineConstants.NEW_JELO_PORUDZBINE_ID);
        assertEquals(actual.getNapomena(), JeloPorudzbineConstants.NEW_JELO_PORUDZBINE_NAPOMENA);
        assertEquals(actual.getKolicina(), JeloPorudzbineConstants.NEW_JELO_PORUDZBINE_KOLICINA);
        assertEquals(actual.getJeloId(), JeloPorudzbineConstants.NEW_JELO_PORUDZBINE_JELO);

    }

    @Test
    public void testIzmeniJeloPorudzbine(){
        ResponseEntity<JeloPorudzbineDTO> responseEntity = restTemplate.exchange("/jelo-porudzbine",
                HttpMethod.PUT,
                new HttpEntity<JeloPorudzbineDTO>(JeloPorudzbineConstants.UPDATED_JELO_PORUDZBINE), JeloPorudzbineDTO.class);
        JeloPorudzbineDTO actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);

        assertEquals(actual.getId(), JeloPorudzbineConstants.UPDATED_JELO_PORUDZBINE_ID);
        assertEquals(actual.getNapomena(), JeloPorudzbineConstants.UPDATED_JELO_PORUDZBINE_NAPOMENA);
        assertEquals(actual.getKolicina(), JeloPorudzbineConstants.UPDATED_JELO_PORUDZBINE_KOLICINA);

    }

    @Test(priority = 1, expectedExceptions = {JeloPorudzbineNotFoundException.class})
    public void testObrisiJeloPorudzbine() throws JeloPorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/jelo-porudzbine/" + JeloPorudzbineConstants.DELETED_JELO_PORUDZBINE_ID,
                HttpMethod.DELETE,
                new HttpEntity<Object>(null), Object.class);
        JeloPorudzbine actual = jeloPorudzbineService.findOne(JeloPorudzbineConstants.DELETED_JELO_PORUDZBINE_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNull(actual);
    }

    @Test
    public void testPreuzmiJeloPorudzbine() throws JeloPorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/jelo-porudzbine/preuzmi/" + JeloPorudzbineConstants.KREIRANO_JELO_PORUDZBINE_ID,
                HttpMethod.PUT,
                new HttpEntity<Object>(null), Object.class);
        JeloPorudzbine actual = jeloPorudzbineService.findOne(JeloPorudzbineConstants.KREIRANO_JELO_PORUDZBINE_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(actual.getStatusJela(), StatusJela.PREUZETO);
    }

    @Test
    public void testPripremiJeloPorudzbine() throws JeloPorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/jelo-porudzbine/pripremi/" + JeloPorudzbineConstants.PREUZETO_JELO_PORUDZBINE_ID,
                HttpMethod.PUT,
                new HttpEntity<Object>(null), Object.class);
        JeloPorudzbine actual = jeloPorudzbineService.findOne(JeloPorudzbineConstants.PREUZETO_JELO_PORUDZBINE_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(actual.getStatusJela(), StatusJela.PRIPREMLJENO);
    }

    @Test
    public void testDostaviJeloPorudzbine() throws JeloPorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/jelo-porudzbine/dostavi/" + JeloPorudzbineConstants.PRIPREMLJENO_JELO_PORUDZBINE_ID,
                HttpMethod.PUT,
                new HttpEntity<Object>(null), Object.class);
        JeloPorudzbine actual = jeloPorudzbineService.findOne(JeloPorudzbineConstants.PRIPREMLJENO_JELO_PORUDZBINE_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(actual.getStatusJela(), StatusJela.DOSTAVLJENO);
    }


}
