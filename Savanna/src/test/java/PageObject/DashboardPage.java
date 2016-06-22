package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.CommonConstant.Sleep;

public class DashboardPage {

	WebDriver driver;
	
	@FindBy(how=How.ID, using="span-bg-operation-count")
	WebElement backgroundOperationCount;
	@FindBy(how=How.ID, using="modal-label")
	WebElement modarHeader;
	@FindBy(how=How.CLASS_NAME, using="nav-services")
	WebElement navServices;
	@FindBy(how=How.CLASS_NAME, using="navbar-inner")
	WebElement navbarInner;
	@FindBy(how=How.ID, using="about")
	WebElement about;
	
	@FindBy(how=How.CSS, using="button.btn.dropdown-toggle")
	WebElement btn_dropdownMenu;
	@FindBy(how=How.CSS, using="ul.dropdown-menu")
	WebElement dropdownMenu;
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	public void backgroundOperationCountBtnClick() {
		backgroundOperationCount.click();
	}
	
	public String readModarHeader() {
		return modarHeader.getText();
	}
	
	public void userBtnClick() throws Exception {
		btn_dropdownMenu.click();
		Thread.sleep(Sleep.OneSecond);
	}
	
	public void userMenuSelect(int menu) throws Exception {
		List<WebElement> items = dropdownMenu.findElements(By.cssSelector("li"));
			switch(menu) {
				case 0: items.get(0).click(); break;
				case 1: items.get(1).click(); break;
				case 2: items.get(2).click(); break;
				case 3: items.get(3).click(); break;
			}
		Thread.sleep(Sleep.OneSecond);
	}
	
	public String readContents() {
		return driver.findElement(By.className("modal-body")).getText();
	}
	
	@FindBy(how=How.CLASS_NAME, using="trim_hostname")
	List<WebElement> trim_hostname;
	
	public List<WebElement> getFlumeHosts(String component) {
		List<WebElement> hosts = trim_hostname;
		return hosts;
	}
	
	public List<WebElement> getDataNodeHosts(String component) {
		List<WebElement> hosts = trim_hostname;
		return hosts;
	}
}