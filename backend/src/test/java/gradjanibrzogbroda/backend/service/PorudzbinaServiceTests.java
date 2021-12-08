package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PorudzbinaServiceTests extends AbstractTestNGSpringContextTests {
    @Autowired
    PorudzbinaService porudzbinaService;

    @Autowired
    PorudzbinaRepository porudzbinaRepository;

    @MockBean
    PorudzbinaRepository porudzbinaRepositoryMock;

    @BeforeTest
    public void setup(){

    }
}
