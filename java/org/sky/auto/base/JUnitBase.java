package org.sky.auto.base;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

public class JUnitBase implements IJUnitBase{
	static Logger logger = Logger.getLogger(JUnitBase.class);
	@Before
	public void setUp(){
		AutoBase.setLogStarted();
		logger.info("自动化测试初始化成功！日志默认配置成功！");
	}
	
	
	@After
	public void destory(){
		AutoBase.closeAllWindow();
		logger.info("自动化测试Case结束，资源释放成功！");
	}
}
