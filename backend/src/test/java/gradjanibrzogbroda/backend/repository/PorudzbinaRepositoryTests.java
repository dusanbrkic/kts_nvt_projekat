package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.constants.JeloConstants;
import gradjanibrzogbroda.backend.constants.PorudzbinaConstants;
import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.Porudzbina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PorudzbinaRepositoryTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Test(priority = -2)
    public void testFindAllByStatusPorudzbine() {
        List<Porudzbina> found = porudzbinaRepository.findAllByStatusPorudzbine(PorudzbinaConstants.FIND_PORUDZBINA_STATUS);
        assertEquals(PorudzbinaConstants.FIND_STATUS_NUMBER_OF_ITEMS, found.size());
    }

    @Test(priority = -2)
    public void testFindAllByKonobarId() {
//        List<Porudzbina> found = porudzbinaRepository.findAllByKonobarId(PorudzbinaConstants.DB_PORUDZBINA_KONOBAR_ID);
//        assertEquals(PorudzbinaConstants.FIND_PORUDZBINA_KONOBAR_COUNT, found.size());
    }
}
