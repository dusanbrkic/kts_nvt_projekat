package gradjanibrzogbroda.backend.service;

import static org.mockito.MockitoAnnotations.openMocks;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import gradjanibrzogbroda.backend.constants.PredlogConstants;
import gradjanibrzogbroda.backend.constants.UserDetailsConstants;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.exceptions.PredlogWrongFormatException;
import gradjanibrzogbroda.backend.exceptions.UserNotFoundException;
import gradjanibrzogbroda.backend.repository.ZaposleniRepository;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDetailsServiceUnitTests extends AbstractTestNGSpringContextTests {
	
	@Mock
	private ZaposleniRepository zaposleniRepositoryMock;
	
	@InjectMocks
	@Autowired
	private UserDetailsServiceImplementation service;
	
	@BeforeMethod
	public void initMock() {
		openMocks(this);
		
		given(zaposleniRepositoryMock.findOneByUsername(UserDetailsConstants.NON_EXISTING_USERNAME)).willReturn(null);
		
		Zaposleni zap=new Zaposleni().builder()
				.id(UserDetailsConstants.EXISTING_ZAPOSLENI_ID)
				.username(UserDetailsConstants.EXISTING_USERNAME)
				.build();
		
		given(zaposleniRepositoryMock.findOneByUsername(UserDetailsConstants.EXISTING_USERNAME)).willReturn(zap);
	}
	
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
