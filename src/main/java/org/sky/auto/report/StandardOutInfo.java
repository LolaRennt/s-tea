package org.sky.auto.report;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import org.sky.auto.exception.MyAutoException;

public class StandardOutInfo {
	private PipedInputStream pis;
	private PipedOutputStream pos;
	public StandardOutInfo(){
		this.pis=new PipedInputStream();
		this.pos=new PipedOutputStream();
	}
	public void init(){
		try {
			pos.connect(pis);
			PrintStream ps = new PrintStream(pos);
			System.setOut(ps);
		} catch (IOException e) {
			System.err.println("连接失败");
			System.exit(1);
			throw new MyAutoException("创建截取数据流时出现了错误，没有截取到输出流！");
		}
	}
	
	public void clearStream(){
		try {
			this.pis.close();
			this.pos.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyAutoException("关闭输出流的时候出现了错误，请检查是否出现了流操作错误！");
		}
		
	}
	
	public void write(){
		
	}
	
}
