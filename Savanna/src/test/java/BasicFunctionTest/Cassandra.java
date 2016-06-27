package BasicFunctionTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import common.CommonConstant.ServiceStatus;
import PageObject.ServicePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Cassandra {

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
		service.stop(Service.Cassandra);
		
		// cassandra seed node 문구 변경 확인
		service.checkStatus(Component.Cassandra_SeedNode, ServiceStatus.Stoped, driver);
		
		// cassandra seed node 프로세스 kill 확인
		List<String> hosts = service.getHost(Component.Cassandra_SeedNode);
		String host = TestEnv.getIP(hosts.get(0));
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -p `cat /var/run/cassandra/cassandra.pid 2>/dev/null` > /dev/null 2>&1 && echo Running || echo \"Not Running\"";
		boolean bCheckExitCode = false;
		
		String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		System.out.println("result: " + result);
		assertTrue(ErrorMessages.ProcessStillAlive, result.trim().equals(TestVar.PROCESS_NOT_RUNNING));
		
		// 프로메테우스 S-Casmo 문구 변경 확인
		service.checkStatus(Component.Cassandra_Prometheus, ServiceStatus.Stoped, driver);
		
		// 카산드라 노드 문구 변경 확인
		service.isCassandraNodeStoped(Component.Cassandra_Node);
		// 카산드라 노드 프로세스 kill 확인
		hosts = service.getHost(Component.Cassandra_Node);
		for(int i=0; i<hosts.size(); i++) {
			host = TestEnv.getIP(hosts.get(i));
			
			result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			assertTrue(ErrorMessages.ProcessStillAlive, result.trim().equals(TestVar.PROCESS_NOT_RUNNING));
		}
		
		/*
		// 프로메테우스 S-Casmo 프로세스 kill 확인
		hosts = service.getHost(Component.Cassandra_Prometheus);
		host = TestEnv.getIP(hosts.get(0));
		result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		assertTrue(ErrorMessages.ProcessStillAlive, !result.contains(TestVar.PROCESS_NOT_RUNNING));
		*/
	}
	
	@Test
	public void case2_ServiceStart() throws Exception {

		// 서비스 시작
		service.start(Service.Cassandra);
		
		// cassandra seed node 문구 변경 확인
		service.checkStatus(Component.Cassandra_SeedNode, ServiceStatus.Started, driver);
		
		// cassandra seed node 프로세스 running 확인
		List<String> hosts = service.getHost(Component.Cassandra_SeedNode);
		String host = TestEnv.getIP(hosts.get(0));
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -p `cat /var/run/cassandra/cassandra.pid 2>/dev/null` > /dev/null 2>&1 && echo Running || echo \"Not Running\"";
		boolean bCheckExitCode = false;
		
		String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		System.out.println("result: " + result);
		assertTrue(ErrorMessages.ProcessNotStarted, result.trim().equals(TestVar.PROCESS_RUNNING));
				
		// 프로메테우스 S-Casmo 문구 변경 확인
		service.checkStatus(Component.Cassandra_Prometheus, ServiceStatus.Started, driver);
		
		// 카산드라 노드 문구 변경 확인
		service.isCassandraNodeStarted(Component.Cassandra_Node);
		// 카산드라 노드 프로세스 running 확인
		hosts = service.getHost(Component.Cassandra_Node);
		for(int i=0; i<hosts.size(); i++) {
			host = TestEnv.getIP(hosts.get(i));
			
			result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
			assertTrue(ErrorMessages.ProcessStillAlive, result.trim().equals(TestVar.PROCESS_RUNNING));
		}
				
		/*
		// 프로메테우스 S-Casmo 프로세스 running 확인
		 hosts = service.getHost(Component.Cassandra_Prometheus);
		 host = TestEnv.getIP(hosts.get(0));
		result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		assertTrue(ErrorMessages.ProcessNotStarted, result.contains(TestVar.CASSANDRA_PROMETHEUS));
		*/
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
