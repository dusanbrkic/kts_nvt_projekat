package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloDTO;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.exceptions.*;
import gradjanibrzogbroda.backend.repository.JeloPorudzbineRepository;
import gradjanibrzogbroda.backend.repository.JeloRepository;
import gradjanibrzogbroda.backend.repository.PorudzbinaRepository;
import gradjanibrzogbroda.backend.util.PorudzbinaUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static gradjanibrzogbroda.backend.constants.JeloPorudzbineUnitTestConstants.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JeloPorudzbineServiceUnitTests extends AbstractTestNGSpringContextTests {

    @Mock
    private JeloPorudzbineRepository jeloPorudzbineRepositoryMock;

    @Mock
    private PorudzbinaRepository porudzbinaRepositoryMock;

    @Mock
    private JeloRepository jeloRepositoryMock;

    @Mock
    private PorudzbinaUtil porudzbinaUtilMock;

    @InjectMocks
    @Autowired
    private JeloPorudzbineService jeloPorudzbineService;

    @BeforeClass
    public void initMock() {
        openMocks(this);

        Jelo jelo = new Jelo();
        jelo.setId(UT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        UT_NOVO_JELO_PORUDZBINE_DTO.setJelo(jeloDTO);

        //kreirana sa 1 kreiranim jelom i 1 preuzetim
        Porudzbina porudzbina = new Porudzbina();
        porudzbina.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        porudzbina.setUkupnaCena(UT_KREIRANA_PORUDZBINA_CENA);
        porudzbina.setId(UT_KREIRANA_PORUDZBINA_ID);
        porudzbina.setPicePorudzbine(new ArrayList<PicePorudzbine>());

        //pripremljena sa 1 jelom
        Porudzbina porudzbinaPripremljena = new Porudzbina();
        porudzbina.setStatusPorudzbine(StatusPorudzbine.PRIPREMLJENO);
        porudzbina.setUkupnaCena(UT_PRIPREMLJENA_PORUDZBINA_CENA);
        porudzbina.setId(UT_PRIPREMLJENA_PORUDZBINA_ID);
        porudzbina.setPicePorudzbine(new ArrayList<PicePorudzbine>());

        ArrayList<JeloPorudzbine> jelaPorudzbine = new ArrayList<>();
        JeloPorudzbine jp = JeloPorudzbine.builder()
                .id(UT_JELO_PORUDZBINE_ID_1)
                .kolicina(UT_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jelo)
                .porudzbina(porudzbina)
                .statusJela(UT_JELO_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();
        jelaPorudzbine.add(jp);
        porudzbina.getJelaPorudzbine().add(jp);

        JeloPorudzbine jp2 = JeloPorudzbine.builder()
                .id(UT_JELO_PORUDZBINE_ID_2)
                .kolicina(UT_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jelo)
                .porudzbina(porudzbina)
                .statusJela(UT_JELO_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();
        jelaPorudzbine.add(jp2);
        porudzbina.getJelaPorudzbine().add(jp2);

        Porudzbina porudzbinaNaplacena = new Porudzbina();
        porudzbinaNaplacena.setStatusPorudzbine(StatusPorudzbine.NAPLACENO);
        porudzbinaNaplacena.setUkupnaCena(600.0);
        porudzbinaNaplacena.setId(UT_NAPLACENA_PORUDZBINA_ID);
        porudzbinaNaplacena.setPicePorudzbine(new ArrayList<PicePorudzbine>());


        JeloPorudzbine preuzeto = JeloPorudzbine.builder()
                .id(UT_PREUZETO_JELO_PORUDZBINE_ID)
                .kolicina(UT_PREUZETO_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_PREUZETO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jelo)
                .porudzbina(porudzbina)
                .statusJela(UT_PREUZETO_JELO_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();
        jelaPorudzbine.add(preuzeto);
        ArrayList<JeloPorudzbine> preuzetaJelaPorudzbine = new ArrayList<JeloPorudzbine>();
        preuzetaJelaPorudzbine.add(preuzeto);

        JeloPorudzbine pripremljeno = JeloPorudzbine.builder()
                .id(UT_PRIPREMLJENO_JELO_PORUDZBINE_ID)
                .kolicina(UT_PRIPREMLJENO_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_PRIPREMLJENO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jelo)
                .porudzbina(porudzbinaPripremljena)
                .statusJela(UT_PRIPREMLJENO_JELO_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();
        jelaPorudzbine.add(pripremljeno);
        porudzbinaPripremljena.getJelaPorudzbine().add(pripremljeno);

        given(jeloPorudzbineRepositoryMock.findAll()).willReturn(jelaPorudzbine);
        given(jeloPorudzbineRepositoryMock.findAllByStatusJela(StatusJela.PREUZETO)).willReturn(preuzetaJelaPorudzbine);
        given(jeloPorudzbineRepositoryMock.findOneById(UT_JELO_PORUDZBINE_ID_1)).willReturn(jp);
        given(jeloPorudzbineRepositoryMock.findOneById(UT_JELO_PORUDZBINE_ID_2)).willReturn(jp2);
        given(jeloPorudzbineRepositoryMock.findOneById(UT_NON_EXISTANT_JELO_PORUDZBINE_ID)).willReturn(null);
        given(jeloPorudzbineRepositoryMock.findOneById(UT_PREUZETO_JELO_PORUDZBINE_ID)).willReturn(preuzeto);
        given(jeloPorudzbineRepositoryMock.findOneById(UT_PRIPREMLJENO_JELO_PORUDZBINE_ID)).willReturn(pripremljeno);
        when(jeloPorudzbineRepositoryMock.save(Mockito.any(JeloPorudzbine.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        given(porudzbinaRepositoryMock.findOneById(UT_KREIRANA_PORUDZBINA_ID)).willReturn(porudzbina);
        given(porudzbinaRepositoryMock.findOneById(UT_NAPLACENA_PORUDZBINA_ID)).willReturn(porudzbinaNaplacena);
        given(porudzbinaRepositoryMock.findOneById(UT_PRIPREMLJENA_PORUDZBINA_ID)).willReturn(porudzbinaPripremljena);

        given(jeloRepositoryMock.findOneById(UT_JELO_ID)).willReturn(jelo);
        given(jeloRepositoryMock.findOneById(UT_NON_EXISTANT_JELO_ID)).willReturn(null);

        given(porudzbinaUtilMock.promeniStatusPorudzbine(porudzbinaPripremljena, StatusJela.DOSTAVLJENO, StatusPica.DOSTAVLJENO)).willReturn(true);

    }

    @Test
    public void testFindAll() {
        ArrayList<JeloPorudzbine> rezultat = (ArrayList<JeloPorudzbine>) jeloPorudzbineService.findAll();
        Assert.assertEquals(rezultat.size(), 4);

        Assert.assertEquals(rezultat.get(0).getId(), UT_JELO_PORUDZBINE_ID_1);
        Assert.assertEquals(rezultat.get(0).getNapomena(), UT_JELO_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.get(0).getKolicina(), UT_JELO_PORUDZBINE_KOLICINA);

        verify(jeloPorudzbineRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testGetAllPreuzeto() {
        ArrayList<JeloPorudzbine> rezultat = (ArrayList<JeloPorudzbine>) jeloPorudzbineService.getAllPreuzeto();
        Assert.assertEquals(rezultat.size(), 1);

        Assert.assertEquals(rezultat.get(0).getId(), UT_PREUZETO_JELO_PORUDZBINE_ID);
        Assert.assertEquals(rezultat.get(0).getNapomena(), UT_PREUZETO_JELO_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.get(0).getKolicina(), UT_PREUZETO_JELO_PORUDZBINE_KOLICINA);

        verify(jeloPorudzbineRepositoryMock, times(1)).findAllByStatusJela(StatusJela.PREUZETO);
    }

    @Test
    public void testFindOne() throws JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.findOne(UT_JELO_PORUDZBINE_ID_1);

        Assert.assertEquals(rezultat.getId(), UT_JELO_PORUDZBINE_ID_1);
        Assert.assertEquals(rezultat.getNapomena(), UT_JELO_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), UT_JELO_PORUDZBINE_KOLICINA);

    }

    @Test(expectedExceptions = {JeloPorudzbineNotFoundException.class})
    public void testFindOne_Not_Found() throws JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.findOne(UT_NON_EXISTANT_JELO_PORUDZBINE_ID);

        Assert.assertNull(rezultat);

        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_JELO_PORUDZBINE_ID);
    }

    @Test(priority = 1)
    public void testDodajJeloPorudzbine() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {

        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(UT_NOVO_JELO_PORUDZBINE_DTO);

        Assert.assertEquals(rezultat.getNapomena(), UT_NOVO_JELO_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), UT_NOVO_JELO_PORUDZBINE_KOLICINA);
        Assert.assertEquals(rezultat.getJelo().getId(), UT_JELO_ID);
        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.KREIRANO);
        Assert.assertEquals(rezultat.getPorudzbina().getStatusPorudzbine(), StatusPorudzbine.KREIRANO);

        //verify(porudzbinaRepositoryMock, times(1)).findOneById(NEW_JELO_PORUDZBINE_PORUDZBINA);
        //verify(jeloRepositoryMock, times(1)).findOneById(NEW_JELO_PORUDZBINE_JELO);


    }

    @Test(expectedExceptions = {PorudzbinaNotFoundException.class})
    public void testDodajJeloPorudzbine_Porudzbina_Not_Found() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        Jelo jelo = new Jelo();
        jelo.setId(UT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(UT_NOVO_JELO_PORUDZBINE_ID)
                .kolicina(UT_NOVO_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_NOVO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(UT_NON_EXISTANT_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_PORUDZBINA_ID);
    }

    @Test(expectedExceptions = {JeloNotFoundException.class})
    public void testDodajJeloPorudzbine_Jelo_Not_Found() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        Jelo jelo = new Jelo();
        jelo.setId(UT_NON_EXISTANT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(UT_NOVO_JELO_PORUDZBINE_ID)
                .kolicina(UT_NOVO_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_NOVO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(UT_NOVO_JELO_PORUDZBINE_PORUDZBINA)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NOVO_JELO_PORUDZBINE_PORUDZBINA);
        verify(jeloRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_JELO_ID);
    }

    @Test(expectedExceptions = {PorudzbinaNaplacenaException.class})
    public void testDodajJeloPorudzbine_Porudzbina_Naplacena() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        Jelo jelo = new Jelo();
        jelo.setId(UT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(UT_NOVO_JELO_PORUDZBINE_ID)
                .kolicina(UT_NOVO_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_NOVO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(UT_NAPLACENA_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();


        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NAPLACENA_PORUDZBINA_ID);
    }

    @Test(expectedExceptions = {NepozitivnaKolicinaException.class})
    public void testDodajJeloPorudzbine_Nepozitivna_Kolicina() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        Jelo jelo = new Jelo();
        jelo.setId(UT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(UT_NOVO_JELO_PORUDZBINE_ID)
                .kolicina(-100.0)
                .napomena(UT_NOVO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(UT_NOVO_JELO_PORUDZBINE_PORUDZBINA)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NOVO_JELO_PORUDZBINE_PORUDZBINA);
    }

    @Test
    public void testIzmeniJeloPorudzbine() throws  JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {
        Jelo jelo = new Jelo();
        jelo.setId(UT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(UT_JELO_PORUDZBINE_ID_1)
                .kolicina(UT_IZMENJENO_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_IZMENJENO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(UT_KREIRANA_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);

        Assert.assertEquals(rezultat.getNapomena(), UT_IZMENJENO_JELO_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), UT_IZMENJENO_JELO_PORUDZBINE_KOLICINA);
        Assert.assertEquals(rezultat.getJelo().getId(), UT_JELO_ID);
        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.KREIRANO);
        Assert.assertEquals(rezultat.getPorudzbina().getUkupnaCena(), 720.0); //3 pljeskavice od po 240

        //verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(DB_JELO_PORUDZBINE_ID);
    }

    @Test(expectedExceptions = {JeloPorudzbineNotFoundException.class})
    public void testIzmeniJeloPorudzbine_Not_Found() throws JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Jelo jelo = new Jelo();
        jelo.setId(UT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(UT_NON_EXISTANT_JELO_PORUDZBINE_ID)
                .kolicina(UT_IZMENJENO_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_IZMENJENO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(UT_KREIRANA_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_JELO_PORUDZBINE_ID);
    }

    @Test(expectedExceptions = {NeodgovarajuciStatusException.class})
    public void testIzmeniJeloPorudzbine_Preuzeto() throws JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Jelo jelo = new Jelo();
        jelo.setId(UT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(UT_PREUZETO_JELO_PORUDZBINE_ID)
                .kolicina(UT_IZMENJENO_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_IZMENJENO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(UT_KREIRANA_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_PREUZETO_JELO_PORUDZBINE_ID);
    }

    @Test(expectedExceptions = {NepozitivnaKolicinaException.class})
    public void testIzmeniJeloPorudzbine_Nepozitivna_Kolicina() throws JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Jelo jelo = new Jelo();
        jelo.setId(UT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);

        JeloDTO jeloDTO = new JeloDTO(jelo);

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(UT_JELO_PORUDZBINE_ID_1)
                .kolicina(-100.0)
                .napomena(UT_IZMENJENO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jeloDTO)
                .porudzbinaId(UT_KREIRANA_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_JELO_PORUDZBINE_ID_1);
    }

    @Test (priority = 2)
    public void testObrisiJeloPorudzbine() throws JeloPorudzbineNotFoundException, NeodgovarajuciStatusException {

        boolean rezultat = jeloPorudzbineService.obrisiJeloPorudzbine(UT_JELO_PORUDZBINE_ID_2);

        Assert.assertTrue(rezultat);

        verify(jeloPorudzbineRepositoryMock, times(1)).deleteById(UT_JELO_PORUDZBINE_ID_2);

    }

    @Test(priority = 2, expectedExceptions = {JeloPorudzbineNotFoundException.class})
    public void testObrisiJeloPorudzbine_Not_Found() throws JeloPorudzbineNotFoundException, NeodgovarajuciStatusException {

        boolean rezultat = jeloPorudzbineService.obrisiJeloPorudzbine(UT_NON_EXISTANT_JELO_PORUDZBINE_ID);

        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_JELO_PORUDZBINE_ID);
    }

    @Test(priority = 2, expectedExceptions = {NeodgovarajuciStatusException.class})
    public void testObrisiJeloPorudzbine_Preuzeto() throws JeloPorudzbineNotFoundException, NeodgovarajuciStatusException {

        boolean rezultat = jeloPorudzbineService.obrisiJeloPorudzbine(UT_PREUZETO_JELO_PORUDZBINE_ID);

        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_PREUZETO_JELO_PORUDZBINE_ID);
    }

    @Test
    public void testPreuzmiJelo() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.preuzmiJelo(UT_JELO_PORUDZBINE_ID_1);

        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.PREUZETO);

    }

    @Test(expectedExceptions = NeodgovarajuciStatusException.class)
    public void testPreuzmiJelo_Status_Nije_KREIRANO() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.preuzmiJelo(UT_PREUZETO_JELO_PORUDZBINE_ID);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_PREUZETO_JELO_PORUDZBINE_ID);
    }

    @Test(expectedExceptions = JeloPorudzbineNotFoundException.class)
    public void testPreuzmiJelo_Jelo_Not_Found() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.preuzmiJelo(UT_NON_EXISTANT_JELO_PORUDZBINE_ID);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_JELO_PORUDZBINE_ID);
    }

    @Test
    public void testPripremiJelo() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.pripremiJelo(UT_PREUZETO_JELO_PORUDZBINE_ID);

        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.PRIPREMLJENO);
    }

    @Test(expectedExceptions = NeodgovarajuciStatusException.class)
    public void testPripremiJelo_Status_Nije_PREUZETO() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.pripremiJelo(UT_PRIPREMLJENO_JELO_PORUDZBINE_ID);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_PRIPREMLJENO_JELO_PORUDZBINE_ID);
    }

    @Test(expectedExceptions = JeloPorudzbineNotFoundException.class)
    public void testPripremiJelo_Jelo_Not_Found() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.pripremiJelo(UT_NON_EXISTANT_JELO_PORUDZBINE_ID);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_JELO_PORUDZBINE_ID);
    }

    @Test
    public void testDostaviJelo() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.dostaviJelo(UT_PRIPREMLJENO_JELO_PORUDZBINE_ID);

        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.DOSTAVLJENO);
        Assert.assertEquals(rezultat.getPorudzbina().getStatusPorudzbine(), StatusPorudzbine.DOSTAVLJENO);

    }

    @Test(expectedExceptions = NeodgovarajuciStatusException.class)
    public void testDostaviJelo_Status_Nije_PRIPREMLJENO() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.dostaviJelo(UT_JELO_PORUDZBINE_ID_1);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_JELO_PORUDZBINE_ID_1);
    }

    @Test(expectedExceptions = JeloPorudzbineNotFoundException.class)
    public void testDostaviJelo_Jelo_Not_Found() throws NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.dostaviJelo(UT_NON_EXISTANT_JELO_PORUDZBINE_ID);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_JELO_PORUDZBINE_ID);
    }
}
