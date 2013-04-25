package org.sky.auto.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import org.sky.auto.base.AutoBase;
import org.sky.auto.exception.MyAutoException;
@Deprecated
public class StandardOutInfo {
	private static PipedInputStream pis=new PipedInputStream(){
		public void close() {
            keepRunning = false;
            try    {
                super.close();
            }
            catch(IOException e) {
                // 记录错误或其他处理
                // 为简单计，此处我们直接结束
                System.exit(1);
            }
        }
	};
	private static PipedOutputStream pos=new PipedOutputStream();;
	private static boolean keepRunning=true;
	private static FileWriter fw;
	private static PrintStream ps;
	private static PrintStream out=System.out;
	private static String str;
	public static void start(){
		try{
			pos.connect(pis);
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyAutoException("创建截取数据流时出现了错误，没有截取到输出流！");
		}
		ps = new PrintStream(getOutputStream());
		//useMyOut();
		startByteArrayReaderThread();
	}
	
	public static void clearStream(){
		try {
			if(byteArrayOS!=null){
				keepRunning=false;
				AutoBase.sleep(1);
				byteArrayOS.close();
			}
			if(pis!=null){
				pis.close();
			}
			
			if(fw!=null){
				fw.close();
			}
			useSystemOut();
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyAutoException("关闭输出流的时候出现了错误，请检查是否出现了流操作错误！");
		}
		
	}
	
	public static void write(){
		try {
			useMyOut();
			fw = new FileWriter(new File("LOG.txt"));
			String strtemp=getOutputStream().toString("UTF-8");
			fw.write(strtemp);
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static ByteArrayOutputStream byteArrayOS =
	        new ByteArrayOutputStream() {
	        public void close() {
	            keepRunning = false;
	            try {
	                super.close();
	            }
	            catch(IOException e) {
	                // 记录错误或其他处理
	                // 为简单计，此处我们直接结束
	                System.exit(1);
	            }
	        }
	    };
	
	private static void startByteArrayReaderThread() {
        new Thread(new Runnable() {
            public void run() {
                while(keepRunning) {
                	try {
						byteArrayOS.write(getInputStream().read(new byte[10]));
						str=byteArrayOS.toString();
						str=str.substring(0, str.length()-1);
						System.out.println(str);
	                   }catch(IOException e){
	                	   e.printStackTrace();
	                   }
                } 
            }
        }).start();
    } // startByteArrayReaderThread()
	
    public static InputStream getInputStream() {
        return pis;
    } // getInputStream()
    public static ByteArrayOutputStream getOutputStream() {
        return byteArrayOS;
    } // getOutputStream()
	
    public static void useSystemOut(){
    	System.setOut(out);
    	System.setErr(out);
    }
    public static void useMyOut(){
    	System.setOut(ps);
    	System.setErr(ps);
    }
//    private static String format(String string){
//    	string=StringContorl.replaceBlank(string);
//    	String[] strs=string.split("2013");
//    	String temp=null;
//    	for(int i=1;i<strs.length;i++){
//    		strs[i]="2013"+strs[i]+"</br>";
//    		temp=temp+strs[i];
//    	}
//    	
//    	//return temp.replaceAll("null", "");
//    	return temp;
//    }
}
