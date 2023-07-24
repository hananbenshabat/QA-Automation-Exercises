package automation;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GoogleSearchTest {  
	@Before
	public void setUp() throws IOException {
		Driver.setUp();
	}

	@After
	public void tearDown() {
		//Driver.quit();
	}
	
	@Test
	public void googleSearchTest() {
		// Test name: GoogleSearchTest
	    // Step # | name | target | value
	    // 1 | open | / | 
		Driver.get("https://www.google.com/");
	    // 2 | setWindowSize | 1500x900 | 
		Driver.setWindowSize();
	    // 3 | click | name=q | 
		Driver.findElement(By.name("q")).click();
	    // 4 | type | name=q | unsplush
		Driver.findElement(By.name("q")).sendKeys("unsplush");
	    // 5 | sendKeys | name=q | ${KEY_ENTER}
		Driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	}
}
