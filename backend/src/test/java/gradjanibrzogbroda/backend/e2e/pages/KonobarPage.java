package gradjanibrzogbroda.backend.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class KonobarPage {
	private WebDriver webDriver;
	private Actions actions;

	public KonobarPage(WebDriver _webDriver){
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
    public WebElement dodajJeloBtn;

    @FindBy(css = ".p-filled")
    public WebElement kolicinaInput;

    @FindBy(id = "napomena")
    public WebElement napomenaInput;

    @FindBy(css = "button[label='Save']")
    public WebElement saveBtn;

    @FindBy(xpath = "//span[text()='Izabrati pice']")
    public WebElement izaberiPiceDropDown;

	@FindBy(xpath = "//div[normalize-space(text()) = 'Coca Cola 0.33l']")
	public WebElement kokaKolaDiv;

    @FindBy(xpath = "//button[normalize-space(text()) = 'Dodaj pice']")
    public WebElement dodajPiceBtn;

	@FindBy(xpath = "//button[normalize-space(text()) = 'Sačuvaj izmene']")
	private WebElement sacuvajIzmeneBtn;

	@FindBy(css = "#inputgroup")
	private WebElement usernameInput;

	@FindBy(css = "p-button[label='Login'] button")
	private WebElement confirmOrderBtn;

	public void doubleClickAstal(){
		actions.doubleClick(astal1Div).perform();
	}

	public void kreirajPorudzbinuBtnClick(){
		kreirajPorudzbinuBtn.click();
	}

	public void izaberiJeloClick(){
		izaberiJeloDropDown.click();
	}

	public void pljeskavicaDivClick(){
		pljeskavicaDiv.click();
	}

	public void dodajJeloBtnClick(){
		dodajJeloBtn.click();
	}

	public void setKolicinaInput(Integer kolicina){
		kolicinaInput.sendKeys(kolicina.toString());
	}

	public void setNapomenaInput(String napomena){
		napomenaInput.sendKeys(napomena);
	}

	public void saveBtnClick(){
		saveBtn.click();
	}

	public void izaberiPiceClick(){
		izaberiPiceDropDown.click();
	}

	public void izaberiKokaKolu(){
		sacuvajIzmeneBtn.click();
	}

	public void dodajPiceBtnClick(){
		dodajPiceBtn.click();
	}

	public void sacuvajIzmeneBtnClick(){
		kokaKolaDiv.click();
	}

	public void setUsernameInput(String username){
		usernameInput.sendKeys(username);
	}

	public void confirmOrderBtnClick(){
		confirmOrderBtn.click();
	}

}
