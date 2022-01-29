package gradjanibrzogbroda.backend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.constants.ZaposleniConstants;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;
import gradjanibrzogbroda.backend.exceptions.UserAlreadyExistsException;
import gradjanibrzogbroda.backend.exceptions.UserNotFoundException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ZaposleniServiceTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	ZaposleniService zaposleniService;
	
	@Test
    public void testIzmeniPlatu() {
        // NEW_PLATA_DTO sadrzi podatke nove plate
        Zaposleni found = zaposleniService.izmeniPlatu(ZaposleniConstants.NEW_PLATA_DTO);

        Assert.assertEquals(found.getTrenutnaPlata(), ZaposleniConstants.NEW_PLATA_DTO.getVisinaPlate());
    }
	
	@Test(priority = -1)
    public void testGetAllZaposleniBeforeAdd() {
        List<ZaposleniDTO> found = zaposleniService.getAllZaposleni();

        Assert.assertEquals(found.size(), 6);
    }
	
	@Test(priority = 5)
    public void testGetAllZaposleniAfterAdd() {
        List<ZaposleniDTO> found = zaposleniService.getAllZaposleni();

        Assert.assertEquals(found.size(), 7);
    }

    @Test(priority = 1)
    public void testAddZaposleni() throws UserAlreadyExistsException {
       Zaposleni z = zaposleniService.addZaposleni(ZaposleniConstants.NEW_ZAPOSLENI_DTO);

        Assert.assertEquals(z.getIme(), ZaposleniConstants.NEW_ZAPOSLENI_DTO.getIme());
        Assert.assertEquals(z.getPrezime(), ZaposleniConstants.NEW_ZAPOSLENI_DTO.getPrezime());

    }
    
    @Test(expectedExceptions = {UserAlreadyExistsException.class})
    public void testAddZaposleniAlreadyExists() throws UserAlreadyExistsException {
    	ZaposleniDTO zap=ZaposleniConstants.UPDATED_ZAPOSLENI_DTO;
    	zap.setUsername(ZaposleniConstants.DB_ZAPOSLENI_USERNAME);
        zaposleniService.addZaposleni(zap);
    }

    @Test
    public void testUpdateZaposleni() throws UserNotFoundException {
    	ZaposleniDTO zap=ZaposleniConstants.UPDATED_ZAPOSLENI_DTO;
    	zap.setUsername(ZaposleniConstants.DB_ZAPOSLENI_USERNAME);
        Zaposleni z = zaposleniService.updateZaposleni(zap);

        Assert.assertEquals(z.getIme(), ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getIme());
        Assert.assertEquals(z.getPrezime(), ZaposleniConstants.UPDATED_ZAPOSLENI_DTO.getPrezime());
    }

    @Test(expectedExceptions = {UserNotFoundException.class})
    public void testUpdateZaposleniDoesntExist() throws UserNotFoundException {
        zaposleniService.updateZaposleni(ZaposleniConstants.UPDATE_FAKE_ZAPOSLENI_DTO);
    }

}
