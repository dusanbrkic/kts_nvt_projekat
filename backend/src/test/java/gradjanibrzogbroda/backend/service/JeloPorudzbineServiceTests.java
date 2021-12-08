package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.constants.PiceConstants;
import gradjanibrzogbroda.backend.domain.Pice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class JeloPorudzbineServiceTests extends AbstractTestNGSpringContextTests {
    @Autowired
    JeloPorudzbineService jeloPorudzbineService;


}
