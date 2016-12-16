package amazonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	private WebDriver driver;
	By dropDownSelectLocator = By.id("searchDropdownBox");
	By searchGlass = By.className("nav-input");
	By successMessageLocator = By.cssSelector(".nav-a.nav-b");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public ElectronicsPage selectElectronicsCategory(String category) {
		driver.findElement(dropDownSelectLocator).sendKeys(category);
		
		driver.findElement(searchGlass).click();
		
		return new ElectronicsPage(driver);
	}
}
