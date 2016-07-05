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

	@FindBy(how=How.CLASS_NAME, using="quick-links-dropdown")
	WebElement pageLink;

	public YARNPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * 페이지 링크를 클릭한다.
	 * 
	 */
	public void pageLinkClick() throws Exception {
		pageLink.click();
		Thread.sleep(Sleep.OneSecond);
	}
	
	/**
	 * 페이지 링크의 메뉴 목록을 리스트로 반환한다.
	 * 
	 */
	public List<WebElement> readPageLinkMenu() {
		List<WebElement> elements = pageLink.findElement(By.className("dropdown-menu")).findElements(By.cssSelector("li"));
		return elements;
	}
	
	
}