package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonConstant.Wait;

public class LivyPage {

	WebDriver driver;
	
	@FindBy(how=How.ID, using="service-actions-dropdown-btn")
	WebElement service_actions_dropdown_btn;
	@FindBy(how=How.CSS, using="ul.pull-right.dropdown-menu")
	WebElement dropdown_menu;
	@FindBy(how=How.CSS, using="button.btn.btn-success")
	WebElement btn_success;
	
	public LivyPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void start() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Wait.TenSecond);
		
		// 서비스 동작 버튼 클릭
		wait.until(ExpectedConditions.elementToBeClickable(service_actions_dropdown_btn)).click();
		
		// 시작 선택
		WebElement we = dropdown_menu.findElements(By.cssSelector("li")).get(0);
		wait.until(ExpectedConditions.attributeToBe(we.findElement(By.tagName("i")), "class", "icon-play enabled"));
		we.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_success)).click(); 
		
	}

	public void stop() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Wait.TenSecond);
		
		// 서비스 동작 버튼 클릭
		wait.until(ExpectedConditions.elementToBeClickable(service_actions_dropdown_btn)).click();
		
		// 시작 선택
		WebElement we = dropdown_menu.findElements(By.cssSelector("li")).get(1);
		wait.until(ExpectedConditions.attributeToBe(we.findElement(By.tagName("i")), "class", "icon-stop enabled"));
		we.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(btn_success)).click(); 
		
	}
		
}
