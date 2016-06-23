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
import common.CommonConstant.Sleep;
import common.RemoteShellUtil;
import common.TestEnv;
import common.TestVar;
import common.CommonConstant.Component;
import common.CommonConstant.ErrorMessages;
import common.CommonConstant.Service;
import PageObject.ServicePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Flume {

	String userId = AccountUtil.getUserId();
	String pwd =  AccountUtil.getUserPwd();
	
	WebDriver driver;
	ServicePage service;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception{
		driver = AccountUtil.login(userId, pwd);
		service = PageFactory.initElements(driver, ServicePage.class);
	}
	
	@Test
	public void case1_ServiceStop() throws Exception {
		
		// 서비스 중지  
		service.stop(Service.Flume);
		
		// 프로세스 kill 확인
		List<String> hosts = service.getHost(Component.Flume);
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep flume | grep -v grep";
		boolean bCheckExitCode = false;
		
		for(int i=0; i<hosts.size(); i++) {
			String host = TestEnv.getIP(hosts.get(i));
			
			String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			assertTrue(ErrorMessages.ProcessStillAlive, !result.contains(TestVar.FLUME_PROCESS_CMD));
		}
		
	}
	
	@Test
	public void case2_ServiceStart() throws Exception {
	
		// 서비스 시작  
		service.start(Service.Flume);
		
		// 프로세스 시작 대기
		Thread.sleep(Sleep.ThirtySecond);
		
		// 프로세스 running 확인
		List<String> hosts = service.getHost(Component.Flume);
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep flume";
		boolean bCheckExitCode = false;
		
		for(int i=0; i<hosts.size(); i++) {
			String host = TestEnv.getIP(hosts.get(i));
			
			String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			assertTrue(ErrorMessages.ProcessNotStarted, result.contains(TestVar.FLUME_PROCESS_CMD));
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
