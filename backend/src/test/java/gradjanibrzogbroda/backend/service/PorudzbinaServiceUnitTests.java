package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.*;
import gradjanibrzogbroda.backend.exceptions.*;
import gradjanibrzogbroda.backend.repository.*;
import lombok.SneakyThrows;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static gradjanibrzogbroda.backend.constants.PicePorudzbineUnitTestConstants.UT_PICE_PORUDZBINE_ID_2;
import static gradjanibrzogbroda.backend.constants.PorudzbineUnitTestConstants.*;
import static gradjanibrzogbroda.backend.constants.PorudzbineUnitTestConstants.UT_DODATO_PICE_PORUDZBINE_ID;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PorudzbinaServiceUnitTests {
    @Mock
    private PorudzbinaRepository porudzbinaRepositoryMock;
    @Mock
    private StoRepository stoRepositoryMock;
    @Mock
    private PicePorudzbineRepository picePorudzbineRepositoryMock;
    @Mock
    private JeloPorudzbineRepository jeloPorudzbineRepositoryMock;
    @Mock
    private JeloRepository jeloRepositoryMock;
    @Mock
    private PiceRepository piceRepositoryMock;
    @Mock
    private KonobarRepository konobarRepositoryMock;

    @Mock
    private JeloPorudzbineService jeloPorudzbineServiceMock;

    @Mock
    private PicePorudzbineService picePorudzbineServiceMock;

    @InjectMocks
    @Autowired
    private PorudzbinaService porudzbinaService;

    @SneakyThrows
    @BeforeMethod
    public void initMock() {
        openMocks(this);

        Jelo jelo = new Jelo();
        jelo.setId(UT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);

        Pice pice = new Pice();
        pice.setId(UT_PICE_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        Sto sto = Sto.builder()
                .id(UT_STO_ID)
                .obrisan(false)
                .brojMesta(4)
                .identificationNumber(UT_STO_IDENTIF_NUM)
                .nazivStola("sto1")
                .build();
        ArrayList<Porudzbina> svePorudzbine = new ArrayList<Porudzbina>();
        ArrayList<Porudzbina> zaSankeraKuvara = new ArrayList<Porudzbina>();

        //KREIRANA, 1 jelo, 1 pice
        Porudzbina porudzbina1 = new Porudzbina();
        porudzbina1.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        porudzbina1.setUkupnaCena(UT_KREIRANA_PORUDZBINA_CENA);
        porudzbina1.setId(UT_KREIRANA_PORUDZBINA_ID_1);
        porudzbina1.setSto(sto);
        porudzbina1.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());
        porudzbina1.setPicePorudzbine(new ArrayList<PicePorudzbine>());
        porudzbina1.setDatumVreme(LocalDateTime.now());

        JeloPorudzbine jp1 = JeloPorudzbine.builder()
                .id(UT_JELO_PORUDZBINE_ID_1)
                .kolicina(UT_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jelo)
                .porudzbina(porudzbina1)
                .statusJela(UT_JELO_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();

        PicePorudzbine pp1 = PicePorudzbine.builder()
                .id(UT_PICE_PORUDZBINE_ID_1)
                .kolicina(UT_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_PICE_PORUDZBINE_NAPOMENA)
                .pice(pice)
                .porudzbina(porudzbina1)
                .statusPica(UT_PICE_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();

        porudzbina1.getJelaPorudzbine().add(jp1);
        porudzbina1.getPicePorudzbine().add(pp1);

        svePorudzbine.add(porudzbina1);
        zaSankeraKuvara.add(porudzbina1);

        //KREIRANA, 1 jelo, 1 pice
        Porudzbina porudzbina2 = new Porudzbina();
        porudzbina2.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        porudzbina2.setUkupnaCena(UT_KREIRANA_PORUDZBINA_CENA);
        porudzbina2.setId(UT_KREIRANA_PORUDZBINA_ID_2);
        porudzbina2.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());
        porudzbina2.setPicePorudzbine(new ArrayList<PicePorudzbine>());
        porudzbina2.setSto(sto);
        porudzbina2.setDatumVreme(LocalDateTime.now());

        JeloPorudzbine jp2 = JeloPorudzbine.builder()
                .id(UT_JELO_PORUDZBINE_ID_2)
                .kolicina(UT_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jelo)
                .porudzbina(porudzbina2)
                .statusJela(UT_JELO_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();

        PicePorudzbine pp2 = PicePorudzbine.builder()
                .id(UT_PICE_PORUDZBINE_ID_1)
                .kolicina(UT_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_PICE_PORUDZBINE_NAPOMENA)
                .pice(pice)
                .porudzbina(porudzbina2)
                .statusPica(UT_PICE_PORUDZBINE_STATUS)
                .obrisan(false)
                .build();

        porudzbina2.getJelaPorudzbine().add(jp2);
        porudzbina2.getPicePorudzbine().add(pp2);

        svePorudzbine.add(porudzbina2);
        zaSankeraKuvara.add(porudzbina2);

        Porudzbina porudzbinaDostavljena = new Porudzbina();
        porudzbinaDostavljena.setStatusPorudzbine(StatusPorudzbine.DOSTAVLJENO);
        porudzbinaDostavljena.setUkupnaCena(240.0);
        porudzbinaDostavljena.setId(UT_DOSTAVLJENA_PORUDZBINA_ID);
        porudzbinaDostavljena.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());
        porudzbinaDostavljena.setPicePorudzbine(new ArrayList<PicePorudzbine>());
        porudzbinaDostavljena.setSto(sto);

        JeloPorudzbine jp3 = JeloPorudzbine.builder()
                .id(UT_JELO_PORUDZBINE_ID_3)
                .kolicina(UT_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jelo)
                .porudzbina(porudzbinaDostavljena)
                .statusJela(StatusJela.DOSTAVLJENO)
                .obrisan(false)
                .build();
        porudzbinaDostavljena.getJelaPorudzbine().add(jp3);

        svePorudzbine.add(porudzbinaDostavljena);
        ArrayList<Porudzbina> dostavljene = new ArrayList<Porudzbina>();
        dostavljene.add(porudzbinaDostavljena);

        Porudzbina porudzbinaNaplacena = new Porudzbina();
        porudzbinaNaplacena.setStatusPorudzbine(StatusPorudzbine.NAPLACENO);
        porudzbinaNaplacena.setUkupnaCena(240.0);
        porudzbinaNaplacena.setId(UT_NAPLACENA_PORUDZBINA_ID);
        porudzbinaNaplacena.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());
        porudzbinaNaplacena.setPicePorudzbine(new ArrayList<PicePorudzbine>());
        porudzbinaNaplacena.setSto(sto);

        JeloPorudzbine jp4 = JeloPorudzbine.builder()
                .id(UT_JELO_PORUDZBINE_ID_4)
                .kolicina(UT_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jelo)
                .porudzbina(porudzbina1)
                .statusJela(StatusJela.DOSTAVLJENO)
                .obrisan(false)
                .build();
        porudzbinaNaplacena.getJelaPorudzbine().add(jp4);

        svePorudzbine.add(porudzbinaNaplacena);

        JeloPorudzbine novoJP = JeloPorudzbine.builder()
                .id(UT_NOVO_JELO_PORUDZBINE_ID)
                .kolicina(UT_NOVO_JELO_PORUDZBINE_KOLICINA)
                .napomena(UT_NOVO_JELO_PORUDZBINE_NAPOMENA)
                .jelo(jelo)
                .porudzbina(porudzbina1)
                .statusJela(StatusJela.KREIRANO)
                .obrisan(false)
                .build();

        PicePorudzbine novoPP = PicePorudzbine.builder()
                .id(UT_NOVO_PICE_PORUDZBINE_ID)
                .kolicina(UT_NOVO_PICE_PORUDZBINE_KOLICINA)
                .napomena(UT_NOVO_PICE_PORUDZBINE_NAPOMENA)
                .pice(pice)
                .porudzbina(porudzbina1)
                .statusPica(StatusPica.KREIRANO)
                .obrisan(false)
                .build();

        given(porudzbinaRepositoryMock.findAll()).willReturn(svePorudzbine);
        given(porudzbinaRepositoryMock.findAllZaSankera()).willReturn(zaSankeraKuvara);
        given(porudzbinaRepositoryMock.findAllByStatusPorudzbine(StatusPorudzbine.KREIRANO)).willReturn(zaSankeraKuvara);
        given(porudzbinaRepositoryMock.findAllByStatusPorudzbine(StatusPorudzbine.DOSTAVLJENO)).willReturn(dostavljene);
        given(porudzbinaRepositoryMock.findOneById(UT_KREIRANA_PORUDZBINA_ID_1)).willReturn(porudzbina1);
        given(porudzbinaRepositoryMock.findOneById(UT_KREIRANA_PORUDZBINA_ID_2)).willReturn(porudzbina2);
        given(porudzbinaRepositoryMock.findOneById(UT_DOSTAVLJENA_PORUDZBINA_ID)).willReturn(porudzbinaDostavljena);
        given(porudzbinaRepositoryMock.findOneById(UT_NAPLACENA_PORUDZBINA_ID)).willReturn(porudzbinaNaplacena);
        when(porudzbinaRepositoryMock.save(Mockito.any(Porudzbina.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        given(stoRepositoryMock.findOneByIdentificationNumber(UT_STO_IDENTIF_NUM)).willReturn(sto);

        given(jeloRepositoryMock.findOneById(UT_JELO_ID)).willReturn(jelo);

        given(piceRepositoryMock.findOneById(UT_PICE_ID)).willReturn(pice);

        given(jeloPorudzbineServiceMock.dodajJeloPorudzbine(UT_NOVO_JELO_PORUDZBINE_DTO)).willReturn(novoJP);

        given(picePorudzbineServiceMock.dodajPicePorudzbine(UT_NOVO_PICE_PORUDZBINE_DTO)).willReturn(novoPP);

    }

    @Test
    public void testFindAll(){
        ArrayList<Porudzbina> rezultat = (ArrayList<Porudzbina>) porudzbinaService.findAll();

        Assert.assertEquals(rezultat.size(), 4);

        Assert.assertEquals(rezultat.get(0).getId(), UT_KREIRANA_PORUDZBINA_ID_1);
        Assert.assertEquals(rezultat.get(0).getUkupnaCena(), UT_KREIRANA_PORUDZBINA_CENA);
        Assert.assertEquals(rezultat.get(0).getStatusPorudzbine(), StatusPorudzbine.KREIRANO);

        verify(porudzbinaRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testFindAllZaSankera(){
        ArrayList<Porudzbina> rezultat = (ArrayList<Porudzbina>) porudzbinaService.findAllZaSankera();

        Assert.assertEquals(rezultat.size(), 2);

        Assert.assertEquals(rezultat.get(0).getId(), UT_KREIRANA_PORUDZBINA_ID_1);
        Assert.assertEquals(rezultat.get(0).getUkupnaCena(), UT_KREIRANA_PORUDZBINA_CENA);
        Assert.assertEquals(rezultat.get(0).getStatusPorudzbine(), StatusPorudzbine.KREIRANO);
        verify(porudzbinaRepositoryMock, times(1)).findAllZaSankera();
    }

    @Test
    public void testFindAllZaKuvara(){
        ArrayList<Porudzbina> rezultat = (ArrayList<Porudzbina>) porudzbinaService.findAllZaKuvara();

        Assert.assertEquals(rezultat.size(), 2);

        Assert.assertEquals(rezultat.get(0).getId(), UT_KREIRANA_PORUDZBINA_ID_1);
        Assert.assertEquals(rezultat.get(0).getUkupnaCena(), UT_KREIRANA_PORUDZBINA_CENA);
        Assert.assertEquals(rezultat.get(0).getStatusPorudzbine(), StatusPorudzbine.KREIRANO);
        verify(porudzbinaRepositoryMock, times(1)).findAllByStatusPorudzbine(StatusPorudzbine.KREIRANO);
    }
    @Test
    public void testFindOne(){
        Porudzbina rezultat = porudzbinaService.findOne(UT_KREIRANA_PORUDZBINA_ID_1);

        Assert.assertEquals(rezultat.getId(), UT_KREIRANA_PORUDZBINA_ID_1);
        Assert.assertEquals(rezultat.getUkupnaCena(), UT_KREIRANA_PORUDZBINA_CENA);
        Assert.assertEquals(rezultat.getStatusPorudzbine(), StatusPorudzbine.KREIRANO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_KREIRANA_PORUDZBINA_ID_1);
    }

    @Test
    public void testFindAllByStatusPorudzbine(){
        ArrayList<Porudzbina> rezultat = (ArrayList<Porudzbina>) porudzbinaService.findAllByStatusPorudzbine(StatusPorudzbine.DOSTAVLJENO);

        Assert.assertEquals(rezultat.size(), 1);

        Assert.assertEquals(rezultat.get(0).getId(), UT_DOSTAVLJENA_PORUDZBINA_ID);
        Assert.assertEquals(rezultat.get(0).getUkupnaCena(), 240.0);
        Assert.assertEquals(rezultat.get(0).getStatusPorudzbine(), StatusPorudzbine.DOSTAVLJENO);
        verify(porudzbinaRepositoryMock, times(1)).findAllByStatusPorudzbine(StatusPorudzbine.DOSTAVLJENO);
    }

    @Test
    public void testSpremiPica() throws PorudzbinaNotFoundException {
        Porudzbina rezultat = porudzbinaService.spremiPica(UT_KREIRANA_PORUDZBINA_ID_2);

        Assert.assertEquals(rezultat.getPicePorudzbine().get(0).getStatusPica(), StatusPica.PRIPREMLJENO);
        Assert.assertEquals(rezultat.getStatusPorudzbine(), StatusPorudzbine.KREIRANO);
        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_KREIRANA_PORUDZBINA_ID_2);
    }

    @Test(expectedExceptions = PorudzbinaNotFoundException.class)
    public void testSpremiPica_Porudzbina_Not_Found() throws PorudzbinaNotFoundException {
        Porudzbina rezultat = porudzbinaService.spremiPica(UT_NON_EXISTANT_ID);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);
    }

    @Test(priority = 1)
    public void testPreuzmiPorudzbinu() throws PorudzbinaNotFoundException, NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        Porudzbina rezultat = porudzbinaService.preuzmiPorudzbinu(UT_KREIRANA_PORUDZBINA_ID_2);

        Assert.assertEquals(rezultat.getStatusPorudzbine(), StatusPorudzbine.PREUZETO);
        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_KREIRANA_PORUDZBINA_ID_2);
    }

    @Test(priority = 1, expectedExceptions = PorudzbinaNotFoundException.class)
    public void testPreuzmiPorudzbinu_Porudzbina_Not_Found() throws PorudzbinaNotFoundException, NeodgovarajuciStatusException, JeloPorudzbineNotFoundException {
        Porudzbina rezultat = porudzbinaService.preuzmiPorudzbinu(UT_NON_EXISTANT_ID);

        verify(porudzbinaRepositoryMock, times(2)).findOneById(UT_NON_EXISTANT_ID);
    }

    @Test(priority = 1)
    public void testIzmeniPorudzbinu() throws PorudzbinaNotFoundException, NeodgovarajuciStatusException, JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, PorudzbinaNaplacenaException, PiceNotFoundException, JeloNotFoundException {
        Jelo jelo = new Jelo();
        jelo.setId(UT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);



        Pice pice = new Pice();
        pice.setId(UT_PICE_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        Sto sto = Sto.builder()
                .id(UT_STO_ID)
                .obrisan(false)
                .brojMesta(4)
                .identificationNumber(UT_STO_IDENTIF_NUM)
                .nazivStola("sto1")
                .build();


        //KREIRANA, 1 jelo, 1 pice
        Porudzbina porudzbina1 = new Porudzbina();
        porudzbina1.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        porudzbina1.setUkupnaCena(UT_KREIRANA_PORUDZBINA_CENA);
        porudzbina1.setId(UT_KREIRANA_PORUDZBINA_ID_1);
        porudzbina1.setNapomena("Nova napomena");
        porudzbina1.setSto(sto);
        porudzbina1.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());
        porudzbina1.setPicePorudzbine(new ArrayList<PicePorudzbine>());
        porudzbina1.setDatumVreme(LocalDateTime.now());

        JeloPorudzbine jp1 = JP1;
        jp1.setJelo(jelo);
        jp1.setPorudzbina(porudzbina1);

        PicePorudzbine pp1 = PP1;
        pp1.setPice(pice);
        pp1.setPorudzbina(porudzbina1);

        porudzbina1.getJelaPorudzbine().add(jp1);
        porudzbina1.getPicePorudzbine().add(pp1);

        PorudzbinaDTO porudzbinaDTO = new PorudzbinaDTO(porudzbina1);

        JeloPorudzbineDTO jeloPorudzbineDTO = UT_NOVO_JELO_PORUDZBINE_DTO;
        jeloPorudzbineDTO.setJelo(new JeloDTO(jelo));
        jeloPorudzbineDTO.setPorudzbinaId(UT_KREIRANA_PORUDZBINA_ID_1);

        PicePorudzbineDTO picePorudzbineDTO = UT_NOVO_PICE_PORUDZBINE_DTO;
        picePorudzbineDTO.setPice(new PiceDTO(pice));
        picePorudzbineDTO.setPorudzbinaId(UT_KREIRANA_PORUDZBINA_ID_1);

        porudzbinaDTO.getPicaPorudzbine().add(picePorudzbineDTO);
        porudzbinaDTO.getJelaPorudzbine().add(jeloPorudzbineDTO);

        Porudzbina rezultat = porudzbinaService.izmeniPorudzbinu(porudzbinaDTO);

        Assert.assertNotNull(rezultat);
        Assert.assertEquals(rezultat.getJelaPorudzbine().size(), 2);
        Assert.assertEquals(rezultat.getJelaPorudzbine().get(1).getId(), UT_NOVO_JELO_PORUDZBINE_ID);
        Assert.assertEquals(rezultat.getPicePorudzbine().size(), 2);
        Assert.assertEquals(rezultat.getPicePorudzbine().get(1).getId(), UT_NOVO_PICE_PORUDZBINE_ID);
        Assert.assertEquals(rezultat.getStatusPorudzbine(), StatusPorudzbine.KREIRANO);

        Assert.assertEquals(rezultat.getNapomena(), "Nova napomena");

        verify(jeloPorudzbineServiceMock, times(1)).dodajJeloPorudzbine(jeloPorudzbineDTO);
        verify(picePorudzbineServiceMock, times(1)).dodajPicePorudzbine(picePorudzbineDTO);
    }


    @Test( expectedExceptions = PorudzbinaNotFoundException.class)
    public void testIzmeniPorudzbinu_Porudzbina_Not_Found() throws PorudzbinaNotFoundException, NeodgovarajuciStatusException, JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, PorudzbinaNaplacenaException, PiceNotFoundException, JeloNotFoundException {
        Sto sto = Sto.builder()
                .id(UT_STO_ID)
                .obrisan(false)
                .brojMesta(4)
                .identificationNumber(UT_STO_IDENTIF_NUM)
                .nazivStola("sto1")
                .build();


        Porudzbina porudzbina1 = new Porudzbina();
        porudzbina1.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        porudzbina1.setUkupnaCena(UT_KREIRANA_PORUDZBINA_CENA);
        porudzbina1.setId(UT_NON_EXISTANT_ID);
        porudzbina1.setNapomena("Nova napomena");
        porudzbina1.setSto(sto);
        porudzbina1.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());
        porudzbina1.setPicePorudzbine(new ArrayList<PicePorudzbine>());
        porudzbina1.setDatumVreme(LocalDateTime.now());

        PorudzbinaDTO porudzbinaDTO = new PorudzbinaDTO(porudzbina1);

        Porudzbina rezultat = porudzbinaService.izmeniPorudzbinu(porudzbinaDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);
    }

    @Test( expectedExceptions = PorudzbinaNaplacenaException.class)
    public void testIzmeniPorudzbinu_Porudzbina_Naplacena() throws PorudzbinaNotFoundException, NeodgovarajuciStatusException, JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, PorudzbinaNaplacenaException, PiceNotFoundException, JeloNotFoundException {
        Sto sto = Sto.builder()
                .id(UT_STO_ID)
                .obrisan(false)
                .brojMesta(4)
                .identificationNumber(UT_STO_IDENTIF_NUM)
                .nazivStola("sto1")
                .build();


        Porudzbina porudzbina1 = new Porudzbina();
        porudzbina1.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        porudzbina1.setUkupnaCena(UT_KREIRANA_PORUDZBINA_CENA);
        porudzbina1.setId(UT_NAPLACENA_PORUDZBINA_ID);
        porudzbina1.setNapomena("Nova napomena");
        porudzbina1.setSto(sto);
        porudzbina1.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());
        porudzbina1.setPicePorudzbine(new ArrayList<PicePorudzbine>());
        porudzbina1.setDatumVreme(LocalDateTime.now());

        PorudzbinaDTO porudzbinaDTO = new PorudzbinaDTO(porudzbina1);

        Porudzbina rezultat = porudzbinaService.izmeniPorudzbinu(porudzbinaDTO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NAPLACENA_PORUDZBINA_ID);
    }

    @Test(priority = 1)
    public void testNapraviPorudzbinu() {
        Jelo jelo = new Jelo();
        jelo.setId(UT_JELO_ID);
        jelo.setTrenutnaCena(UT_JELO_CENA);
        jelo.setNaziv(UT_JELO_NAZIV);
        jelo.setTipJela(UT_JELO_TIP);
        jelo.setKategorijaJela(UT_JELO_KAT);

        Pice pice = new Pice();
        pice.setId(UT_PICE_ID);
        pice.setTrenutnaCena(UT_PICE_CENA);
        pice.setNaziv(UT_PICE_NAZIV);

        Sto sto = Sto.builder()
                .id(UT_STO_ID)
                .obrisan(false)
                .brojMesta(4)
                .identificationNumber(UT_STO_IDENTIF_NUM)
                .nazivStola("sto1")
                .build();

        Porudzbina porudzbina1 = new Porudzbina();
        porudzbina1.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        porudzbina1.setUkupnaCena(0.0);
        porudzbina1.setId(UT_NOVA_PORUDZBINA_ID);
        porudzbina1.setNapomena("Nova napomena");
        porudzbina1.setSto(sto);
        porudzbina1.setJelaPorudzbine(new ArrayList<JeloPorudzbine>());
        porudzbina1.setPicePorudzbine(new ArrayList<PicePorudzbine>());
        porudzbina1.setDatumVreme(LocalDateTime.now());

        JeloPorudzbine jp1 = JP1;
        JP1.setId(UT_DODATO_JELO_PORUDZBINE_ID);
        jp1.setJelo(jelo);
        jp1.setKolicina(1.0);
        jp1.setPorudzbina(porudzbina1);

        PicePorudzbine pp1 = PP1;
        pp1.setId(UT_DODATO_PICE_PORUDZBINE_ID);
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
        Assert.assertEquals(rezultat.getUkupnaCena(), 480.0);

        Assert.assertEquals(rezultat.getNapomena(), "Nova napomena");

    }

    @Test (priority = 2)
    public void testObrisiPorudzbinuPoId() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        boolean rezultat = porudzbinaService.obrisiPorudzbinuPoId(UT_KREIRANA_PORUDZBINA_ID_1);

        Assert.assertTrue(rezultat);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_KREIRANA_PORUDZBINA_ID_1);
        verify(porudzbinaRepositoryMock, times(1)).deleteById(UT_KREIRANA_PORUDZBINA_ID_1);

    }

    @Test (priority = 2, expectedExceptions = NeodgovarajuciStatusException.class)
    public void testObrisiPorudzbinuPoId_Status_Error() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        boolean rezultat = porudzbinaService.obrisiPorudzbinuPoId(UT_NAPLACENA_PORUDZBINA_ID);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NAPLACENA_PORUDZBINA_ID);

    }

    @Test (priority = 2, expectedExceptions = PorudzbinaNotFoundException.class)
    public void testObrisiPorudzbinuPoId_Porudzbina_Not_Found() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        boolean rezultat = porudzbinaService.obrisiPorudzbinuPoId(UT_NON_EXISTANT_ID);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);

    }

    @Test
    public void testNaplatiPorudzbinu() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        Porudzbina rezultat = porudzbinaService.naplatiPorudzbinu(UT_DOSTAVLJENA_PORUDZBINA_ID);

        Assert.assertNotNull(rezultat);
        Assert.assertEquals(rezultat.getStatusPorudzbine(), StatusPorudzbine.NAPLACENO);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_DOSTAVLJENA_PORUDZBINA_ID);

    }

    @Test ( expectedExceptions = NeodgovarajuciStatusException.class)
    public void testNaplatiPorudzbinu_Status_Error() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        Porudzbina rezultat = porudzbinaService.naplatiPorudzbinu(UT_NAPLACENA_PORUDZBINA_ID);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NAPLACENA_PORUDZBINA_ID);

    }

    @Test ( expectedExceptions = PorudzbinaNotFoundException.class)
    public void testNaplatiPorudzbinu_Porudzbina_Not_Found() throws NeodgovarajuciStatusException, PorudzbinaNotFoundException {

        Porudzbina rezultat = porudzbinaService.naplatiPorudzbinu(UT_NON_EXISTANT_ID);

        verify(porudzbinaRepositoryMock, times(1)).findOneById(UT_NON_EXISTANT_ID);

    }





}
