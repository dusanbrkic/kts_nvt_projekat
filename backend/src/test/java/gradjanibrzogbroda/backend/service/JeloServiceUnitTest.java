package gradjanibrzogbroda.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.Test;

import gradjanibrzogbroda.backend.constants.JeloConstants;
import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.repository.JeloRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class JeloServiceUnitTest{

	
	@Autowired
	private JeloService jeloService;
	
	@MockBean
	private JeloRepository mockJeloRepository;
	
	
	@Test
	public void unitTestFindOneById() {
		Mockito.when(mockJeloRepository.findOneById(JeloConstants.DB_JELO_ID)).thenReturn(new Jelo(JeloConstants.DB_JELO_ID));
		
		assertEquals(jeloService.findOne(JeloConstants.DB_JELO_ID).getId(), JeloConstants.DB_JELO_ID);
		
		verify(mockJeloRepository, times(1)).findOneById(JeloConstants.DB_JELO_ID);
	}
	
	@Test
	public void unitTestFindOneByIdUnitShouldReturnNull() {
		Mockito.when(mockJeloRepository.findOneById(JeloConstants.FAIL_JELO_ID)).thenReturn(null);
		
		assertNull(jeloService.findOne(JeloConstants.FAIL_JELO_ID));
		
		verify(mockJeloRepository, times(1)).findOneById(JeloConstants.FAIL_JELO_ID);
	}
}
