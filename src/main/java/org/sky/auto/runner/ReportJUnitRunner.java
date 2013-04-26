package org.sky.auto.runner;


import java.io.File;

import org.apache.log4j.Logger;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.sky.auto.base.MyFile;
import org.sky.auto.report.CssAndJSReport;
import org.sky.auto.report.ReportListener;


public class ReportJUnitRunner extends BaseJUnitAutoRunner{
	private static Logger logger = Logger.getLogger(ReportJUnitRunner.class);
	public ReportJUnitRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier rn) {
		rn.addListener(new ReportListener());
		if(new File("report").exists()){
			MyFile.delFolder("report");
		}
		MyFile.createDictory("report");
		CssAndJSReport cj=new CssAndJSReport();
		cj.copyCssAndJsSource();
		if(new File("LOG.txt").exists()){
			new File("LOG.txt").delete();
			MyFile.createFile("LOG.txt");
		}	
		MyFile.delAllFile("templet", ".inc");	
		logger.info("初始化了报告文件夹");
		super.run(rn);
	}
	
	
}
