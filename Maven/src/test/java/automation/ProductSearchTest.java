package automation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductSearchTest {
	public static final String EXCEL_PRODUCT_SEARCH_FILE_NAME = "inputProductSearchTest.xlsx";
	public static final String XML_SEARCH_BUTTON = "/html/body/header/div/div/div[2]/div/span/button";

	@Before
	public void setUp() throws IOException {
		Driver.setUp(Driver.EXCEL_FILE_PATH, EXCEL_PRODUCT_SEARCH_FILE_NAME, Driver.EXCEL_SHEET_NAME);
	}

	@After
	public void tearDown() {
		Driver.quit();
	}

	@Test
	public void productSearchTest() throws InterruptedException {
		//Test count, each test in a row in the excel file
		int excelRowCount = ReadExcl.getRowcount() + 1;
		Sheet thsSheet = ReadExcl.getsheet();

		for (int excellRowIndex = 0; excellRowIndex < excelRowCount; excellRowIndex++) {
			Log.testLogging(excellRowIndex + 1);
			Row excellRow = thsSheet.getRow(excellRowIndex);
			Driver.importToBrowser(excellRow);
			String searchData = "";
			if(excellRowIndex != 2) {
				searchData = Driver.importToTextBox("importing the data and entering it", Driver.findElement(By.name("search")), excellRow);
			}
    	    Driver.buttonClickWithDelay("search", Driver.findElement(By.xpath(XML_SEARCH_BUTTON)), Driver.ONE_SECOND);
			List<WebElement> results = Driver.findElements(By.className("caption"));
			String text = "";
			Log.info(results.size() + " products found");
			for(int i = 0; i < results.size(); i++) {
				WebElement result = results.get(i);
				text = result.findElement(By.tagName("a")).getText();
				text = text.toLowerCase();
				if(excellRowIndex == 0 && !text.equals(searchData)) {
					Log.debug("First step: The test failed, the result is different than the search");
				}
				else if(excellRowIndex == 1 && !text.contains(searchData)) {
					Log.debug("Second step: The test failed, the result is does not contain the search");
				}
				else if(excellRowIndex == 2) {
					Log.debug("Third step: The test failed, found results with an empty search");
					break;
				}
			}

		}
	}
}
