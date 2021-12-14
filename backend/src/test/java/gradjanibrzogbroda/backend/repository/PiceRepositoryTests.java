package gradjanibrzogbroda.backend.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import gradjanibrzogbroda.backend.constants.PiceConstants;
import gradjanibrzogbroda.backend.domain.Pice;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PiceRepositoryTests {

	@Autowired
	private PiceRepository piceRepository;
	
	@Test
	public void testFindOneById() {
		Pice actual = piceRepository.findOneById(PiceConstants.DB_PICE_ID);
		
		assertEquals(PiceConstants.DB_PICE_ID, actual.getId());
		
	}
	
	@Test
	public void testFindOneByIdFoundNone() {
		Pice actual = piceRepository.findOneById(PiceConstants.FAIL_PICE_ID);
		
		assertNull(actual);
		
	}
}
