package org.sky.auto.pagetest;
import org.sky.auto.page.Page;


public class newPage extends Page{
	public void hello(){
		System.out.println("hello,girl!~");
	}
	public String name(){
		return this.getClass().getName();
	}
	public static void main(String[] args) {
		newPage np =new newPage();
		System.out.println(np.name());
	}

	
}
