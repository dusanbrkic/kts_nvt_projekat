package gradjanibrzogbroda.backend.e2e.pages;

import gradjanibrzogbroda.backend.e2e.util.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AdminPage {
	private WebDriver webDriver;
	private Actions actions;

	public AdminPage(WebDriver _webDriver) {
		webDriver = _webDriver;
		actions = new Actions(_webDriver);
	}

	@FindBy(xpath = "//button[normalize-space(text()) = 'Add Table']")
	private WebElement dodajStoBtn;

	@FindBy(xpath = "//button[normalize-space(text()) = 'Save']")
	private WebElement saveZoneBtn;

	@FindBy(xpath = "//*[normalize-space(text()) = 'Novi sto']")
	private WebElement noviStoDiv;

	@FindBy(id = "inputgroup")
	private WebElement nazivStolaInput;

	@FindBy(id = "integeronly")
	private WebElement brMestaInput;

	@FindBy(css = ".p-button-danger")
	private WebElement deleteStoBtn;

	@FindBy(css = ".p-button-success")
	private WebElement saveStoBtn;

	@FindBy(xpath = "//a[.//div[normalize-space(text()) = 'Zaposleni']]")
	private WebElement zaposleniTableBtn;

	@FindBy(xpath = "//*[normalize-space(text()) = 'New']")
	private WebElement newUserBtn;

	@FindBy(xpath = "//*[normalize-space(text()) = 'Jovana']")
	private WebElement jovanaCell;

	@FindBy(xpath = "//*[normalize-space(text()) = 'Delete']")
	private WebElement deleteUserBtn;

	@FindBy(xpath = "//tr[.//td[text()='user3']]//button")
	private WebElement editUserBtn;

	@FindBy(css = "input[ng-reflect-model='Jovic']")
	private WebElement changePrezimeInput;

	@FindBy(xpath = "//button[@icon='pi pi-check']")
	private WebElement potvrdiIzmenuKorisnikaBtn;

	@FindBy(id = "username")
	private WebElement usernameInput;

	@FindBy(id = "ime")
	private WebElement imeInput;

	@FindBy(id = "prezime")
	private WebElement prezimeInput;

	@FindBy(xpath = "//input[@inputmode='decimal']")
	private WebElement plataInput;

	@FindBy(css = "button[label='Save']")
	private WebElement sacuvajKorisnikaBtn;

	@FindBy(xpath = "//button[@ng-reflect-text='Return']")
	private WebElement logOutBtn;

	@FindBy(xpath = "//button[normalize-space(text()) = 'Delete zone']")
	private WebElement obrisiZonuBtn;

	@FindBy(xpath = "//button[normalize-space(text()) = 'Add Zone']")
	private WebElement dodajZonuBtn;

	@FindBy(id = "naziv")
	private WebElement nazivZoneInput;

	@FindBy(css = ".p-button-success")
	private WebElement potvrdiZonuBtn;

	@FindBy(xpath = "//*[text() = 'SuccessUspesno.']")
	private WebElement successNotification;

	@FindBy(xpath = "//*[text() = 'Uspesno.Zona obrisana']")
	private WebElement zonaObrisanaNotification;

    @FindBy(css = "button[ng-reflect-label='Yes']")
    private WebElement sayYesBtn;

	public void dodajStoBtnClick() {
		Utilities.clickableWait(webDriver, dodajStoBtn, 10);
		dodajStoBtn.click();
	}

	public void sayYesBtnClick() {
		Utilities.clickableWait(webDriver, sayYesBtn, 10);
		sayYesBtn.click();
	}

	public void saveStoBtnClick() {
		saveStoBtn.click();
	}

	public void deleteStoBtnClick() {
		deleteStoBtn.click();
	}

	public void saveZoneBtnClick() {
		saveZoneBtn.click();
	}

	public void zaposleniTableBtnClick() {
		Utilities.clickableWait(webDriver, zaposleniTableBtn, 10);
		zaposleniTableBtn.click();
	}

	public void potvrdiIzmenuKorisnikaBtnClick() {
		potvrdiIzmenuKorisnikaBtn.click();
	}

	public void newUserBtnClick() {
		newUserBtn.click();
	}

	public void deleteUserBtnClick() {
		Utilities.clickableWait(webDriver, deleteUserBtn, 10);
		deleteUserBtn.click();
	}

	public void editUserBtnClick() {
		editUserBtn.click();
	}

	public void noviStoDivDoubleClick() {
		actions.doubleClick(noviStoDiv).perform();
	}

	public void jovanaCellContextClick() {
		Utilities.clickableWait(webDriver, jovanaCell, 10);
		actions.contextClick(jovanaCell).perform();
	}

	public void setNazivStolaInput(String keys) {
		nazivStolaInput.clear();
		nazivStolaInput.sendKeys(keys);
	}

	public void setBrMestaInput(Integer brMesta) {
		brMestaInput.sendKeys(brMesta.toString());
	}

	public void setChangePrezimeInput(String prezime) {
		Utilities.visibilityWait(webDriver, changePrezimeInput, 10);
		changePrezimeInput.clear();
		changePrezimeInput.sendKeys(prezime);
	}

	public void setPrezimeInput(String prezime) {
		prezimeInput.sendKeys(prezime);
	}

	public void setUsernameInput(String username) {
		usernameInput.sendKeys(username);
	}

	public void setImeInput(String ime) {
		Utilities.visibilityWait(webDriver, imeInput, 10);
		imeInput.sendKeys(ime);
	}

	public void setPlataInput(Integer plata) {
		plataInput.sendKeys(plata.toString());
	}

	public void sacuvajKorisnikaBtnClick() {
		sacuvajKorisnikaBtn.click();
	}

	public void logOutBtnClick() {
		logOutBtn.click();
	}

	public void obrisiZonuBtnClick() {
		Utilities.clickableWait(webDriver, obrisiZonuBtn, 10);
		obrisiZonuBtn.click();
	}

	public void dodajZonuBtnClick() {
		Utilities.clickableWait(webDriver, dodajZonuBtn, 10);
		dodajZonuBtn.click();
	}

	public void potvrdiZonuBtnClick() {
		potvrdiZonuBtn.click();
	}

	public void setNazivZoneInput(String naziv) {
		nazivZoneInput.sendKeys(naziv);
	}

	public boolean successNotificationIsDisplayed() {
		return successNotification.isDisplayed();
	}

	public boolean zonaObrisanaNotificationIsDisplayed() {
		return zonaObrisanaNotification.isDisplayed();
	}


}
