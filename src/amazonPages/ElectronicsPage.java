package amazonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElectronicsPage {
	private WebDriver driver;
	By searchBoxLocator = By.id("twotabsearchtextbox");
	By searchGlass = By.className("nav-input");
	By successMessageLocator = By.className("nav-a-content");
	
	public ElectronicsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public SearchResultsPage SearchFor(String searchKey) {
		driver.findElement(searchBoxLocator).clear();
		driver.findElement(searchBoxLocator).sendKeys(searchKey);
		
		driver.findElement(searchGlass).click();
		
		return new SearchResultsPage(driver);
	}
	
	public boolean SuccessMessage() {
		return driver.findElement(successMessageLocator).isDisplayed();
	}
}
