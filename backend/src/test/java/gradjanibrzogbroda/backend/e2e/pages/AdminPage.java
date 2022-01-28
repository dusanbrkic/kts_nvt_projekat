package gradjanibrzogbroda.backend.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AdminPage {
	private WebDriver webDriver;
	private Actions actions;

	public AdminPage(WebDriver _webDriver){
		webDriver = _webDriver;
		actions = new Actions(_webDriver);
	}
	
	@FindBy()
	int id;
}
