package gradjanibrzogbroda.backend.contoller;

import gradjanibrzogbroda.backend.constants.PorudzbinaConstants;
import gradjanibrzogbroda.backend.domain.Porudzbina;
import gradjanibrzogbroda.backend.dto.PorudzbinaDTO;
import gradjanibrzogbroda.backend.service.PorudzbinaService;
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

import java.util.Optional;

import static org.testng.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PorudzbinaControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    PorudzbinaService porudzbinaService;

    @Autowired
    TestRestTemplate restTemplate;

    @Test(priority = -1)
    public void testGetAllPorudzbine(){
        ResponseEntity<PorudzbinaDTO[]> responseEntity = restTemplate.getForEntity("/porudzbine", PorudzbinaDTO[].class);

        PorudzbinaDTO[] actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals((Optional.of(actual.length)).get(), PorudzbinaConstants.DB_PORUDZBINA_COUNT);
        assertEquals(actual[0].getId(), PorudzbinaConstants.DB_PORUDZBINA_ID);
        assertEquals(actual[0].getNapomena(), PorudzbinaConstants.DB_PORUDZBINA_NAPOMENA);
        assertEquals(actual[0].getStoId(), PorudzbinaConstants.DB_PORUDZBINA_STO_ID);
    }

    @Test
    public void testGetPorudzbina(){
        ResponseEntity<PorudzbinaDTO> responseEntity = restTemplate.getForEntity("/porudzbine/" +
                PorudzbinaConstants.DB_PORUDZBINA_ID, PorudzbinaDTO.class);

        PorudzbinaDTO actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(actual.getId(), PorudzbinaConstants.DB_PORUDZBINA_ID);
        assertEquals(actual.getNapomena(), PorudzbinaConstants.DB_PORUDZBINA_NAPOMENA);
        assertEquals(actual.getStoId(), PorudzbinaConstants.DB_PORUDZBINA_STO_ID);
    }

    @Test(priority = -1)
    public void testGetAllPorudzbineByStatus(){
        ResponseEntity<PorudzbinaDTO[]> responseEntity = restTemplate.getForEntity("/porudzbine/status/" +
                PorudzbinaConstants.DB_STATUS_PORUDZBINE, PorudzbinaDTO[].class);

        PorudzbinaDTO[] actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(Optional.of(actual.length), Optional.of(PorudzbinaConstants.DB_PORUDZBINA_STATUS_COUNT));
        assertEquals(actual[0].getStatusPorudzbine(), PorudzbinaConstants.DB_STATUS_PORUDZBINE);
    }

    @Test
    public void testGetPorudzbineByKonobarId(){
        ResponseEntity<PorudzbinaDTO[]> responseEntity = restTemplate.getForEntity("/porudzbine/konobar/" +
                PorudzbinaConstants.DB_PORUDZBINA_KONOBAR_ID, PorudzbinaDTO[].class);

        PorudzbinaDTO[] actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(Optional.of(actual.length), Optional.of(PorudzbinaConstants.DB_PORUDZBINA_KONOBAR_COUNT));
        assertEquals(actual[0].getKonobarId(), PorudzbinaConstants.DB_PORUDZBINA_KONOBAR_ID);
        assertEquals(actual[1].getKonobarId(), PorudzbinaConstants.DB_PORUDZBINA_KONOBAR_ID);
        assertEquals(actual[2].getKonobarId(), PorudzbinaConstants.DB_PORUDZBINA_KONOBAR_ID);
    }

    @Test
    public void testNapraviPorudzbinu(){
        ResponseEntity<PorudzbinaDTO> responseEntity = restTemplate.postForEntity("/porudzbine"
                , PorudzbinaConstants.NEW_PORUDZBINA , PorudzbinaDTO.class);

        Porudzbina actual = porudzbinaService.findOne(PorudzbinaConstants.NEW_PORUDZBINA_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(actual.getId(), PorudzbinaConstants.NEW_PORUDZBINA_ID);
        assertEquals(actual.getNapomena(), PorudzbinaConstants.NEW_PORUDZBINA_NAPOMENA);
        assertEquals(actual.getSto().getId(), PorudzbinaConstants.NEW_PORUDZBINA_STO_ID);
    }

    @Test
    public void testPromeniPorudzbinu() {
        ResponseEntity<PorudzbinaDTO> responseEntity = restTemplate.exchange(
                "/porudzbine", HttpMethod.PUT,
                new HttpEntity<PorudzbinaDTO>(PorudzbinaConstants.UPDATED_PORUDZBINA), PorudzbinaDTO.class);

        Porudzbina actual = porudzbinaService.findOne(PorudzbinaConstants.UPDATED_PORUDZBINA_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(actual.getId(), PorudzbinaConstants.UPDATED_PORUDZBINA_ID);
        assertEquals(actual.getNapomena(), PorudzbinaConstants.UPDATED_PORUDZBINA_NAPOMENA);
        assertEquals(actual.getSto().getId(), PorudzbinaConstants.UPDATED_PORUDZBINA_STO_ID);
    }

    @Test(priority = 2)
    public void testObrisiPorudzbinu() {
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                "/porudzbine/" + PorudzbinaConstants.DELETED_PORUDZBINA_ID, HttpMethod.DELETE,
                new HttpEntity<Object>(null), Object.class);

        Porudzbina actual = porudzbinaService.findOne(PorudzbinaConstants.DELETED_PORUDZBINA_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNull(actual);
    }

    @Test()
    public void testNaplatiPorudzbinu() {
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                "/porudzbine/naplati/" +  PorudzbinaConstants.NAPLACENA_PORUDZBINA_ID, HttpMethod.PUT,
                new HttpEntity<Object>(null), Object.class);

        Porudzbina actual = porudzbinaService.findOne(PorudzbinaConstants.NAPLACENA_PORUDZBINA_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(actual.getId(), PorudzbinaConstants.NAPLACENA_PORUDZBINA_ID);
        assertEquals(actual.getStatusPorudzbine(), PorudzbinaConstants.NAPLACENA_PORUDZBINA_STATUS);
    }
}
