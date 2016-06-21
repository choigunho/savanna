package common;

import java.util.ResourceBundle;

public class TestEnv {

	static ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
	
	public static String getHOST_IP(String host) {
		System.out.println("host: " + host);
		return rb.getString(host);
	}
	
	public static String getSYSTEM_USER_ID() {
		return rb.getString("SYSTEM_USER_ID");
	}
	
	public static String getSYSTEM_USER_PASSWORD() {
		return rb.getString("SYSTEM_PASSWORD");
	}
	
	//Spark
	public static String getSPARK_HISTORY_SERVER_PORT() {
		return rb.getString("SPARK_HISTORY_SERVER_PORT");
	}

	//Elasticsearch
	public static String getMASTER_DATA_NODE_PORT() {
		return rb.getString("KAFKA_BROKER_PORT");
	}
	
	//Kafka
	public static String getKAFKA_BROKER_PORT() {
		return rb.getString("KAFKA_BROKER_PORT");
	}
	
	//Livy
	public static String getLIVY_SPARK_REST_SERVER_PORT() {
		return rb.getString("LIVY_SPARK_REST_SERVER_PORT");
	}
}
