package gradjanibrzogbroda.backend.e2e.tests;

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

public class PreporucivanjeIzmeneJela {
	private WebDriver browser;

	private MainPage mainPage;
	private GlavniKuvarPage glavniKuvarPage;
	private ManagerPage managerPage;

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
		glavniKuvarPage = PageFactory.initElements(browser, GlavniKuvarPage.class);
		managerPage = PageFactory.initElements(browser, ManagerPage.class);
	}

	@Test
	public void commenceTesting(){
		mainPage.loginAsChef("user3", "pass1");

		glavniKuvarPage.jelovnikBtnClick();
		glavniKuvarPage.jelaBtnClick();
		glavniKuvarPage.newJeloBtnClick();
		glavniKuvarPage.setNazivInput("Brkina Salata");
		glavniKuvarPage.setCenaInput(420.0);
		glavniKuvarPage.setOpisJela("Jelo od specijalnih Brkinih namirnica.");
		glavniKuvarPage.setVremeInput(150000);
		glavniKuvarPage.saveBtnClick();
		browser.navigate().to("http://localhost:4200");

		mainPage.loginAsManager("user2", "pass1");

		managerPage.predloziBtnClick();

		Assert.assertTrue(managerPage.prihvatiPredlogBtnDisplayed());

		managerPage.prihvatiPredlogBtnClick();

	}

	@AfterClass
	public void quitBrowser(){
		browser.quit();
	}
}
