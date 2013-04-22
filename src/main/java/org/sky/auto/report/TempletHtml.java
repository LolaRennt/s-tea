package org.sky.auto.report;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class TempletHtml {
	Configuration config;
	static private TempletHtml th;
	private TempletHtml(){
		try {
			config=new Configuration();
			config.setDirectoryForTemplateLoading(new File("templet"));
			config.setObjectWrapper(new DefaultObjectWrapper());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static TempletHtml getInstance(){
		if(th==null){
			synchronized (TempletHtml.class) {
				if(th==null){
					th=new TempletHtml();
				}
			}
		}
		return th;
	}
	
	public Template getTemplate(String file){
		return th.getTemplate(file);
	}
	
}
