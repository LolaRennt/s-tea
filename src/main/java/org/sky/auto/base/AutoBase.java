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
import org.sky.auto.exception.MyElementNotFoundException;
import org.sky.auto.intrumentation.ClassPool;
import org.sky.auto.load.Source;
import org.sky.auto.load.SourceLoader;
import org.sky.auto.page.Page;
import org.sky.auto.page.source.CurrentPage;
import org.sky.auto.proxy.ProxyRunnerListener;
import org.sky.auto.runner.AutoResetThreadLocal;
import org.sky.auto.text.read.TxtLoader;
import org.sky.auto.text.read.TxtProvider;
import org.sky.auto.window.Window;
import org.sky.auto.xml.XMLLoader;
import org.sky.auto.xml.XMLToWebElement;
//import org.sky.auto.xml.XMLParser;

/**
 * 这是整个框架的一个核心类，可以理解为主要入口类，把分层的各种概念都整合在了这个类里面
 * @author 王天庆
 * */
public class AutoBase {
	//private static ThreadDriver td
	private static AutoResetThreadLocal<AutoDriver> art = new AutoResetThreadLocal<AutoDriver>(){
		protected synchronized AutoDriver initialValue() {
			return new AutoDriver();	
		};
	};
	/**获取获取框架的核心driver对象
	 * @return 返回s-tea的核心浏览器对象
	 * */
	public static AutoDriver getAutoDriver(){
		return art.get();
	}
	/**这个字段表明了浏览器是否执行过了关闭操作*/
	private static boolean CLOSE_STATUS;
	//static private ThreadDriver td =new ThreadDriver();
	static Logger logger =Logger.getLogger(AutoBase.class);
	/**资源目录*/
	private static Actions action;
	private AutoBase(){}
	/**设置log的日志目录
	 * @param path 设置log4j配置文件的目录
	 * */
	public static void setLogProperties(String path){
		PropertyConfigurator.configure(path);
	}
	
	/**设置浏览器类型
	 * @param browser 设置浏览器属性，通过Browser枚举类来设置
	 * */
	public static void setDriver(Browser browser){
		setLogStarted();
		XMLLoader.load();
		TxtLoader.load();
		setClose_Status(false);
		getAutoDriver().setDriver(browser);
		ClassPool.reset();
		Set<Class<?>> cls = ClassPool.getClassPool();
		logger.info("开始扫描动作监听器......");
		for(Class<?>clazz:cls){
			
			if(clazz.isAnnotationPresent(Register.class)){
				logger.info("扫描到了动作监听器："+clazz.getName());
				ProxyRunnerListener.register(clazz);
			}
		}
		logger.info("扫描动作监听器结束");
		Window.maxWindow();
	}
	
	/**设置浏览器类型
	 * @param browser 设置浏览器属性，通过String来设置
	 * */
	public static void setDriver(String browser){
		//setLogStarted();
		XMLLoader.load();
		TxtLoader.load();
		setClose_Status(false);
		getAutoDriver().setDriver(browser);
		ClassPool.reset();
		Set<Class<?>> cls = ClassPool.getClassPool();
		logger.info("开始扫描监听器......");
		for(Class<?>clazz:cls){
			
			if(clazz.isAnnotationPresent(Register.class)){
				logger.info("扫描到了动作监听器："+clazz.getName());
				ProxyRunnerListener.register(clazz);
			}
		}
		logger.info("扫描动作监听器结束");
		Window.maxWindow();	
	}
	
	/**打开浏览器,里面包含了初始化操作，必须以此方法为开始
	 * @param browser 设置浏览器
	 * @url 初始化的时候打开的浏览器地址
	 * */
	public static void open(Browser browser,String url){
		//setLogStarted();
		setDriver(browser);
		getAutoDriver().getDriver().get(url);
		logger.info("使用浏览器"+browser+"进行自动化测试，将要打开网址"+url+"测试");
		ProxyRunnerListener.getDispatcher().afterOpen();
	}
	
	/**打开浏览器,里面包含了初始化操作，必须以此方法为开始
	 * @param browser 设置浏览器
	 * @url 初始化的时候打开的浏览器地址
	 * */
	public static void open(String browser,String url){
		setDriver(browser);
		getAutoDriver().getDriver().get(url);
		logger.info("使用浏览器"+browser+"进行自动化测试，将要打开网址"+url+"测试");
		ProxyRunnerListener.getDispatcher().afterOpen();
	}
	/**在本页面跳转到指定的链接处
	 * @param 需要跳转的页面
	 * */
	public static void open(String url){
		driver().get(url);
		logger.info("打开了网址"+url+"来进行自动化测试！");
		ProxyRunnerListener.getDispatcher().afterOpen();
	}
	/**返回一个page对象
	 * @param 返回一个page类的对象，Page-object模式的支持方式
	 * */
	public static Page page(){
		return new Page();
	}
	/**将鼠标移动到指定的元素位置上面
	 * */
	public static void moveToElement(WebElement element){
		Actions action = new Actions(driver());
		action.moveToElement(element).build().perform();	
	}

