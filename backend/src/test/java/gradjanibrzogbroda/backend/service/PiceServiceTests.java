package gradjanibrzogbroda.backend.service;

import static org.testng.Assert.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.constants.PiceConstants;
import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.repository.PiceRepository;

@ExtendWith(SpringExtension.class)
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
	public void testAddPice() {
		
	}
	
}
