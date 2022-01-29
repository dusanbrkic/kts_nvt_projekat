package gradjanibrzogbroda.backend.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ManagerPage {
	private WebDriver webDriver;
	private Actions actions;

	public ManagerPage(WebDriver _webDriver){
		webDriver = _webDriver;
		actions = new Actions(_webDriver);
	}

	@FindBy(xpath = "//button[@ng-reflect-text='Return']")
	private WebElement logOutBtn;

	public void logOutBtnClick(){logOutBtn.click();}

    @FindBy(xpath = "//a[.//div[normalize-space(text()) = 'Predlozi']]")
    public WebElement predlogBtn;

    @FindBy(xpath = "//button[@icon='pi pi-check']")
    public WebElement prihvatiPredlogBtn;
}
