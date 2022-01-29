package gradjanibrzogbroda.backend.e2e.pages;

import gradjanibrzogbroda.backend.e2e.util.Utilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class KonobarPage {
	private WebDriver webDriver;
	private Actions actions;

	public KonobarPage(WebDriver _webDriver) {
		webDriver = _webDriver;
		actions = new Actions(_webDriver);
	}

	@FindBy(xpath = "//div[normalize-space(text()) = 'astal1']")
	private WebElement astal1Div;

	@FindBy(xpath = "//*[text() = 'Izabrati jelo']")
	private WebElement izaberiJeloDropDown;

	@FindBy(xpath = "//button[normalize-space(text()) = 'Kreiraj porudzbinu']")
	private WebElement kreirajPorudzbinuBtn;

	@FindBy(xpath = "//div[normalize-space(text()) = 'Pljeskavica']")
	private WebElement pljeskavicaDiv;

	@FindBy(xpath = "//button[normalize-space(text()) = 'Dodaj jelo']")
	private WebElement dodajJeloBtn;

	@FindBy(css = ".p-filled")
	private WebElement kolicinaInput;

	@FindBy(id = "napomena")
	private WebElement napomenaInput;

	@FindBy(css = "button[label='Save']")
	private WebElement saveBtn;

	@FindBy(xpath = "//span[text()='Izabrati pice']")
	private WebElement izaberiPiceDropDown;

	@FindBy(xpath = "//div[normalize-space(text()) = 'Coca Cola 0.33l']")
	private WebElement kokaKolaDiv;

	@FindBy(xpath = "//button[normalize-space(text()) = 'Dodaj pice']")
	private WebElement dodajPiceBtn;

	@FindBy(css = ".p-dropdown-filter")
	private WebElement izaberiPiceFilter;

	@FindBy(xpath = "//button[normalize-space(text()) = 'Sačuvaj izmene']")
	private WebElement sacuvajIzmeneBtn;

	@FindBy(css = "#inputgroup")
	private WebElement usernameInput;

	@FindBy(css = "p-button[label='Login'] button")
	private WebElement confirmOrderBtn;

	@FindBy(xpath = "//button[@ng-reflect-text='Return']")
	private WebElement logOutBtn;

    @FindBy(css = ".p-sidebar-close")
    private WebElement exitCardBtn;

    @FindBy(xpath = "//button[normalize-space(text()) = 'Dostavi']")
	private WebElement dostaviBtn;

    @FindBy(xpath = "//button[normalize-space(text()) = 'Naplati']")
	private WebElement naplatiBtn;

	public void naplatiBtnClick() {
		Utilities.clickableWait(webDriver, naplatiBtn, 10);
		naplatiBtn.click();
	}
	public void dostaviBtnClick() {
		Utilities.clickableWait(webDriver, dostaviBtn, 10);
		dostaviBtn.click();
	}
	public void logOutBtnClick() {
		Utilities.clickableWait(webDriver, logOutBtn, 10);
		logOutBtn.click();
	}

	public void doubleClickAstal() {
		Utilities.clickableWait(webDriver, astal1Div, 10);
		actions.doubleClick(astal1Div).perform();
	}

	public void exitCardBtnClick() {
		exitCardBtn.click();
	}

	public void kreirajPorudzbinuBtnClick() {
		Utilities.clickableWait(webDriver, kreirajPorudzbinuBtn, 10);
		kreirajPorudzbinuBtn.click();
	}

	public void izaberiJeloClick() {
		izaberiJeloDropDown.click();
	}

	public void pljeskavicaDivClick() {
		pljeskavicaDiv.click();
	}

	public void dodajJeloBtnClick() {
		dodajJeloBtn.click();
	}

	public void setKolicinaInput(Integer kolicina) {
		kolicinaInput.sendKeys(kolicina.toString());
	}

	public void setNapomenaInput(String napomena) {
		napomenaInput.sendKeys(napomena);
	}

	public void saveBtnClick() {
		saveBtn.click();
	}

	public void izaberiPiceClick() {
		izaberiPiceDropDown.click();
	}

	public void selectKokaKolaDiv() {
		actions.sendKeys(Keys.ARROW_DOWN);
	}

	public void sacuvajIzmeneBtnClick() {
		sacuvajIzmeneBtn.click();
	}

	public void dodajPiceBtnClick() {
		dodajPiceBtn.click();
	}

	public void kokaKolaDivClick() {
		Utilities.clickableWait(webDriver, kokaKolaDiv, 10);
		kokaKolaDiv.click();
	}

	public void setUsernameInput(String username) {
		usernameInput.clear();
		usernameInput.sendKeys(username);
	}

	public void confirmOrderBtnClick() {
		confirmOrderBtn.click();
	}

	public boolean kreirajPorudzbinuBtnDisplayed() {
		Utilities.visibilityWait(webDriver, kreirajPorudzbinuBtn, 10);
		return kreirajPorudzbinuBtn.isDisplayed();
	}

	public void waitForExitBtnNotVisible() {
		Utilities.nonVisibilityWait(webDriver, exitCardBtn, 10);
	}
}
