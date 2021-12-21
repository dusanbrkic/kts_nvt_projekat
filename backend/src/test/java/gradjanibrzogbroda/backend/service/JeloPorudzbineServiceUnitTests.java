package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.exceptions.*;
import gradjanibrzogbroda.backend.repository.JeloPorudzbineRepository;
import gradjanibrzogbroda.backend.repository.JeloRepository;
import gradjanibrzogbroda.backend.repository.PorudzbinaRepository;
import gradjanibrzogbroda.backend.repository.ZaposleniRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert.*;
import org.testng.internal.junit.ArrayAsserts;

import java.util.ArrayList;

import static gradjanibrzogbroda.backend.constants.JeloPorudzbineConstants.*;
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

    @InjectMocks
    @Autowired
    private JeloPorudzbineService jeloPorudzbineService;

    @BeforeClass
    public void initMock() {
        openMocks(this);

        Jelo jelo = new Jelo();
        jelo.setId(DB_JELO_ID);
        jelo.setTrenutnaCena(DB_JELO_CENA);
        jelo.setNaziv(DB_JELO_NAZIV);
        jelo.setTipJela(TipJela.BASIC);
        jelo.setKategorijaJela(KategorijaJela.GLAVNO_JELO);

        Porudzbina porudzbina = new Porudzbina();
        porudzbina.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        porudzbina.setUkupnaCena(480.0);
        porudzbina.setId(DB_PORUDZBINA_ID);
        porudzbina.setPicePorudzbine(new ArrayList<PicePorudzbine>());

        Porudzbina naplacena = new Porudzbina();
        naplacena.setStatusPorudzbine(StatusPorudzbine.NAPLACENO);
        naplacena.setUkupnaCena(600.0);
        naplacena.setId(NAPLACENA_PORUDZBINA_ID);
        naplacena.setPicePorudzbine(new ArrayList<PicePorudzbine>());


        ArrayList<JeloPorudzbine> jelaPorudzbine = new ArrayList<>();
        JeloPorudzbine jp = JeloPorudzbine.builder()
                .id(DB_JELO_PORUDZBINE_ID)
                .kolicina(DB_JELO_PORUDZBINE_KOLICINA)
                .napomena(DB_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jelo)
                .porudzbina(porudzbina)
                .statusJela(DB_JELO_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();
        jelaPorudzbine.add(jp);

        porudzbina.getJelaPorudzbine().add(jp);

        JeloPorudzbine novo = JeloPorudzbine.builder()
                .id(2)
                .jelo(jelo)
                .porudzbina(porudzbina)
                .kolicina(NEW_JELO_PORUDZBINE_KOLICINA)
                .napomena(NEW_JELO_PORUDZBINE_NAPOMENA)
                .statusJela(StatusJela.KREIRANO)
                .obrisan(false)
                .build();

        JeloPorudzbine preuzeto = JeloPorudzbine.builder()
                .id(PREUZETO_JELO_PORUDZBINE_ID)
                .kolicina(DB_JELO_PORUDZBINE_KOLICINA)
                .napomena(DB_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jelo)
                .porudzbina(porudzbina)
                .statusJela(StatusJela.PREUZETO)
                .obrisan(false)
                .build();
        jelaPorudzbine.add(preuzeto);

        given(jeloPorudzbineRepositoryMock.findAll()).willReturn(jelaPorudzbine);
        given(jeloPorudzbineRepositoryMock.findOneById(DB_JELO_PORUDZBINE_ID)).willReturn(jp);
        given(jeloPorudzbineRepositoryMock.findOneById(NON_EXISTANT_JELO_PORUDZBINE_ID)).willReturn(null);
        given(jeloPorudzbineRepositoryMock.findOneById(PREUZETO_JELO_PORUDZBINE_ID)).willReturn(preuzeto);
        when(jeloPorudzbineRepositoryMock.save(Mockito.any(JeloPorudzbine.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        given(porudzbinaRepositoryMock.findOneById(DB_PORUDZBINA_ID)).willReturn(porudzbina);
        given(porudzbinaRepositoryMock.findOneById(NAPLACENA_PORUDZBINA_ID)).willReturn(naplacena);

        given(jeloRepositoryMock.findOneById(DB_JELO_ID)).willReturn(jelo);

    }

    @Test
    public void testFindAll() {
        ArrayList<JeloPorudzbine> rezultat = (ArrayList<JeloPorudzbine>) jeloPorudzbineService.findAll();
        Assert.assertEquals(rezultat.size(), 2);

        Assert.assertEquals(rezultat.get(0).getId(), DB_JELO_PORUDZBINE_ID);
        Assert.assertEquals(rezultat.get(0).getNapomena(), DB_JELO_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.get(0).getKolicina(), DB_JELO_PORUDZBINE_KOLICINA);

        verify(jeloPorudzbineRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testFindOne() throws JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.findOne(DB_JELO_PORUDZBINE_ID);

        Assert.assertEquals(rezultat.getId(), DB_JELO_PORUDZBINE_ID);
        Assert.assertEquals(rezultat.getNapomena(), DB_JELO_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), DB_JELO_PORUDZBINE_KOLICINA);

        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(DB_JELO_PORUDZBINE_ID);
    }

    @Test(expectedExceptions = {JeloPorudzbineNotFoundException.class})
    public void testFindOne_Not_Found() throws JeloPorudzbineNotFoundException {
        JeloPorudzbine rezultat = jeloPorudzbineService.findOne(NON_EXISTANT_JELO_PORUDZBINE_ID);

        Assert.assertNull(rezultat);

        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(NON_EXISTANT_JELO_PORUDZBINE_ID);
    }

    @Test(priority = 1)
    public void testDodajJeloPorudzbine() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {

        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(NEW_JELO_PORUDZBINE);

        Assert.assertEquals(rezultat.getNapomena(), NEW_JELO_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), NEW_JELO_PORUDZBINE_KOLICINA);
        Assert.assertEquals(rezultat.getJelo().getId(), NEW_JELO_PORUDZBINE_JELO);
        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.KREIRANO);
        Assert.assertEquals(rezultat.getPorudzbina().getId(), NEW_JELO_PORUDZBINE_PORUDZBINA);

        //verify(porudzbinaRepositoryMock, times(1)).findOneById(NEW_JELO_PORUDZBINE_PORUDZBINA);
        //verify(jeloRepositoryMock, times(1)).findOneById(NEW_JELO_PORUDZBINE_JELO);


    }

    @Test(expectedExceptions = {PorudzbinaNotFoundException.class})
    public void testDodajJeloPorudzbine_Porudzbina_Not_Found() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(NEW_JELO_PORUDZBINE_ID)
                .kolicina(NEW_JELO_PORUDZBINE_KOLICINA)
                .napomena(NEW_JELO_PORUDZBINE_NAPOMENA)
                .jeloId(NEW_JELO_PORUDZBINE_JELO)
                .porudzbinaId(NON_EXISTANT_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(NEW_JELO_PORUDZBINE_PORUDZBINA);
    }

    @Test(expectedExceptions = {JeloNotFoundException.class})
    public void testDodajJeloPorudzbine_Jelo_Not_Found() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(NEW_JELO_PORUDZBINE_ID)
                .kolicina(NEW_JELO_PORUDZBINE_KOLICINA)
                .napomena(NEW_JELO_PORUDZBINE_NAPOMENA)
                .jeloId(NON_EXISTANT_JELO_ID)
                .porudzbinaId(NEW_JELO_PORUDZBINE_PORUDZBINA)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(NEW_JELO_PORUDZBINE_PORUDZBINA);
        verify(jeloRepositoryMock, times(1)).findOneById(NON_EXISTANT_JELO_ID);
    }

    @Test(expectedExceptions = {PorudzbinaNaplacenaException.class})
    public void testDodajJeloPorudzbine_Porudzbina_Naplacena() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(NEW_JELO_PORUDZBINE_ID)
                .kolicina(NEW_JELO_PORUDZBINE_KOLICINA)
                .napomena(NEW_JELO_PORUDZBINE_NAPOMENA)
                .jeloId(NEW_JELO_PORUDZBINE_JELO)
                .porudzbinaId(NAPLACENA_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(NAPLACENA_PORUDZBINA_ID);
    }

    @Test(expectedExceptions = {NepozitivnaKolicinaException.class})
    public void testDodajJeloPorudzbine_Nepozitivna_Kolicina() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, JeloNotFoundException, NepozitivnaKolicinaException {
        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(NEW_JELO_PORUDZBINE_ID)
                .kolicina(NEGATIVNA_KOLICINA)
                .napomena(NEW_JELO_PORUDZBINE_NAPOMENA)
                .jeloId(NEW_JELO_PORUDZBINE_JELO)
                .porudzbinaId(NEW_JELO_PORUDZBINE_PORUDZBINA)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.dodajJeloPorudzbine(jeloPorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(NEW_JELO_PORUDZBINE_PORUDZBINA);
    }

    @Test
    public void testIzmeniJeloPorudzbine() throws JeloPorudzbineVecPreuzetoException, JeloPorudzbineNotFoundException, NepozitivnaKolicinaException {

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(DB_JELO_PORUDZBINE_ID)
                .kolicina(UPDATED_JELO_PORUDZBINE_KOLICINA)
                .napomena(UPDATED_JELO_PORUDZBINE_NAPOMENA)
                .jeloId(DB_JELO_ID)
                .porudzbinaId(DB_PORUDZBINA_ID)
                .statusJela(StatusJela.KREIRANO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);

        Assert.assertEquals(rezultat.getNapomena(), UPDATED_JELO_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), UPDATED_JELO_PORUDZBINE_KOLICINA);
        Assert.assertEquals(rezultat.getJelo().getId(), DB_JELO_ID);
        Assert.assertEquals(rezultat.getStatusJela(), StatusJela.KREIRANO);
        Assert.assertEquals(rezultat.getPorudzbina().getId(), DB_PORUDZBINA_ID);
        Assert.assertEquals(rezultat.getPorudzbina().getUkupnaCena(), 720.0); //3 pljeskavice od po 240

        //verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(DB_JELO_PORUDZBINE_ID);
    }

    @Test(expectedExceptions = {JeloPorudzbineNotFoundException.class})
    public void testIzmeniJeloPorudzbine_Not_Found() throws JeloPorudzbineVecPreuzetoException, JeloPorudzbineNotFoundException, NepozitivnaKolicinaException {

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(NON_EXISTANT_JELO_PORUDZBINE_ID)
                .kolicina(UPDATED_JELO_PORUDZBINE_KOLICINA)
                .napomena(UPDATED_JELO_PORUDZBINE_NAPOMENA)
                .jeloId(DB_JELO_ID)
                .porudzbinaId(DB_PORUDZBINA_ID)
                .statusJela(StatusJela.PREUZETO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(NON_EXISTANT_JELO_PORUDZBINE_ID);
    }

    @Test(expectedExceptions = {JeloPorudzbineVecPreuzetoException.class})
    public void testIzmeniJeloPorudzbine_Preuzeto() throws JeloPorudzbineVecPreuzetoException, JeloPorudzbineNotFoundException, NepozitivnaKolicinaException {

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(PREUZETO_JELO_PORUDZBINE_ID)
                .kolicina(UPDATED_JELO_PORUDZBINE_KOLICINA)
                .napomena(UPDATED_JELO_PORUDZBINE_NAPOMENA)
                .jeloId(DB_JELO_ID)
                .porudzbinaId(DB_PORUDZBINA_ID)
                .statusJela(StatusJela.PREUZETO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(PREUZETO_JELO_PORUDZBINE_ID);
    }

    @Test(expectedExceptions = {NepozitivnaKolicinaException.class})
    public void testIzmeniJeloPorudzbine_Nepozitivna_Kolicina() throws JeloPorudzbineVecPreuzetoException, JeloPorudzbineNotFoundException, NepozitivnaKolicinaException {

        JeloPorudzbineDTO jeloPorudzbineDTO = JeloPorudzbineDTO.builder()
                .id(DB_JELO_PORUDZBINE_ID)
                .kolicina(NEGATIVNA_KOLICINA)
                .napomena(UPDATED_JELO_PORUDZBINE_NAPOMENA)
                .jeloId(DB_JELO_ID)
                .porudzbinaId(DB_PORUDZBINA_ID)
                .statusJela(StatusJela.PREUZETO)
                .build();
        JeloPorudzbine rezultat = jeloPorudzbineService.izmeniJeloPorudzbine(jeloPorudzbineDTO);


        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(DB_JELO_PORUDZBINE_ID);
    }

    @Test (priority = 2)
    public void testObrisiJeloPorudzbine() throws  JeloPorudzbineVecPreuzetoException, JeloPorudzbineNotFoundException {

        boolean rezultat = jeloPorudzbineService.obrisiJeloPorudzbine(DELETED_JELO_PORUDZBINE_ID);

        Assert.assertTrue(rezultat);

        verify(jeloPorudzbineRepositoryMock, times(1)).deleteById(DELETED_JELO_PORUDZBINE_ID);

    }

    @Test(priority = 2, expectedExceptions = {JeloPorudzbineNotFoundException.class})
    public void testObrisiJeloPorudzbine_Not_Found() throws JeloPorudzbineVecPreuzetoException, JeloPorudzbineNotFoundException {

        boolean rezultat = jeloPorudzbineService.obrisiJeloPorudzbine(NON_EXISTANT_JELO_PORUDZBINE_ID);

        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(NON_EXISTANT_JELO_PORUDZBINE_ID);
    }

    @Test(priority = 2, expectedExceptions = {JeloPorudzbineVecPreuzetoException.class})
    public void testObrisiJeloPorudzbine_Preuzeto() throws JeloPorudzbineVecPreuzetoException, JeloPorudzbineNotFoundException {

        boolean rezultat = jeloPorudzbineService.obrisiJeloPorudzbine(PREUZETO_JELO_PORUDZBINE_ID);

        verify(jeloPorudzbineRepositoryMock, times(1)).findOneById(NON_EXISTANT_JELO_PORUDZBINE_ID);
    }
}
