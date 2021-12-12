package gradjanibrzogbroda.backend.service;

import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.constants.JeloConstants;
import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.repository.JeloRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class JeloServiceTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private JeloService jeloService;
	
	@Mock
	private JeloRepository mockJeloRepository;

	@BeforeClass
	public void initMock() {
		MockitoAnnotations.openMocks(this);
		//initMock(mockJeloRepository);
	}
	
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
	public void unitTestFindOneById() {
		Mockito.when(mockJeloRepository.findOneById(JeloConstants.DB_JELO_ID)).thenReturn(new Jelo(JeloConstants.DB_JELO_ID));
		
		assertEquals(jeloService.findOne(JeloConstants.DB_JELO_ID).getId(), JeloConstants.DB_JELO_ID);
	}
	
	@Test
	public void unitTestFindOneByIdUnitFail() {
		Mockito.when(mockJeloRepository.findOneById(JeloConstants.FAIL_JELO_ID)).thenReturn(null);
		
		assertNull(jeloService.findOne(JeloConstants.FAIL_JELO_ID));
	}
}
