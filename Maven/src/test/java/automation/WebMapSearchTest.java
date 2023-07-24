package automation;

import org.openqa.selenium.By;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WebMapSearchTest {
	public static final String EXCEL_WEB_MAP_SEARCH_FILE_NAME = "inputWebMapSearchTest.xlsx";
	public static final String XPATH_ABOUT_US_BUTTON = "/html/body/footer/div/div/div[1]/ul/li[1]/a";
	public static final String XPATH_MY_ACCOUNT_MY_ACCOUNT = "/html/body/footer/div/div/div[4]/ul/li[1]/a";
	public static final String XPATH_MY_ACCOUNT_ORDER_HISTORY = "/html/body/footer/div/div/div[4]/ul/li[2]/a";
	public static final String XPATH_MY_ACCOUNT_WISH_LIST = "/html/body/footer/div/div/div[4]/ul/li[3]/a";
	public static final String XPATH_MY_ACCOUNT_NEWSLETTER = "/html/body/footer/div/div/div[4]/ul/li[4]/a";
	
	@Before
	public void setUp() throws IOException {
		Driver.setUp(Driver.EXCEL_FILE_PATH, EXCEL_WEB_MAP_SEARCH_FILE_NAME, Driver.EXCEL_SHEET_NAME);
	}

	@After
	public void tearDown() {
		Driver.quit();
	}

	@Test
	public void webMapSearchTest() throws InterruptedException {
		//Test count, each test in a row in the excel file
		int excelRowCount = ReadExcl.getRowcount() + 1;
		Sheet thsSheet = ReadExcl.getsheet();

		for (int excellRowIndex = 0; excellRowIndex < excelRowCount; excellRowIndex++) {
			Log.testLogging(excellRowIndex + 1);
			Row excellRow = thsSheet.getRow(excellRowIndex);
			Driver.importToBrowser(excellRow);
			if(excellRowIndex == 0) {
	    	    Driver.buttonClickAndConfirmNewURL("About Us", Driver.findElement(By.xpath(XPATH_ABOUT_US_BUTTON)), Driver.ONE_SECOND, Driver.readExcellCellData(excellRow));
			}
			else if(excellRowIndex == 1) {
				String loginURL = Driver.readExcellCellData(excellRow);
	    	    Driver.buttonClickAndConfirmNewURL("My Account", Driver.findElement(By.xpath(XPATH_MY_ACCOUNT_MY_ACCOUNT)), Driver.ONE_SECOND, loginURL);
	    	    Driver.buttonClickAndConfirmNewURL("Order History", Driver.findElement(By.xpath(XPATH_MY_ACCOUNT_ORDER_HISTORY)), Driver.ONE_SECOND, loginURL);
	    	    Driver.buttonClickAndConfirmNewURL("Wish List", Driver.findElement(By.xpath(XPATH_MY_ACCOUNT_WISH_LIST)), Driver.ONE_SECOND, loginURL);
	    	    Driver.buttonClickAndConfirmNewURL("Newsletter", Driver.findElement(By.xpath(XPATH_MY_ACCOUNT_NEWSLETTER)), Driver.ONE_SECOND, loginURL);
			}
		}
	}
}
