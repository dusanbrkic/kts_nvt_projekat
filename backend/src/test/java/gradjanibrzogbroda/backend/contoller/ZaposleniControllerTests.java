package gradjanibrzogbroda.backend.contoller;

import gradjanibrzogbroda.backend.exceptions.UserNotFoundException;
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
        ResponseEntity<ZaposleniDTO[]> responseEntity = restTemplate.getForEntity("/zaposleni/all",
                ZaposleniDTO[].class);

        ZaposleniDTO[] actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(Optional.of(actual.length), Optional.of(ZaposleniConstants.DB_ZAPOSLENI_COUNT));
        assertEquals(actual[1].getIdentificationNumber(), ZaposleniConstants.DB_ZAPOSLENI_IDENTIFICATION_NUMBER);
        assertEquals(actual[1].getIme(), ZaposleniConstants.DB_ZAPOSLENI_IME);
        assertEquals(actual[1].getPrezime(), ZaposleniConstants.DB_ZAPOSLENI_PREZIME);
    }

    @Test
    public void testGetZaposleniById() {
        ResponseEntity<ZaposleniDTO> responseEntity = restTemplate.getForEntity(
                "/zaposleni/id/" + ZaposleniConstants.DB_ZAPOSLENI_IDENTIFICATION_NUMBER, ZaposleniDTO.class);

        ZaposleniDTO actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(actual.getIdentificationNumber(), ZaposleniConstants.DB_ZAPOSLENI_IDENTIFICATION_NUMBER);
        assertEquals(actual.getIme(), ZaposleniConstants.DB_ZAPOSLENI_IME);
        assertEquals(actual.getPrezime(), ZaposleniConstants.DB_ZAPOSLENI_PREZIME);

    }

    @Test
    public void testGetZaposleniByFakeId() {
        ResponseEntity<ZaposleniDTO> responseEntity = restTemplate
                .getForEntity("/zaposleni/id/" + ZaposleniConstants.FAKE_IDENTIFICATION_NUMBER, ZaposleniDTO.class);

        ZaposleniDTO actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
        assertNull(actual);
    }

    @Test
    public void testUpdateZaposleni() throws UserNotFoundException {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "/zaposleni/update?identificationNumber=" + ZaposleniConstants.UPDATED_ZAPOSLENI_IDENTIFICATION_NUMBER
                        + "&ime=" + ZaposleniConstants.UPDATED_ZAPOSLENI_IME +
                        "&prezime=" + ZaposleniConstants.UPDATED_ZAPOSLENI_PREZIME
                        + "&pol=MUSKI&datumRodjenja=1999-11-10&trenutnaPlata=90000.0&tipZaposlenja=MENADZER&slikaString",
                "", String.class);

//        Zaposleni actual = zaposleniService
//                .findOneByIdentificationNumber(ZaposleniConstants.UPDATED_ZAPOSLENI_IDENTIFICATION_NUMBER);

//        assertNotNull(actual);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//        assertEquals(actual.getId(), ZaposleniConstants.UPDATED_ZAPOSLENI_ID);
//        assertEquals(actual.getIme(), ZaposleniConstants.UPDATED_ZAPOSLENI_IME);
//        assertEquals(actual.getPrezime(), ZaposleniConstants.UPDATED_ZAPOSLENI_PREZIME);

    }

    @Test
    public void testUpdateZaposleniDoesntExist() throws UserNotFoundException {
        ResponseEntity<ZaposleniDTO> responseEntity = restTemplate.postForEntity(
                "/zaposleni/update?identificationNumber=" + ZaposleniConstants.FAKE_IDENTIFICATION_NUMBER + "&ime="
                        + ZaposleniConstants.UPDATED_ZAPOSLENI_IME +
                        "&prezime=" + ZaposleniConstants.UPDATED_ZAPOSLENI_PREZIME
                        + "&pol=MUSKI&datumRodjenja=1999-11-10&trenutnaPlata=90000.0&tipZaposlenja=MENADZER&slikaString",
                "", ZaposleniDTO.class);

        ZaposleniDTO actual = responseEntity.getBody();

        assertNull(actual);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testAddZaposleni() throws UserNotFoundException {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "/zaposleni/add?identificationNumber=" + ZaposleniConstants.NEW_ZAPOSLENI_IDENTIFICATION_NUMBER
                        + "&ime=" + ZaposleniConstants.NEW_ZAPOSLENI_IME + "&prezime="
                        + ZaposleniConstants.NEW_ZAPOSLENI_PREZIME
                        + "&pol=MUSKI&datumRodjenja=1967-08-27&trenutnaPlata=46000&tipZaposlenja=KONOBAR&slikaString",
                "", String.class);

//        Zaposleni actual = zaposleniService
//                .findOneByIdentificationNumber(ZaposleniConstants.NEW_ZAPOSLENI_IDENTIFICATION_NUMBER);

//        assertNotNull(actual);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//        assertEquals(actual.getId(), ZaposleniConstants.NEW_ZAPOSLENI_ID);
//        assertEquals(actual.getIme(), ZaposleniConstants.NEW_ZAPOSLENI_IME);
//        assertEquals(actual.getPrezime(), ZaposleniConstants.NEW_ZAPOSLENI_PREZIME);

    }

    @Test
    public void testAddZaposleniAlreadyExists() throws UserNotFoundException {
        ResponseEntity<ZaposleniDTO> responseEntity = restTemplate.postForEntity(
                "/zaposleni/add?identificationNumber=" + ZaposleniConstants.DB_ZAPOSLENI_IDENTIFICATION_NUMBER + "&ime="
                        + ZaposleniConstants.NEW_ZAPOSLENI_IME + "&prezime="
                        + ZaposleniConstants.NEW_ZAPOSLENI_PREZIME
                        + "&pol=MUSKI&datumRodjenja=1967-08-27&trenutnaPlata=46000&tipZaposlenja=KONOBAR&slikaString",
                "", ZaposleniDTO.class);

        ZaposleniDTO actual = responseEntity.getBody();

        assertNull(actual);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test(priority = 3, expectedExceptions = { UserNotFoundException.class })
    public void testDeleteZaposleni() throws UserNotFoundException {
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                "/zaposleni/delete/" + ZaposleniConstants.DELETED_ZAPOSLENI_IDENTIFICATION_NUMBER,
                HttpMethod.DELETE, new HttpEntity<Object>(null), Object.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        zaposleniService.findOneByIdentificationNumber(ZaposleniConstants.DELETED_ZAPOSLENI_IDENTIFICATION_NUMBER);

    }

    @Test
    public void testIzmeniPlatu() throws UserNotFoundException {
        ResponseEntity<ZaposleniDTO> responseEntity = restTemplate.exchange(
                "/zaposleni/izmeni-platu", HttpMethod.PUT, new HttpEntity<PlataDTO>(ZaposleniConstants.NEW_PLATA_DTO),
                ZaposleniDTO.class);

        Zaposleni actual = zaposleniService.findOneById(ZaposleniConstants.NEW_PLATA_DTO.getZaposleniId());

        assertNotNull(actual);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(actual.getTrenutnaPlata(), ZaposleniConstants.NEW_VISINA_PLATE);

    }
}
