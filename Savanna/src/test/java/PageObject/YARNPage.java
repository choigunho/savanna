package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.CommonConstant.Sleep;

public class YARNPage {

	WebDriver driver;

	@FindBy(how=How.ID, using="service-actions-dropdown-btn")
	WebElement serviceactionsdropdownbtn;
	@FindBy(how=How.CSS, using="ul.pull-right.dropdown-menu")
	WebElement dropdownmenu;
	
	@FindBy(how=How.CLASS_NAME, using="label_for_app_timeline_server")
	WebElement label_for_app_timeline_server;
	@FindBy(how=How.CLASS_NAME, using="label_for_resourcemanager")
	WebElement label_for_resourcemanager;
	
	@FindBy(how=How.CLASS_NAME, using="value_for_app_timeline_server")
	WebElement value_for_app_timeline_server;
	@FindBy(how=How.CLASS_NAME, using="value_for_resourcemanager")
	WebElement value_for_resourcemanager;
	@FindBy(how=How.CSS, using="button.btn.btn-success")
	WebElement btnsuccess;
	
	@FindBy(how=How.CLASS_NAME, using="quick-links-dropdown")
	WebElement pageLink;

	
	public YARNPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void start() throws Exception {
		
		// 서비스 동작 버튼 클릭
		serviceactionsdropdownbtn.click();
		Thread.sleep(Sleep.HalfASecond);
		
		// 시작 선택
		List<WebElement> elements = dropdownmenu.findElements(By.cssSelector("li"));
		elements.get(0).click();
		Thread.sleep(Sleep.HalfASecond);
		
		// 최종 확인
		btnsuccess.click();
		
	}
	
	public void stop() throws Exception {
		
		// 서비스 동작 버튼 클릭
		serviceactionsdropdownbtn.click();
		Thread.sleep(Sleep.HalfASecond);
		
		// 중지 선택
		List<WebElement> elements = dropdownmenu.findElements(By.cssSelector("li"));
		elements.get(1).click();
		Thread.sleep(Sleep.HalfASecond);
		
		// 최종 확인
		btnsuccess.click();
		
	}

	public void pageLinkClick() throws Exception {
		pageLink.click();
		Thread.sleep(Sleep.OneSecond);
	}
	
	public List<WebElement> readPageLinkMenu() {
		List<WebElement> elements = pageLink.findElement(By.className("dropdown-menu")).findElements(By.cssSelector("li"));
		return elements;
	}
	
	
}