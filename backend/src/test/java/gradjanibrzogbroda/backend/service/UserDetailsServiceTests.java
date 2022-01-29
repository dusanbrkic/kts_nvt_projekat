package gradjanibrzogbroda.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.constants.UserDetailsConstants;
import gradjanibrzogbroda.backend.domain.Zaposleni;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class UserDetailsServiceTests extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private UserDetailsServiceImplementation service;
	
	@Test(expectedExceptions=UsernameNotFoundException.class)
	public void testloadUserByUsernameNonExistingUsername() {
		
		service.loadUserByUsername(UserDetailsConstants.NON_EXISTING_USERNAME);
	}
	
	@Test
	public void testloadUserByUsernameExistingUsername() {
		Zaposleni zap=(Zaposleni) service.loadUserByUsername(UserDetailsConstants.EXISTING_USERNAME);
		
		Assert.assertEquals(zap.getId(), UserDetailsConstants.EXISTING_ZAPOSLENI_ID);
		Assert.assertEquals(zap.getUsername(), UserDetailsConstants.EXISTING_USERNAME);
	}

}
