package org.sky.auto.test;

import org.sky.auto.intrumentation.FileFinder;
import org.testng.annotations.Test;

@SuppressWarnings("deprecation")
public class FileFinderTest {
	@Test
	public void testClassPath(){
		System.out.println(FileFinder.getClassFile());
	}
}
