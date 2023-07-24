package automation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoggingDemo {
	@Before
	public void setUp() throws IOException {
		Driver.setUp();
	}

	@After
	public void tearDown() {
		//Driver.quit();
	}
	
	@Test
	public void loggingDemo() throws InterruptedException {
        Driver.get("http://healthunify.com/bmicalculator/");
		Driver.setWindowSize();
	    Log.infoOpeningWebsite();
		Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Log.debug("entering weight");
		Driver.findElement(By.name("wg")).sendKeys("87");
		Log.debug("selecting kilograms");
        Driver.findElement(By.name("opt1")).sendKeys("kilograms");
        Log.debug("selecting height in feet");
        Driver.findElement(By.name("opt2")).sendKeys("5");
        Log.debug("selecting height in inches");
        Driver.findElement(By.name("opt3")).sendKeys("10");
        Log.debug("Clicking on calculate");
        Driver.findElement(By.name("cc")).click();

        Log.debug("Getting SIUnit value");
        String SIUnit = Driver.findElement(By.name("si")).getAttribute("value");
        Log.debug("Getting USUnit value");
        String USUnit = Driver.findElement(By.name("us")).getAttribute("value");
        Log.debug("Getting UKUnit value");
        String UKUnit = Driver.findElement(By.name("uk")).getAttribute("value");
        Log.debug("Getting overall description");
        String note = Driver.findElement(By.name("desc")).getAttribute("value");
     
        System.out.println("SIUnit = " + SIUnit);
        System.out.println("USUnit = " + USUnit);
        System.out.println("UKUnit = " + UKUnit);
        System.out.println("note = " + note); 
	}
}