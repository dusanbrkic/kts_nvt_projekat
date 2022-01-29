package gradjanibrzogbroda.backend.contoller;

import gradjanibrzogbroda.backend.config.TestWebSecurity;
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
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static gradjanibrzogbroda.backend.constants.JeloPorudzbineConstants.*;
import static org.testng.Assert.*;

@Import(TestWebSecurity.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class JeloPorudzbineControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    JeloPorudzbineService jeloPorudzbineService;

    @Test
    public void testPripremiJeloPorudzbine() throws JeloPorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/jelo-porudzbine/pripremi/" + DB_PREUZETO_JELO_PORUDZBINE_ID,
                HttpMethod.POST,
                new HttpEntity<Object>(null), Object.class);
        JeloPorudzbine actual = jeloPorudzbineService.findOne(DB_PREUZETO_JELO_PORUDZBINE_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(actual.getStatusJela(), StatusJela.PRIPREMLJENO);
    }

    @Test
    public void testPripremiJeloPorudzbine_Not_Found() throws JeloPorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/jelo-porudzbine/pripremi/" + DB_NON_EXISTANT_ID,
                HttpMethod.POST,
                new HttpEntity<Object>(null), Object.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testPripremiJeloPorudzbine_Neodgovarajuci_Status() throws JeloPorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/jelo-porudzbine/pripremi/" + DB_NAPLACENA_PORUDZBINA_ID,
                HttpMethod.POST,
                new HttpEntity<Object>(null), Object.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testDostaviJeloPorudzbine_Not_Found() throws JeloPorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/jelo-porudzbine/dostavi/" + DB_NON_EXISTANT_ID,
                HttpMethod.POST,
                new HttpEntity<Object>(null), Object.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDostaviJeloPorudzbine_Neodgovarajuci_Status() throws JeloPorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/jelo-porudzbine/dostavi/" + DB_NAPLACENA_PORUDZBINA_ID,
                HttpMethod.POST,
                new HttpEntity<Object>(null), Object.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }


}
