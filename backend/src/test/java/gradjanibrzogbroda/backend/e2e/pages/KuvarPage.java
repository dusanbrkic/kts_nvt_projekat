package gradjanibrzogbroda.backend.e2e.pages;

import gradjanibrzogbroda.backend.e2e.util.Utilities;
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

	@FindBy(xpath = "//button[@ng-reflect-text='Return']")
	private WebElement logOutBtn;

	@FindBy(css = "button.p-ripple.p-element.ng-star-inserted.p-carousel-next.p-link")
	private WebElement nextPageOrdersBtn;

	@FindBy(id = "preuzmiPorudzbinu9")
	private WebElement preuzmiPorudzbinu9Button;

	@FindBy(id = "spremiJelo11")
	private WebElement spremiJelo11Button;


	public void spremiJelo11ButtonClick() {
		Utilities.clickableWait(webDriver, spremiJelo11Button, 10);
		spremiJelo11Button.click();
	}

	public void logOutBtnClick() {
		logOutBtn.click();
	}

	public KuvarPage(WebDriver _webDriver) {
		webDriver = _webDriver;
		actions = new Actions(_webDriver);
	}

	public void preuzmiPorudzbinu1ButtonClick() {
		preuzmiPorudzbinu1Button.click();
	}

	public boolean preuzmiPorudzbinu9ButtonIsDisplayed() {
		Utilities.visibilityWait(webDriver, preuzmiPorudzbinu9Button, 10);
		return preuzmiPorudzbinu9Button.isDisplayed();
	}

	public void nextPageOrdersBtnClick() {
		Utilities.clickableWait(webDriver, nextPageOrdersBtn, 10);
		nextPageOrdersBtn.click();
	}

	public void setUsernameInput(String username) {
		usernameInput.clear();
		usernameInput.sendKeys(username);
	}

	public void confirmOrderButtonClick() {
		confirmOrderButton.click();
	}

	public void SpremiJelo1Click() {
		spremiJelo1Button.click();
	}

	public void preuzmiPorudzbinu9ButtonClick() {
		preuzmiPorudzbinu9Button.click();
	}
}
