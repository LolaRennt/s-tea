package org.sky.example;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.lmm.annotation.Pict;
import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.runner.JUnitBaseRunner;
import org.databene.benerator.anno.*;

@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads=1)
public class Parameters {
	
	@Test
	@Source("test.xls")
	public void test(String name,String password){
		System.out.println(name);
		System.out.println(password);
	}
	
	@Test
	@Pict("test.txt")
	public void testtwo(String name,String password){
		System.out.println(name);
		System.out.println(password);
	}
}
