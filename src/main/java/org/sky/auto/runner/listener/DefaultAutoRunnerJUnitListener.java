package org.sky.auto.runner.listener;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.sky.auto.anno.Author;
import org.sky.auto.base.AddToFileWrite;
import org.sky.auto.base.ConfigParser;
import org.sky.auto.base.MyFile;
import org.sky.auto.mail.MailSenderInfo;
import org.sky.auto.mail.SimpleMailSender;
import org.sky.auto.mail.date.SimpleDate;



public class DefaultAutoRunnerJUnitListener  extends RunListener{
	static Logger logger = Logger.getLogger(DefaultAutoRunnerJUnitListener.class);
	static String TEST_RESULT_ALL_RIGHT;
	static String TEST_RESULT_WITH_ERROR;
	@Override
	public void testFailure(Failure failure) throws Exception {
		//System.out.println("出现了错误！程序无法执行！");
		String filepath = ConfigParser.getErrorLogDir()+File.separator+SimpleDate.getTodayFormat()+"--"+failure.getDescription().getClassName()+"ERROR记录"+".log";
		String line0 = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>用例执行失败的简要记录>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+MyFile.lineBreak();
		String line1 = "------>用例所属于的Class为---["+failure.getDescription().getClassName()+"]"+MyFile.lineBreak();
		String line2 = "------>用例的方法名字为: ***["+failure.getDescription().getMethodName()+"]***"+MyFile.lineBreak();
		String line3 = "------>用例失败抛出的异常信息为: "+failure.getMessage()+MyFile.lineBreak();
		String line6 = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>分割线>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
		String text = MyFile.lineBreak()+line0+line1+line2+line3+line6+MyFile.lineBreak();
		AddToFileWrite.writeContext(text, filepath);
	}
	
	@Override
	public void testFinished(Description description) throws Exception {
		logger.info(">>>>>>>用例["+description.getMethodName()+"]执行结束了！");
		
	}

	@Override
	public void testStarted(Description description) throws Exception {
		logger.info(">>>>>>>用例["+description.getMethodName()+"]开始执行！");
	}
	
	
	@Override
	public void testRunFinished(Result result) throws Exception {
		
		StringBuilder text = new StringBuilder();
		//text.append("<html>");
		text.append("<table border=\"1\">");
		text.append("<tr>"+"<th>"+"执行case的总数"+"</th>");
		text.append("<td colspan=\"2\">"+result.getRunCount()+"</td>"+"</tr>");
		text.append("<tr>"+"<th>"+"执行结束时间(年-月-日)"+"</th>"+"<td colspan=\"2\">"+SimpleDate.getSimpleDateFormat()+"</td>"+"</tr>");
		text.append("<tr>"+"<th>"+"执行失败的方法数目"+"</th>"+"<td colspan=\"2\">"+result.getFailureCount()+"</td>"+"</tr>");
		text.append("<tr>"+"<th>"+"执行中忽略掉的方法数目"+"</th>"+"<td colspan=\"2\">"+result.getIgnoreCount()+"</td>"+"</tr>");
		text.append("<tr>"+"<th>"+"执行用例消耗掉的时间为"+"</th>"+"<td colspan=\"2\">"+result.getRunTime()+"ms</td>"+"</tr>");	
		if(result.getFailureCount()>0){
			text.append("<tr>"+"<th>"+"执行出错的方法汇总"+"</th>"+"<td colspan=\"2\">"+"&nbsp"+"</td>"+"</tr>");
			for(int i=0;i<result.getFailureCount();i++){
				Failure f=result.getFailures().get(i);
				text.append("<tr>"+"<th rowspan=\"4\">"+"错误方法*"+(i+1)+"</th>+<th>"+"失败方法所属于的类名"+"</th>"+"<td>"+f.getDescription().getClassName()+"</td>"+"</tr>");
				text.append("<tr>"+"<th>"+"失败方法所属于的方法名"+"</th>"+"<td>"+f.getDescription().getMethodName()+"</td>"+"</tr>");
				text.append("<tr>"+"<th>"+"失败方法的异常信息"+"</th>"+"<td>"+f.getMessage()+"</td>"+"</tr>");
				
				if(f.getDescription().getTestClass().isAnnotationPresent(Author.class)){
					Author author = f.getDescription().getTestClass().getAnnotation(Author.class);
					String name = author.Name();
					text.append("<tr>"+"<th>"+"失败方法的创建者"+"</th>"+"<td>"+name+"</td>"+"</tr>");
				}
				
			}
		}else{
			text.append("<tr>"+"<th>"+"执行出错的方法汇总"+"</th>"+"<td colspan=\"2\">"+"所有case都运行正常没有发现异常"+"</td>"+"</tr>");
		}
		text.append("</table>");
		MailSenderInfo msi = new MailSenderInfo();
		msi.setMailServerHost(ConfigParser.getMailHost());
		msi.setMailServerPort(ConfigParser.getMailPort());
		msi.setValidate(ConfigParser.getMailValidate());
		msi.setUserName(ConfigParser.getMailUsername());
		msi.setPassword(ConfigParser.getMailPassword());
		msi.setFromAddress(ConfigParser.getFromMail());
		msi.setSubject(SimpleDate.getTodayFormat()+"自动化测试报告");
		msi.setContent(text.toString());
		msi.setToAddress(ConfigParser.getToEMail());
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendHtmlMail(msi);
	}
	
	@Override
	public void testRunStarted(Description description) throws Exception {
	}
}
