package tests;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;

import amazonPages.HomePage;
import amazonPages.ElectronicsPage;
import amazonPages.SearchResultsPage;
import util.TestBase;
import dataProvider.DataProviderClass;
import org.testng.Reporter;

public class TestSearch extends TestBase{
	
	public static java.util.List<WebElement> elements;
  
	@Test  
	public void TestHomePage() {
		// report step1 - logging into amazon.com
		Reporter.log("Logging into " + baseUrl + "\n");
	  
		// Initialize home page and navigate to amazon.com
		homePage = new HomePage(driver);	  
		
		driver.get(baseUrl);
	  
		// Select Electronics category for search
		Reporter.log("Searching for 'Electronics' category \n");
		electronicsPage = homePage.selectElectronicsCategory("Electronics");
	  	  
		// assert that the category "All Electronics" is displayed.
		assertTrue(electronicsPage.SuccessMessage());
		Reporter.log("'All Electronics' selected" );
	}
  
	@Test(dependsOnMethods = "TestHomePage", dataProvider="SearchProvider", dataProviderClass=dataProvider.DataProviderClass.class)  
	public void TestSearchPage(String searchKey) {
	  
		// report search with a search string
		Reporter.log("Searching for input search keys \n");
		searchResultsPage = electronicsPage.SearchFor(searchKey);
	
		// assert that the search results is displayed.
		assertTrue(searchResultsPage.SuccessMessage());
		Reporter.log("Search successful \n");
	  
		// report the total number of results returned and the results displayed on page.
		Reporter.log("Count of results on page " + searchResultsPage.GetResultsCntOnPage() + "\n");
		Reporter.log("Count of total number of results " + searchResultsPage.GetResultscount() + "\n");
	}  
  
	@Test(dependsOnMethods = "TestSearchPage")
	public void TestStarRatings() {
		Reporter.log("Checking the presence of star ratings <br>");
		searchResultsPage.GetStarRatings();		
	}
}

