package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.constants.ZaposleniConstants;
import gradjanibrzogbroda.backend.domain.Predlog;
import gradjanibrzogbroda.backend.domain.Role;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;
import gradjanibrzogbroda.backend.exceptions.UserAlreadyExistsException;
import gradjanibrzogbroda.backend.exceptions.UserNotFoundException;
import gradjanibrzogbroda.backend.repository.ZaposleniRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.ArrayList;
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
        
        Zaposleni updatedZaposleni=new Zaposleni();
        updatedZaposleni.updateFields(ZaposleniConstants.UPDATED_ZAPOSLENI_DTO, new Role(ZaposleniConstants.UPDATED_ZAPOSLENI_ROLE));
        
        Zaposleni newZaposleni=new Zaposleni();
        newZaposleni.updateFields(ZaposleniConstants.NEW_ZAPOSLENI_DTO, new Role(ZaposleniConstants.NEW_ZAPOSLENI_ROLE));

        given(zaposleniRepositoryMock.findOneById(ZaposleniConstants.UPDATED_ZAPOSLENI_ID))
                .willReturn(updatedZaposleni);
        
        
        ArrayList<Zaposleni> zaposleni=new ArrayList<Zaposleni>();
        zaposleni.add(updatedZaposleni);
        zaposleni.add(newZaposleni);
        
        given(zaposleniRepositoryMock.findAll()).willReturn(zaposleni);
        
        given(zaposleniRepositoryMock.findOneByUsername(ZaposleniConstants.NEW_ZAPOSLENI_USERNAME))
        .willReturn(null);
        
        given(zaposleniRepositoryMock.findOneByUsername(ZaposleniConstants.UPDATED_ZAPOSLENI_USERNAME))
        .willReturn(updatedZaposleni);
        
        given(zaposleniRepositoryMock.findOneByUsername(ZaposleniConstants.FAKE_ZAPOSLENI_USERNAME))
        .willReturn(null);
        
        when(zaposleniRepositoryMock.save(any(Zaposleni.class))).thenAnswer(i -> i.getArguments()[0]);
        
    }

    @Test
    public void testIzmeniPlatu() {
    	
    	

        // NEW_PLATA_DTO sadrzi podatke nove plate
        Zaposleni found = zaposleniService.izmeniPlatu(ZaposleniConstants.NEW_PLATA_DTO);

        verify(zaposleniRepositoryMock, times(1)).findOneById(ZaposleniConstants.UPDATED_ZAPOSLENI_ID);
        verify(zaposleniRepositoryMock, times(1)).save(any());

        Assert.assertEquals(found.getTrenutnaPlata(), ZaposleniConstants.NEW_PLATA_DTO.getVisinaPlate());
    }

    @Test
    public void testGetAllZaposleni() {
        List<ZaposleniDTO> found = zaposleniService.getAllZaposleni();

        verify(zaposleniRepositoryMock, times(1)).findAll();

        Assert.assertEquals(found.size(), 2);
    }

    @Test
    public void testAddZaposleni() throws UserAlreadyExistsException {


       Zaposleni z = zaposleniService.addZaposleni(ZaposleniConstants.NEW_ZAPOSLENI_DTO);

        verify(zaposleniRepositoryMock, times(1)).findOneByUsername(ZaposleniConstants.NEW_ZAPOSLENI_USERNAME);

        Assert.assertEquals(z.getIme(), ZaposleniConstants.NEW_ZAPOSLENI_DTO.getIme());
        Assert.assertEquals(z.getPrezime(), ZaposleniConstants.NEW_ZAPOSLENI_DTO.getPrezime());

    }

    @Test(expectedExceptions = {UserAlreadyExistsException.class})
    public void testAddZaposleniAlreadyExists() throws UserAlreadyExistsException {

        zaposleniService.addZaposleni(ZaposleniConstants.UPDATED_ZAPOSLENI_DTO);
    }

    @Test
    public void testUpdateZaposleni() throws UserNotFoundException {


        Zaposleni z = zaposleniService.updateZaposleni(ZaposleniConstants.UPDATED_ZAPOSLENI_DTO);

        verify(zaposleniRepositoryMock, times(1)).findOneByUsername(Mockito.anyString());

        Assert.assertEquals(z.getIme(), ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getIme());
        Assert.assertEquals(z.getPrezime(), ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getPrezime());
    }

    @Test(expectedExceptions = {UserNotFoundException.class})
    public void testUpdateZaposleniDoesntExist() throws UserNotFoundException {
        zaposleniService.updateZaposleni(ZaposleniConstants.UPDATE_FAKE_ZAPOSLENI_DTO);
    }
}
