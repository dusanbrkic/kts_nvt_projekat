package gradjanibrzogbroda.backend.e2e.pages;

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

	@FindBy(css="#2")
	private WebElement porudzbinaId2Btn;

	@FindBy(css="#5")
	private WebElement porudzbinaId5Btn;

	@FindBy(css = "#inputgroup")
	private WebElement usernameInput;

	@FindBy(css = "p-button[label='Login'] button")
	private WebElement confirmOrderBtn;


	public void porudzbinaId2BtnClick(){
		porudzbinaId2Btn.click();
	}

	public void porudzbinaId5BtnClick(){
		porudzbinaId5Btn.click();
	}

	public void setUsernameInput(String username){
		usernameInput.sendKeys(username);
	}

	public void confirmOrderBtnClick(){
		confirmOrderBtn.click();
	}



}
