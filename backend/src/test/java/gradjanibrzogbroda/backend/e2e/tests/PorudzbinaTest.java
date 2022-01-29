package gradjanibrzogbroda.backend.e2e.tests;

import gradjanibrzogbroda.backend.e2e.pages.KonobarPage;
import gradjanibrzogbroda.backend.e2e.pages.KuvarPage;
import gradjanibrzogbroda.backend.e2e.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PorudzbinaTest {
	private WebDriver browser;

	private MainPage mainPage;
	private KonobarPage konobarPage;
	private KuvarPage kuvarPage;

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
	}

	@Test
	public void commenceTesting(){
		mainPage.loginAsKonobar();
		konobarPage.doubleClickAstal();
		konobarPage.kreirajPorudzbinuBtnClick();

		// pljeska
		konobarPage.izaberiJeloClick();
		konobarPage.pljeskavicaDivClick();
		konobarPage.dodajJeloBtnClick();
		konobarPage.setKolicinaInput(2);
		konobarPage.setNapomenaInput("puno luka i malo pavlake");
		konobarPage.saveBtnClick();

		//save
		konobarPage.sacuvajIzmeneBtnClick();
		konobarPage.setUsernameInput("user4");
		konobarPage.confirmOrderBtnClick();
		konobarPage.exitCardBtnClick();

		// logout
		konobarPage.logOutBtnClick();

		//login as kuvar
		mainPage.loginAsKuvar();
		kuvarPage.nextPageOrdersBtnClick();

		// check if kuvar received the order
		Assert.assertTrue(kuvarPage.preuzmiPorudzbinu9ButtonIsDisplayed());

		//kuvar makes the order
		kuvarPage.preuzmiPorudzbinu9ButtonClick();
		kuvarPage.setUsernameInput("user6");
		kuvarPage.confirmOrderButtonClick();
		kuvarPage.spremiJelo11ButtonClick();
		kuvarPage.setUsernameInput("user6");
		kuvarPage.confirmOrderButtonClick();
		kuvarPage.logOutBtnClick();

		//log in as konobar again
		mainPage.loginAsKonobar();
		konobarPage.doubleClickAstal();

		konobarPage.dostaviBtnClick();
		konobarPage.setUsernameInput("user4");
		konobarPage.confirmOrderBtnClick();

		konobarPage.naplatiBtnClick();
		konobarPage.setUsernameInput("user4");
		konobarPage.confirmOrderBtnClick();

		konobarPage.waitForExitBtnNotVisible();

		konobarPage.doubleClickAstal();

		// proveri da li je astal oslobodjen
		Assert.assertTrue(konobarPage.kreirajPorudzbinuBtnDisplayed());
	}

	@AfterClass
	public void quitBrowser(){
		browser.quit();
	}
}
