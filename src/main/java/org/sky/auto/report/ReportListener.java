package org.sky.auto.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.sky.auto.base.MyFile;
import org.sky.auto.result.CaseStatus;
import org.sky.auto.result.Environment;
import org.sky.auto.result.MyDescription;
import org.sky.auto.result.MyFailure;
import org.sky.auto.result.MyResult;
import org.sky.auto.intrumentation.*;

import freemarker.template.Template;

public class ReportListener extends RunListener{
	private MyResult res;
	private MyFailure fail;
	private MyDescription des;
	private TempletHtml th;
	private Environment env;
	private Template temp;
	@Override
	public void testRunStarted(Description description) throws Exception {
		th=TempletHtml.getInstance();
		env=new Environment();
		MyFile.createDictory("report");
		MyFile.createFile("report/report.html");
		CaseStatus.setNotRunClasses(ClassPool.getClassPool());
		des=new MyDescription(description);
	}
	
	
	@Override
	public void testStarted(Description description) throws Exception {
		String caseName = description.getMethodName()+".html";
		MyFile.createFile("report/"+caseName);
		temp=th.getTemplate(caseName);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void testFinished(Description description) throws Exception {
		Map map = new HashMap();
		map.put("des",des);
		map.put("env", env);
		Writer write = new OutputStreamWriter(new FileOutputStream(new File("report/"+des.getMethodName())+".html"));
		temp.process(map, write);
		write.flush();
		write.close();
	}
	
	@Override
	public void testFailure(Failure failure) throws Exception {
		fail = new MyFailure(failure);
		CaseStatus.addFailureClass(fail);
	}
	
	
	
}
