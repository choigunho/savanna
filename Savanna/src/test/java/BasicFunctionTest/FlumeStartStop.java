package BasicFunctionTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.AccountUtil;
import common.RemoteShellUtil;
import common.TestEnv;
import common.TestVar;
import common.CommonConstant.Component;
import common.CommonConstant.Service;
import PageObject.DashboardPage;
import PageObject.FlumePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FlumeStartStop {

	String userId = AccountUtil.getUserId();
	String pwd =  AccountUtil.getUserPwd();
	
	WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception{
		driver = AccountUtil.login(userId, pwd);
	}
	
	@Test
	public void case1_FluemServiceStop() throws Exception {
		
		// 페이지 이동
		DashboardPage dashboard = PageFactory.initElements(driver, DashboardPage.class);
		dashboard.serviceClick(Service.Flume);
		
		// 서비스 중지  
		FlumePage flume = PageFactory.initElements(driver, FlumePage.class);
		flume.stop();
		
		// 프로세스 kill 확인
		List<String> hosts = dashboard.getHost(Component.Flume);
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep flume";
		boolean bCheckExitCode = true;
		
		for(int i=0; i<hosts.size(); i++) {
			String host = TestEnv.getHOST_IP(hosts.get(i));
			
			String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			// !넣어야함
			assertTrue(result.contains(TestVar.FLUME_PROCESS_CMD));
		}
		
	}
	
	@Test
	public void case2_FlumeServiceStart() throws Exception {
	
		// 페이지 이동
		DashboardPage dashboard = PageFactory.initElements(driver, DashboardPage.class);
		dashboard.serviceClick(Service.Flume);
		
		// 서비스 시작  
		FlumePage flume = PageFactory.initElements(driver, FlumePage.class);
		flume.start();
		
		// 프로세스 running 확인
		List<String> hosts = dashboard.getHost(Component.Flume);
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep flume";
		boolean bCheckExitCode = true;
		
		for(int i=0; i<hosts.size(); i++) {
			String host = TestEnv.getHOST_IP(hosts.get(i));
			
			String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			assertTrue(result.contains(TestVar.FLUME_PROCESS_CMD));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
}
