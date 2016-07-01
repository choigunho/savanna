package common;

import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonConstant.Wait;

public class AccountUtil {

	public static WebDriver login(String userid, String password) throws Exception {
		
		WebDriver driver = new FirefoxDriver();
		driver.get(getSavannaManagerUrl() + "/#/login");
		
		WebDriverWait wait = new WebDriverWait(driver, Wait.FiveSecond);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("login-user-name"))).sendKeys(userid);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("login-user-password"))).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("login-btn"))).click();; 
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("nav-services"))); 
		
		return driver;
	}
	
	static ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
	
	public static String getUserId() {
		return rb.getString("TEST_ACCOUNT_USER_ID");
	}
	
	public static String getUserPwd() {
		return rb.getString("TEST_ACCOUNT_USER_PW");
	}
	
	public static String getSavannaManagerUrl() {
		return rb.getString("SAVANNA_MANAGER_URL");
	}
	
}
