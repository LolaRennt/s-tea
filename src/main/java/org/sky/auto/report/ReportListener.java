package org.sky.auto.report;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.sky.auto.base.MyFile;
import org.sky.auto.result.CaseStatus;
import org.sky.auto.result.Environment;
import org.sky.auto.result.MyDescription;
import org.sky.auto.result.MyFailure;
import org.sky.auto.intrumentation.*;

import freemarker.template.Template;
public class ReportListener extends RunListener{
	private static Logger logger = Logger.getLogger(ReportListener.class);
	private MyFailure fail;
	private MyDescription des;
	private TempletHtml th;
	private Environment env;
	private Class<?>[] clas;
	private CaseInfo caseinfo;
	private Map<String,CaseInfo> infomap=new HashMap<String,CaseInfo>();
	@Override
	public void testStarted(Description description) throws Exception {
		logger.info(">>>>>>>>>>>>>>>测试用例执行开始>>>>>>>>>>>");
		MyFile.createLevelFile("report"+File.separator+"report.html");
		for(Class<?> clazz: ClassPool.getClassPool()){
			for(Method m:clazz.getDeclaredMethods()){
				if(m.getAnnotation(Test.class)!=null){
					CaseStatus.addNotRunCase(m);
				}
			}
		}
		for(Method m:description.getTestClass().getDeclaredMethods()){
			if(m.equals(description.getMethodName())){
				clas=m.getParameterTypes();
			}else{
				clas=null;
			}
		}
	}
	
	@Override
	public void testFinished(Description description) throws Exception {
		logger.info(">>>>>>>>>>>>>>>测试用例执行结束>>>>>>>>>>>");
		env=new Environment();
		des=new MyDescription(description);
		if(clas!=null){
			CaseStatus.addRunCase(des.getTestClass().getDeclaredMethod(description.getMethodName(),clas));
		}else{
			CaseStatus.addRunCase(des.getTestClass().getDeclaredMethod(description.getMethodName(),clas));
		}
		if(infomap.get(description.getMethodName())==null){
			CaseStatus.addRunCaseDescription(description);
			caseinfo = new CaseInfo(env,des);
			infomap.put(description.getMethodName(), caseinfo);
		}	
	}
	
	@Override
	public void testFailure(Failure failure) throws Exception {
		logger.error(">>>>>>>>>>>>>>>测试用例执行失败>>>>>>>>>>>");
		logger.error(failure.getException());
		logger.error(failure.getMessage());
		logger.error(failure.getTrace());
		if(infomap.get(failure.getDescription().getMethodName())!=null){
			infomap.remove(failure.getDescription().getMethodName());
			CaseStatus.removeRunCaseDescription(new MyDescription(failure.getDescription()));
		}
		env=new Environment();
		if(clas!=null){
			Description d =failure.getDescription();
			des=new MyDescription(d);
			des.setFailureCase(true);
			des.setFailure(new MyFailure(failure));
			CaseStatus.addRunCaseDescription(des);
			CaseStatus.addRunCase(d.getTestClass().getDeclaredMethod(d.getMethodName(),clas));
			
		}else{
			Description d =failure.getDescription();
			des=new MyDescription(d);
			des.setFailureCase(true);
			des.setFailure(new MyFailure(failure));
			CaseStatus.addRunCaseDescription(des);
			CaseStatus.addRunCase(d.getTestClass().getDeclaredMethod(d.getMethodName()));
		}
		fail = new MyFailure(failure);
		CaseStatus.addFailureCase(fail);
		caseinfo = new CaseInfo(env,des);
		infomap.put(failure.getDescription().getMethodName(),caseinfo);
	}
	
	@Override
	public void testRunFinished(Result result) throws Exception {
		ReportParse pp=new ReportParse();
		pp.LogParse();
		//CssAndJSReport cj=new CssAndJSReport();
		//cj.copyCssAndJsSource();
		th=TempletHtml.getInstance();
		Template temp=th.getTemplate("frame.ftl");
		pp.FinishedReport(temp, result);
		Template ctemp=th.getTemplate("case.ftl");
		for(MyDescription des:CaseStatus.getDescriptionCases()){
			pp.caseReport(ctemp, infomap.get(des.getMethodName()).getDescription(), infomap.get(des.getMethodName()).getEnviroment());
		}
		
	}
	
	
}
