package gradjanibrzogbroda.backend.e2e.pages;

import gradjanibrzogbroda.backend.e2e.util.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
	private WebDriver driver;

	public MainPage(WebDriver webDriver){
		driver = webDriver;
	}

    @FindBy(css = ".konobar")
	private WebElement konobarLogin;

    @FindBy(css = ".sanker")
	private WebElement sankerLogin;

    @FindBy(css = ".kuvar")
	private WebElement kuvarLogin;

    @FindBy(css = ".manager")
	private WebElement managerLogin;

    @FindBy(css = ".admin")
	private WebElement adminLogin;

    @FindBy(css = ".chef")
	private WebElement chefLogin;

    @FindBy(css = "#inputgroup")
	private WebElement usernameInput;

    @FindBy(css = ".p-password-input")
	private WebElement passwordInput;

    @FindBy(xpath = "//*[normalize-space(text()) = 'Login']")
	private WebElement loginBtn;

	
	public void loginAsKonobar(){
		Utilities.visibilityWait(driver, konobarLogin, 10);
		konobarLogin.click();
	}

	public void loginAsSanker(){
		sankerLogin.click();
	}

	public void loginAsKuvar(){
		kuvarLogin.click();
	}

	public void loginAsManager(String username, String password){
		managerLogin.click();
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginBtn.click();
	}
	public void loginAsChef(String username, String password){
		chefLogin.click();
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginBtn.click();
	}
	public void loginAsAdmin(String username, String password){
		adminLogin.click();
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginBtn.click();
	}
}
