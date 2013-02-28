package org.sky.auto.base;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sky.auto.anno.Register;
import org.sky.auto.driver.AutoDriver;
import org.sky.auto.driver.Browser;
import org.sky.auto.driver.event.RunnerListener;
import org.sky.auto.element.Button;
import org.sky.auto.element.CheckBox;
import org.sky.auto.element.ComoboBox;
import org.sky.auto.element.Image;
import org.sky.auto.element.Link;
import org.sky.auto.element.ListElement;
import org.sky.auto.element.RadioButton;
import org.sky.auto.element.RichTextField;
import org.sky.auto.element.SElement;
import org.sky.auto.element.Table;
import org.sky.auto.element.TextField;
import org.sky.auto.exception.MyAutoException;
import org.sky.auto.intrumentation.ClassPool;
import org.sky.auto.page.Page;
import org.sky.auto.proxy.ProxyRunnerListener;
import org.sky.auto.runner.AutoResetThreadLocal;
//import org.sky.auto.xml.XMLParser;
import org.sky.auto.xml.XmlProvider;


public class AutoBase {
	//private static ThreadDriver td;
	
	private static AutoResetThreadLocal<AutoDriver> art = new AutoResetThreadLocal<AutoDriver>(){
		protected synchronized AutoDriver initialValue() {
			return new AutoDriver();	
		};
	};
	public static AutoDriver getAutoDriver(){
		return art.get();
	}
	private static boolean CLOSE_STATUS;
	//static private ThreadDriver td =new ThreadDriver();
	static Logger logger =Logger.getLogger(AutoBase.class);
	//private  String sourcePath=File.separator+"xml"+File.separator+"source.xml";
	//private static String type="SINGLE";
	private String sourcePath;
	private static Actions action;
	public AutoBase(String path){
		this.sourcePath=path;
	}
	
