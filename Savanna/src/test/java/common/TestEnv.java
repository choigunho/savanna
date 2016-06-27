package common;

import java.util.ResourceBundle;

public class TestEnv {

	static ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
	
	public static String getIP(String host) {
		System.out.println("host: " + host);
		return rb.getString(host);
	}
	
	public static String getSYSTEM_USER_ID() {
		return rb.getString("SYSTEM_USER_ID");
	}
	
	public static String getSYSTEM_USER_PASSWORD() {
		return rb.getString("SYSTEM_PASSWORD");
	}
	
}
