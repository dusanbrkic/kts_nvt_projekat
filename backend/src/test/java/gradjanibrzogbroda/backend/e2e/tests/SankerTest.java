package gradjanibrzogbroda.backend.e2e.tests;

import gradjanibrzogbroda.backend.e2e.pages.KonobarPage;
import gradjanibrzogbroda.backend.e2e.pages.KuvarPage;
import gradjanibrzogbroda.backend.e2e.pages.MainPage;
import gradjanibrzogbroda.backend.e2e.pages.SankerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SankerTest {
	private WebDriver browser;

	private MainPage mainPage;
	private SankerPage sankerPage;

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
		sankerPage = PageFactory.initElements(browser, SankerPage.class);
	}

	@Test
	public void commenceTesting(){
		mainPage.loginAsSanker();

		sankerPage.porudzbinaId2BtnClick();
		sankerPage.setUsernameInput("user5");
		sankerPage.confirmOrderBtnClick();

		sankerPage.logOutBtnClick();
	}

	@AfterClass
	public void quitBrowser(){
		browser.quit();
	}
}
