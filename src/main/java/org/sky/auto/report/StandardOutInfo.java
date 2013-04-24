package org.sky.auto.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import org.sky.auto.exception.MyAutoException;
import org.sky.auto.text.read.StringContorl;

public class StandardOutInfo {
	private PipedInputStream pis;
	private PipedOutputStream pos;
	private boolean keepRunning=true;
	private FileWriter fw;
	private PrintStream ps;
	private PrintStream out=System.out;
	private File file;
	private String str;
	public StandardOutInfo(){
		file=new File("templet"+File.separator+"LOG.txt");
		pis = new PipedInputStream() {
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
		this.pos=new PipedOutputStream();
		try {
			pos.connect(pis);
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyAutoException("创建截取数据流时出现了错误，没有截取到输出流！");
		}
		ps = new PrintStream(getOutputStream());
	}
	
	public void start(){
		useMyOut();
		startByteArrayReaderThread();
	}
	
	public void clearStream(){
		try {
			getOutputStream().close();
			this.pis.close();
			this.pos.close();
			if(fw!=null){
				fw.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyAutoException("关闭输出流的时候出现了错误，请检查是否出现了流操作错误！");
		}
		
	}
	
	public void write(){
		try {
			useMyOut();
			fw = new FileWriter(file);
			String strtemp=getOutputStream().toString("UTF-8");
			fw.write(format(strtemp));
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private ByteArrayOutputStream byteArrayOS =
	        new ByteArrayOutputStream() {
	        public void close() {
	            keepRunning = false;
	            try {
	                super.close();
	                pos.close();
	            }
	            catch(IOException e) {
	                // 记录错误或其他处理
	                // 为简单计，此处我们直接结束
	                System.exit(1);
	            }
	        }
	    };
	
	private void startByteArrayReaderThread() {
        new Thread(new Runnable() {
            public void run() {
                while(keepRunning) {
                	try {
						byteArrayOS.write(getInputStream().read(new byte[200]));
						str=byteArrayOS.toString();
						useSystemOut();
						str=str.substring(0, str.length()-1);
						System.out.println(str);
	                   }catch(IOException e){
	                	   e.printStackTrace();
	                   }
//                	if(byteArrayOS.size() > 0) {
//                        byte[] buffer = null;
//                        synchronized(byteArrayOS) {
//                            buffer = byteArrayOS.toByteArray();
//                            byteArrayOS.reset(); // 清除缓冲区
//                        }
//                        try {
//                            // 把提取到的数据发送给PipedOutputStream
//                            pos.write(buffer, 0, buffer.length);
//                        }
//                        catch(IOException e) {
//                            // 记录错误或其他处理
//                            // 为简单计，此处我们直接结束
//                            System.exit(1);
//                        }
//                    }
//                    else // 没有数据可用，线程进入睡眠状态
//                        try {
//                            // 每隔1秒查看ByteArrayOutputStream检查新数据
//                            Thread.sleep(1000);
//                        }
//                        catch(InterruptedException e) {}
//                    }
                } 
            }
        }).start();
    } // startByteArrayReaderThread()
	
    public InputStream getInputStream() {
        return pis;
    } // getInputStream()
    public ByteArrayOutputStream getOutputStream() {
        return byteArrayOS;
    } // getOutputStream()
	
    public void useSystemOut(){
    	System.setOut(out);
    	System.setErr(out);
    }
    public void useMyOut(){
    	System.setOut(ps);
    	System.setErr(ps);
    }

    
    
    private String format(String string){
    	string=StringContorl.replaceBlank(string);
    	String[] strs=string.split("2013");
    	String temp=null;
    	for(int i=1;i<strs.length;i++){
    		strs[i]="2013"+strs[i]+"</br>";
    		temp=temp+strs[i];
    	}
    	
    	return temp.replaceAll("null", "");
    }
}
