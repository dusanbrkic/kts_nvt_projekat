package gradjanibrzogbroda.backend.contoller;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import gradjanibrzogbroda.backend.constants.PiceConstants;
import gradjanibrzogbroda.backend.controller.PiceController;
import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.dto.PiceDTO;
import gradjanibrzogbroda.backend.service.PiceService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PiceControllerUnitTests {
	
	@Autowired
	private PiceController piceController;
	@MockBean
	private PiceService piceServiceMock;
	
	@Autowired
	private TestRestTemplate testRest;
	
	@Test
	public void unitTestGetById() {
		Mockito.when(piceServiceMock.findOne(PiceConstants.DB_PICE_ID)).thenReturn(new Pice(PiceConstants.DB_PICE_ID));
		
		ResponseEntity<PiceDTO> actual = testRest.getForEntity("/pice/id/"+PiceConstants.DB_PICE_ID, PiceDTO.class);
		
		assertEquals(HttpStatus.OK, actual.getStatusCode());
		assertEquals(PiceConstants.DB_PICE_ID,actual.getBody().getId());
		
	}
}
