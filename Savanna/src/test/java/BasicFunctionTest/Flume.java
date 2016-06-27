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
		
		// 프로세스 종료 대기
		service.checkStatus(Component.Flume, "Stopped", driver);
				
		// 프로세스 kill 확인
		List<String> agentNameList = service.getFlumeAgentList();
		List<String> hosts = service.getHost(Component.Flume);
		
		for(int i=0; i<hosts.size(); i++) {
			String host = TestEnv.getIP(hosts.get(i));
			int port = 22;
			String user = TestEnv.getSYSTEM_USER_ID();
			String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
			String agentName = agentNameList.get(i);
			String command = "ps -p `cat /var/run/flume/" + agentName +".pid 2>/dev/null` > /dev/null 2>&1 && echo Running || echo \"Not Running\"";
			boolean bCheckExitCode = false;
			
			String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			assertTrue(ErrorMessages.ProcessStillAlive, result.trim().equals(TestVar.PROCESS_NOT_RUNNING));
		}
		
	}
	
	@Test
	public void case2_ServiceStart() throws Exception {
	
		// 서비스 시작  
		service.start(Service.Flume);
		
		// 프로세스 시작 대기
		service.checkStatus(Component.Flume, "Running", driver);
		
		// 프로세스 running 확인
		List<String> agentNameList = service.getFlumeAgentList();
		List<String> hosts = service.getHost(Component.Flume);
		
		for(int i=0; i<hosts.size(); i++) {
			String host = TestEnv.getIP(hosts.get(i));
			int port = 22;
			String user = TestEnv.getSYSTEM_USER_ID();
			String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
			String agentName = agentNameList.get(i);
			String command = "ps -p `cat /var/run/flume/" + agentName +".pid 2>/dev/null` > /dev/null 2>&1 && echo Running || echo \"Not Running\"";
			boolean bCheckExitCode = false;
			
			String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			assertTrue(ErrorMessages.ProcessNotStarted, result.trim().equals(TestVar.PROCESS_RUNNING));
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
