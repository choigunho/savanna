package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.CommonConstant.Sleep;

public class CassandraPage {

	WebDriver driver;

	@FindBy(how=How.ID, using="service-actions-dropdown-btn")
	WebElement serviceactionsdropdownbtn;
	@FindBy(how=How.CSS, using="ul.pull-right.dropdown-menu")
	WebElement dropdownmenu;
	@FindBy(how=How.CLASS_NAME, using="value_for_cassandraseednode")
	WebElement value_for_cassandraseednode;
	@FindBy(how=How.CLASS_NAME, using="value_for_prometheus")
	WebElement value_for_prometheus;
	@FindBy(how=How.CSS, using="button.btn.btn-success")
	WebElement btnsuccess;
	
	@FindBy(how=How.CLASS_NAME, using="quick-links-dropdown")
	WebElement pageLink;

	
	public CassandraPage(WebDriver driver) {
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
		
		btnsuccess.click();
		
	}
	
}