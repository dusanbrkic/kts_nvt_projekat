package gradjanibrzogbroda.backend.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class KuvarPage {
	private WebDriver webDriver;
	private Actions actions;

    @FindBy(id = "preuzmiPorudzbinu1")
    private WebElement preuzmiPorudzbinu1Button;

    @FindBy(id = "inputgroup")
	private WebElement usernameInput;

    @FindBy(css = "p-button[label='Login'] button")
	private WebElement confirmOrderButton;

    @FindBy(id = "spremiJelo1")
	private WebElement spremiJelo1Button;

	public KuvarPage(WebDriver _webDriver){
		webDriver = _webDriver;
		actions = new Actions(_webDriver);
	}

	public void preuzmiPorudzbinu1ButtonClick(){
		preuzmiPorudzbinu1Button.click();
	}

	public void setUsernameInput(String username){
		usernameInput.sendKeys(username);
	}

	public void confirmOrderButtonClick(){
		confirmOrderButton.click();
	}

	public void SpremiJelo1Click(){
		spremiJelo1Button.click();
	}
}
