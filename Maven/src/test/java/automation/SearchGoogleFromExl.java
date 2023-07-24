package automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class SearchGoogleFromExl {
	@Before
	public void setUp() throws IOException {
		//Driver.setUp();
		Driver.setUp(Driver.EXCEL_FILE_PATH, Driver.EXCEL_FILE_NAME, Driver.EXCEL_SHEET_NAME);
	}
	
	@After
	public void tearDown() {
		//Driver.quit();
	}
	
	@Test
	public void simple() {
		Driver.get("https://www.google.com/");
		Driver.setWindowSize();
		
		int rowCount = ReadExcl.getRowcount();
		Sheet thsSheet = ReadExcl.getsheet();
		
		for (int i = 0; i < rowCount + 1; i++) {
			Row row = thsSheet.getRow(i);
			
			//Create a loop to print cell values in a row
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	        	//Print Excel data in console
	        	System.out.print(row.getCell(j).getStringCellValue() + " ** ");
	        	Driver.findElement(By.name("q")).sendKeys(row.getCell(j).getStringCellValue());
	        	Driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	        }
		}
	}
}