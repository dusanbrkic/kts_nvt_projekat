package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloDTO;
import gradjanibrzogbroda.backend.dto.PiceDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.exceptions.*;
import gradjanibrzogbroda.backend.repository.PicePorudzbineRepository;
import gradjanibrzogbroda.backend.repository.PiceRepository;
import gradjanibrzogbroda.backend.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static gradjanibrzogbroda.backend.constants.PicePorudzbineConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PicePorudzbineServiceTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private PicePorudzbineService picePorudzbineService;

    @Test
    public void testFindAll() {
        ArrayList<PicePorudzbine> rezultat = (ArrayList<PicePorudzbine>) picePorudzbineService.findAll();
        Assert.assertEquals(rezultat.size(), 9);

        Assert.assertEquals(rezultat.get(0).getId(), Integer.valueOf(1));
        Assert.assertEquals(rezultat.get(0).getNapomena(), "Sa ledom.");
        Assert.assertEquals(rezultat.get(0).getKolicina(), 2.0);

    }

    @Test
    public void testFindOne() throws PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.findOne(DB_PICE_PORUDZBINE_ID_1);

        Assert.assertEquals(rezultat.getId(), DB_PICE_PORUDZBINE_ID_1);
        Assert.assertEquals(rezultat.getNapomena(), "Bez leda");
        Assert.assertEquals(rezultat.getKolicina(), 2.0);

    }

    @Test(expectedExceptions = {PicePorudzbineNotFoundException.class})
    public void testFindOne_Not_Found() throws PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.findOne(DB_NON_EXISTANT_ID);

        Assert.assertNull(rezultat);
    }

    @Test(priority = 5)
    public void testDodajPicePorudzbine() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, PiceNotFoundException, NepozitivnaKolicinaException {
        Pice pice = new Pice();
        pice.setId(DB_PICE_ID);
        pice.setTrenutnaCena(DB_PICE_CENA);
        pice.setNaziv(DB_PICE_NAZIV);

        DB_NOVO_PICE_PORUDZBINE_DTO.setPice(new PiceDTO(pice));
        PicePorudzbine rezultat = picePorudzbineService.dodajPicePorudzbine(DB_NOVO_PICE_PORUDZBINE_DTO);

        Assert.assertEquals(rezultat.getNapomena(), DB_NOVO_PICE_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), DB_NOVO_PICE_PORUDZBINE_KOLICINA);
        Assert.assertEquals(rezultat.getPice().getId(), DB_PICE_ID);
        Assert.assertEquals(rezultat.getStatusPica(), StatusPica.KREIRANO);
        Assert.assertEquals(rezultat.getPorudzbina().getStatusPorudzbine(), StatusPorudzbine.KREIRANO);

    }

    @Test(expectedExceptions = {PorudzbinaNotFoundException.class})
    public void testDodajPicePorudzbine_Porudzbina_Not_Found() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, NepozitivnaKolicinaException, PiceNotFoundException {
        Pice pice = new Pice();
        pice.setId(DB_PICE_ID);
        pice.setTrenutnaCena(DB_PICE_CENA);
        pice.setNaziv(DB_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(DB_NOVO_PICE_PORUDZBINE_ID)
                .kolicina(DB_NOVO_PICE_PORUDZBINE_KOLICINA)
                .napomena(DB_NOVO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(DB_NON_EXISTANT_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.dodajPicePorudzbine(picePorudzbineDTO);

    }

    @Test(expectedExceptions = {PiceNotFoundException.class})
    public void testDodajPicePorudzbine_Pice_Not_Found() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, NepozitivnaKolicinaException, PiceNotFoundException {
        Pice pice = new Pice();
        pice.setId(DB_NON_EXISTANT_ID);
        pice.setTrenutnaCena(DB_PICE_CENA);
        pice.setNaziv(DB_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(DB_NOVO_PICE_PORUDZBINE_ID)
                .kolicina(DB_NOVO_PICE_PORUDZBINE_KOLICINA)
                .napomena(DB_NOVO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(DB_NOVO_PICE_PORUDZBINE_PORUDZBINA)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.dodajPicePorudzbine(picePorudzbineDTO);

    }

    @Test(expectedExceptions = {PorudzbinaNaplacenaException.class})
    public void testDodajPicePorudzbine_Porudzbina_Naplacena() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, NepozitivnaKolicinaException, PiceNotFoundException {
        Pice pice = new Pice();
        pice.setId(DB_PICE_ID);
        pice.setTrenutnaCena(DB_PICE_CENA);
        pice.setNaziv(DB_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(DB_NOVO_PICE_PORUDZBINE_ID)
                .kolicina(DB_NOVO_PICE_PORUDZBINE_KOLICINA)
                .napomena(DB_NOVO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(DB_NAPLACENA_PORUDZBINA_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.dodajPicePorudzbine(picePorudzbineDTO);

    }

    @Test(expectedExceptions = {NepozitivnaKolicinaException.class})
    public void testDodajPicePorudzbine_Nepozitivna_Kolicina() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, NepozitivnaKolicinaException, PiceNotFoundException {
        Pice pice = new Pice();
        pice.setId(DB_PICE_ID);
        pice.setTrenutnaCena(DB_PICE_CENA);
        pice.setNaziv(DB_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(DB_NOVO_PICE_PORUDZBINE_ID)
                .kolicina(-100.0)
                .napomena(DB_NOVO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(DB_NOVO_PICE_PORUDZBINE_PORUDZBINA)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.dodajPicePorudzbine(picePorudzbineDTO);

    }

    @Test
    public void testIzmeniPicePorudzbine() throws  PicePorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {
        Pice pice = new Pice();
        pice.setId(DB_PICE_ID);
        pice.setTrenutnaCena(DB_PICE_CENA);
        pice.setNaziv(DB_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(DB_PICE_PORUDZBINE_ID_1)
                .kolicina(DB_IZMENJENO_PICE_PORUDZBINE_KOLICINA)
                .napomena(DB_IZMENJENO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(DB_KREIRANA_PORUDZBINA_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.izmeniPicePorudzbine(picePorudzbineDTO);

        Assert.assertEquals(rezultat.getNapomena(), DB_IZMENJENO_PICE_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), DB_IZMENJENO_PICE_PORUDZBINE_KOLICINA);
        Assert.assertEquals(rezultat.getPice().getId(), Integer.valueOf(7));
        Assert.assertEquals(rezultat.getStatusPica(), StatusPica.KREIRANO);
        Assert.assertEquals(rezultat.getPorudzbina().getUkupnaCena(), 375.0); //3 artikla od po 240

    }

    @Test(expectedExceptions = {PicePorudzbineNotFoundException.class})
    public void testIzmeniPicePorudzbine_Not_Found() throws PicePorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Pice pice = new Pice();
        pice.setId(DB_PICE_ID);
        pice.setTrenutnaCena(DB_PICE_CENA);
        pice.setNaziv(DB_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(DB_NON_EXISTANT_ID)
                .kolicina(DB_IZMENJENO_PICE_PORUDZBINE_KOLICINA)
                .napomena(DB_IZMENJENO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(DB_KREIRANA_PORUDZBINA_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.izmeniPicePorudzbine(picePorudzbineDTO);

    }

    @Test(expectedExceptions = {NeodgovarajuciStatusException.class})
    public void testIzmeniPicePorudzbine_Pripremljeno() throws PicePorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Pice pice = new Pice();
        pice.setId(DB_PICE_ID);
        pice.setTrenutnaCena(DB_PICE_CENA);
        pice.setNaziv(DB_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(DB_PRIPREMLJENO_PICE_PORUDZBINE_ID)
                .kolicina(DB_IZMENJENO_PICE_PORUDZBINE_KOLICINA)
                .napomena(DB_IZMENJENO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(DB_KREIRANA_PORUDZBINA_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.izmeniPicePorudzbine(picePorudzbineDTO);

    }

    @Test(expectedExceptions = {NepozitivnaKolicinaException.class})
    public void testIzmeniPicePorudzbine_Nepozitivna_Kolicina() throws PicePorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Pice pice = new Pice();
        pice.setId(DB_PICE_ID);
        pice.setTrenutnaCena(DB_PICE_CENA);
        pice.setNaziv(DB_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(DB_PICE_PORUDZBINE_ID_1)
                .kolicina(-100.0)
                .napomena(DB_IZMENJENO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(DB_KREIRANA_PORUDZBINA_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.izmeniPicePorudzbine(picePorudzbineDTO);

    }

    @Test (priority = 2)
    public void testObrisiPicePorudzbine() throws PicePorudzbineNotFoundException, NeodgovarajuciStatusException {

        boolean rezultat = picePorudzbineService.obrisiPicePorudzbine(DB_PICE_PORUDZBINE_ID_2);

        Assert.assertTrue(rezultat);

    }

    @Test(priority = 2, expectedExceptions = {PicePorudzbineNotFoundException.class})
    public void testObrisiPicePorudzbine_Not_Found() throws PicePorudzbineNotFoundException, NeodgovarajuciStatusException {

        boolean rezultat = picePorudzbineService.obrisiPicePorudzbine(DB_NON_EXISTANT_ID);

    }

    @Test(priority = 2, expectedExceptions = {NeodgovarajuciStatusException.class})
    public void testObrisiPicePorudzbine_Pripremljeno() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {

        boolean rezultat = picePorudzbineService.obrisiPicePorudzbine(DB_PRIPREMLJENO_PICE_PORUDZBINE_ID);

    }

    @Test
    public void testPripremiPice() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.pripremiPice(DB_PICE_PORUDZBINE_ID_1);

        Assert.assertEquals(rezultat.getStatusPica(), StatusPica.PRIPREMLJENO);
    }

    @Test(expectedExceptions = NeodgovarajuciStatusException.class)
    public void testPripremiPice_Status_Nije_KREIRANO() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.pripremiPice(DB_PRIPREMLJENO_PICE_PORUDZBINE_ID);

    }

    @Test(expectedExceptions = PicePorudzbineNotFoundException.class)
    public void testPripremiPice_Pice_Not_Found() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.pripremiPice(DB_NON_EXISTANT_ID);
    }

    @Test
    public void testDostaviPice() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.dostaviPice(DB_PRIPREMLJENO_PICE_PORUDZBINE_ID);

        Assert.assertEquals(rezultat.getStatusPica(), StatusPica.DOSTAVLJENO);
        Assert.assertEquals(rezultat.getPorudzbina().getStatusPorudzbine(), StatusPorudzbine.DOSTAVLJENO);

    }

    @Test(expectedExceptions = NeodgovarajuciStatusException.class)
    public void testDostaviPice_Status_Nije_PRIPREMLJENO() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.dostaviPice(DB_PICE_PORUDZBINE_ID_1);
    }

    @Test(expectedExceptions = PicePorudzbineNotFoundException.class)
    public void testDostaviPice_Pice_Not_Found() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.dostaviPice(DB_NON_EXISTANT_ID);
    }
}
