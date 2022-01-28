package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.constants.PicePorudzbineUnitTestConstants;
import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloDTO;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PiceDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.exceptions.*;
import gradjanibrzogbroda.backend.repository.*;
import gradjanibrzogbroda.backend.util.PorudzbinaUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static gradjanibrzogbroda.backend.constants.PicePorudzbineUnitTestConstants.*;
import static gradjanibrzogbroda.backend.constants.PicePorudzbineUnitTestConstants.UT_IZMENJENO_PICE_PORUDZBINE_KOLICINA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PicePorudzbineServiceUnitTests {
    @Mock
    private PicePorudzbineRepository picePorudzbineRepositoryMock;

    @Mock
    private PorudzbinaRepository porudzbinaRepositoryMock;

    @Mock
    private PiceRepository piceRepositoryMock;

    @Mock
    private PorudzbinaUtil porudzbinaUtilMock;

    @InjectMocks
    @Autowired
    private PicePorudzbineService picePorudzbineService;

    @BeforeClass
    public void initMock() {
        openMocks(this);

        Pice pice = new Pice();
        pice.setId(UT_PICE_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        UT_NOVO_PICE_PORUDZBINE_DTO.setPice(piceDTO);

        //kreirana sa 1 kreiranim picem i 1 preuzetim
        Porudzbina porudzbina = new Porudzbina();
        porudzbina.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        porudzbina.setUkupnaCena(UT_KREIRANA_PORUDZBINA_CENA);
        porudzbina.setId(UT_KREIRANA_PORUDZBINA_ID);
        porudzbina.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());

        //pripremljena sa 1 picem
        Porudzbina porudzbinaPripremljena = new Porudzbina();
        porudzbina.setStatusPorudzbine(StatusPorudzbine.PRIPREMLJENO);
        porudzbina.setUkupnaCena(UT_PRIPREMLJENA_PORUDZBINA_CENA);
        porudzbina.setId(UT_PRIPREMLJENA_PORUDZBINA_ID);
        porudzbina.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());

        ArrayList<PicePorudzbine> picaPorudzbine = new ArrayList<>();
        PicePorudzbine jp = PicePorudzbine.builder()
                .id(UT_PICE_PORUDZBINE_ID_1)
                .kolicina(UT_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_PICE_PORUDZBINE_NAPOMENA)
                .pice(pice)
                .porudzbina(porudzbina)
                .statusPica(UT_PICE_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();
        picaPorudzbine.add(jp);
        porudzbina.getPicePorudzbine().add(jp);

        PicePorudzbine jp2 = PicePorudzbine.builder()
                .id(UT_PICE_PORUDZBINE_ID_2)
                .kolicina(UT_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_PICE_PORUDZBINE_NAPOMENA)
                .pice(pice)
                .porudzbina(porudzbina)
                .statusPica(UT_PICE_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();
        picaPorudzbine.add(jp2);
        porudzbina.getPicePorudzbine().add(jp2);

        Porudzbina porudzbinaNaplacena = new Porudzbina();
        porudzbinaNaplacena.setStatusPorudzbine(StatusPorudzbine.NAPLACENO);
        porudzbinaNaplacena.setUkupnaCena(600.0);
        porudzbinaNaplacena.setId(UT_NAPLACENA_PORUDZBINA_ID);
        porudzbinaNaplacena.setPicePorudzbine(new ArrayList<PicePorudzbine>());


        PicePorudzbine pripremljeno = PicePorudzbine.builder()
                .id(UT_PRIPREMLJENO_PICE_PORUDZBINE_ID)
                .kolicina(UT_PRIPREMLJENO_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_PRIPREMLJENO_PICE_PORUDZBINE_NAPOMENA)
                .pice(pice)
                .porudzbina(porudzbinaPripremljena)
                .statusPica(UT_PRIPREMLJENO_PICE_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();
        picaPorudzbine.add(pripremljeno);
        porudzbinaPripremljena.getPicePorudzbine().add(pripremljeno);

        given(picePorudzbineRepositoryMock.findAll()).willReturn(picaPorudzbine);
        given(picePorudzbineRepositoryMock.findOneById(UT_PICE_PORUDZBINE_ID_1)).willReturn(jp);
        given(picePorudzbineRepositoryMock.findOneById(UT_PICE_PORUDZBINE_ID_2)).willReturn(jp2);
        given(picePorudzbineRepositoryMock.findOneById(UT_NON_EXISTANT_ID)).willReturn(null);
        given(picePorudzbineRepositoryMock.findOneById(UT_PRIPREMLJENO_PICE_PORUDZBINE_ID)).willReturn(pripremljeno);
        when(picePorudzbineRepositoryMock.save(Mockito.any(PicePorudzbine.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        given(porudzbinaRepositoryMock.findOneById(UT_KREIRANA_PORUDZBINA_ID)).willReturn(porudzbina);
        given(porudzbinaRepositoryMock.findOneById(UT_NAPLACENA_PORUDZBINA_ID)).willReturn(porudzbinaNaplacena);
        given(porudzbinaRepositoryMock.findOneById(UT_PRIPREMLJENA_PORUDZBINA_ID)).willReturn(porudzbinaPripremljena);

        given(piceRepositoryMock.findOneById(UT_PICE_ID)).willReturn(pice);
        given(piceRepositoryMock.findOneById(UT_NON_EXISTANT_ID)).willReturn(null);

        given(porudzbinaUtilMock.promeniStatusPorudzbine(porudzbinaPripremljena, StatusJela.DOSTAVLJENO, StatusPica.DOSTAVLJENO)).willReturn(true);

    }

    @Test
    public void testFindAll() {
        ArrayList<PicePorudzbine> rezultat = (ArrayList<PicePorudzbine>) picePorudzbineService.findAll();
        Assert.assertEquals(rezultat.size(), 3);

        Assert.assertEquals(rezultat.get(0).getId(), UT_PICE_PORUDZBINE_ID_1);
        Assert.assertEquals(rezultat.get(0).getNapomena(), UT_PICE_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.get(0).getKolicina(), UT_PICE_PORUDZBINE_KOLICINA);

    }

    @Test
    public void testFindOne() throws PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.findOne(UT_PICE_PORUDZBINE_ID_1);

        Assert.assertEquals(rezultat.getId(), UT_PICE_PORUDZBINE_ID_1);
        Assert.assertEquals(rezultat.getNapomena(), UT_PICE_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), UT_PICE_PORUDZBINE_KOLICINA);

    }

    @Test(expectedExceptions = {PicePorudzbineNotFoundException.class})
    public void testFindOne_Not_Found() throws PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.findOne(UT_NON_EXISTANT_ID);

        Assert.assertNull(rezultat);

        verify(picePorudzbineRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);
    }

    @Test(priority = 1)
    public void testDodajPicePorudzbine() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, PiceNotFoundException, NepozitivnaKolicinaException {

        PicePorudzbine rezultat = picePorudzbineService.dodajPicePorudzbine(UT_NOVO_PICE_PORUDZBINE_DTO);

        Assert.assertEquals(rezultat.getNapomena(), UT_NOVO_PICE_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), UT_NOVO_PICE_PORUDZBINE_KOLICINA);
        Assert.assertEquals(rezultat.getPice().getId(), UT_PICE_ID);
        Assert.assertEquals(rezultat.getStatusPica(), StatusPica.KREIRANO);
        Assert.assertEquals(rezultat.getPorudzbina().getStatusPorudzbine(), StatusPorudzbine.KREIRANO);

    }

    @Test(expectedExceptions = {PorudzbinaNotFoundException.class})
    public void testDodajPicePorudzbine_Porudzbina_Not_Found() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, NepozitivnaKolicinaException, PiceNotFoundException {
        Pice pice = new Pice();
        pice.setId(UT_PICE_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(UT_NOVO_PICE_PORUDZBINE_ID)
                .kolicina(UT_NOVO_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_NOVO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(UT_NON_EXISTANT_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.dodajPicePorudzbine(picePorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);
    }

    @Test(expectedExceptions = {PiceNotFoundException.class})
    public void testDodajPicePorudzbine_Pice_Not_Found() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, NepozitivnaKolicinaException, PiceNotFoundException {
        Pice pice = new Pice();
        pice.setId(UT_NON_EXISTANT_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(UT_NOVO_PICE_PORUDZBINE_ID)
                .kolicina(UT_NOVO_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_NOVO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(UT_NOVO_PICE_PORUDZBINE_PORUDZBINA)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.dodajPicePorudzbine(picePorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NOVO_PICE_PORUDZBINE_PORUDZBINA);
        verify(piceRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);
    }

    @Test(expectedExceptions = {PorudzbinaNaplacenaException.class})
    public void testDodajPicePorudzbine_Porudzbina_Naplacena() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, NepozitivnaKolicinaException, PiceNotFoundException {
        Pice pice = new Pice();
        pice.setId(UT_PICE_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(UT_NOVO_PICE_PORUDZBINE_ID)
                .kolicina(UT_NOVO_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_NOVO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(UT_NAPLACENA_PORUDZBINA_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.dodajPicePorudzbine(picePorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);
    }

    @Test(expectedExceptions = {NepozitivnaKolicinaException.class})
    public void testDodajPicePorudzbine_Nepozitivna_Kolicina() throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, NepozitivnaKolicinaException, PiceNotFoundException {
        Pice pice = new Pice();
        pice.setId(UT_PICE_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(UT_NOVO_PICE_PORUDZBINE_ID)
                .kolicina(-100.0)
                .napomena(UT_NOVO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(UT_NOVO_PICE_PORUDZBINE_PORUDZBINA)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.dodajPicePorudzbine(picePorudzbineDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NOVO_PICE_PORUDZBINE_PORUDZBINA);
    }

    @Test
    public void testIzmeniPicePorudzbine() throws  PicePorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {
        Pice pice = new Pice();
        pice.setId(UT_PICE_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(UT_PICE_PORUDZBINE_ID_1)
                .kolicina(UT_IZMENJENO_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_IZMENJENO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(UT_KREIRANA_PORUDZBINA_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.izmeniPicePorudzbine(picePorudzbineDTO);

        Assert.assertEquals(rezultat.getNapomena(), UT_IZMENJENO_PICE_PORUDZBINE_NAPOMENA);
        Assert.assertEquals(rezultat.getKolicina(), UT_IZMENJENO_PICE_PORUDZBINE_KOLICINA);
        Assert.assertEquals(rezultat.getPice().getId(), UT_PICE_ID);
        Assert.assertEquals(rezultat.getStatusPica(), StatusPica.KREIRANO);
        Assert.assertEquals(rezultat.getPorudzbina().getUkupnaCena(), 720.0); //3 artikla od po 240

    }

    @Test(expectedExceptions = {PicePorudzbineNotFoundException.class})
    public void testIzmeniPicePorudzbine_Not_Found() throws PicePorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Pice pice = new Pice();
        pice.setId(UT_PICE_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(UT_NON_EXISTANT_ID)
                .kolicina(UT_IZMENJENO_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_IZMENJENO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(UT_KREIRANA_PORUDZBINA_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.izmeniPicePorudzbine(picePorudzbineDTO);


        verify(picePorudzbineRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);
    }

    @Test(expectedExceptions = {NeodgovarajuciStatusException.class})
    public void testIzmeniPicePorudzbine_Pripremljeno() throws PicePorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Pice pice = new Pice();
        pice.setId(UT_PICE_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(UT_PRIPREMLJENO_PICE_PORUDZBINE_ID)
                .kolicina(UT_IZMENJENO_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_IZMENJENO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(UT_KREIRANA_PORUDZBINA_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.izmeniPicePorudzbine(picePorudzbineDTO);


        verify(picePorudzbineRepositoryMock, times(1)).findOneById(UT_PRIPREMLJENO_PICE_PORUDZBINE_ID);
    }

    @Test(expectedExceptions = {NepozitivnaKolicinaException.class})
    public void testIzmeniPicePorudzbine_Nepozitivna_Kolicina() throws PicePorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {

        Pice pice = new Pice();
        pice.setId(UT_PICE_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        PiceDTO piceDTO = new PiceDTO(pice);

        PicePorudzbineDTO picePorudzbineDTO = PicePorudzbineDTO.builder()
                .id(UT_PICE_PORUDZBINE_ID_1)
                .kolicina(-100.0)
                .napomena(UT_IZMENJENO_PICE_PORUDZBINE_NAPOMENA)
                .pice(piceDTO)
                .porudzbinaId(UT_KREIRANA_PORUDZBINA_ID)
                .statusPica(StatusPica.KREIRANO)
                .build();
        PicePorudzbine rezultat = picePorudzbineService.izmeniPicePorudzbine(picePorudzbineDTO);


        verify(picePorudzbineRepositoryMock, times(1)).findOneById(UT_PICE_PORUDZBINE_ID_1);
    }

    @Test (priority = 2)
    public void testObrisiPicePorudzbine() throws PicePorudzbineNotFoundException, NeodgovarajuciStatusException {

        boolean rezultat = picePorudzbineService.obrisiPicePorudzbine(UT_PICE_PORUDZBINE_ID_2);

        Assert.assertTrue(rezultat);

        verify(picePorudzbineRepositoryMock, times(1)).deleteById(UT_PICE_PORUDZBINE_ID_2);

    }

    @Test(priority = 2, expectedExceptions = {PicePorudzbineNotFoundException.class})
    public void testObrisiPicePorudzbine_Not_Found() throws PicePorudzbineNotFoundException, NeodgovarajuciStatusException {

        boolean rezultat = picePorudzbineService.obrisiPicePorudzbine(UT_NON_EXISTANT_ID);

        verify(picePorudzbineRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);
    }

    @Test(priority = 2, expectedExceptions = {NeodgovarajuciStatusException.class})
    public void testObrisiPicePorudzbine_Pripremljeno() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {

        boolean rezultat = picePorudzbineService.obrisiPicePorudzbine(UT_PRIPREMLJENO_PICE_PORUDZBINE_ID);

        verify(picePorudzbineRepositoryMock, times(1)).findOneById(UT_PRIPREMLJENO_PICE_PORUDZBINE_ID);
    }

    @Test
    public void testPripremiPice() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.pripremiPice(UT_PICE_PORUDZBINE_ID_1);

        Assert.assertEquals(rezultat.getStatusPica(), StatusPica.PRIPREMLJENO);
    }

    @Test(expectedExceptions = NeodgovarajuciStatusException.class)
    public void testPripremiPice_Status_Nije_KREIRANO() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.pripremiPice(UT_PRIPREMLJENO_PICE_PORUDZBINE_ID);


        verify(picePorudzbineRepositoryMock, times(1)).findOneById(UT_PRIPREMLJENO_PICE_PORUDZBINE_ID);
    }

    @Test(expectedExceptions = PicePorudzbineNotFoundException.class)
    public void testPripremiPice_Pice_Not_Found() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.pripremiPice(UT_NON_EXISTANT_ID);


        verify(picePorudzbineRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);
    }

    @Test
    public void testDostaviPice() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.dostaviPice(UT_PRIPREMLJENO_PICE_PORUDZBINE_ID);

        Assert.assertEquals(rezultat.getStatusPica(), StatusPica.DOSTAVLJENO);
        Assert.assertEquals(rezultat.getPorudzbina().getStatusPorudzbine(), StatusPorudzbine.DOSTAVLJENO);

    }

    @Test(expectedExceptions = NeodgovarajuciStatusException.class)
    public void testDostaviPice_Status_Nije_PRIPREMLJENO() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.dostaviPice(UT_PICE_PORUDZBINE_ID_1);
        verify(picePorudzbineRepositoryMock, times(1)).findOneById(UT_PICE_PORUDZBINE_ID_1);
    }

    @Test(expectedExceptions = PicePorudzbineNotFoundException.class)
    public void testDostaviPice_Pice_Not_Found() throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine rezultat = picePorudzbineService.dostaviPice(UT_NON_EXISTANT_ID);
        verify(picePorudzbineRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);
    }

}
