package IntegrationTest;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.AccountUtil;
import common.CommonConstant.Component;
import common.CommonConstant.ComponentAction;
import common.CommonConstant.ServiceStatus;
import PageObject.HostPage;
import PageObject.ServicePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddKafkaBrokerWhileDataProducing {

	String userId = AccountUtil.getUserId();
	String pwd =  AccountUtil.getUserPwd();
	
	WebDriver driver;
	ServicePage servicePage;
	HostPage hostPage;
	
	@Before
	public void setUp() throws Exception{
		driver = AccountUtil.login(userId, pwd);
		servicePage = PageFactory.initElements(driver, ServicePage.class);
		hostPage = PageFactory.initElements(driver, HostPage.class);
	}
	
	
	/*
	 * 케이스 요약 : Kafka 데이터 프로듀싱 중 노드 추가/삭제
	 * 
	 * Log Generator -> Kafka -> 데이터 프로듀싱 지속 -> Kafka 노드 제거 -> 데이터 확인 -> Kafka 노드 추가 (Kafka Replication 2 이상 설정)
	 */
	@Test
	public void ALL_I_0010() throws Exception {

		// todo
		// Log Generator에서 로그 생성
		
		// 카프카 브로커 중지
		
		// 호스트 삭제
		
		// 호스트 추가
		
		// 카프카 브로커 시작
		
	}
	
	@Test
	public void step1_stopKafkaBroker() throws Exception {
		String host = "bp148";
		
		// 시작
		hostPage.componentAction(host, Component.KafkaBroker, ComponentAction.Stop);
		servicePage.checkChangeOfCompnentStatus(host, Component.KafkaBroker, ServiceStatus.Stoped, driver);
	}
	
	@Test
	public void step2_deleteKafkaBroker() throws Exception {
		String host = "bp148";
		
		// 시작
		hostPage.componentAction(host, Component.KafkaBroker, ComponentAction.Delete);
	}
	
	@Test
	public void step3_addKafkaBroker() throws Exception {
		String host = "bp148";
		hostPage.addComponent(host, Component.KafkaBroker);
		
		// 시작
		hostPage.componentAction(host, Component.KafkaBroker, ComponentAction.Start);
		servicePage.checkChangeOfCompnentStatus(host, Component.KafkaBroker, ServiceStatus.Started, driver);
	}
	
	@Test
	public void step4_startKafkaBroker() throws Exception {
		String host = "bp148";
		
		// 시작
		hostPage.componentAction(host, Component.KafkaBroker, ComponentAction.Start);
		servicePage.checkChangeOfCompnentStatus(host, Component.KafkaBroker, ServiceStatus.Started, driver);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
}
