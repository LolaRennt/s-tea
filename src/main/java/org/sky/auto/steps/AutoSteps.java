package org.sky.auto.steps;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.sky.auto.anno.PageStory;
import org.sky.auto.base.AutoBase;
import org.sky.auto.driver.Browser;
import org.sky.auto.intrumentation.ClassPool;
import org.sky.auto.story.action.Actions;
import org.sky.auto.story.action.CheckboxActions;
import org.sky.auto.story.action.KeyActions;
import org.sky.auto.story.action.SelectActions;
import org.sky.auto.story.listener.ProxyStoryListener;
import org.sky.auto.window.Window;


public  class AutoSteps {
	
	private  String expectedResult;
	private  String realResult;
	
	@Given("打开'$browser'")
	public void open(@Named("browser")String browser){
		if(browser.equals(KeyActions.ACTION_CHROME.toLowerCase())){
			AutoBase.setDriver(Browser.Chrome);
			AutoBase.setElementWaitTime(10);
		}else if(browser.equals(KeyActions.ACTION_FIREFOX.toLowerCase())){
			AutoBase.setDriver(Browser.Firefox);
			AutoBase.setElementWaitTime(10);
		}else if(browser.equals(KeyActions.ACTION_HTMLUNIT.toLowerCase())){
			AutoBase.setDriver(Browser.HtmlUnit);
			AutoBase.setElementWaitTime(10);
		}else if(browser.equals(KeyActions.ACTION_IE.toLowerCase())){
			AutoBase.setDriver(Browser.IE);
			AutoBase.setElementWaitTime(10);
		}else if(browser.equals(KeyActions.ACTION_SAFARI.toLowerCase())){
			AutoBase.setDriver(Browser.Safari);
			AutoBase.setElementWaitTime(10);
		}else if(browser.equals(KeyActions.ACTION_OPERA.toLowerCase())){
			AutoBase.setDriver(Browser.Opera);
			AutoBase.setElementWaitTime(10);
		}else if(browser.equals(KeyActions.ACTION_REMOTE_CHROME.toLowerCase())){
			AutoBase.setDriver(Browser.RemoteChrome);
			AutoBase.setElementWaitTime(10);
		}else if(browser.equals(KeyActions.ACTION_REMOTE_FIREFOX.toLowerCase())){
			AutoBase.setDriver(Browser.RemoteFirefox);
			AutoBase.setElementWaitTime(10);
		}else if(browser.equals(KeyActions.ACTION_REMOTE_HTMLUNIT.toLowerCase())){
			AutoBase.setDriver(Browser.RemoteHtmlUnit);
			AutoBase.setElementWaitTime(10);
		}else if(browser.equals(KeyActions.ACTION_REMOTE_IE.toLowerCase())){
			AutoBase.setDriver(Browser.RemoteIE);
			AutoBase.setElementWaitTime(10);
		}else if(browser.equals(KeyActions.ACTION_REMOTE_OPERA.toLowerCase())){
			AutoBase.setDriver(Browser.RemoteOpera);
			AutoBase.setElementWaitTime(10);
		}else if(browser.equals(KeyActions.ACTION_REMOTE_SAFARI.toLowerCase())){
			AutoBase.setDriver(Browser.RemoteSafari);
			AutoBase.setElementWaitTime(10);
		}
	}
	
	@Given("访问'$url'")
	public void get(@Named("url")String url){
		AutoBase.open(url);
	}
	
	@Given("元素'$element'进行'$action'操作")
	public void action(@Named("element")String id,@Named("action")String action){
		Actions a = new Actions();
		a.setElement(AutoBase.sElement(id));
		a.simpleAction(action);
	}
	
	@Given("窗口进行'$action'操作")
	public void windowAction(@Named("action")String action){
		Actions a = new Actions();
		a.simpleAction(action); 
	}
	
	@Given("元素'$element'拖拽到元素'$toElement'")
	public void drapAndDrop(@Named("element")String id,@Named("toElement")String toid){
		AutoBase.sElement(id).dragAndDrop(AutoBase.sElement(toid));
	}
	
	@Given("按下按键'$key'")
	public void keyDown(@Named("key")String key){
		Keys k = Enum.valueOf(Keys.class, key.toUpperCase().trim());
		AutoBase.keyDown(k);
	}
	
	@Given("松开按键'$key'")
	public void keyUp(@Named("key")String key){
		Keys k = Enum.valueOf(Keys.class, key.toUpperCase().trim());
		AutoBase.keyDown(k);
	}	
	
	@BeforeStories
	public void beforeStories(){
		ProxyStoryListener.getDispatcher().beforeStories();
	}
	
	@AfterStories
	public void afterStories(){
		ProxyStoryListener.getDispatcher().afterStories();
	}
	
	@BeforeScenario
	public void beforeScenario(){
		ProxyStoryListener.getDispatcher().beforeScenario();
	}
	
	@AfterScenario
	public void afterScenario(){
		ProxyStoryListener.getDispatcher().afterScenario();
	}
	@AfterStory
	public void afterStory(){
		ProxyStoryListener.getDispatcher().afterStory();
	}
	
