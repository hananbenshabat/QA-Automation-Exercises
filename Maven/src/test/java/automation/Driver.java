package automation;

import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Driver {
	public static final String CHROME_DRIVER_PATH = "D:\\\\College\\\\Studies\\\\Eclipse Workspaces\\\\QA Workspace\\\\chromedriver.exe";
	public static final String EDGE_DRIVER_PATH = "D:\\\\College\\\\Studies\\\\Eclipse Workspaces\\\\QA Workspace\\\\msedgedriver.exe";
	public static final String CHROME_DRIVER = "webdriver.chrome.driver";
	public static final String EDGE_DRIVER = "webdriver.edge.driver";
	public static final String EXCEL_FILE_PATH = "exlFiles";
	public static final String EXCEL_FILE_NAME = "inputXLS.xlsx";
	public static final String EXCEL_SHEET_NAME = "sheet1";
	public static final int WINDOW_WIDTH_SIZE = 1500;
	public static final int WINDOW_HEIGHT_SIZE = 900;
	public static final int ONE_SECOND = 1000;

	private static String driverType = CHROME_DRIVER, driverPath = CHROME_DRIVER_PATH;
	private static WebDriver driver = null;
	public  static int excellColumnIndex = 0;

	public static void setDriver(String newDriverType, String newDriverPath) {
		if(driverType != newDriverType && driverPath != newDriverPath) {
			driverType = newDriverType;
			driverPath = newDriverPath;
		}
	}
	
	public static void setUp() throws IOException {
		// Change the following line to your preferred driver, Chrome by default.
		//setDriver(Driver.EDGE_DRIVER, Driver.EDGE_DRIVER_PATH);
		
		System.setProperty(driverType, driverPath);
		
		if(driverType == EDGE_DRIVER) {
			driver = new EdgeDriver();
		}
		else if(driverType == CHROME_DRIVER) {
			driver = new ChromeDriver();
		}
	}
	
	public static void setUp(String exlFilePath, String exlFileName, String exlSheetName) throws IOException {
		setUp();
		ReadExcl objExcelFile = new ReadExcl();
		objExcelFile.readExcel(exlFilePath, exlFileName, exlSheetName);
	}

	// Delay in milliseconds
	public static void delay(int time) throws InterruptedException {
		Thread.sleep(time);
		Log.info(time + " milliseconds delay, for the site to load");
	}
	
	public static void setWindowSize(int width, int height) {
		manage().window().setSize(new Dimension(width, height));
	}
	
	public static void setWindowSize() {
		setWindowSize(WINDOW_WIDTH_SIZE, WINDOW_HEIGHT_SIZE);
	}
	
	public static String readExcellCellData(Row excellRow) {
		//Getting the cell representing the given column and incrementing the column
		Cell cell = excellRow.getCell(excellColumnIndex++);
		String cellValue = null;

		switch (cell.getCellType()) {
		    case STRING:
		    	cellValue = cell.getStringCellValue();
		        break;
		    case NUMERIC:         
		    	cellValue = Long.toString((long)cell.getNumericCellValue());
		        break;
		        default:
		        	Log.error("invalid cell type");
		}
		
		return cellValue;
    }

	//Info logs the page title and then imports data to the browser from the excel file
	//In case no data is found the URL will remain at its default values
	public static void importToBrowser(Row excellRow) {
		try {
			excellColumnIndex = 0;
    		get(readExcellCellData(excellRow));
    		setWindowSize();
		}
		catch(NullPointerException nullException) {
			Log.fatal("no data found in row " + excellRow.getRowNum() + ", column " + (excellColumnIndex - 1));
		}
	}
	
	
	//Debug logs the inserted log text and then imports data to an element from the excel file
	//In case no data is found the text box value will remain at its default values
	public static String importToTextBox(String logText, WebElement element, Row excellRow) {
		String importedData = "";
		try {
		    Log.debug(logText);
		    importedData = readExcellCellData(excellRow);
			element.sendKeys(importedData);
			return importedData;
		}
		catch(NullPointerException nullException) {
			Log.warn("no data found in row " + excellRow.getRowNum() + ", column " + (excellColumnIndex - 1));
		}

		return importedData;
	}

	//Debug logs the inserted log text and then imports data to an element from the excel file
	//In case no data is found the text box value will remain at its default values
	public static String importToEmptyTextBox(String logText, WebElement element, Row excellRow) {
	    element.clear();
	    return importToTextBox(logText, element, excellRow);
	}

	//Debug logs the inserted log text and then imports data to an element from the excel file
	//In case no data is found the select box value will remain at its default values
	public static void importToSelectBox(String logText, WebElement element, Row excellRow) {
		try {
    	    Log.debug(logText);
    	    Select selectBox = new Select(element);
    	    selectBox.selectByVisibleText(readExcellCellData(excellRow));
		}
		catch(NullPointerException nullException) {
			Log.warn("no data found in row " + excellRow.getRowNum() + ", column " + (excellColumnIndex - 1));
		}
	}
	
	public static void buttonClick(String logText, WebElement element) 
			throws InterruptedException{
    	Log.debugButtonClick(logText);
    	element.click();
	}
	
	public static void buttonClickWithDelay(String logText, WebElement element, int delay) 
			throws InterruptedException {
		buttonClick(logText, element);
	    delay(delay);
	}
	
	public static void buttonClickAndConfirmNewURL(String logText, WebElement element, int delay, String url) 
			throws InterruptedException {
		buttonClickWithDelay(logText, element, delay);
	    if(!getCurrentUrl().equals(url)) {
	    	Log.fatal("Requested URL was not found");
	    }
	}
	
	public static void close() {
		driver.close();
	}
	
	public boolean equals(Object obj) {
		return driver.equals(obj);
	}
	
	public static WebElement findElement(By by) {
		return driver.findElement(by);
	}
	
	public static List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	public static void get(String URL) {
		driver.get(URL);
	}
	
	public static String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public static String getPageSource() {
		return driver.getPageSource();
	}

	public static String getTitle() {
		return driver.getTitle();
	}
	
	public static String getWindowHandle() {
		return 	driver.getWindowHandle();
	}
	
	public static Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public int hashCode() {
		return driver.hashCode();
	}
	
	public static Options manage() {
		return driver.manage();
	}

	public static Navigation navigate() {
		return driver.navigate();
	}

	public static void quit() {
		driver.quit();
	}
	
	public static TargetLocator switchTo() {
		return driver.switchTo();
	}

	public String toString() {
		return driver.toString();
	}
}