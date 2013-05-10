package org.sky.auto.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;
import org.apache.commons.net.telnet.EchoOptionHandler;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;
import org.apache.commons.net.telnet.SuppressGAOptionHandler;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TelnetNotificationHandler;
import org.apache.commons.net.telnet.TerminalTypeOptionHandler;
import org.apache.log4j.Logger;

public class TelnetRemoteShell implements Runnable, TelnetNotificationHandler{
	private Logger logger =Logger.getLogger(TelnetRemoteShell.class);
	private InputStream input;
	private PrintStream out;
	private int port;
	private String host;
	private String user;
	private String password;
	private TelnetClient tclient;
	public Thread thread;
	//private char prompt='>';
	int ret_read;

	public TelnetRemoteShell(String host,int port,String user,String password){
		this.host=host;
		this.setPort(port);
		this.user=user;
		this.password=password;
		tclient=new TelnetClient();
		connect();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	protected void write(String value) {
		out.print(value);
		//System.out.println(value);
		out.flush();
		while(!thread.getState().equals(Thread.State.BLOCKED));
	}

	
	public void disconnect(){
		try {
//			this.write("quit"+"\r");
//			this.write("exit"+"\r");
			ret_read=-1;
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
	
	protected boolean connect(){
		try{
			TerminalTypeOptionHandler ttopt = new TerminalTypeOptionHandler("VT100", false, false, true, false);
			EchoOptionHandler echoopt = new EchoOptionHandler(true, false, true, false);
			SuppressGAOptionHandler gaopt = new SuppressGAOptionHandler(true, true, true, true);
			try {
				 tclient.addOptionHandler(ttopt);
				 tclient.addOptionHandler(echoopt);
				 tclient.addOptionHandler(gaopt);
			 } catch (InvalidTelnetOptionException e) {
				 e.printStackTrace();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
			 try {
				 tclient.connect(getHost(), getPort());
				 thread = new Thread(this);
				 tclient.registerNotifHandler(this);
				 input =tclient.getInputStream();
				 out = new PrintStream(tclient.getOutputStream()); 
				 logger.info("连接到节点服务器正常");
				 return true;
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
			try {
				tclient.disconnect();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		return false;
	}
	
	
	 public void receivedNegotiation(int negotiation_code, int option_code) {  
	        String command = null;  
	        if (negotiation_code == TelnetNotificationHandler.RECEIVED_DO) {  
	            command = "DO";  
	        } else if (negotiation_code == TelnetNotificationHandler.RECEIVED_DONT) {  
	            command = "DONT";  
	        } else if (negotiation_code == TelnetNotificationHandler.RECEIVED_WILL) {  
	            command = "WILL";  
	        } else if (negotiation_code == TelnetNotificationHandler.RECEIVED_WONT) {  
	            command = "WONT";  
	        }  
	        System.out.println("Received " + command + " for option code "  
	                + option_code);  
	    } 
	 
	 public void run() {  
	        input = tclient.getInputStream();  
	        try {     
	            byte[] buff = new byte[1024];     
	            ret_read = 0;     
	            do {     
	            	if(this.ret_read<0){
	            		break;
	            	}
	                ret_read = input.read(buff);     
	                if (ret_read > 0) {     
	                    //System.out.print(new String(buff, 0, ret_read));     
	                }     
	            } while (ret_read >= 0);         
	        } catch (Exception e) {  
	            System.err.println("Exception while reading socket:"  
	                    + e.getMessage());  
	        }   
	    }  

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	protected void writescript(String filename) {     
        try {     
            if(new File(filename).exists()){     
                FileReader f = new FileReader(filename);     
                BufferedReader reader = new BufferedReader(f);     
                String command = "";     
                while(command !=null){     
                    command = reader.readLine();     
                    if(command!=null){
                    	write(command+"\r");     
                    	logger.info("执行了命令"+command);
                    }
                }   
                reader.close();
            }     
        } catch (FileNotFoundException e) {     
            // TODO Auto-generated catch block     
            e.printStackTrace();     
        } catch (IOException e) {     
            // TODO Auto-generated catch block     
            e.printStackTrace();     
        }             
    }

	
	public static void main(String[] args) {
		TelnetRemoteShell trs = new TelnetRemoteShell("10.168.20.129", 23, "sky", "123");
		trs.thread=new Thread(trs);
		trs.thread.start();
		trs.write(trs.getUser()+"\r");
		trs.write(trs.getPassword()+"\r");
		trs.writescript("resource/command.txt");
	}
	
	
}
