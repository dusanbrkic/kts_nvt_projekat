package gradjanibrzogbroda.backend.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ManagerPage {
	private WebDriver webDriver;
	private Actions actions;

	public ManagerPage(WebDriver _webDriver){
		webDriver = _webDriver;
		actions = new Actions(_webDriver);
	}
}
