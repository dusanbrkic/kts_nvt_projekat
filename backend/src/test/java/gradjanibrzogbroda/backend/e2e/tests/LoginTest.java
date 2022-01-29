package gradjanibrzogbroda.backend.e2e.tests;

import gradjanibrzogbroda.backend.e2e.pages.*;
import gradjanibrzogbroda.backend.e2e.util.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
	private WebDriver browser;

	private MainPage mainPage;
	private KonobarPage konobarPage;
	private KuvarPage kuvarPage;
	private SankerPage sankerPage;
	private ManagerPage managerPage;
	private AdminPage adminPage;
	private GlavniKuvarPage glavniKuvarPage;

	@BeforeClass
	public void setupSelenium() {
		// instantiate browser
		System.setProperty("webdriver.chrome.driver", "src/test/java/gradjanibrzogbroda/backend/e2e/driver/chromedriver.exe");
		browser = new ChromeDriver();
		// maximize window
		browser.manage().window().maximize();
		// navigate
		browser.navigate().to("http://localhost:4200");

		mainPage = PageFactory.initElements(browser, MainPage.class);
		konobarPage = PageFactory.initElements(browser, KonobarPage.class);
		kuvarPage = PageFactory.initElements(browser, KuvarPage.class);
		sankerPage = PageFactory.initElements(browser, SankerPage.class);
		adminPage = PageFactory.initElements(browser, AdminPage.class);
		managerPage = PageFactory.initElements(browser, ManagerPage.class);
		glavniKuvarPage = PageFactory.initElements(browser, GlavniKuvarPage.class);
	}

	@Test
	public void commenceTesting(){
		mainPage.loginAsKonobar();
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/konobar");
		konobarPage.logOutBtnClick();
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/");

		mainPage.loginAsSanker();
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/sanker");
		sankerPage.logOutBtnClick();
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/");

		mainPage.loginAsKuvar();
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/kuvar");
		kuvarPage.logOutBtnClick();
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/");

		mainPage.loginAsChef("user3", "pass1");
		Utilities.urlWait(browser, "http://localhost:4200/kuvar", 10);
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/kuvar");
		glavniKuvarPage.logOutBtnClick();
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/");

		mainPage.loginAsManager("user2", "pass1");
		Utilities.urlWait(browser, "http://localhost:4200/menadzer", 10);
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/menadzer");
		managerPage.logOutBtnClick();
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/");

		mainPage.loginAsAdmin("user1", "pass1");
		Utilities.urlWait(browser, "http://localhost:4200/admin", 10);
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/admin");
		adminPage.logOutBtnClick();
		Assert.assertEquals(browser.getCurrentUrl(), "http://localhost:4200/");
	}

	@AfterClass
	public void quitBrowser(){
		browser.quit();
	}

}
