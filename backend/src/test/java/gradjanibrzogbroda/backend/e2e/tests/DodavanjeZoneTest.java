package gradjanibrzogbroda.backend.e2e.tests;

import gradjanibrzogbroda.backend.e2e.pages.AdminPage;
import gradjanibrzogbroda.backend.e2e.pages.GlavniKuvarPage;
import gradjanibrzogbroda.backend.e2e.pages.MainPage;
import gradjanibrzogbroda.backend.e2e.pages.ManagerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DodavanjeZoneTest {
	private WebDriver browser;

	private MainPage mainPage;
	private AdminPage adminPage;

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
		adminPage = PageFactory.initElements(browser, AdminPage.class);
	}

	@Test
	public void commenceTesting(){
		mainPage.loginAsChef("user1", "pass1");

		adminPage.dodajZonuBtnClick();
		adminPage.setNazivZoneInput("Zona 51");
		adminPage.potvrdiZonuBtnClick();

	}

	@AfterClass
	public void quitBrowser(){
		browser.quit();
	}
}
