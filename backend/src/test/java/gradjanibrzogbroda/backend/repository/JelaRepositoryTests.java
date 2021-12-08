package gradjanibrzogbroda.backend.repository;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import gradjanibrzogbroda.backend.constants.JeloConstants;
import gradjanibrzogbroda.backend.domain.Jelo;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class JelaRepositoryTests extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private JeloRepository jeloRepository;
	
	@Test(priority = -2)
    public void testFindByNaziv() {
        Jelo found = jeloRepository.findOneByNaziv(JeloConstants.FIND_JELO_NAZIV);
        assertEquals(JeloConstants.FIND_JELO_NAZIV, found.getNaziv());
    }
	
	@Test(priority = -2)
    public void testFindAllByTipJela() {
        List<Jelo> found = jeloRepository.findAllByTipJela(JeloConstants.FIND_JELO_TIP);
        assertEquals(JeloConstants.FIND_TIP_NUMBER_OF_ITEMS, found.size());
    }
	
	@Test(priority = -2)
    public void testFindAllByKategorijaJela() {
		List<Jelo> found = jeloRepository.findAllByKategorijaJela(JeloConstants.FIND_JELO_KATEGORIJA);
        assertEquals(JeloConstants.FIND_KATEGORIJA_NUMBER_OF_ITEMS, found.size());
    }

}