	/**通过这个方法可以得到driver的对象
	 * @param <T>*/
	public static WebDriver driver(){
		return  getAutoDriver().getDriver();
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

	
	/**设置默认的元素等待时间
	 * @param secondes 设置为秒，单位即为秒
	 * */
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
		//Window.updateWindow();
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
	/**关闭所有的窗口，并且设置浏览器当前的状态，如果正常关闭的话，AuoBase的Close_Status设置为true*/
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
		//setClose_Status(true);
		ProxyRunnerListener.getDispatcher().aftercloseCurrentWindow();
	}

	/**得到元素的列表*/
	public static ListElement listElement(String id){
		ListElement le= new ListElement(AutoBase.elements("id"));
		//logger.info("元素LIST["+id+"]-->");
		return le;
 	}
	public static Button button(String id){
		Button bt=new Button(element(id));
		//logger.info("按钮["+id+"]-->");
		return bt;
	}
	
	public static CheckBox checkBox(String id){
		CheckBox cb =new CheckBox(element(id));
		//logger.info("CheckBox["+id+"]-->");
		return cb;
	}
	
	public static ComoboBox comoboBox(String id){
		ComoboBox cb=new ComoboBox(element(id));
		//logger.info("ComoboBox["+id+"]-->");
		return cb;
	}
	
	public static Image image(String id){
		Image i = new Image(element(id));
		//logger.info("Image["+id+"]-->");
		return i;
	}
	
	public static Link link(String id){
		Link l =new Link(element(id));
		//logger.info("Link["+id+"]-->");
		return l;
	}
	
	public static RadioButton radioButton(String id){
		RadioButton rd = new RadioButton(element(id));
		//logger.info("RadioButton["+id+"]-->");
		return rd;
	}
	public static RichTextField richTextField(String id){
		RichTextField rtf = new RichTextField(element(id));
		//logger.info("RichTextField["+id+"]-->");
		return rtf;
	}
	public static Table table(String id){
		Table t=new Table(element(id));
		//logger.info("Table["+id+"]-->");
		return t;
	}
	public static TextField textField(String id){
		TextField tf = new TextField(element(id));
		//logger.info("TextField["+id+"]-->");
		return tf;
	}
	/**把浏览器的设置为null，释放资源*/
	public static void clearCurrentThreadDriver(){
		getAutoDriver().setDriver(Browser.NULL);
		logger.info("多线程资源释放成功！");
	}
	/**开启日志功能，默认为resource目录下的log4j.properties*/
	public static void setLogStarted(){
		setLogProperties("resource"+File.separator+"log4j.properties");
	}
	
	public static void setLogStarted(String path){
		setLogProperties(path);
	}
	/**睡眠等待，参数单位为秒
	 * @param seconds 设置睡眠时间，单位为秒
	 * */
	public static void sleep(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**判断是否浏览器已经关闭
	 * @return true 已经关闭了，false没有关闭
	 * */
	public static boolean isClose_Status() {
		return CLOSE_STATUS;
	}

	public static void setClose_Status(boolean status) {
		CLOSE_STATUS = status;
	}	
	/**返回在资源中定义好的元素*/
	public static SElement sElement(String id){
		SElement se=new SElement(element(id));
		se.setId(id);
		return se;
	}
	

	/**获得element的list元素
	 * @param id 在资源定义的id值
	 * @return 通过id值返回的定义好的WebElement元素的列表
	 * */
	public static List<WebElement> elements(String id){
		XMLToWebElement xtw = new XMLToWebElement();
		return xtw.elements(id);
	}
	
	
	private static Source elementBelongTo(String id){
		try{
			return SourceLoader.getSource(id);
		}catch(Exception e){
			throw new MyAutoException("没有找到这个"+id+"资源，请检查是否在资源文件中定义");
		}
		
	}
	
	/**当前操作的当前页面
	 * @return 返回一个当前页的对象，可以操作关闭CurrentPage类的各种方法
	 * */
	public static CurrentPage currentpage(){
		return new CurrentPage();
	}
	
	public static SElement sElement(By by){
		return new SElement(by);
		}
	
	
	/**@return 返回一个空的SELment元素*/
	public static SElement sElement(){
		return new SElement();
	}
	
	  
	/**返回WebElement的元素
	 * @param id 我们在资源中定义的id值
	 * @return 通过id值返回一个定义的WebElement元素
	 * */
	public static WebElement element(String id){
		if(elementBelongTo(id)!=null){
			if(elementBelongTo(id).toString().equals("TXT")){
				logger.info("["+id+"]是来自TXT的资源");
				TxtProvider tp = new TxtProvider();
				return tp.element(id);
			}else if(elementBelongTo(id).toString().equals("XML")){
				logger.info("["+id+"]是来自XML的资源");
				XMLToWebElement xtw = new XMLToWebElement();
				return xtw.element(id);
			}else{
				throw new MyElementNotFoundException("扫描的资源中没有找到["+id+"]元素，请检查是否输入正确");
			}
		}
		return null;
	}
	
	
}
