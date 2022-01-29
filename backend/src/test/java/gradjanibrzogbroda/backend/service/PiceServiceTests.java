package gradjanibrzogbroda.backend.service;

import static gradjanibrzogbroda.backend.constants.JeloConstants.DB_JELO_CENA;
import static gradjanibrzogbroda.backend.constants.JeloConstants.DB_JELO_ID;
import static gradjanibrzogbroda.backend.constants.JeloConstants.NEW_JELO_CENA;
import static gradjanibrzogbroda.backend.constants.JeloConstants.NEW_JELO_NAZIV;
import static org.junit.Assert.assertNull;
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
import gradjanibrzogbroda.backend.constants.PiceConstants;
import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.exceptions.PiceNotFoundException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PiceServiceTests extends AbstractTestNGSpringContextTests {
	@Autowired
	private PiceService piceSer;
	
	@Test(priority=-1)
	public void testFindOne() {
		Pice actual = piceSer.findOne(PiceConstants.DB_PICE_ID);
		
		assertEquals(actual.getNaziv(), PiceConstants.DB_PICE_NAZIV);
		assertEquals(actual.getTrenutnaCena(), PiceConstants.DB_PICE_CENA);
	}
	
	@Test
	public void testFindOneByIdFail() {
	  Pice actual = piceSer.findOne(PiceConstants.FAIL_PICE_ID);
	  
	  assertNull(actual);
	}
	
	@Test
	public void shouldReturnPage() {
		Page<Pice> actual = piceSer.findPage(0, 5, "", Optional.empty(), 1);

		assertEquals(actual.getTotalElements(), 5);
	}
	
	@Test
	public void shouldReturnPageSearchKafa() {
		Page<Pice> actual = piceSer.findPage(0, 5, "Kafa", Optional.empty(), 1);

		assertEquals(actual.getTotalElements(), 0);
	}
	
	@Test(priority = 3)
	public void testChangeNaziv() throws PiceNotFoundException {
		Pice j = new Pice();
        j.setId(PiceConstants.DB_PICE_ID);
        j.setTrenutnaCena(PiceConstants.DB_PICE_CENA);
        j.setNaziv(PiceConstants.UPDATED_PICE_NAZIV);
        
        Pice actual = piceSer.updatePice(j);
        
		assertEquals(actual.getTrenutnaCena(), PiceConstants.DB_PICE_CENA);
		assertEquals(actual.getNaziv(), PiceConstants.UPDATED_PICE_NAZIV);
		
	}
	
	@Test(priority = 5)
	public void shouldSetObrisanToTrue() {
		Jelo j = new Jelo();
        j.setId(PiceConstants.DB_PICE_ID);
		piceSer.deletePice(PiceConstants.DB_PICE_ID);
		
		assertTrue(true);
	}
	
	@Test(priority = 11)
	public void shouldReturnNewJelo() {
		Pice j = new Pice();
		j.setNaziv(NEW_JELO_NAZIV);
		j.setTrenutnaCena(NEW_JELO_CENA);
		Pice actual = piceSer.addPice(j);
		
		assertTrue(!actual.isObrisan());
		assertEquals(j.getNaziv(), actual.getNaziv());
		
	}
	
}
