package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.constants.ZaposleniConstants;
import gradjanibrzogbroda.backend.domain.Menadzer;
import gradjanibrzogbroda.backend.domain.Plata;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;
import gradjanibrzogbroda.backend.repository.ZaposleniRepository;
import gradjanibrzogbroda.exception.UserAlreadyExistsException;
import gradjanibrzogbroda.exception.UserNotFoundException;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ZaposleniServiceUnitTests extends AbstractTestNGSpringContextTests {
    @Mock
    ZaposleniRepository zaposleniRepositoryMock;

    @InjectMocks
    @Autowired
    ZaposleniService zaposleniService;

    @BeforeMethod
    public void initMock() {
        openMocks(this);

        given(zaposleniRepositoryMock.findOneById(ZaposleniConstants.UPDATED_ZAPOSLENI_ID))
                .willReturn(ZaposleniConstants.UPDATED_ZAPOSLENI);
        given(zaposleniRepositoryMock.findAll())
                .willReturn(ZaposleniConstants.ZAPOSLENI);
    }

    @Test
    public void testIzmeniPlatu() {
        given(zaposleniRepositoryMock.save(Mockito.any()))
        .willReturn(ZaposleniConstants.UPDATED_ZAPOSLENI);

        // NEW_PLATA_DTO sadrzi podatke nove plate
        Zaposleni found = zaposleniService.izmeniPlatu(ZaposleniConstants.NEW_PLATA_DTO);

        verify(zaposleniRepositoryMock, times(1)).findOneById(ZaposleniConstants.UPDATED_ZAPOSLENI_ID);
        verify(zaposleniRepositoryMock, times(1)).save(ZaposleniConstants.UPDATED_ZAPOSLENI);

        // servis belezi iznos nove plate PlataDTO.visinaPlate u polje
        // Zaposleni.trenutnaPlata,
        // trenutnaPlata mockovanog objekta treba da bude jednaka kao i plata iz
        // konstante NEW_PLATA_DTO.visinaPlate
        Assert.assertEquals(found.getTrenutnaPlata(), ZaposleniConstants.NEW_PLATA_DTO.getVisinaPlate());
    }

    @Test
    public void testGetAllZaposleni() {
        List<ZaposleniDTO> found = zaposleniService.getAllZaposleni();

        verify(zaposleniRepositoryMock, times(1)).findAll();

        Assert.assertEquals(found.size(), ZaposleniConstants.ZAPOSLENI.size());
        Assert.assertEquals(found.get(1).getIdentificationNumber(),
                ZaposleniConstants.ZAPOSLENI.get(1).getIdentificationNumber());
        Assert.assertEquals(found.get(0).getIdentificationNumber(),
                ZaposleniConstants.ZAPOSLENI.get(0).getIdentificationNumber());
    }

    @Test
    public void testAddZaposleni() throws UserAlreadyExistsException {
        given(zaposleniRepositoryMock.findOneByIdentificationNumber(ZaposleniConstants.NEW_ZAPOSLENI_DTO.getIdentificationNumber()))
                .willReturn(null);
        given(zaposleniRepositoryMock.save(Mockito.any()))
                .willReturn(ZaposleniConstants.NEW_ZAPOSLENI);

        Zaposleni z = zaposleniService.addZaposleni(ZaposleniConstants.NEW_ZAPOSLENI_DTO);

        verify(zaposleniRepositoryMock, times(1)).findOneByIdentificationNumber(Mockito.anyString());

        Assert.assertEquals(z.getIme(), ZaposleniConstants.NEW_ZAPOSLENI_DTO.getIme());
        Assert.assertEquals(z.getPrezime(), ZaposleniConstants.NEW_ZAPOSLENI_DTO.getPrezime());
        Assert.assertEquals(z.getIdentificationNumber(),
                ZaposleniConstants.NEW_ZAPOSLENI_DTO.getIdentificationNumber());

    }

    @Test(expectedExceptions = {UserAlreadyExistsException.class})
    public void testAddZaposleniAlreadyExists() throws UserAlreadyExistsException {
        given(zaposleniRepositoryMock.findOneByIdentificationNumber(ZaposleniConstants.NEW_ZAPOSLENI_DTO.getIdentificationNumber()))
                .willReturn(new Menadzer());
        given(zaposleniRepositoryMock.save(Mockito.any()))
                .willReturn(ZaposleniConstants.NEW_ZAPOSLENI);

        Zaposleni z = zaposleniService.addZaposleni(ZaposleniConstants.NEW_ZAPOSLENI_DTO);

        verify(zaposleniRepositoryMock, times(1)).findOneByIdentificationNumber(Mockito.anyString());

        Assert.assertEquals(z.getIme(), ZaposleniConstants.NEW_ZAPOSLENI_DTO.getIme());
        Assert.assertEquals(z.getPrezime(), ZaposleniConstants.NEW_ZAPOSLENI_DTO.getPrezime());
        Assert.assertEquals(z.getIdentificationNumber(),
                ZaposleniConstants.NEW_ZAPOSLENI_DTO.getIdentificationNumber());

    }

    @Test
    public void testUpdateZaposleni() throws UserNotFoundException {
        given(zaposleniRepositoryMock.findOneByIdentificationNumber(ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getIdentificationNumber()))
                .willReturn(new Menadzer());
        given(zaposleniRepositoryMock.save(Mockito.any()))
                .willReturn(ZaposleniConstants.UPDATED_ZAPOSLENI);

        Zaposleni z = zaposleniService.updateZaposleni(ZaposleniConstants.UPDATED_ZAPOSLENI_DTO);

        verify(zaposleniRepositoryMock, times(1)).findOneByIdentificationNumber(Mockito.anyString());

        Assert.assertEquals(z.getIme(), ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getIme());
        Assert.assertEquals(z.getPrezime(), ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getPrezime());
        Assert.assertEquals(z.getIdentificationNumber(),
                ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getIdentificationNumber());
    }

    @Test(expectedExceptions = {UserNotFoundException.class})
    public void testUpdateZaposleniDoesntExist() throws UserNotFoundException {
        given(zaposleniRepositoryMock.findOneByIdentificationNumber(ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getIdentificationNumber()))
                .willReturn(null);
        given(zaposleniRepositoryMock.save(Mockito.any()))
                .willReturn(ZaposleniConstants.UPDATED_ZAPOSLENI);

        Zaposleni z = zaposleniService.updateZaposleni(ZaposleniConstants.UPDATED_ZAPOSLENI_DTO);

        verify(zaposleniRepositoryMock, times(1)).findOneByIdentificationNumber(Mockito.anyString());

        Assert.assertEquals(z.getIme(), ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getIme());
        Assert.assertEquals(z.getPrezime(), ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getPrezime());
        Assert.assertEquals(z.getIdentificationNumber(),
                ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getIdentificationNumber());
    }
}
