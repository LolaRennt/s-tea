package org.sky.auto.server;

import org.sky.auto.base.AutoBase;

public class NodeServer {
	private static TelnetRemoteShell trs;
	public static void start(String host,int port,String user,String password){
		trs=new TelnetRemoteShell(host, port, user, password);
		trs.thread=new Thread(trs);
		trs.thread.start();
		trs.write(user+"\r");
		AutoBase.sleep(1);
		trs.write(password+"\r");
		AutoBase.sleep(1);
		trs.writescript("resource/command.txt");
	}
	
	public static void stop(){
		//trs.write("quit"+"\r");
		trs.disconnect();
		//System.exit(1);
	}
}
