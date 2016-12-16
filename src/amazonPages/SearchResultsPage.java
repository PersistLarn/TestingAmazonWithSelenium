package amazonPages;

import static org.testng.AssertJUnit.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

public class SearchResultsPage {

		private WebDriver driver;
		public static java.util.List<WebElement> elements;
		
		// contains the list of elements returned from search
		//By resultElementsLocator = By.xpath("//*[@id='atfResults']/ul/li"); 
		//By starRatingsLocator = By.xpath("//*[@id='atfResults']/ul/li/div/div[last()]");
		By resultElementsLocator = By.xpath("//*[@id='atfResults']/ul/li/div");
		By successMessageLocator = By.id("s-result-count");
		
		
		//By starRatingLocator = By.xpath("//*[@id='result_0']//following-sibling::div"); 
		/* This is the locator for the first result star rating
		 * since this is dynamic, will take each one from within the method and not
		 * declare the locator itself.
		 * 
		 */
				
		public SearchResultsPage(WebDriver driver) {
			this.driver = driver;
		}
		
		public String GetResultStr() {
			return driver.findElement(successMessageLocator).getText();
		}
		
		public boolean SuccessMessage() {
			return driver.findElement(successMessageLocator).isDisplayed();
		}
		
		// return the list of star rating locators webelements
		public void GetStarRatings() {
			elements =  driver.findElements(resultElementsLocator);	
			int itemsCnt = elements.size();
			
			for(int i = 0; i < itemsCnt; i++) {
				Actions actions = new Actions(driver);
				WebElement hoverRatings = driver.findElement(By.xpath("//*[@id='atfResults']/ul/li/div/div[last()]"));
				 	  
				actions.moveToElement(hoverRatings);	  
				actions.perform();	
				//driver.resultElementsLocator
				
				Reporter.log(hoverRatings.findElement(By.cssSelector(".a-icon.a-icon-popover")).getText());
				System.out.println(hoverRatings.findElement(By.cssSelector(".a-icon.a-icon-popover")).getText());
				assertTrue(hoverRatings.findElement(By.cssSelector(".a-icon.a-icon-popover")).isDisplayed());
				
			}
			
			
		}
		
		public String GetResultscount() {
			// Get the result str
			String resultStr = GetResultStr();
			
			String splitArray[] = SplitResultStr(resultStr);
			  
			  /* Sometimes the result is 24(example) results shown for.
			   * In such cases, the count same for both total count and 
			   * that on the page
			   */
			  if(IsTotalCntAndPgCntSame(splitArray[0])) {
				  return splitArray[0];
			  }
			  else {
				  return splitArray[2];
			  }
		}
		
		// Get the total number of results displayed on the page.
		public String GetResultsCntOnPage() {
			
			// Get the result str
			String resultStr = GetResultStr();
			
			String splitResult[] = SplitResultStr(resultStr);
			
			  /* Sometimes the result is 24(example) results shown for.
			   * In such cases, the count same for both total count and 
			   * that on the page
			   */
			  String pgCnt;
			  if(IsTotalCntAndPgCntSame(splitResult[0])) {
				  pgCnt = splitResult[0];
			  }
			  else {
				  // make a second split to get exact count
				  pgCnt = splitResult[0].split("-")[1];
			  }					  
			  
			  return pgCnt;
		}
		
		// split the results string 
		private String[] SplitResultStr(String str)
		{
			// split the results to get the number of results count
			String splitArray[] = str.split(" ");
				
			return splitArray;		
		}
		
		//Check the count of results returned and count of results on page are same
		private boolean IsTotalCntAndPgCntSame(String splitStr)
		{
			boolean isCntSame = true;
			if(splitStr.contains("-")) {
				isCntSame = false;
			}
			return isCntSame;
		}
}
