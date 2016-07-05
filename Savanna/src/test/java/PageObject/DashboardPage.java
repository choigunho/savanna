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

	/**
	 * 상단 네비게이션바에 있는 백그라운드 오퍼레이션 버튼을 클릭하여 모달을 팝업한다. 
	 * 
	 */
	public void backgroundOperationCountBtnClick() {
		backgroundOperationCount.click();
	}
	
	/**
	 * 백그라운드 오퍼레이션 모달 상단의 문구를 읽는다. 
	 * 
	 */
	public String readModarHeader() {
		return modarHeader.getText();
	}
	
	/**
	 * 상단 네비게이션바 우측끝에 있는 드롭다운 메뉴를 클릭한다. 
	 * 
	 */
	public void userBtnClick() throws Exception {
		btn_dropdownMenu.click();
		Thread.sleep(Sleep.OneSecond);
	}
	
	/**
	 * 상단 네비게이션바 우측끝에 있는 드롭다운 메뉴에서 원하는 항목을 클릭한다. 
	 * 
	 */
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
	
}