package org.sky.auto.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class CssAndJSReport {
	private String css ="templet"+File.separator+"css";
	private String js ="templet"+File.separator+"js";
	private String img="templet"+File.separator+"bg.jpg";
	public void copyCssAndJsSource(){
		copyDirectory(css, "report"+File.separator+"css");
		copyDirectory(js, "report"+File.separator+"js");
		copyFile(img,"report"+File.separator+"bg.jpg");
	}
	
	
	protected void copyFile(String oldPath,String newPath){
	       try  {  
	           int  byteread  =  0;  
	           File oldfile  =  new File(oldPath);  
	           if(oldfile.exists())  {  //文件存在时  
	        	   InputStream  inStream  =  new  FileInputStream(oldPath);  //读入原文件  
	               FileOutputStream  fs  =  new  FileOutputStream(newPath);  
	               byte[]  buffer  =  new  byte[100];   
	               while  ((byteread=inStream.read(buffer))  !=  -1)  {  
	                   //bytesum  +=  byteread;  //字节数  文件大小   
	                   fs.write(buffer,  0,  byteread);  
	               }  
	               inStream.close();  
	               fs.close();
	           }  
	       }  
	       catch  (Exception  e)  {  
	           System.out.println("复制单个文件操作出错");  
	           e.printStackTrace();  
	 
	       }  
	}
	
	protected void copyDirectory(String oldPath,String newPath){
		try  {  
	           (new  File(newPath)).mkdirs();  //如果文件夹不存在  则建立新文件夹  
	           File  a=new  File(oldPath);  
	           String[]  file=a.list();  
	           File  temp=null;  
	           for  (int  i  =  0;  i  <  file.length;  i++)  {  
	               if(oldPath.endsWith(File.separator)){  
	                   temp=new  File(oldPath+file[i]);  
	               }  
	               else{  
	                   temp=new  File(oldPath+File.separator+file[i]);  
	               }  
	 
	               if(temp.isFile()){  
	                   FileInputStream  input  =  new  FileInputStream(temp);  
	                   FileOutputStream  output  =  new  FileOutputStream(newPath  +  "/"  +  
	                           (temp.getName()).toString());  
	                   byte[]  b  =  new  byte[1024  *  5];  
	                   int  len;  
	                   while  (  (len  =  input.read(b))  !=  -1)  {  
	                       output.write(b,  0,  len);  
	                   }  
	                   output.flush();  
	                   output.close();  
	                   input.close();  
	               }  
	               if(temp.isDirectory()){//如果是子文件夹  
	                   copyDirectory(oldPath+"/"+file[i],newPath+"/"+file[i]);  
	               }  
	           }  
	       }  
	       catch  (Exception  e)  {  
	           System.out.println("复制整个文件夹内容操作出错");  
	           e.printStackTrace();  
	       }  
	 
	}
}
