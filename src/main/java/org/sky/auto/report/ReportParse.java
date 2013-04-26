package org.sky.auto.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.runner.Result;
import org.sky.auto.base.AddToFileWrite;
import org.sky.auto.base.MyFile;
import org.sky.auto.result.CaseStatus;
import org.sky.auto.result.Environment;
import org.sky.auto.result.MyDescription;
import org.sky.auto.result.MyResult;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ReportParse {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void FinishedReport(Template temp,Result result){
		//CssAndJSReport cj=new CssAndJSReport();
		TempletHtml th =TempletHtml.getInstance();
		//cj.copyCssAndJsSource();
		MyResult res=new MyResult(result);
		Set<MyDescription> dess=CaseStatus.getDescriptionCases(); 
		temp=th.getTemplate("frame.ftl");
		Map resmap = new HashMap();
		resmap.put("res",res);
		resmap.put("des", dess);
		Writer write;
		try {
			write = new OutputStreamWriter(new FileOutputStream(new File("report"+File.separator+"report.html")));
			temp.process(resmap, write);
			write.flush();
			write.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Template logtemp = th.getTemplate("log.ftl");
		Map logmap = new HashMap();
		logmap.put("res", res);
		try {
			Writer lw =new OutputStreamWriter(new FileOutputStream(new File("report")+File.separator+"log.html"));
			logtemp.process(logmap, lw);
			lw.flush();
			lw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void LogParse() throws IOException{
		boolean isWrite=false;
		File file = new File("LOG.txt");		
		FileInputStream pis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(pis,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String string=br.readLine();
		//MyFile.delAllFile("templet", ".inc");
		MyFile.createFile("templet"+File.separator+"log.inc");
		while(string!=null){
			string=string+"</br>"+"\n";
			string=string.replaceAll("<s>", "").replaceAll("<S>", "");
			//System.out.println(string);
			if(string.contains("[Case:")){
				
				isWrite=true;
				if(isWrite){
					if(string.contains("AUTO-RUN")){
						String methodname = string.substring(string.indexOf("=>")+2,string.indexOf("]"));
						//System.out.println("+++++++++++"+methodname);
						if(!new File("templet"+File.separator+methodname+".inc").exists()){
							new File("templet"+File.separator+methodname+".inc").createNewFile();	
						}
						AddToFileWrite.writeContext(string, "templet"+File.separator+methodname+".inc");
						
					}
					
				}
			}
			AddToFileWrite.writeContext(string, "templet"+File.separator+"log.inc");
			if(string.contains("测试用例执行失败")||string.contains("测试用例执行结束")){
				isWrite=false;
			}
			string=br.readLine();
		}
		br.close();
		isr.close();
		pis.close();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void caseReport(Template temp,MyDescription des,Environment env) throws IOException, TemplateException{
		//MyDescription des=new MyDescription(description);
		Map map = new HashMap();
		map.put("des",des);
		map.put("env", env);
		Writer write = new OutputStreamWriter(new FileOutputStream(new File("report"+File.separator+des.getMethodName())+".html"));
		temp.process(map, write);
		write.flush();
		write.close();
	}
}
