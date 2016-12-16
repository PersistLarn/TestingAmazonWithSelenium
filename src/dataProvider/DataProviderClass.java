package dataProvider;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataProviderClass {
  
  @DataProvider(name="SearchProvider")
  public static Object[][] GetDataFromProvider() {
	  return new Object[][] {
			{ "Samsung TV:40 Inches:2015" }
		}; 	
  }
}
