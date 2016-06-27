package common;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class RemoteShellUtil {

	public RemoteShellUtil() {
		
	}

	public static void execCommand(List<String> host, int port, String user, String passwd, String command, boolean bCheckExitCode) {
		
		for(int i=0; i<host.size(); i++) {
			String result = execCommand(host.get(i), port, user, passwd, command, bCheckExitCode);
		}
		
	}
	
	public static String execCommand(String host, int port, String user, String passwd, String command, boolean bCheckExitCode) {
		System.out.println("host: " + host + ", port:" + port + ", user: " + user + ", command:" + command);
		
		JSch jsch = new JSch();
		Session session = null;
		Channel channel = null;
		StringBuilder outputBuffer = new StringBuilder();
		try {
			// TODO: Emit friendly error if ~/.ssh doesn't exist.
	        String dotSshDir = System.getProperty("user.home") + "/.ssh";
	        String userName = System.getProperty("user.name");

	        jsch.setKnownHosts(dotSshDir + "/known_hosts");
			
			session=jsch.getSession(user, host, port);
			session.setPassword(passwd);
			session.connect();
			
			channel = session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			((ChannelExec)channel).setErrStream(System.err);
			InputStream in=channel.getInputStream();
			channel.connect();
			
			byte[] tmp=new byte[1024];
			while(true) {
				while(in.available()>0) {
					int i=in.read(tmp, 0, 1024);
					if(i<0)break;
					outputBuffer.append(new String(tmp, 0, i));
				}
				if(channel.isClosed()) {
					if(in.available()>0) continue; 
					
					System.out.println("exit-status: "+channel.getExitStatus());
					if(bCheckExitCode) {
						if(channel.getExitStatus() != 0) {
							System.out.println(outputBuffer.toString());
							fail("return code : " + channel.getExitStatus());
						}
					}
					break;
				}
				try{Thread.sleep(1000);}catch(Exception ee){}
			}
		} catch (JSchException e) {
			e.printStackTrace();
			fail("JSchException Occured!!!");
		} catch (IOException e) {
			e.printStackTrace();
			fail("IOException Occured!!!");
		} finally {
			if(channel !=null && channel.isConnected())	{channel.disconnect();}
			if(session !=null && session.isConnected())	{session.disconnect();}
		}
		
		return outputBuffer.toString();
	}

}
