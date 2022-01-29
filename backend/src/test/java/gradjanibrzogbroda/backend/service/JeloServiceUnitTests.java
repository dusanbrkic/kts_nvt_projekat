package gradjanibrzogbroda.backend.service;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static gradjanibrzogbroda.backend.constants.JeloConstants.*;

import gradjanibrzogbroda.backend.constants.JeloConstants;
import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.domain.StavkaCenovnika;
import gradjanibrzogbroda.backend.repository.JeloRepository;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class JeloServiceUnitTests extends AbstractTestNGSpringContextTests{

	@Mock
	private JeloRepository mockJeloRepository;
	
	@Mock
	private JeloService mockJeloService;
	
	@Autowired
	@InjectMocks
	private JeloService jeloService;
	
	private Jelo jelo;
	
	@BeforeClass
	    public void initMock() {
	        openMocks(this);
	        
	        given(mockJeloRepository.findOneById(JeloConstants.FAIL_JELO_ID)).willReturn(null);
	        Jelo j = new Jelo();
	        j.setId(DB_JELO_ID);
	        j.setTrenutnaCena(DB_JELO_CENA);
	        j.setNaziv(DB_JELO_NAZIV);
	        j.setObrisan(false);
	        StavkaCenovnika st =  new StavkaCenovnika(1, LocalDateTime.now().minusDays(2), null, DB_JELO_CENA, j);
	        ArrayList<StavkaCenovnika> cene = new ArrayList<StavkaCenovnika>();
	        cene.add(st);
	        j.setCeneArtikla(cene);
	        given(mockJeloRepository.findOneById(JeloConstants.DB_JELO_ID)).willReturn(j);
	        j.setTrenutnaCena(UPDATED_JELO_CENA);
	        given(mockJeloRepository.save(j)).willReturn(j);
	        j.setTrenutnaCena(DB_JELO_CENA);
	        j.setObrisan(true);
	        given(mockJeloRepository.save(j)).willReturn(j);
	        j.setObrisan(false);
	        this.jelo = j;
	 }
	
	
	@Test(priority = -15)
	public void shouldReturnJelo() {
		assertEquals(jeloService.findOne(JeloConstants.DB_JELO_ID).getId(), JeloConstants.DB_JELO_ID);
		
		verify(mockJeloRepository, times(1)).findOneById(JeloConstants.DB_JELO_ID);
	}
	
	@Test(priority = -14)
	public void shouldReturnNull() {
		assertNull(jeloService.findOne(JeloConstants.FAIL_JELO_ID));
		
		verify(mockJeloRepository, times(1)).findOneById(JeloConstants.FAIL_JELO_ID);
	}
	
	@Test(priority = -13)
	public void shouldChangeCena() {
		Jelo j = new Jelo();
        j.setId(DB_JELO_ID);
        j.setTrenutnaCena(NEW_JELO_CENA);
        
        Jelo actual = jeloService.izmeniCenu(DB_JELO_ID, NEW_JELO_CENA);
        
		assertEquals(actual.getTrenutnaCena(), NEW_JELO_CENA);
		assertEquals(actual.getCeneArtikla().size(), 2);
		
		verify(mockJeloRepository, times(2)).findOneById(JeloConstants.DB_JELO_ID);
		
	}
	
	@Test(priority = -12)
	public void shouldSetObrisanToTrue() {
		jeloService.deleteJelo(DB_JELO_ID);
		jelo.setObrisan(true);
		
		verify(mockJeloRepository, times(3)).findOneById(JeloConstants.DB_JELO_ID);
		verify(mockJeloRepository, times(2)).save(jelo);
	}
	
	@Test(priority = -11)
	public void shouldReturnNewJelo() {
		Jelo actual = jeloService.addJelo(jelo);
		
		assertTrue(!actual.isObrisan());
		assertEquals(jelo.getId(), actual.getId());
		verify(mockJeloRepository, times(3)).save(jelo);
	}
	
	
}