	@BeforeStory
	public void beforeStory(){
		ProxyStoryListener.getDispatcher().beforeStory();
	}
	
	
	@When("'$description'实际结果为:'$real'")
	public  String realResult(@Named("description")String des,@Named("real")String r){
		System.out.println(des+"的实际结果为->"+r);
		setRealResult(r);
		return r;
	}
	
	@When("'$description'预期结果为:'$expected'")
	public  String expectedResult(@Named("description")String des,@Named("expected")String e){
		System.out.println(des+"的预期结果为->"+e);
		setExpectedResult(e);
		return e;
	}

	public Object getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public  String  getRealResult() {
		return realResult;
	}

	public  void setRealResult(String realResult) {
		this.realResult = realResult;
	}
	
	@Then("用例结果验证")
	public void assertResult(){
		Assert.assertEquals(realResult, expectedResult);
	}
	
	
	@When("'$description'实际结果为->'$action'")
	public String realActionResult(@Named("description")String des,@Named("action")String action){
		Actions a = new Actions();
		String result=a.normalAction(action);
		setRealResult(result);
		System.out.println(des+"的实际结果为->"+result);
		return expectedResult;		
	}
	

	@When("'$description'实际结果为->元素'$id'的'$action'")
	public String realResult(@Named("description")String des,@Named("id")String id,@Named("action")String action){
		Actions a = new Actions();
		a.setElement(AutoBase.sElement(id));
		String result =null;
		result = a.normalAction(action);
		setRealResult(result);
		System.out.println(des+"的实际结果为->"+result);
		return expectedResult;		
	}
	
	@When("'$description'实际结果为->元素'$id'['$condition'->'$value']的'$action'")
	public String realResult(@Named("description")String des,@Named("id")String id,@Named("condition")String condition,@Named("value")String value,@Named("action")String action){
		Actions a = new Actions();
		a.setElement(AutoBase.sElement(id));
		String result = a.normalAction(action,condition);
		setRealResult(result);
		System.out.println(des+"的实际结果为->"+result);
		return expectedResult;		
	}
	
	@When("'$description'预期结果为->'$action'")
	public String expectedActionResult(@Named("description")String des,@Named("action")String action){
		Actions a = new Actions();
		String result=a.normalAction(action);
		setExpectedResult(result);
		System.out.println(des+"的预期结果为->"+result);
		return expectedResult;		
	}
	

	@When("'$description'预期结果为->元素'$id'的'$action'")
	public String expectedResult(@Named("description")String des,@Named("id")String id,@Named("action")String action){
		Actions a = new Actions();
		a.setElement(AutoBase.sElement(id));
		String result =null;
		result = a.normalAction(action);
		setExpectedResult(result);
		System.out.println(des+"的预期结果为->"+result);
		return expectedResult;		
	}
	
	@When("'$description'预期结果为->元素'$id'['$condition'->'$value']的'$action'")
	public String expectedResult(@Named("description")String des,@Named("id")String id,@Named("condition")String condition,@Named("value")String value,@Named("action")String action){
		Actions a = new Actions();
		a.setElement(AutoBase.sElement(id));
		String result = a.normalAction(action,value);
		setExpectedResult(result);
		System.out.println(des+"的预期结果为->"+result);
		return expectedResult;		
	}
	
	@Given("点选框'$id'进行'$action'操作")
	public void checkboxSelect(@Named("id")String id,@Named("action")String action){
		CheckboxActions ca = new CheckboxActions(AutoBase.checkBox(id));
		ca.checkboxAction(action);
	}
	
	@Given("下拉框'$id'进行'$action'['$condition'->'$value']")
	public void selectAction(@Named("id")String id,@Named("action")String action,@Named("condition")String condition,@Named("value")String value){
		SelectActions sa = new SelectActions(AutoBase.comoboBox(id));
		sa.selectAction(action, value);
	}
	
	@Given("下拉框'$id'进行'$action'")
	public void selectAction(@Named("id")String id,@Named("action")String action){
		SelectActions sa = new SelectActions(AutoBase.comoboBox(id));
		sa.selectAction(action);
	}
	
	/**参数需要用逗号分割*/
	@Given("页面'$page'进行'$method'操作[$paramters]")
	public void pageAction(@Named("method")String method,@Named("paramters")String paramters){
		Object[] paras=paramters.split(",");
		Set<Class<?>>cls = ClassPool.getClassPool();
		for(Class<?> clazz:cls){
			if(clazz.isAnnotationPresent(PageStory.class)){
				Method[] methods = clazz.getDeclaredMethods();
				for(Method m : methods){
					if(method.toLowerCase().equals(m.getName().toLowerCase())){
						try {
							m.invoke(clazz.newInstance(),paras);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (InstantiationException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
		}
	}
	
	

	
	@Given("休眠$second秒")
	public void sleep(@Named("second")int second){
		AutoBase.sleep(second*1000);
	}
	
	@Given("通过Title'$title'切换页面")
	public void selectWindowByTitle(@Named("title")String title){
		Window.selectWindowContainTitle(title);
	}
	
	@Given("通过Url'$url'切换页面")
	public void selectWindowByUrl(@Named("url")String url){
		Window.selectWindowContainUrl(url);
	}
	
}
