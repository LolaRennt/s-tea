package org.sky.auto.server;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

public class SSH2RemoteShell {
	static private Logger logger = Logger.getLogger(SSH2RemoteShell.class);
	private String username;
	private String password;
	private String host;
	private int port;
	private JSch jsch;
	private Session session;
	private Channel channel;
	UserInfo ui = new SimpleUserInfo(){
        public void showMessage(String message){
         // JOptionPane.showMessageDialog(null, message);
        }
        public boolean promptYesNo(String message){
			return true;
//          Object[] options={ "yes", "no" };
//          int foo=JOptionPane.showOptionDialog(null, 
//                                               message,
//                                               "Warning", 
//                                               JOptionPane.DEFAULT_OPTION, 
//                                               JOptionPane.WARNING_MESSAGE,
//                                               null, options, options[0]);
//          return foo==0;
        }
 
        // If password is not given before the invocation of Session#connect(),
        // implement also following methods,
        //   * UserInfo#getPassword(),
        //   * UserInfo#promptPassword(String message) and
        //   * UIKeyboardInteractive#promptKeyboardInteractive()
 
      };
//	UserInfo ui = new SimpleUserInfo(){
//		
//	};
	public SSH2RemoteShell(String host,String username,String password,int port){
		this.username=username;
		this.password=password;
		this.host=host;
		this.port=port;
		this.jsch=new JSch();
	}
	public SSH2RemoteShell(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	protected Session getSession(){
		try {
			session= jsch.getSession(getUsername(), getHost(), getPort());
			session.setPassword(this.password);
			session.setUserInfo(ui);
			return session;
		} catch (JSchException e) {
			e.printStackTrace();
			logger.error("获取远程服务器的session的时候出现了错误！请检查输入的用户名和主机");
			throw new RuntimeException("获取远程服务器的session的时候出现了错误！请检查输入的用户名和主机");
		}
	}
	
	public Session login(){
		try {
			getSession().connect(30000);
			return session;
		} catch (JSchException e) {
			System.out.println(e);
		}
		return session;	
	}
	
	public void exec(String... command){
		InputStream in;
		try {
			channel=session.openChannel("exec");
			((ChannelExec)channel).setCommand(command[0]);
		    channel.setInputStream(null);
		    ((ChannelExec)channel).setErrStream(System.err);
			in = channel.getInputStream();
			channel.connect(); 
		    byte[] tmp=new byte[1024];
		    while(true){
		        while(in.available()>0){
		          int i=in.read(tmp, 0, 1024);
		          if(i<0)break;
		          System.out.print(new String(tmp, 0, i));
		        }
		        if(channel.isClosed()){
		          System.out.println("exit-status: "+channel.getExitStatus());
		          break;
		        }
		        try{
		        	Thread.sleep(1000);
		        }
		        catch(Exception ee){
		        }
		      }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
	}
	
	public static class MyUserInfo implements UserInfo, UIKeyboardInteractive{
	    public String getPassword(){ return passwd; }
	    public boolean promptYesNo(String str){
	      Object[] options={ "yes", "no" };
	      int foo=JOptionPane.showOptionDialog(null, 
	             str,
	             "Warning", 
	             JOptionPane.DEFAULT_OPTION, 
	             JOptionPane.WARNING_MESSAGE,
	             null, options, options[0]);
	       return foo==0;
	    }
	  
	    String passwd;
	    JTextField passwordField=(JTextField)new JPasswordField(20);
	 
	    public String getPassphrase(){ return null; }
	    public boolean promptPassphrase(String message){ return true; }
	    public boolean promptPassword(String message){
	      Object[] ob={passwordField}; 
	      int result=
	        JOptionPane.showConfirmDialog(null, ob, message,
	                                      JOptionPane.OK_CANCEL_OPTION);
	      if(result==JOptionPane.OK_OPTION){
	        passwd=passwordField.getText();
	        return true;
	      }
	      else{ 
	        return false; 
	      }
	    }
	    public void showMessage(String message){
	      JOptionPane.showMessageDialog(null, message);
	    }
	    final GridBagConstraints gbc = 
	      new GridBagConstraints(0,0,1,1,1,1,
	                             GridBagConstraints.NORTHWEST,
	                             GridBagConstraints.NONE,
	                             new Insets(0,0,0,0),0,0);
	    private Container panel;
	    public String[] promptKeyboardInteractive(String destination,
	                                              String name,
	                                              String instruction,
	                                              String[] prompt,
	                                              boolean[] echo){
	      panel = new JPanel();
	      panel.setLayout(new GridBagLayout());
	 
	      gbc.weightx = 1.0;
	      gbc.gridwidth = GridBagConstraints.REMAINDER;
	      gbc.gridx = 0;
	      panel.add(new JLabel(instruction), gbc);
	      gbc.gridy++;
	 
	      gbc.gridwidth = GridBagConstraints.RELATIVE;
	 
	      JTextField[] texts=new JTextField[prompt.length];
	      for(int i=0; i<prompt.length; i++){
	        gbc.fill = GridBagConstraints.NONE;
	        gbc.gridx = 0;
	        gbc.weightx = 1;
	        panel.add(new JLabel(prompt[i]),gbc);
	 
	        gbc.gridx = 1;
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        gbc.weighty = 1;
	        if(echo[i]){
	          texts[i]=new JTextField(20);
	        }
	        else{
	          texts[i]=new JPasswordField(20);
	        }
	        panel.add(texts[i], gbc);
	        gbc.gridy++;
	      }
	 
	      if(JOptionPane.showConfirmDialog(null, panel, 
	                                       destination+": "+name,
	                                       JOptionPane.OK_CANCEL_OPTION,
	                                       JOptionPane.QUESTION_MESSAGE)
	         ==JOptionPane.OK_OPTION){
	        String[] response=new String[prompt.length];
	        for(int i=0; i<prompt.length; i++){
	          response[i]=texts[i].getText();
	        }
		return response;
	      }
	      else{
	        return null;  // cancel
	      }
	    }
	   	}
	
	public void close(){
		this.channel.disconnect();
		this.session.disconnect();
	}
	
	 public static abstract class SimpleUserInfo
     implements UserInfo, UIKeyboardInteractive{
		 public String getPassword(){ return null; }
		 public boolean promptYesNo(String str){ return false; }
		 public String getPassphrase(){ return null; }
		 public boolean promptPassphrase(String message){ return false; }
		 public boolean promptPassword(String message){ return false; }
		 public void showMessage(String message){ }
		 public String[] promptKeyboardInteractive(String destination,
                         String name,
                         String instruction,
                         String[] prompt,
                         boolean[] echo){
			 return null;
		 }
	 }
}
