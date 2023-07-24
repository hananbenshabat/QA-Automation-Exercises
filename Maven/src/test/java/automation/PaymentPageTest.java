package automation;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.io.IOException;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class PaymentPageTest {
	public static final String EXCEL_PAYMENT_PAGE_FILE_NAME = "inputPaymentPageTest.xlsx";
	public static final String XPATH_CART_BUTTON = "/html/body/nav/div/div[2]/ul/li[5]/a/span";
	public static final String XPATH_GUEST_CHECKOUT_BUTTON = "/html/body/div[2]/div/div/div/div[1]/div[2]/div/div/div[1]/div[2]/label/input";
	public static final String XPATH_CONDITIONS_BUTTON = "/html/body/div[2]/div/div/div/div[5]/div[2]/div/div[2]/div/input[1]";
	
	@Before
	public void setUp() throws IOException {
		Driver.setUp(Driver.EXCEL_FILE_PATH, EXCEL_PAYMENT_PAGE_FILE_NAME, Driver.EXCEL_SHEET_NAME);
	}
	
	@After
	public void tearDown() {
		Driver.quit();
	}
	
	@Test
	public void paymentPageTest() throws InterruptedException {
		//Test count, each test in a row in the excel file
		int excelRowCount = ReadExcl.getRowcount() + 1;
		Sheet thsSheet = ReadExcl.getsheet();
		
		for (int excellRowIndex = 0; excellRowIndex < excelRowCount; excellRowIndex++) {
			try {
				Log.testLogging(excellRowIndex + 1);
				Row excellRow = thsSheet.getRow(excellRowIndex);
				Driver.importToBrowser(excellRow);
	    	    Log.debugButtonClick("add to cart");
	    	    Driver.findElement(By.id("button-cart")).click();
	    	    Driver.buttonClickWithDelay("checkout", Driver.findElement(By.xpath(XPATH_CART_BUTTON)), Driver.ONE_SECOND);
	    	    Log.info("Step 1: Checkout Options");
	    	    Driver.buttonClickWithDelay("guest checkout", Driver.findElement(By.xpath(XPATH_GUEST_CHECKOUT_BUTTON)), Driver.ONE_SECOND);
	    	    Driver.buttonClickWithDelay("continue", Driver.findElement(By.id("button-account")), Driver.ONE_SECOND);
	    	    Log.info("Step 2 & 3: Billing & Delivery Details");
				Driver.importToEmptyTextBox("entering first name", Driver.findElement(By.id("input-payment-firstname")), excellRow);
				Driver.importToEmptyTextBox("entering last name", Driver.findElement(By.id("input-payment-lastname")), excellRow);
				Driver.importToEmptyTextBox("entering email", Driver.findElement(By.id("input-payment-email")), excellRow);
				Driver.importToEmptyTextBox("entering telephone", Driver.findElement(By.id("input-payment-telephone")), excellRow);
				Driver.importToEmptyTextBox("entering company", Driver.findElement(By.id("input-payment-company")), excellRow);
				Driver.importToEmptyTextBox("entering adress 1", Driver.findElement(By.id("input-payment-address-1")), excellRow);
				Driver.importToEmptyTextBox("entering adress 2", Driver.findElement(By.id("input-payment-address-2")), excellRow);
				Driver.importToEmptyTextBox("entering city", Driver.findElement(By.id("input-payment-city")), excellRow);
				Driver.importToEmptyTextBox("entering postcode", Driver.findElement(By.id("input-payment-postcode")), excellRow);
				Driver.importToSelectBox("selecting country", Driver.findElement(By.id("input-payment-country")), excellRow);
	    	    Driver.delay(Driver.ONE_SECOND);
				Driver.importToSelectBox("selecting region", Driver.findElement(By.id("input-payment-zone")), excellRow);
	    	    Driver.delay(Driver.ONE_SECOND);
	    	    Driver.buttonClickWithDelay("continue", Driver.findElement(By.id("button-guest")), Driver.ONE_SECOND);
	    	    Log.info("Step 4: Delivery Method");
	    	    Driver.buttonClickWithDelay("continue", Driver.findElement(By.id("button-shipping-method")), Driver.ONE_SECOND);
	    	    Log.info("Step 5: Payment Method");
	    	    Driver.buttonClick("terms & conditions", Driver.findElement(By.xpath(XPATH_CONDITIONS_BUTTON)));
	    	    Driver.buttonClickWithDelay("continue", Driver.findElement(By.id("button-payment-method")), Driver.ONE_SECOND);
	    	    Log.info("Step 6: Confirm Order");
	    	    Driver.buttonClickWithDelay("confirm order", Driver.findElement(By.id("button-confirm")), Driver.ONE_SECOND);
			}
			catch (NoSuchElementException noElementException) {
				continue;
			}
		}
	}
}
