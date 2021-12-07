package gradjanibrzogbroda.backend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

import gradjanibrzogbroda.backend.contoller.ZaposleniControllerTests;

@RunWith(Suite.class)
@SuiteClasses({ZaposleniControllerTests.class})
@TestPropertySource("classpath:application-test.properties")
public class SuiteZaposleni {
    
}
