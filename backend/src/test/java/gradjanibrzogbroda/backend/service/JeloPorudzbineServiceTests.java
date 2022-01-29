package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.JeloPorudzbine;
import gradjanibrzogbroda.backend.domain.StatusJela;
import gradjanibrzogbroda.backend.domain.StatusPorudzbine;
import gradjanibrzogbroda.backend.dto.JeloDTO;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static gradjanibrzogbroda.backend.constants.JeloPorudzbineConstants.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class JeloPorudzbineServiceTests extends AbstractTestNGSpringContextTests {
    @Autowired
	JeloPorudzbineService jeloPorudzbineService;

    @Test
    public void testFindAll() {
        ArrayList<JeloPorudzbine> rezultat = (ArrayList<JeloPorudzbine>) jeloPorudzbineService.findAll();
        Assert.assertEquals(rezultat.size(), 13);

        Assert.assertEquals(rezultat.get(0).getId(), Integer.valueOf(1));
        Assert.assertEquals(rezultat.get(0).getNapomena(), "Sa svezim sastojcima.");
        Assert.assertEquals(rezultat.get(0).getKolicina(), 2.0);
    }

    @Test
    public void testGetAllPreuzeto() {
        ArrayList<JeloPorudzbine> rezultat = (ArrayList<JeloPorudzbine>) jeloPorudzbineService.getAllPreuzeto();
        Assert.assertEquals(rezultat.size(), 1);

        Assert.assertEquals(rezultat.get(0).getId(), Integer.valueOf(8));
        Assert.assertEquals(rezultat.get(0).getNapomena(), "Sa kecapom.");
        Assert.assertEquals(rezultat.get(0).getKolicina(), 1.0);

    }

    @Test
    public void testFindOne() throws JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.findOne(DB_JELO_PORUDZBINE_ID_1);

        Assert.assertEquals(rezultat.getId(), DB_JELO_PORUDZBINE_ID_1);
        Assert.assertEquals(rezultat.getNapomena(), "Sa svezim sastojcima i sirom.");
        Assert.assertEquals(rezultat.getKolicina(), 2.0);

    }

    @Test(expectedExceptions = {JeloPorudzbineNotFoundException.class})
    public void testFindOne_Not_Found() throws JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.findOne(DB_NON_EXISTANT_ID);

        Assert.assertNull(rezultat);

    }

    @Test(priority = 5)
    public void testDodajJeloPorudzbine() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {

        Jelo jelo = new Jelo();
        jelo.setId(2);
        jelo.setTrenutnaCena(DB_JELO_CENA);
        jelo.setNaziv(DB_JELO_NAZIV);
        jelo.setTipJela(DB_JELO_TIP);
        jelo.setKategorijaJela(DB_JELO_KAT);

        DB_NOVO_JELO_PORUDZBINE_DTO.setJelo(new JeloDTO(jelo));

        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(DB_NOVO_JELO_PORUDZBINE_DTO);

        Assert.assertEquals(rezultat.getNapomena(), DB_NOVO_JELO_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), DB_NOVO_JELO_PORUDZBINE_KOLICINA);
        Assert.assertEquals(rezultat.getJelo().getId(), Integer.valueOf(2));
        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.KREIRANO);
        Assert.assertEquals(rezultat.getPorudzbina().getStatusPorudzbine(), StatusPorudzbine.KREIRANO);



    }

    @Test(expectedExceptions = {PorudzbinaNotFoundException.class})
    public void testDodajJeloPorudzbine_Porudzbina_Not_Found() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        Jelo jelo = new Jelo();
        jelo.setId(DB_JELO_ID);
        jelo.setTrenutnaCena(DB_JELO_CENA);
        jelo.setNaziv(DB_JELO_NAZIV);
        jelo.setTipJela(DB_JELO_TIP);
        jelo.setKategorijaJela(DB_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(DB_NOVO_JELO_PORUDZBINE_ID)
                .kolicina(DB_NOVO_JELO_PORUDZBINE_KOLICINA)
                .napomena(DB_NOVO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(DB_NON_EXISTANT_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

    }

    @Test(expectedExceptions = {JeloNotFoundException.class})
    public void testDodajJeloPorudzbine_Jelo_Not_Found() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        Jelo jelo = new Jelo();
        jelo.setId(DB_NON_EXISTANT_ID);
        jelo.setTrenutnaCena(DB_JELO_CENA);
        jelo.setNaziv(DB_JELO_NAZIV);
        jelo.setTipJela(DB_JELO_TIP);
        jelo.setKategorijaJela(DB_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(DB_NOVO_JELO_PORUDZBINE_ID)
                .kolicina(DB_NOVO_JELO_PORUDZBINE_KOLICINA)
                .napomena(DB_NOVO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(DB_NOVO_JELO_PORUDZBINE_PORUDZBINA)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

    }

    @Test(expectedExceptions = {PorudzbinaNaplacenaException.class})
    public void testDodajJeloPorudzbine_Porudzbina_Naplacena() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        Jelo jelo = new Jelo();
        jelo.setId(DB_JELO_ID);
        jelo.setTrenutnaCena(DB_JELO_CENA);
        jelo.setNaziv(DB_JELO_NAZIV);
        jelo.setTipJela(DB_JELO_TIP);
        jelo.setKategorijaJela(DB_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(DB_NOVO_JELO_PORUDZBINE_ID)
                .kolicina(DB_NOVO_JELO_PORUDZBINE_KOLICINA)
                .napomena(DB_NOVO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(DB_NAPLACENA_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();


        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

    }

    @Test(expectedExceptions = {NepozitivnaKolicinaException.class})
    public void testDodajJeloPorudzbine_Nepozitivna_Kolicina() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        Jelo jelo = new Jelo();
        jelo.setId(DB_JELO_ID);
        jelo.setTrenutnaCena(DB_JELO_CENA);
        jelo.setNaziv(DB_JELO_NAZIV);
        jelo.setTipJela(DB_JELO_TIP);
        jelo.setKategorijaJela(DB_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(DB_NOVO_JELO_PORUDZBINE_ID)
                .kolicina(-100.0)
                .napomena(DB_NOVO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(DB_NOVO_JELO_PORUDZBINE_PORUDZBINA)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

    }

    @Test
    public void testIzmeniJeloPorudzbine() throws  JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {
        Jelo jelo = new Jelo();
        jelo.setId(2);
        jelo.setTrenutnaCena(220.0);
        jelo.setNaziv(DB_JELO_NAZIV);
        jelo.setTipJela(DB_JELO_TIP);
        jelo.setKategorijaJela(DB_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(DB_JELO_PORUDZBINE_ID_1)
                .kolicina(5.0)
                .napomena(DB_IZMENJENO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(DB_KREIRANA_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);

        Assert.assertEquals(rezultat.getNapomena(), DB_IZMENJENO_JELO_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), 5.0);
        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.KREIRANO);

    }

    @Test(expectedExceptions = {JeloPorudzbineNotFoundException.class})
    public void testIzmeniJeloPorudzbine_Not_Found() throws JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Jelo jelo = new Jelo();
        jelo.setId(DB_JELO_ID);
        jelo.setTrenutnaCena(DB_JELO_CENA);
        jelo.setNaziv(DB_JELO_NAZIV);
        jelo.setTipJela(DB_JELO_TIP);
        jelo.setKategorijaJela(DB_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(DB_NON_EXISTANT_ID)
                .kolicina(DB_IZMENJENO_JELO_PORUDZBINE_KOLICINA)
                .napomena(DB_IZMENJENO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(DB_KREIRANA_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);

    }

    @Test(expectedExceptions = {NeodgovarajuciStatusException.class})
    public void testIzmeniJeloPorudzbine_Preuzeto() throws JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Jelo jelo = new Jelo();
        jelo.setId(DB_JELO_ID);
        jelo.setTrenutnaCena(DB_JELO_CENA);
        jelo.setNaziv(DB_JELO_NAZIV);
        jelo.setTipJela(DB_JELO_TIP);
        jelo.setKategorijaJela(DB_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(DB_PREUZETO_JELO_PORUDZBINE_ID)
                .kolicina(DB_IZMENJENO_JELO_PORUDZBINE_KOLICINA)
                .napomena(DB_IZMENJENO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(DB_KREIRANA_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);

    }

    @Test(expectedExceptions = {NepozitivnaKolicinaException.class})
    public void testIzmeniJeloPorudzbine_Nepozitivna_Kolicina() throws JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Jelo jelo = new Jelo();
        jelo.setId(DB_JELO_ID);
        jelo.setTrenutnaCena(DB_JELO_CENA);
        jelo.setNaziv(DB_JELO_NAZIV);
        jelo.setTipJela(DB_JELO_TIP);
        jelo.setKategorijaJela(DB_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(DB_JELO_PORUDZBINE_ID_1)
                .kolicina(-100.0)
                .napomena(DB_IZMENJENO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(DB_KREIRANA_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);

    }

    @Test (priority = 2)
    public void testObrisiJeloPorudzbine() throws JeloPorudzbineNotFoundException, NeodgovarajuciStatusException {

        boolean rezultat = jeloPorudzbineService.obrisiJeloPorudzbine(DB_JELO_PORUDZBINE_ID_2);

        Assert.assertTrue(rezultat);

    }

    @Test(priority = 2, expectedExceptions = {JeloPorudzbineNotFoundException.class})
    public void testObrisiJeloPorudzbine_Not_Found() throws JeloPorudzbineNotFoundException, NeodgovarajuciStatusException {

        boolean rezultat = jeloPorudzbineService.obrisiJeloPorudzbine(DB_NON_EXISTANT_ID);

    }

    @Test(priority = 2, expectedExceptions = {NeodgovarajuciStatusException.class})
    public void testObrisiJeloPorudzbine_Preuzeto() throws JeloPorudzbineNotFoundException, NeodgovarajuciStatusException {

        boolean rezultat = jeloPorudzbineService.obrisiJeloPorudzbine(DB_PREUZETO_JELO_PORUDZBINE_ID);

    }

    @Test
    public void testPreuzmiJelo() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.preuzmiJelo(DB_JELO_PORUDZBINE_ID_1);

        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.PREUZETO);

    }

    @Test(expectedExceptions = NeodgovarajuciStatusException.class)
    public void testPreuzmiJelo_Status_Nije_KREIRANO() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.preuzmiJelo(DB_PREUZETO_JELO_PORUDZBINE_ID);

    }

    @Test(expectedExceptions = JeloPorudzbineNotFoundException.class)
    public void testPreuzmiJelo_Jelo_Not_Found() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.preuzmiJelo(DB_NON_EXISTANT_ID);

    }

    @Test
    public void testPripremiJelo() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.pripremiJelo(DB_PREUZETO_JELO_PORUDZBINE_ID);

        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.PRIPREMLJENO);
    }

    @Test(expectedExceptions = NeodgovarajuciStatusException.class)
    public void testPripremiJelo_Status_Nije_PREUZETO() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.pripremiJelo(DB_PRIPREMLJENO_JELO_PORUDZBINE_ID);

    }

    @Test(expectedExceptions = JeloPorudzbineNotFoundException.class)
    public void testPripremiJelo_Jelo_Not_Found() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.pripremiJelo(DB_NON_EXISTANT_ID);

    }

    @Test
    public void testDostaviJelo() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.dostaviJelo(DB_PRIPREMLJENO_JELO_PORUDZBINE_ID);

        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.DOSTAVLJENO);
        Assert.assertEquals(rezultat.getPorudzbina().getStatusPorudzbine(), StatusPorudzbine.DOSTAVLJENO);

    }

    @Test(expectedExceptions = NeodgovarajuciStatusException.class)
    public void testDostaviJelo_Status_Nije_PRIPREMLJENO() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.dostaviJelo(DB_JELO_PORUDZBINE_ID_1);

    }

    @Test(expectedExceptions = JeloPorudzbineNotFoundException.class)
    public void testDostaviJelo_Jelo_Not_Found() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.dostaviJelo(DB_NON_EXISTANT_ID);

    }


}
