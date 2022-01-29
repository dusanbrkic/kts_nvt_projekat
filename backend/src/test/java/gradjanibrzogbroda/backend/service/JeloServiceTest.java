package gradjanibrzogbroda.backend.service;



import static gradjanibrzogbroda.backend.constants.JeloConstants.*;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.constants.JeloConstants;
import gradjanibrzogbroda.backend.domain.Jelo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class JeloServiceTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private JeloService jeloService;
	
	
	@Test
	public void testFindOnById() {
	  Jelo actual = jeloService.findOne(JeloConstants.DB_JELO_ID);
	  
	  assertEquals(actual.getNaziv(), JeloConstants.DB_JELO_NAZIV);
	}
  
	@Test
	public void testFindOneByIdFail() {
	  Jelo actual = jeloService.findOne(JeloConstants.FAIL_JELO_ID);
	  
	  assertNull(actual);
	}
	
	@Test
	public void shouldReturnPage() {
		Page<Jelo> actual = jeloService.findPage(0, 5, "", Optional.empty(), 1, Optional.empty(), Optional.empty());

		assertEquals(actual.getTotalElements(), 5);
	}
	
	@Test
	public void shouldReturnPagePlj() {
		Page<Jelo> actual = jeloService.findPage(0, 5, "plj", Optional.empty(), 1, Optional.empty(), Optional.empty());

		assertEquals(actual.getTotalElements(), 1);
	}
	
	@Test(priority = 3)
	public void testChangeNaziv() {
		Jelo j = new Jelo();
        j.setId(DB_JELO_ID);
        j.setTrenutnaCena(DB_JELO_CENA);
        j.setNaziv(JeloConstants.UPDATED_JELO_NAZIV);
        
        Jelo actual = jeloService.updateJelo(j);
        
		assertEquals(actual.getTrenutnaCena(), DB_JELO_ID);
		assertEquals(actual.getNaziv(), JeloConstants.UPDATED_JELO_NAZIV);
		
	}
	
	@Test(priority = 5)
	public void shouldSetObrisanToTrue() {
		Jelo j = new Jelo();
        j.setId(DB_JELO_ID);
		jeloService.deleteJelo(DB_JELO_ID);
		
		assertTrue(true);
	}
	
	@Test(priority = 11)
	public void shouldReturnNewJelo() {
		Jelo j = new Jelo();
		j.setNaziv(NEW_JELO_NAZIV);
		j.setTrenutnaCena(NEW_JELO_CENA);
		Jelo actual = jeloService.addJelo(j);
		
		assertTrue(!actual.isObrisan());
		assertEquals(j.getNaziv(), actual.getNaziv());
		
	}
	
}
