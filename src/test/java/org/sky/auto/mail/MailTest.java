package org.sky.auto.mail;


import org.junit.Test;
import org.sky.auto.base.ConfigParser;
import org.sky.auto.mail.date.SimpleDate;

public class MailTest {
//	@Test
//	public void mailTestOne(){
//		MailSenderInfo ms =new MailSenderInfo();
//		ms.setMailServerHost("smtp.163.com");
//		ms.setMailServerPort("25");
//		ms.setValidate(true);
//		ms.setUserName("celeskyking@163.com");
//		ms.setPassword("tiantian5326417");
//		ms.setFromAddress("celeskyking@163.com");
//		ms.setToAddress("669357664@qq.com");
//		ms.setContent("这是一个警告！");
//		ms.setSubject("这是一个标题！");
//		SimpleMailSender sms = new SimpleMailSender();
//		//sms.sendTextMail(ms);
//		sms.sendEmail(ms);
//	}
	@Test
	public void mailTestTwo(){
	//	MailAttachment ma = new MailAttachment();
		//ma.addAttachmentSource(ConfigParser.getErrorLogDir()+File.separator+SimpleDate.getTodayFormat()+"TestReport.log");
		MailSenderInfo msi = new MailSenderInfo();
		msi.setMailServerHost(ConfigParser.getMailHost());
		msi.setMailServerPort(ConfigParser.getMailPort());
		msi.setValidate(ConfigParser.getMailValidate());
		msi.setUserName(ConfigParser.getMailUsername());
		msi.setPassword(ConfigParser.getMailPassword());
		msi.setFromAddress(ConfigParser.getFromMail());
		msi.setSubject(SimpleDate.getTodayFormat()+"自动化测试报告");
		StringBuilder text = new StringBuilder();
		text.append("<html>");
		text.append("<head>"+"</head>");
		text.append("<body>"+"<table border=\"1\">");
		text.append("<tr>"+"<th>"+"执行case的总数"+"</th>");
		text.append("<td colspan=\"2\">"+100+"</td>"+"</tr>");
		text.append("<tr>"+"<th>"+"执行失败的方法数目"+"</th>"+"<td colspan=\"2\">"+12+"</td>"+"</tr>");
		text.append("<tr>"+"<th>"+"执行中忽略掉的方法数目"+"</th>"+"<td colspan=\"2\">"+32+"</td>"+"</tr>");
		text.append("<tr>"+"<th>"+"执行用例消耗掉的时间为"+"</th>"+"<td colspan=\"2\">"+21+"</td>"+"</tr>");
		text.append("<tr>"+"<th>"+"执行出错的方法汇总"+"</th>"+"<td colspan=\"2\">"+86+"</td>"+"</tr>");
		//if(result.getFailureCount()>0){
			for(int i=0;i<3;i++){
				//Failure f=result.getFailures().get(i);
				text.append("<tr>"+"<th rowspan=\"3\">"+"NO"+i+"</th>+<th>"+"失败方法所属于的类名"+"</th>"+"<td>"+"aafadfa"+"</td>"+"</tr>");
				text.append("<tr>"+"<th>"+"失败方法所属于的方法名"+"</th>"+"<td>"+"bdadfafa"+"</td>"+"</tr>");
				text.append("<tr>"+"<th>"+"失败方法的异常信息"+"</th>"+"<td>"+"cdfafad"+"</td>"+"</tr>");
			}
		//}
		text.append("</table>"+"</body>"+"</html>");
		msi.setContent(text.toString());
		//msi.setAttachment(ma.getAttachmentContext());
		msi.setToAddress("669357664@qq.com");
		//msi.setAttachment(ma.getAttachmentContext());
		SimpleMailSender sms = new SimpleMailSender();
		//sms.sendEmail(msi);
		sms.sendHtmlMail(msi);
		System.out.println(msi.getMailServerHost());
		//System.out.println(msi.getAttachment().toString());
		if(msi.getAttachment()==null){
			System.out.println("yes!");
		}
	}
}
