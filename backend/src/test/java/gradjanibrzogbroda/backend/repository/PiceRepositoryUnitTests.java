package gradjanibrzogbroda.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import gradjanibrzogbroda.backend.constants.PiceConstants;
import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.domain.StavkaCenovnika;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PiceRepositoryUnitTests {

	@Autowired
	private PiceRepository piceRepository;
	
	@Autowired
	private TestEntityManager testEntity;
	
	@Test()
	public void unitTestFindOneByIdReturnsPice() {
		//Pice p = new Pice(42343);
		//p.setNaziv(PiceConstants.NEW_PICE_NAZIV);
		//p.setTrenutnaCena(PiceConstants.NEW_PICE_CENA);
		//p.setObrisan(false);
		//p.setCeneArtikla(new ArrayList<StavkaCenovnika>());
		
		//testEntity.persist(p);
		testEntity.flush();
		
		Pice actual = piceRepository.findOneById(PiceConstants.DB_PICE_ID);
		assertThat(actual.getId().equals(PiceConstants.DB_PICE_ID));
	}
	
	@Test()
	public void unitTestFindOneByIdReturnsNull() {
		
		testEntity.flush();
		
		Pice actual = piceRepository.findOneById(PiceConstants.FAIL_PICE_ID);
		assertNull(actual);
	}
	
}
