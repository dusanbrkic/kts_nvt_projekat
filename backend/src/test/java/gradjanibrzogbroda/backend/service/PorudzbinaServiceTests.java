package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.*;
import gradjanibrzogbroda.backend.exceptions.*;
import gradjanibrzogbroda.backend.repository.JeloRepository;
import gradjanibrzogbroda.backend.repository.PiceRepository;
import gradjanibrzogbroda.backend.repository.PorudzbinaRepository;
import gradjanibrzogbroda.backend.repository.StoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static gradjanibrzogbroda.backend.constants.PorudzbinaConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PorudzbinaServiceTests extends AbstractTestNGSpringContextTests {
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

    @Test
    public void testFindAll(){
        ArrayList<Porudzbina> rezultat = (ArrayList<Porudzbina>) porudzbinaService.findAll();

        Assert.assertEquals(rezultat.size(), 12);

        Assert.assertEquals(rezultat.get(0).getId(), Integer.valueOf(1));
        Assert.assertEquals(rezultat.get(0).getUkupnaCena(), 730.0);
        Assert.assertEquals(rezultat.get(0).getStatusPorudzbine(), StatusPorudzbine.NAPLACENO);

    }

    @Test(priority = -2)
    public void testFindAllZaSankera(){
        ArrayList<Porudzbina> rezultat = (ArrayList<Porudzbina>) porudzbinaService.findAllZaSankera();

        Assert.assertEquals(rezultat.size(), 4);

        Assert.assertEquals(rezultat.get(0).getId(), Integer.valueOf(7));
        Assert.assertEquals(rezultat.get(0).getUkupnaCena(), 375.0);
        Assert.assertEquals(rezultat.get(0).getStatusPorudzbine(), StatusPorudzbine.KREIRANO);
    }

    @Test(priority = -1)
    public void testFindAllZaKuvara(){
        ArrayList<Porudzbina> rezultat = (ArrayList<Porudzbina>) porudzbinaService.findAllZaKuvara();

        Assert.assertEquals(rezultat.size(), 3);

        Assert.assertEquals(rezultat.get(0).getId(), Integer.valueOf(4));
        Assert.assertEquals(rezultat.get(0).getUkupnaCena(), 660.0);
        Assert.assertEquals(rezultat.get(0).getStatusPorudzbine(), StatusPorudzbine.KREIRANO);
    }
    @Test
    public void testFindOne(){
        Porudzbina rezultat = porudzbinaService.findOne(DB_KREIRANA_PORUDZBINA_ID_1);

        Assert.assertEquals(rezultat.getId(), DB_KREIRANA_PORUDZBINA_ID_1);
        Assert.assertEquals(rezultat.getUkupnaCena(), 2650.0);
        Assert.assertEquals(rezultat.getStatusPorudzbine(), StatusPorudzbine.KREIRANO);

    }

    @Test(priority = -1)
    public void testFindAllByStatusPorudzbine(){
        ArrayList<Porudzbina> rezultat = (ArrayList<Porudzbina>) porudzbinaService.findAllByStatusPorudzbine(StatusPorudzbine.DOSTAVLJENO);

        Assert.assertEquals(rezultat.size(), 1);

        Assert.assertEquals(rezultat.get(0).getId(), DB_DOSTAVLJENA_PORUDZBINA_ID);
        Assert.assertEquals(rezultat.get(0).getUkupnaCena(), 440.0);
        Assert.assertEquals(rezultat.get(0).getStatusPorudzbine(), StatusPorudzbine.DOSTAVLJENO);
    }

    @Test
    public void testSpremiPica() throws PorudzbinaNotFoundException {
        Porudzbina rezultat = porudzbinaService.spremiPica(DB_KREIRANA_PORUDZBINA_ID_2);

        Assert.assertEquals(rezultat.getPicePorudzbine().get(0).getStatusPica(), StatusPica.PRIPREMLJENO);
        Assert.assertEquals(rezultat.getStatusPorudzbine(), StatusPorudzbine.KREIRANO);
    }

    @Test(expectedExceptions = PorudzbinaNotFoundException.class)
    public void testSpremiPica_Porudzbina_Not_Found() throws PorudzbinaNotFoundException {
        Porudzbina rezultat = porudzbinaService.spremiPica(DB_NON_EXISTANT_ID);

    }

    @Test(priority = 1)
    public void testPreuzmiPorudzbinu() throws PorudzbinaNotFoundException, NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        Porudzbina rezultat = porudzbinaService.preuzmiPorudzbinu(DB_KREIRANA_PORUDZBINA_ID_2);

        Assert.assertEquals(rezultat.getStatusPorudzbine(), StatusPorudzbine.PREUZETO);
    }

    @Test(priority = 1, expectedExceptions = PorudzbinaNotFoundException.class)
    public void testPreuzmiPorudzbinu_Porudzbina_Not_Found() throws PorudzbinaNotFoundException, NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        Porudzbina rezultat = porudzbinaService.preuzmiPorudzbinu(DB_NON_EXISTANT_ID);

    }

    @Test(priority = 1)
    public void testIzmeniPorudzbinu() throws PorudzbinaNotFoundException, NeodgovarajuciStatusException, JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, PorudzbinaNaplacenaException, PiceNotFoundException, JeloNotFoundException {
        Jelo jelo = new Jelo();
        jelo.setId(DB_JELO_ID);
        jelo.setTrenutnaCena(DB_JELO_CENA);
        jelo.setNaziv(DB_JELO_NAZIV);
        jelo.setTipJela(DB_JELO_TIP);
        jelo.setKategorijaJela(DB_JELO_KAT);

        Pice pice = new Pice();
        pice.setId(DB_PICE_ID);
        pice.setTrenutnaCena(DB_PICE_CENA);
        pice.setNaziv(DB_PICE_NAZIV);

        Sto sto = stoRepository.findOneById(10);

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

        Porudzbina rezultat = porudzbinaService.izmeniPorudzbinu(porudzbinaDTO);

        Assert.assertNotNull(rezultat);
        Assert.assertEquals(rezultat.getJelaPorudzbine().size(), 2);
        Assert.assertEquals(rezultat.getJelaPorudzbine().get(1).getId(), DB_NOVO_JELO_PORUDZBINE_ID);
        Assert.assertEquals(rezultat.getPicePorudzbine().size(), 2);
        Assert.assertEquals(rezultat.getPicePorudzbine().get(1).getId(), DB_NOVO_PICE_PORUDZBINE_ID);
        Assert.assertEquals(rezultat.getStatusPorudzbine(), StatusPorudzbine.KREIRANO);

        Assert.assertEquals(rezultat.getNapomena(), "Nova napomena.");

    }


    @Test( expectedExceptions = PorudzbinaNotFoundException.class)
    public void testIzmeniPorudzbinu_Porudzbina_Not_Found() throws PorudzbinaNotFoundException, NeodgovarajuciStatusException, JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, PorudzbinaNaplacenaException, PiceNotFoundException, JeloNotFoundException {
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

        Porudzbina rezultat = porudzbinaService.izmeniPorudzbinu(porudzbinaDTO);

    }

    @Test( expectedExceptions = PorudzbinaNaplacenaException.class)
    public void testIzmeniPorudzbinu_Porudzbina_Naplacena() throws PorudzbinaNotFoundException, NeodgovarajuciStatusException, JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, PorudzbinaNaplacenaException, PiceNotFoundException, JeloNotFoundException {
        Sto sto = stoRepository.findOneById(10);

        Porudzbina porudzbina1 = porudzbinaRepository.findOneById(DB_NAPLACENA_PORUDZBINA_ID);

        PorudzbinaDTO porudzbinaDTO = new PorudzbinaDTO(porudzbina1);

        Porudzbina rezultat = porudzbinaService.izmeniPorudzbinu(porudzbinaDTO);
    }

    @Test(priority = 5)
    public void testNapraviPorudzbinu() {

        Pice pice = piceRepository.findOneById(7);
        Jelo jelo = jeloRepository.findOneById(2);
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
        //JP1.setId(DB_DODATO_JELO_PORUDZBINE_ID);
        jp1.setJelo(jelo);
        jp1.setKolicina(1.0);
        jp1.setPorudzbina(porudzbina1);

        PicePorudzbine pp1 = PP1;
        //pp1.setId(DB_DODATO_PICE_PORUDZBINE_ID);
        pp1.setPice(pice);
        pp1.setKolicina(1.0);
        pp1.setPorudzbina(porudzbina1);

        porudzbina1.getJelaPorudzbine().add(jp1);
        porudzbina1.getPicePorudzbine().add(pp1);

        PorudzbinaDTO porudzbinaDTO = new PorudzbinaDTO(porudzbina1);

        Porudzbina rezultat = porudzbinaService.napraviPorudzbinu(porudzbinaDTO);

        Assert.assertNotNull(rezultat);
        Assert.assertEquals(rezultat.getJelaPorudzbine().size(), 1);
        Assert.assertEquals(rezultat.getPicePorudzbine().size(), 1);
        Assert.assertEquals(rezultat.getStatusPorudzbine(), StatusPorudzbine.KREIRANO);
        Assert.assertEquals(rezultat.getUkupnaCena(), 345.0);

        Assert.assertEquals(rezultat.getNapomena(), "Nova napomena");

    }

    @Test (priority = 2)
    public void testObrisiPorudzbinuPoId() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        boolean rezultat = porudzbinaService.obrisiPorudzbinuPoId(DB_KREIRANA_PORUDZBINA_ID_1);

        Assert.assertTrue(rezultat);

    }

    @Test (priority = 2, expectedExceptions = NeodgovarajuciStatusException.class)
    public void testObrisiPorudzbinuPoId_Status_Error() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        boolean rezultat = porudzbinaService.obrisiPorudzbinuPoId(DB_NAPLACENA_PORUDZBINA_ID);


    }

    @Test (priority = 2, expectedExceptions = PorudzbinaNotFoundException.class)
    public void testObrisiPorudzbinuPoId_Porudzbina_Not_Found() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        boolean rezultat = porudzbinaService.obrisiPorudzbinuPoId(DB_NON_EXISTANT_ID);

    }

    @Test
    public void testNaplatiPorudzbinu() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        Porudzbina rezultat = porudzbinaService.naplatiPorudzbinu(DB_DOSTAVLJENA_PORUDZBINA_ID);

        Assert.assertNotNull(rezultat);
        Assert.assertEquals(rezultat.getStatusPorudzbine(), StatusPorudzbine.NAPLACENO);


    }

    @Test ( expectedExceptions = NeodgovarajuciStatusException.class)
    public void testNaplatiPorudzbinu_Status_Error() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        Porudzbina rezultat = porudzbinaService.naplatiPorudzbinu(DB_NAPLACENA_PORUDZBINA_ID);


    }

    @Test ( expectedExceptions = PorudzbinaNotFoundException.class)
    public void testNaplatiPorudzbinu_Porudzbina_Not_Found() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        Porudzbina rezultat = porudzbinaService.naplatiPorudzbinu(DB_NON_EXISTANT_ID);


    }
}
