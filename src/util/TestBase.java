package util;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import amazonPages.HomePage;
import amazonPages.ElectronicsPage;
import amazonPages.SearchResultsPage;

public class TestBase {
	protected String baseUrl = "http://www.amazon.com";
	protected WebDriver driver;
	protected HomePage homePage;
	protected ElectronicsPage electronicsPage;
	protected SearchResultsPage searchResultsPage;
	
  @BeforeSuite
  public void BeforeSuite() {
	  try {
		  //Initialize the chrome driver before testing
		  System.setProperty("webdriver.chrome.driver", "C:\\Selenium-chrome-driver-2.26\\chromedriver.exe");
		  driver = new ChromeDriver();
	  
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  catch(Exception ex) {
		  ex.printStackTrace();
	  }
  }

  @AfterSuite
  public void AfterSuite() {
	  // close the browser after test executes
	  driver.quit();
  }

}
