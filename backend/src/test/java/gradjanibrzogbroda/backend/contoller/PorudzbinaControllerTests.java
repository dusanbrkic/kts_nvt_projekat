package gradjanibrzogbroda.backend.contoller;

import gradjanibrzogbroda.backend.config.TestWebSecurity;
import gradjanibrzogbroda.backend.constants.PorudzbinaConstants;
import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.*;
import gradjanibrzogbroda.backend.exceptions.PorudzbinaNotFoundException;
import gradjanibrzogbroda.backend.repository.JeloRepository;
import gradjanibrzogbroda.backend.repository.PiceRepository;
import gradjanibrzogbroda.backend.repository.PorudzbinaRepository;
import gradjanibrzogbroda.backend.repository.StoRepository;
import gradjanibrzogbroda.backend.service.PorudzbinaService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static gradjanibrzogbroda.backend.constants.PorudzbinaConstants.*;
import static org.testng.Assert.*;

@Import(TestWebSecurity.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PorudzbinaControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    PorudzbinaService porudzbinaService;

    @Autowired
    StoRepository stoRepository;

    @Autowired
    PiceRepository piceRepository;

    @Autowired
    JeloRepository jeloRepository;

    @Autowired
    PorudzbinaRepository porudzbinaRepository;

    @Autowired
    TestRestTemplate restTemplate;

    @Test(priority = -1)
    public void testGetAllPorudzbine(){
        ResponseEntity<PorudzbinaDTO[]> responseEntity = restTemplate.getForEntity("/porudzbine", PorudzbinaDTO[].class);

        PorudzbinaDTO[] actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals((Optional.of(actual.length)).get(), PorudzbinaConstants.DB_PORUDZBINA_COUNT);
        assertEquals(actual[0].getId(), Integer.valueOf(1));
        assertEquals(actual[0].getNapomena(), "Posluziti hranu dok je topla.");
        assertEquals(actual[0].getStoId(), "12345112");
    }

    @Test(priority = -1)
    public void testGetZaSankera(){
        ResponseEntity<PorudzbinaDTO[]> responseEntity = restTemplate.getForEntity("/porudzbine/zaSankera", PorudzbinaDTO[].class);

        PorudzbinaDTO[] actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals((Optional.of(actual.length)).get(), Integer.valueOf(4));
        assertEquals(actual[0].getId(), Integer.valueOf(7));
        assertEquals(actual[0].getNapomena(), "Gosti zure, posluziti brzo.");
        assertEquals(actual[0].getStoId(), "32345112");
    }

    @Test(priority = -1)
    public void testGetZaKuvara(){
        ResponseEntity<PorudzbinaDTO[]> responseEntity = restTemplate.getForEntity("/porudzbine/zaKuvara", PorudzbinaDTO[].class);

        PorudzbinaDTO[] actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals((Optional.of(actual.length)).get(), Integer.valueOf(3));
        assertEquals(actual[0].getId(), Integer.valueOf(4));
        assertEquals(actual[0].getNapomena(), "Gosti zure, posluziti brzo.");
        assertEquals(actual[0].getStoId(), "22345112");
    }
//
    @Test
    public void testGetPorudzbina(){
        ResponseEntity<PorudzbinaDTO> responseEntity = restTemplate.getForEntity("/porudzbine/" +
                PorudzbinaConstants.DB_KREIRANA_PORUDZBINA_ID_1, PorudzbinaDTO.class);

        PorudzbinaDTO actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(actual.getId(), PorudzbinaConstants.DB_KREIRANA_PORUDZBINA_ID_1);
        assertEquals(actual.getNapomena(), "Gosti zure, posluziti brzo.");
        assertEquals(actual.getStoId(), "12345112");
    }

    @Test(priority = -1)
    public void testGetAllPorudzbineByStatus(){
        ResponseEntity<PorudzbinaDTO[]> responseEntity = restTemplate.getForEntity("/porudzbine/status/" +
                StatusPorudzbine.DOSTAVLJENO, PorudzbinaDTO[].class);

        PorudzbinaDTO[] actual = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(Optional.of(actual.length), Optional.of(1));
        assertEquals(actual[0].getStatusPorudzbine(), StatusPorudzbine.DOSTAVLJENO);
    }


    @Test(priority = 5)
    public void testNapraviPorudzbinu(){

        Pice pice = piceRepository.findOneById(DB_PICE_ID);
        Jelo jelo = jeloRepository.findOneById(DB_JELO_ID);
        Sto sto = stoRepository.findOneById(10);

        Porudzbina porudzbina1 = new Porudzbina();
        porudzbina1.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        porudzbina1.setUkupnaCena(0.0);
        porudzbina1.setId(DB_NOVA_PORUDZBINA_ID);
        porudzbina1.setNapomena("Nova napomena");
        porudzbina1.setSto(sto);
        porudzbina1.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());
        porudzbina1.setPicePorudzbine(new ArrayList<PicePorudzbine>());
        porudzbina1.setDatumVreme(LocalDateTime.now());

        JeloPorudzbine jp1 = JP1;
        jp1.setJelo(jelo);
        jp1.setKolicina(1.0);
        jp1.setPorudzbina(porudzbina1);

        PicePorudzbine pp1 = PP1;
        pp1.setPice(pice);
        pp1.setKolicina(1.0);
        pp1.setPorudzbina(porudzbina1);

        porudzbina1.getJelaPorudzbine().add(jp1);
        porudzbina1.getPicePorudzbine().add(pp1);

        PorudzbinaDTO porudzbinaDTO = new PorudzbinaDTO(porudzbina1);

        ResponseEntity<PorudzbinaDTO> responseEntity = restTemplate.postForEntity("/porudzbine"
                , porudzbinaDTO , PorudzbinaDTO.class);

        Porudzbina actual = porudzbinaService.findOne(DB_PORUDZBINA_COUNT+1);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(actual.getId(), Integer.valueOf(DB_PORUDZBINA_COUNT+1));
        assertEquals(actual.getNapomena(), "Nova napomena");
        assertEquals(actual.getSto().getId(), Integer.valueOf(10));
    }

    @Test()
    public void testSpremiPica(){
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("/porudzbine/spremiPica/" +
                DB_KREIRANA_PORUDZBINA_ID_2, Object.class);

        Porudzbina actual = porudzbinaRepository.findOneById(DB_KREIRANA_PORUDZBINA_ID_2);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(actual.getPicePorudzbine().get(0).getStatusPica(), StatusPica.PRIPREMLJENO);
        assertEquals(actual.getStatusPorudzbine(), StatusPorudzbine.KREIRANO);
    }

    @Test()
    public void testSpremiPica_Porudzbina_Not_Found(){
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("/porudzbine/spremiPica/" +
                DB_NON_EXISTANT_ID, Object.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test(priority = 1)
    public void testPreuzmiPorudzbinu(){
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("/porudzbine/preuzmiPorudzbinu/" +
                DB_KREIRANA_PORUDZBINA_ID_2, null, Object.class);

        Porudzbina actual = porudzbinaRepository.findOneById(DB_KREIRANA_PORUDZBINA_ID_2);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(actual.getJelaPorudzbine().get(0).getStatusJela(), StatusJela.PREUZETO);
        assertEquals(actual.getStatusPorudzbine(), StatusPorudzbine.PREUZETO);
    }

    @Test()
    public void testPreuzmiPorudzbinu_Porudzbina_Not_Found(){
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("/porudzbine/preuzmiPorudzbinu/" +
                DB_NON_EXISTANT_ID, null, Object.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }



    @Test
    public void testIzmeniPorudzbinu() {

        Pice pice = piceRepository.findOneById(DB_PICE_ID);
        Jelo jelo = jeloRepository.findOneById(DB_JELO_ID);

        Porudzbina porudzbina1 = porudzbinaRepository.findOneById(DB_KREIRANA_PORUDZBINA_ID_1);
        porudzbina1.setNapomena("Nova napomena.");

        PorudzbinaDTO porudzbinaDTO = new PorudzbinaDTO(porudzbina1);

        JeloPorudzbineDTO jeloPorudzbineDTO = DB_NOVO_JELO_PORUDZBINE_DTO;
        jeloPorudzbineDTO.setJelo(new JeloDTO(jelo));
        jeloPorudzbineDTO.setPorudzbinaId(DB_KREIRANA_PORUDZBINA_ID_1);

        PicePorudzbineDTO picePorudzbineDTO = DB_NOVO_PICE_PORUDZBINE_DTO;
        picePorudzbineDTO.setPice(new PiceDTO(pice));
        picePorudzbineDTO.setPorudzbinaId(DB_KREIRANA_PORUDZBINA_ID_1);

        porudzbinaDTO.getPicaPorudzbine().add(picePorudzbineDTO);
        porudzbinaDTO.getJelaPorudzbine().add(jeloPorudzbineDTO);

        ResponseEntity<PorudzbinaDTO> responseEntity = restTemplate.exchange(
                "/porudzbine/update", HttpMethod.POST,
                new HttpEntity<PorudzbinaDTO>(porudzbinaDTO), PorudzbinaDTO.class);

        Porudzbina actual = porudzbinaService.findOne(DB_KREIRANA_PORUDZBINA_ID_1);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        Assert.assertEquals(actual.getJelaPorudzbine().size(), 2);
        Assert.assertEquals(actual.getJelaPorudzbine().get(1).getId(), DB_NOVO_JELO_PORUDZBINE_ID);
        Assert.assertEquals(actual.getPicePorudzbine().size(), 2);
        Assert.assertEquals(actual.getPicePorudzbine().get(1).getId(), DB_NOVO_PICE_PORUDZBINE_ID);
        Assert.assertEquals(actual.getStatusPorudzbine(), StatusPorudzbine.KREIRANO);

        Assert.assertEquals(actual.getNapomena(), "Nova napomena.");
    }

    @Test
    public void testIzmeniPorudzbinu_Porudzbina_Not_Found() {
        Sto sto = stoRepository.findOneById(10);
        Porudzbina porudzbina1 = new Porudzbina();
        porudzbina1.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        porudzbina1.setUkupnaCena(DB_KREIRANA_PORUDZBINA_CENA);
        porudzbina1.setId(DB_NON_EXISTANT_ID);
        porudzbina1.setNapomena("Nova napomena");
        porudzbina1.setSto(sto);
        porudzbina1.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());
        porudzbina1.setPicePorudzbine(new ArrayList<PicePorudzbine>());
        porudzbina1.setDatumVreme(LocalDateTime.now());

        PorudzbinaDTO porudzbinaDTO = new PorudzbinaDTO(porudzbina1);

        ResponseEntity<PorudzbinaDTO> responseEntity = restTemplate.exchange(
                "/porudzbine/update", HttpMethod.POST,
                new HttpEntity<PorudzbinaDTO>(porudzbinaDTO), PorudzbinaDTO.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testIzmeniPorudzbinu_Porudzbina_Naplacena() {
        Sto sto = stoRepository.findOneById(10);
        Porudzbina porudzbina1 = porudzbinaRepository.findOneById(DB_NAPLACENA_PORUDZBINA_ID);
        PorudzbinaDTO porudzbinaDTO = new PorudzbinaDTO(porudzbina1);

        ResponseEntity<PorudzbinaDTO> responseEntity = restTemplate.exchange(
                "/porudzbine/update", HttpMethod.POST,
                new HttpEntity<PorudzbinaDTO>(porudzbinaDTO), PorudzbinaDTO.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }



    @Test()
    public void testNaplatiPorudzbinu() {
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                "/porudzbine/naplati/" +  DB_DOSTAVLJENA_PORUDZBINA_ID, HttpMethod.POST,
                new HttpEntity<Object>(null), Object.class);

        Porudzbina actual = porudzbinaService.findOne(DB_DOSTAVLJENA_PORUDZBINA_ID);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actual);
        assertEquals(actual.getId(), DB_DOSTAVLJENA_PORUDZBINA_ID);
        assertEquals(actual.getStatusPorudzbine(), StatusPorudzbine.NAPLACENO);
    }

    @Test()
    public void testNaplatiPorudzbinu_Porudzbina_Not_Found() {
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                "/porudzbine/naplati/" +  DB_NON_EXISTANT_ID, HttpMethod.POST,
                new HttpEntity<Object>(null), Object.class);


        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
