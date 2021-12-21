package gradjanibrzogbroda.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@DataJpaTest

public class ZaposleniRepositoryTests extends AbstractTestNGSpringContextTests {
    @Autowired
	TestEntityManager manager;

    @Autowired
    ZaposleniRepository zaposleniRepository;

    
}
