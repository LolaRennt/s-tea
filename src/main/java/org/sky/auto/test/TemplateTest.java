package org.sky.auto.test;

import org.junit.Test;
import org.sky.auto.report.TempletHtml;

import freemarker.template.Template;

public class TemplateTest {
	@Test
	public void tempTest(){
		TempletHtml th =TempletHtml.getInstance();
		Template temp = th.getTemplate("frame.ftl");
	}
}
