package gradjanibrzogbroda.backend.e2e.pages;

import gradjanibrzogbroda.backend.e2e.util.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SankerPage {
	private WebDriver webDriver;
	private Actions actions;

	public SankerPage(WebDriver _webDriver){
		webDriver = _webDriver;
		actions = new Actions(_webDriver);
	}

	@FindBy(id="2")
	private WebElement porudzbinaId2Btn;

	@FindBy(id="9")
	private WebElement porudzbinaId9Btn;

	@FindBy(css = "#inputgroup")
	private WebElement usernameInput;

	@FindBy(css = "p-button[label='Login'] button")
	private WebElement confirmOrderBtn;

	@FindBy(xpath = "//button[@ng-reflect-text='Return']")
	private WebElement logOutBtn;

    @FindBy(xpath = "//*[normalize-space(text()) = 'Porudzbina 2']")
    public WebElement porudzbinaId2;

	public void logOutBtnClick(){logOutBtn.click();}


	public void porudzbinaId2BtnClick(){
		Utilities.clickableWait(webDriver, porudzbinaId2Btn, 10);
		porudzbinaId2Btn.click();
	}

	public void porudzbinaId9BtnClick(){
		porudzbinaId9Btn.click();
	}

	public void setUsernameInput(String username){
		usernameInput.clear();
		usernameInput.sendKeys(username);
	}

	public void confirmOrderBtnClick(){
		confirmOrderBtn.click();
	}


	public boolean porudzbinaId9BtnDisplayed() {
		Utilities.visibilityWait(webDriver, porudzbinaId9Btn, 10);
		return porudzbinaId9Btn.isDisplayed();
	}
}
