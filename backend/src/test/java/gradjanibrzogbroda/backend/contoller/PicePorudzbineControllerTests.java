package gradjanibrzogbroda.backend.contoller;

import gradjanibrzogbroda.backend.config.TestWebSecurity;
import gradjanibrzogbroda.backend.constants.PicePorudzbineConstants;
import gradjanibrzogbroda.backend.domain.PicePorudzbine;
import gradjanibrzogbroda.backend.domain.StatusPica;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.exceptions.PicePorudzbineNotFoundException;
import gradjanibrzogbroda.backend.service.PicePorudzbineService;
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

import static gradjanibrzogbroda.backend.constants.PicePorudzbineConstants.*;
import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

@Import(TestWebSecurity.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PicePorudzbineControllerTests extends AbstractTestNGSpringContextTests {
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    PicePorudzbineService PicePorudzbineService;


    @Test
    public void testDostaviPicePorudzbine() throws PicePorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/pice-porudzbine/dostavi/" + DB_PRIPREMLJENO_PICE_PORUDZBINE_ID,
                HttpMethod.POST,
                new HttpEntity<Object>(null), Object.class);
        PicePorudzbine actual = PicePorudzbineService.findOne(DB_PRIPREMLJENO_PICE_PORUDZBINE_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(actual.getStatusPica(), StatusPica.DOSTAVLJENO);
    }

    @Test
    public void testDostaviPicePorudzbine_Not_Found() throws PicePorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/pice-porudzbine/dostavi/" + DB_NON_EXISTANT_ID,
                HttpMethod.POST,
                new HttpEntity<Object>(null), Object.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDostaviPicePorudzbine_Neodgovarajuci_Status() throws PicePorudzbineNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/pice-porudzbine/dostavi/" + DB_PICE_PORUDZBINE_ID_1,
                HttpMethod.POST,
                new HttpEntity<Object>(null), Object.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
