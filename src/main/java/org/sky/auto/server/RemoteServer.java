package org.sky.auto.server;

import java.io.IOException;
import java.io.InputStream;

public class RemoteServer{
	static private Process process; 
	static boolean isClosed;
	public static void start(String serverpath){
		//String serverpath=ConfigParser.getRemoteServerPath();
		isClosed=true;
		try {
			process=Runtime.getRuntime().exec("java -jar"+serverpath+"-role hub");
			 new Thread(){
			    	InputStream input = process.getInputStream(); 
				    byte[] tmp=new byte[1024];
			    	@Override
			    	public void run() {
			    		while(isClosed){
			    	        try {    				
			    				  int i=input.read(tmp, 0, 1024);
			    				  if(i<0)break;
			    				  System.out.print(new String(tmp, 0, i));
			    				
			    			} catch (IOException e) {
			    				// TODO Auto-generated catch block
			    				e.printStackTrace();
			    			}
			    	        try{
			    	        	Thread.sleep(1000);
			    	        }
			    	        catch(Exception ee){
			    	        }
			    	    }
			    	}	
			    	public void destroy() {
			    		try {
							input.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
			    	};
			    }.start();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public static void close(){
		isClosed=false;
		process.destroy();
	}
}
