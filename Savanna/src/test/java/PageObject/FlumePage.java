package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonConstant.Wait;

public class FlumePage {

	WebDriver driver;

	@FindBy(how=How.ID, using="service-actions-dropdown-btn")
	WebElement service_actions_dropdown_btn;
	@FindBy(how=How.CSS, using="ul.pull-right.dropdown-menu")
	WebElement dropdown_menu;
	@FindBy(how=How.CSS, using="button.btn.btn-success")
	WebElement btn_success;
	
	public FlumePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void start() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		// 서비스 동작 버튼 클릭
		wait.until(ExpectedConditions.elementToBeClickable(By.id("service-actions-dropdown-btn")));
		service_actions_dropdown_btn.click();

		// 시작 선택
		List<WebElement> elements = dropdown_menu.findElements(By.cssSelector("li"));
		wait.until(ExpectedConditions.attributeToBe(elements.get(0).findElement(By.tagName("i")), "class", "icon-play enabled"));
		elements.get(0).click();
		
		// 최종 확인
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-success"))); 
		btn_success.click();
		
	}
	
	public void stop() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Wait.FiveSecond);
		
		// 서비스 동작 버튼 클릭
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("service-actions-dropdown-btn")));
		service_actions_dropdown_btn.click();
		
		// 중지 선택
		List<WebElement> elements = dropdown_menu.findElements(By.cssSelector("li"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.pull-right.dropdown-menu")));
		elements.get(1).click();
		
		// 최종 확인
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btn-success"))); 
		btn_success.click();
		
	}

}