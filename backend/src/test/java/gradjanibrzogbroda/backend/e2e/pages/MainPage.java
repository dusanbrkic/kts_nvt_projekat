package gradjanibrzogbroda.backend.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
	private WebDriver driver;
	
	@FindBy()
	int a;

    @FindBy(css = "div.property-image")
    public WebElement propertyimageDiv;

    @FindBy(css = ".sanker")
    public WebElement propertyimageDiv2;

    @FindBy(css = ".kuvar")
    public WebElement propertyimageDiv3;

    @FindBy(css = ".manager")
    public WebElement propertyimageDiv4;

    @FindBy(css = ".admin")
    public WebElement propertyimageDiv5;

    @FindBy(css = ".chef")
    public WebElement propertyimageDiv6;
}
