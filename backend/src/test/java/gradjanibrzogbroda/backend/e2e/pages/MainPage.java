package gradjanibrzogbroda.backend.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
	private WebDriver driver;

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
	
	
	public void loginAsKonobar(){
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
	}
	public void loginAsChef(String username, String password){
		chefLogin.click();
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
	}
	public void loginAsAdmin(String username, String password){
		adminLogin.click();
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
	}
}