	public static void setLogProperties(String path){
		PropertyConfigurator.configure(path);
	}
	
	
	public static void setDriver(Browser browser){
		setClose_Status(false);
		getAutoDriver().setDriver(browser);
		ClassPool.reset();
		Set<Class<?>> cls = ClassPool.getClassPool();
		for(Class<?>clazz:cls){
			if(clazz.isAnnotationPresent(Register.class)){
				try {
					ProxyRunnerListener.register((RunnerListener) clazz.newInstance());
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**打开浏览器,里面包含了初始化操作，必须以此方法为开始*/
	public static void open(Browser browser,String url){
		ProxyRunnerListener.getDispatcher().beforeOpen();
		getAutoDriver().setDriver(browser);
		getAutoDriver().getDriver().get(url);
		logger.info("使用浏览器"+browser+"进行自动化测试，将要打开网址"+url);
		ProxyRunnerListener.getDispatcher().afterOpen();
	}
	/**在本页面跳转到指定的链接处*/
	public static void open(String url){
		ProxyRunnerListener.getDispatcher().beforeOpen();
		driver().get(url);
		logger.info("打开了网址"+url+"来进行自动化测试！");
		ProxyRunnerListener.getDispatcher().afterOpen();
	}
	/**返回一个page对象*/
	public static Page page(){
		return new Page();
	}
	/**将鼠标移动到指定的元素位置上面*/
	public static void moveToElement(WebElement element){
		Actions action = new Actions(driver());
		action.moveToElement(element).build().perform();	
	}

	/**通过这个方法可以得到driver的对象*/
	public static WebDriver driver(){
//		//System.out.println(type);
//		if(type.toLowerCase().contains("single")){
//			return (WebDriver) DriverHandler.getInstance().getDriver().getEngine();
//		}else if(type.toLowerCase().contains("thread")){
//			return getThreadDriver().getDriver();
//		}
//		return null;
		return getAutoDriver().getDriver();
	}
	
	/**判断元素是否存在*/
	public static boolean isElementExist(By selector){
		try{
			driver().findElement(selector);
			//System.out.println("元素存在！");
		}catch(NoSuchElementException e){
			return false;
		}
		return true;
	}

	
	/**设置默认的元素等待时间*/
	public static void setElementWaitTime(long seconds){
		driver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**基础方法，得到鼠标键盘事件基础*/
	public static Actions getActions(){
		if(action==null){
			return new Actions(driver());
		}
		return action;
	}
	
	/**按下指定键操作*/
	public static void keyDown(Keys key){
		getActions().keyDown(key).build().perform();
	}
	/**在某个元素上面进行指定键的按下操作*/
	public static void keyDown(WebElement element,Keys key){
		getActions().keyDown(element, key).build().perform();
	}
	/**点击某个元素*/
	public static void click(WebElement element){
		getActions().click(element);
	}
	/**拖拽某个元素*/
	public static void dragAndDrop(WebElement fromElement,WebElement toElement){
		getActions().dragAndDrop(fromElement,toElement).build().perform();
	}
	/**拖拽某个元素到指定的位置*/
	public static void dragAndDrop(WebElement fromElement,int xlocation, int ylocation){
		getActions().dragAndDropBy(fromElement, xlocation, ylocation).build().perform();
	}
	/**将按下的键松开*/
	public static void keyUp(Keys key){
		getActions().keyUp(key);
	}
	/**在指定的元素上面进行按键松开*/
	public static void keyUp(WebElement element,Keys key){
		getActions().keyUp(element, key);
	}
	/**获取元素的焦点，主要是输入框的焦点*/
	public static void focus(WebElement element){
		getActions().sendKeys(element,"");
	}
	/**按下鼠标左键在指定的元素位置上*/
	public static void leftMouseDown(WebElement element){
		getActions().clickAndHold(element);
	}
	/**松开在指定元素位置的鼠标左键*/
	public static void leftMouseUp(WebElement onElement){
		getActions().release(onElement);
	}
	/**在指定元素的位置双击操作*/
	public static void doubleClick(WebElement element){
		getActions().doubleClick(element);
	}
	/**在指定的元素处输入*/
	public static void sendKeys(WebElement element,java.lang.CharSequence...charSequences){
		getActions().sendKeys(element, charSequences);
	}
	/**关闭所有的窗口*/
	public  static void closeAllWindow(){
		if(driver()!=null){
			driver().quit();
			setClose_Status(true);
			logger.info("关闭了所有的浏览器！");
		}else{
			logger.error("driver是空值！不存在认识的浏览器对象！");
			throw new MyAutoException("driver设置值出现错误，导致driver值为空值，请检查是否配置正确的driver");
		}
	}
	/**关闭当前的窗口*/
	public static void closeCurrentWindow(){
		ProxyRunnerListener.getDispatcher().beforecloseCurrentWindow();
		driver().close();
		ProxyRunnerListener.getDispatcher().aftercloseCurrentWindow();
	}
	/**得到xml的路径*/
	public String getSourcePath() {
		return sourcePath;
	}
	/**设置默认的xml的路径*/
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	/**得到WebElement类型的元素*/
	public  static WebElement element(String id){
		AutoBase ab=new AutoBase("xml"+File.separator+"source.xml");
		//WebElement element = XMLParser.element(id, ab.getSourcePath());
		XmlProvider xp = new XmlProvider();
		xp.setPath(ab.getSourcePath());
		return xp.element(id, xp.getPath());
	}
	public  static List<WebElement> elements(String id){
		AutoBase ab = new AutoBase("xml"+File.separator+"source.xml");
		//List<WebElement> list= XMLParser.elements(id, ab.getSourcePath());
		//logger.info("元素LIST["+id+"]-->");
		XmlProvider xp =new XmlProvider();
		xp.setPath(ab.getSourcePath());
		return xp.elements(id, xp.getPath());
	}
	
	public static SElement sElement(String id){
		AutoBase ab = new AutoBase("xml"+File.separator+"source.xml");
		//SElement element= XMLParser.sElement(id, ab.getSourcePath());
		XmlProvider xp = new XmlProvider();
		xp.setPath(ab.getSourcePath());
		logger.info("元素["+id+"]-->");
		return xp.sElement(id, xp.getPath());
	}
	
	public static ListElement listElement(String id){
		ListElement le= new ListElement(AutoBase.elements("id"));
		logger.info("元素LIST["+id+"]-->");
		return le;
 	}
	public static Button button(String id){
		Button bt=new Button(element(id));
		logger.info("按钮["+id+"]-->");
		return bt;
	}
	
	public static CheckBox checkBox(String id){
		CheckBox cb =new CheckBox(element(id));
		logger.info("CheckBox["+id+"]-->");
		return cb;
	}
	
	public static ComoboBox comoboBox(String id){
		ComoboBox cb=new ComoboBox(element(id));
		logger.info("ComoboBox["+id+"]-->");
		return cb;
	}
	
	public static Image image(String id){
		Image i = new Image(element(id));
		logger.info("Image["+id+"]-->");
		return i;
	}
	
	public static Link link(String id){
		Link l =new Link(element(id));
		logger.info("Link["+id+"]-->");
		return l;
	}
	
	public static RadioButton radioButton(String id){
		RadioButton rd = new RadioButton(element(id));
		logger.info("RadioButton["+id+"]-->");
		return rd;
	}
	public static RichTextField richTextField(String id){
		RichTextField rtf = new RichTextField(element(id));
		logger.info("RichTextField["+id+"]-->");
		return rtf;
	}
	public static Table table(String id){
		Table t=new Table(element(id));
		logger.info("Table["+id+"]-->");
		return t;
	}
	public static TextField textField(String id){
		TextField tf = new TextField(element(id));
		logger.info("TextField["+id+"]-->");
		return tf;
	}
	
	public static void clearCurrentThreadDriver(){
		getAutoDriver().setDriver(Browser.NULL);
		logger.info("多线程资源释放成功！");
	}
	
	public static void setLogStarted(){
		setLogProperties("resource"+File.separator+"log4j.properties");
	}
	
	public static void sleep(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean isClose_Status() {
		return CLOSE_STATUS;
	}

	public static void setClose_Status(boolean status) {
		CLOSE_STATUS = status;
	}	
}
