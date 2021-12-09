package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.constants.ZaposleniConstants;
import gradjanibrzogbroda.backend.domain.Plata;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.repository.ZaposleniRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ZaposleniServiceTests extends AbstractTestNGSpringContextTests {
    @Mock
    ZaposleniRepository zaposleniRepositoryMock;

    @InjectMocks
    @Autowired
    ZaposleniService zaposleniService;

    @BeforeClass
    public void initMock() {
        openMocks(this);

        given(zaposleniRepositoryMock.save(ZaposleniConstants.UPDATED_ZAPOSLENI))
                .willReturn(ZaposleniConstants.UPDATED_ZAPOSLENI);
        given(zaposleniRepositoryMock.findOneById(ZaposleniConstants.UPDATED_ZAPOSLENI_ID))
                .willReturn(ZaposleniConstants.UPDATED_ZAPOSLENI);
    }

    @Test
    public void izmeniPlatu(){
        // NEW_PLATA_DTO sadrzi podatke nove plate
        Zaposleni found = zaposleniService.izmeniPlatu(ZaposleniConstants.NEW_PLATA_DTO);

        verify(zaposleniRepositoryMock, times(1)).findOneById(ZaposleniConstants.UPDATED_ZAPOSLENI_ID);
        verify(zaposleniRepositoryMock, times(1)).save(ZaposleniConstants.UPDATED_ZAPOSLENI);

        // servis belezi iznos nove plate PlataDTO.visinaPlate u polje Zaposleni.trenutnaPlata, 
        // trenutnaPlata mockovanog objekta treba da bude jednaka kao i plata iz konstante NEW_PLATA_DTO.visinaPlate
        Assert.assertEquals(found.getTrenutnaPlata(), ZaposleniConstants.NEW_PLATA_DTO.getVisinaPlate());
    }
}
