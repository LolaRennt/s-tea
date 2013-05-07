package org.sky.auto.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;
import org.sky.auto.base.AutoBase;

public class TelnetRemoteShell {
	private InputStream input;
	private PrintStream out;
	private int port;
	private String host;
	private String user;
	private String password;
	private TelnetClient tclient;
	//private char prompt='>';
	public TelnetRemoteShell(String host,int port,String user,String password){
		this.host=host;
		this.setPort(port);
		this.user=user;
		this.password=password;
		tclient=new TelnetClient();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public TelnetRemoteShell(){
		
	}
	
	/**登录*/
	public boolean login(){
		try {
			tclient.connect(host, port);
			input=tclient.getInputStream();
			out = new PrintStream(tclient.getOutputStream());  
			readUntil("login:");  
			write(user+"\r");  
			AutoBase.sleep(1);
			readUntil("password:");  
			write(password+"\r"); 
			readUntil(user+">");
			return true;
		} catch (SocketException e) {
			disconnect();
			return false;
		} catch (IOException e) {
			disconnect();
			return false;
		}
		 

	}

	private void write(String value) {
		out.print(value);
		out.flush();
		//System.out.println(value);
		
	}

	private String readUntil(String pattern) {
		try {  
			char lastChar = pattern.charAt(pattern.length() - 1);  
			StringBuffer sb = new StringBuffer();  
			char ch = (char) input.read();  
			while (true) {  
				System.out.print(ch);  
				sb.append(ch);  
				if (ch == lastChar) {  
					if (sb.toString().trim().endsWith(pattern)) {  
						return sb.toString();  
					}  
				}  
				ch = (char) input.read();  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
		return null;  

		
	}
	
	public String sendCommand(String command){
		write(command);
		return null;
	}
	
	public void disconnect(){
		try {
			this.tclient.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void main(String[] args) {
		TelnetRemoteShell trs = new TelnetRemoteShell("192.168.1.102", 23, "wlg", "123456");
		trs.login();
		trs.sendCommand("dir");
		trs.disconnect();
	}
}
