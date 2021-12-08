package gradjanibrzogbroda.backend.contoller;

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

import gradjanibrzogbroda.backend.constants.ZaposleniConstants;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.dto.PlataDTO;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;
import gradjanibrzogbroda.backend.service.ZaposleniService;

import java.util.Optional;

import static org.testng.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ZaposleniControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ZaposleniService zaposleniService;

    @Test(priority = -1)
    public void testGetAllZaposleni() {
        ResponseEntity<ZaposleniDTO[]> responseEntity = restTemplate.getForEntity("/zaposleni/all", ZaposleniDTO[].class);

        ZaposleniDTO[] actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(Optional.of(actual.length), Optional.of(ZaposleniConstants.DB_ZAPOSLENI_COUNT));
        assertEquals(actual[1].getId(), ZaposleniConstants.DB_ZAPOSLENI_ID);
        assertEquals(actual[1].getIme(), ZaposleniConstants.DB_ZAPOSLENI_IME);
        assertEquals(actual[1].getPrezime(), ZaposleniConstants.DB_ZAPOSLENI_PREZIME);
    }

    @Test
    public void testGetZaposleniById() {
        ResponseEntity<ZaposleniDTO> responseEntity = restTemplate.getForEntity("/zaposleni/id/" + ZaposleniConstants.DB_ZAPOSLENI_ID, ZaposleniDTO.class);

        ZaposleniDTO actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(actual.getId(), ZaposleniConstants.DB_ZAPOSLENI_ID);
        assertEquals(actual.getIme(), ZaposleniConstants.DB_ZAPOSLENI_IME);
        assertEquals(actual.getPrezime(), ZaposleniConstants.DB_ZAPOSLENI_PREZIME);

    }

    @Test
    public void testUpdateZaposleni() {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "/zaposleni/update?id=" + ZaposleniConstants.UPDATED_ZAPOSLENI_ID + "&ime=" + ZaposleniConstants.UPDATED_ZAPOSLENI_IME +
                        "&prezime=" + ZaposleniConstants.UPDATED_ZAPOSLENI_PREZIME + "&pol=MUSKI&datumRodjenja=1999-11-10&trenutnaPlata=90000.0&tipZaposlenja=MENADZER&slikaString",
                "", String.class);

        Zaposleni actual = zaposleniService.findOneById(ZaposleniConstants.UPDATED_ZAPOSLENI_ID);

        assertNotNull(actual);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(actual.getId(), ZaposleniConstants.UPDATED_ZAPOSLENI_ID);
        assertEquals(actual.getIme(), ZaposleniConstants.UPDATED_ZAPOSLENI_IME);
        assertEquals(actual.getPrezime(), ZaposleniConstants.UPDATED_ZAPOSLENI_PREZIME);

    }

    @Test
    public void testAddZaposleni() {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "/zaposleni/add?id=" + ZaposleniConstants.NEW_ZAPOSLENI_ID + "&ime=" + ZaposleniConstants.NEW_ZAPOSLENI_IME + "&prezime="
                        + ZaposleniConstants.NEW_ZAPOSLENI_PREZIME + "&pol=MUSKI&datumRodjenja=1967-08-27&trenutnaPlata=46000&tipZaposlenja=KONOBAR&slikaString",
                "", String.class);

        Zaposleni actual = zaposleniService.findOneById(ZaposleniConstants.NEW_ZAPOSLENI_ID);

        assertNotNull(actual);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(actual.getId(), ZaposleniConstants.NEW_ZAPOSLENI_ID);
        assertEquals(actual.getIme(), ZaposleniConstants.NEW_ZAPOSLENI_IME);
        assertEquals(actual.getPrezime(), ZaposleniConstants.NEW_ZAPOSLENI_PREZIME);

    }

    @Test
    public void testDeleteZaposleni() {
        ResponseEntity<Object> responseEntity = restTemplate.exchange("/zaposleni/delete/" + ZaposleniConstants.DELETED_ZAPOSLENI_ID,
                HttpMethod.DELETE, new HttpEntity<Object>(null), Object.class);

        Zaposleni actual = zaposleniService.findOneById(ZaposleniConstants.DELETED_ZAPOSLENI_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNull(actual);

    }

    @Test
    public void testIzmeniPlatu() {
        ResponseEntity<ZaposleniDTO> responseEntity = restTemplate.exchange(
                "/zaposleni/izmeni-platu", HttpMethod.PUT, new HttpEntity<PlataDTO>(ZaposleniConstants.NEW_PLATA_DTO), ZaposleniDTO.class);

        Zaposleni actual = zaposleniService.findOneById(ZaposleniConstants.NEW_PLATA_DTO.getZaposleniId());

        assertNotNull(actual);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(actual.getTrenutnaPlata(), ZaposleniConstants.NEW_VISINA_PLATE);

    }
}
