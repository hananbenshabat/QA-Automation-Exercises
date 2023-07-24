package automation;

import org.openqa.selenium.By;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SaucedemoLogin {
	@Before
	public void setUp() throws IOException {
		Driver.setUp();
	}

	@After
	public void tearDown() {
		//Driver.quit();
	}
	
	@Test
	public void saucedemoLogin() {
		Driver.get("https://www.saucedemo.com/");
		Driver.setWindowSize();
		Driver.findElement(By.id("user-name")).sendKeys("standard_user");
		Driver.findElement(By.id("password")).sendKeys("secret_sauce");
		Driver.findElement(By.id("login-button")).click();
	}
}
