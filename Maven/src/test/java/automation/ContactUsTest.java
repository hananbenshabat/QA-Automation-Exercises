package automation;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.io.IOException;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class ContactUsTest {
	public static final String EXCEL_CONTACT_US_FILE_NAME = "inputContactUsTest.xlsx";
	public static final String XPATH_SUBMIT_BUTTON = "/html/body/div[2]/div/div/form/div/div/input";
	
	@Before
	public void setUp() throws IOException {
		Driver.setUp(Driver.EXCEL_FILE_PATH, EXCEL_CONTACT_US_FILE_NAME, Driver.EXCEL_SHEET_NAME);
	}
	
	@After
	public void tearDown() {
		Driver.quit();
	}
	
	@Test
	public void contactUsTest() throws InterruptedException {
		//Test count, each test in a row in the excel file
		int excelRowCount = ReadExcl.getRowcount() + 1;
		Sheet thsSheet = ReadExcl.getsheet();
		
		for (int excellRowIndex = 0; excellRowIndex < excelRowCount; excellRowIndex++) {
			try {
				Log.testLogging(excellRowIndex + 1);
				Row excellRow = thsSheet.getRow(excellRowIndex);
				Driver.importToBrowser(excellRow);
				Driver.importToEmptyTextBox("entering name", Driver.findElement(By.id("input-name")), excellRow);
				Driver.importToEmptyTextBox("entering email", Driver.findElement(By.id("input-email")), excellRow);
				Driver.importToEmptyTextBox("entering enquiry", Driver.findElement(By.id("input-enquiry")), excellRow);
	    	    Driver.buttonClickWithDelay("submit", Driver.findElement(By.xpath(XPATH_SUBMIT_BUTTON)), Driver.ONE_SECOND);
			}
			catch (NoSuchElementException noElementException) {
				continue;
			}
		}
	}
}
