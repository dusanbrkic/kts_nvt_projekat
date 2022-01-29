package gradjanibrzogbroda.backend.service;

import static gradjanibrzogbroda.backend.constants.PiceConstants.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.constants.JeloConstants;
import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.domain.StavkaCenovnika;
import gradjanibrzogbroda.backend.repository.JeloRepository;
import gradjanibrzogbroda.backend.repository.PiceRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PiceServiceUnitTests extends AbstractTestNGSpringContextTests{

	@Mock
	private PiceRepository mockPiceRepository;
	
	@Mock
	private PiceService mockJeloService;
	
	@Autowired
	@InjectMocks
	private PiceService piceService;
	
	private Pice pice;
	
	@BeforeClass
	    public void initMock() {
	        openMocks(this);
	        
	        given(mockPiceRepository.findOneById(JeloConstants.FAIL_JELO_ID)).willReturn(null);
	        Pice p = new Pice();
	        p.setId(DB_PICE_ID);
	        p.setTrenutnaCena(DB_PICE_CENA);
	        p.setNaziv(DB_PICE_NAZIV);
	        p.setObrisan(false);
	        StavkaCenovnika st =  new StavkaCenovnika(1, LocalDateTime.now().minusDays(2), null, DB_PICE_CENA, p);
	        ArrayList<StavkaCenovnika> cene = new ArrayList<StavkaCenovnika>();
	        cene.add(st);
	        p.setCeneArtikla(cene);
	        given(mockPiceRepository.findOneById(DB_PICE_ID)).willReturn(p);
	        given(mockPiceRepository.save(p)).willReturn(p);
	        p.setTrenutnaCena(UPDATED_PICE_CENA);
	        given(mockPiceRepository.save(p)).willReturn(p);
	        p.setTrenutnaCena(DB_PICE_CENA);
	        p.setObrisan(true);
	        given(mockPiceRepository.save(p)).willReturn(p);
	        p.setObrisan(false);
	        this.pice = p;
	 }
	
	
	@Test(priority = -15)
	public void shouldReturnPice() {
		assertEquals(piceService.findOne(DB_PICE_ID).getId(), DB_PICE_ID);
		
		verify(mockPiceRepository, times(1)).findOneById(DB_PICE_ID);
	}
	
	@Test(priority = -14)
	public void shouldReturnNull() {
		assertNull(piceService.findOne(FAIL_PICE_ID));
		
		verify(mockPiceRepository, times(1)).findOneById(FAIL_PICE_ID);
	}
	
	@Test(priority = -13)
	public void shouldChangeCena() {
		Pice p = new Pice();
        p.setId(DB_PICE_ID);
        p.setTrenutnaCena(NEW_PICE_CENA);
        
        Pice actual = piceService.izmeniCenu(DB_PICE_ID, NEW_PICE_CENA);
        
		assertEquals(actual.getTrenutnaCena(), NEW_PICE_CENA);
		assertEquals(actual.getCeneArtikla().size(), 2);
		
		verify(mockPiceRepository, times(2)).findOneById(DB_PICE_ID);
		
	}
	
	@Test(priority = -12)
	public void shouldSetObrisanToNull() {
		piceService.deletePice(DB_PICE_ID);
		pice.setObrisan(true);
		
		verify(mockPiceRepository, times(3)).findOneById(DB_PICE_ID);
		
	}
	
	@Test(priority = -11)
	public void shouldReturnNewPice() {
		Pice actual = piceService.addPice(pice);
		
		assertTrue(!actual.isObrisan());
		assertEquals(pice.getId(), actual.getId());
		verify(mockPiceRepository, times(3)).save(pice);
	}
	
}
