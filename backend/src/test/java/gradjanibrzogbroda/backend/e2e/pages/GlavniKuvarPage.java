package gradjanibrzogbroda.backend.e2e.pages;

import gradjanibrzogbroda.backend.e2e.util.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class GlavniKuvarPage {
	private WebDriver webDriver;
	private Actions actions;

	public GlavniKuvarPage(WebDriver _webDriver) {
		webDriver = _webDriver;
		actions = new Actions(_webDriver);
	}

	@FindBy(xpath = "//a[.//div[normalize-space(text()) = 'Jelovnik']]")
	private WebElement jelovnikBtn;

	@FindBy(xpath = "//*[text() = 'Jela']")
	private WebElement jelaBtn;

	@FindBy(xpath = "//*[text() = 'New']")
	private WebElement newJeloBtn;

	@FindBy(xpath = "//td[.//p-celleditor[normalize-space(text()) = 'Pljeskavica']]")
	private WebElement pljeskavicaJeloRow;

	@FindBy(xpath = "//*[text() = 'Opis']")
	private WebElement opisBtn;

	@FindBy(xpath = "//*[text() = 'Predlog izmene']")
	private WebElement editBtn;

	@FindBy(xpath = "//*[text() = 'Predlog brisanja']")
	private WebElement deleteBtn;

	@FindBy(id = "naziv")
	private WebElement nazivInput;

	@FindBy(css = "#cena input")
	private WebElement cenaInput;

	@FindBy(css = "#vreme input")
	private WebElement vremeInput;

	@FindBy(id = "opis")
	private WebElement opisJela;

	@FindBy(css = "button[label='Save']")
	private WebElement saveBtn;

	@FindBy(xpath = "//a[.//span[contains(@class, 'pi-key')]]")
	private WebElement changePassBtn;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement passInput;

	@FindBy(xpath = "//input[@placeholder='Repeat Password']")
	private WebElement rptPassInput;

	@FindBy(css = "p-button[label='Change'] button")
	private WebElement confirmPassChangeBtn;

	@FindBy(xpath = "//button[@ng-reflect-text='Return']")
	private WebElement logOutBtn;

	public void jelovnikBtnClick() {
		Utilities.clickableWait(webDriver, jelovnikBtn, 10);
		jelovnikBtn.click();
	}

	public void jelaBtnClick() {
		jelaBtn.click();
	}

	public void newJeloBtnClick() {
		newJeloBtn.click();
	}

	public void pljeskavicaJeloRowRightClick() {
		actions.contextClick(pljeskavicaJeloRow).perform();
	}

	public void opisBtnClick() {
		opisBtn.click();
	}

	public void editBtnClick() {
		Utilities.clickableWait(webDriver, editBtn, 10);
		editBtn.click();
	}

	public void deleteBtnClick() {
		deleteBtn.click();
	}

	public void setNazivInput(String naziv) {
		nazivInput.clear();
		nazivInput.sendKeys(naziv);
	}

	public void setCenaInput(Double cena) {
		cenaInput.clear();
		cenaInput.sendKeys(cena.toString());
	}

	public void setVremeInput(Integer vreme) {
		vremeInput.clear();
		vremeInput.sendKeys(vreme.toString());
	}

	public void setOpisJela(String opis) {
		opisJela.clear();
		opisJela.sendKeys(opis);
	}

	public void saveBtnClick() {
		saveBtn.click();
	}

	public void changePassBtnClick() {
		changePassBtn.click();
	}

	public void setPassInput(String pass) {
		passInput.sendKeys(pass);
	}

	public void setRptPassInput(String pass) {
		rptPassInput.sendKeys(pass);
	}

	public void confirmPassChangeBtnClick() {
		confirmPassChangeBtn.click();
	}

	public void logOutBtnClick() {
		Utilities.clickableWait(webDriver, logOutBtn, 10);
		logOutBtn.click();
	}


}
