package gradjanibrzogbroda.backend.contoller;

import gradjanibrzogbroda.backend.constants.PicePorudzbineConstants;
import gradjanibrzogbroda.backend.domain.PicePorudzbine;
import gradjanibrzogbroda.backend.domain.StatusPica;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.exceptions.PicePorudzbineNotFoundException;
import gradjanibrzogbroda.backend.service.PicePorudzbineService;
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
import static org.testng.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PicePorudzbineControllerTests extends AbstractTestNGSpringContextTests {
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    PicePorudzbineService PicePorudzbineService;

    @Test
    public void testDodajPicePorudzbine(){
        ResponseEntity<PicePorudzbineDTO> responseEntity = restTemplate.postForEntity("/pice-porudzbine",
                PicePorudzbineConstants.NEW_PICE_PORUDZBINE, PicePorudzbineDTO.class);
        PicePorudzbineDTO actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);

        assertEquals(actual.getId(), PicePorudzbineConstants.NEW_PICE_PORUDZBINE_ID);
        assertEquals(actual.getNapomena(), PicePorudzbineConstants.NEW_PICE_PORUDZBINE_NAPOMENA);
        assertEquals(actual.getKolicina(), PicePorudzbineConstants.NEW_PICE_PORUDZBINE_KOLICINA);
//        assertEquals(actual.getPiceId(), PicePorudzbineConstants.NEW_PICE_PORUDZBINE_PICE);

    }

    @Test
    public void testIzmeniPicePorudzbine(){
        ResponseEntity<PicePorudzbineDTO> responseEntity = restTemplate.exchange("/pice-porudzbine",
                HttpMethod.PUT,
                new HttpEntity<PicePorudzbineDTO>(PicePorudzbineConstants.UPDATED_PICE_PORUDZBINE), PicePorudzbineDTO.class);
        PicePorudzbineDTO actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);

        assertEquals(actual.getId(), PicePorudzbineConstants.UPDATED_PICE_PORUDZBINE_ID);
        assertEquals(actual.getNapomena(), PicePorudzbineConstants.UPDATED_PICE_PORUDZBINE_NAPOMENA);
        assertEquals(actual.getKolicina(), PicePorudzbineConstants.UPDATED_PICE_PORUDZBINE_KOLICINA);

    }

    @Test(priority = 1)
    public void testObrisiPicePorudzbine() throws PicePorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/pice-porudzbine/" + PicePorudzbineConstants.DELETED_PICE_PORUDZBINE_ID,
                HttpMethod.DELETE,
                new HttpEntity<Object>(null), Object.class);
        PicePorudzbine actual = PicePorudzbineService.findOne(PicePorudzbineConstants.DELETED_PICE_PORUDZBINE_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNull(actual);
    }


    @Test
    public void testPripremiPicePorudzbine() throws PicePorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/pice-porudzbine/pripremi/" + PicePorudzbineConstants.KREIRANO_PICE_PORUDZBINE_ID,
                HttpMethod.PUT,
                new HttpEntity<Object>(null), Object.class);
        PicePorudzbine actual = PicePorudzbineService.findOne(PicePorudzbineConstants.KREIRANO_PICE_PORUDZBINE_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(actual.getStatusPica(), StatusPica.PRIPREMLJENO);
    }

    @Test
    public void testDostaviPicePorudzbine() throws PicePorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/pice-porudzbine/dostavi/" + PicePorudzbineConstants.PRIPREMLJENO_PICE_PORUDZBINE_ID,
                HttpMethod.PUT,
                new HttpEntity<Object>(null), Object.class);
        PicePorudzbine actual = PicePorudzbineService.findOne(PicePorudzbineConstants.PRIPREMLJENO_PICE_PORUDZBINE_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(actual.getStatusPica(), StatusPica.DOSTAVLJENO);
    }
}
