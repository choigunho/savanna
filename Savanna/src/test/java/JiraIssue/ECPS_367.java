package JiraIssue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.AccountUtil;

import PageObject.DashboardPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ECPS_367 {

	String userId = AccountUtil.getUserId();
	String pwd =  AccountUtil.getUserPwd();
	
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception{
		driver = AccountUtil.login(userId, pwd);
	}
	
	@Test
	public void ECPS_367() throws Exception {
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		
		dashboardPage.userBtnClick();
		dashboardPage.userMenuSelect(0); //0: 정보, 1:Savanna 관리, 2:설정, 3:로그아웃
		
		String result = dashboardPage.readContents();
		//System.out.println(result);

		assertTrue(result.contains("All Rights Reserved"));
		
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}
