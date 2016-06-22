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
import common.CommonConstant.Service;
import common.CommonConstant.ServiceStatus;
import PageObject.CassandraPage;
import PageObject.DashboardPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Cassandra {

	String userId = AccountUtil.getUserId();
	String pwd =  AccountUtil.getUserPwd();
	
	WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception{
		driver = AccountUtil.login(userId, pwd);
//		driver.navigate().to("http://172.21.17.161:8080/#/main/services");
	}
	
	
	@Test
	public void case1_ServiceStop() throws Exception {
		
		// 페이지 이동
		DashboardPage dashboard = PageFactory.initElements(driver, DashboardPage.class);
		dashboard.serviceClick(Service.Cassandra);
		
		// 서비스 중지  
		CassandraPage cassandra = PageFactory.initElements(driver, CassandraPage.class);
		cassandra.stop();
		
		// cassandra seed node 문구 변경 확인
		dashboard.checkStatus(Component.Cassandra_SeedNode, ServiceStatus.Stoped, driver);
		
		// cassandra seed node 프로세스 kill 확인
		List<String> hosts = dashboard.getHost(Component.Cassandra_SeedNode);
		String host = TestEnv.getHOST_IP(hosts.get(0));
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep cassandra";
		boolean bCheckExitCode = true;
		
		String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		assertTrue(!result.contains(TestVar.CASSANDRA_SEED_NODE_CMD));
		
		// 프로메테우스 S-Casmo 문구 변경 확인
		dashboard.checkStatus(Component.Cassandra_Prometheus, ServiceStatus.Stoped, driver);
		
		// 프로메테우스 S-Casmo 프로세스 kill 확인
		hosts = dashboard.getHost(Component.Cassandra_Prometheus);
		host = TestEnv.getHOST_IP(hosts.get(0));
		result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		assertTrue(!result.contains(TestVar.CASSANDRA_PROMETHEUS_CMD));
		
	}
	
	@Test
	public void case2_ServiceStart() throws Exception {

		// 페이지 이동
		DashboardPage dashboard = PageFactory.initElements(driver, DashboardPage.class);
		dashboard.serviceClick(Service.Cassandra);
		
		// 서비스 시작  
		CassandraPage cassandra = PageFactory.initElements(driver, CassandraPage.class);
		cassandra.start();
		
		// cassandra seed node 문구 변경 확인
		dashboard.checkStatus(Component.Cassandra_SeedNode, ServiceStatus.Started, driver);
		
		// cassandra seed node 프로세스 running 확인
		List<String> hosts = dashboard.getHost(Component.Cassandra_SeedNode);
		String host = TestEnv.getHOST_IP(hosts.get(0));
		int port = 22;
		String user = TestEnv.getSYSTEM_USER_ID();
		String passwd = TestEnv.getSYSTEM_USER_PASSWORD();
		String command = "ps -ef | grep cassandra";
		boolean bCheckExitCode = true;
		
		String result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		assertTrue(result.contains(TestVar.CASSANDRA_SEED_NODE_CMD));
				
		// 프로메테우스 S-Casmo 문구 변경 확인
		dashboard.checkStatus(Component.Cassandra_Prometheus, ServiceStatus.Started, driver);
		
		// 프로메테우스 S-Casmo 프로세스 running 확인
		 hosts = dashboard.getHost(Component.Cassandra_Prometheus);
		 host = TestEnv.getHOST_IP(hosts.get(0));
		result = RemoteShellUtil.execCommand(host, port, user, passwd, command, bCheckExitCode);
		assertTrue(result.contains(TestVar.CASSANDRA_PROMETHEUS_CMD));
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
