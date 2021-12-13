package gradjanibrzogbroda.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import gradjanibrzogbroda.backend.constants.JeloConstants;
import gradjanibrzogbroda.backend.domain.Jelo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class JeloServiceTest {
	
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
	
	
}
