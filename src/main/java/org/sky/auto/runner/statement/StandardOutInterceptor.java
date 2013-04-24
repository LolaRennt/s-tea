package org.sky.auto.runner.statement;

import org.sky.auto.report.StandardOutInfo;

public class StandardOutInterceptor implements Interceptor{
	private StandardOutInfo soi;
	@Override
	public void interceptorBefore() {
		soi=new StandardOutInfo();
		soi.start();
		
	}

	@Override
	public void interceptorAfter() {
		soi.write();
		soi.clearStream();
		soi=null;
	}

}
