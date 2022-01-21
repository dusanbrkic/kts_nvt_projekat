package gradjanibrzogbroda.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ZaposleniRepositoryTests extends AbstractTestNGSpringContextTests {
    @Autowired
	TestEntityManager manager;

    @Autowired
    ZaposleniRepository zaposleniRepository;

    
}
