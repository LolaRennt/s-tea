package org.sky.auto.test;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.sky.auto.report.StandardOutInfo;

public class STDTest extends RunListener{
	private StandardOutInfo soi;
	@Override
	public void testStarted(Description description) throws Exception {
		soi = new StandardOutInfo();
		soi.start();
	}
	
	@Override
	public void testFinished(Description description) throws Exception {
		soi.write();
		soi.clearStream();
	}
}
