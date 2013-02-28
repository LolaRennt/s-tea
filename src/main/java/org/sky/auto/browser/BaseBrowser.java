package org.sky.auto.browser;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sky.auto.base.AutoBase;
import org.sky.auto.base.ConfigParser;
import org.sky.auto.browser.element.Button;
import org.sky.auto.browser.element.CheckBox;
import org.sky.auto.browser.element.ComoboBox;
import org.sky.auto.browser.element.Contorl;
import org.sky.auto.browser.element.Image;
import org.sky.auto.browser.element.Link;
import org.sky.auto.browser.element.ListElement;
import org.sky.auto.browser.element.RadioButton;
import org.sky.auto.browser.element.RichTextField;
import org.sky.auto.browser.element.Table;
import org.sky.auto.browser.element.TextField;
import org.sky.auto.browser.page.Page;

import org.sky.auto.proxy.ProxyRunnerListener;


public class BaseBrowser implements IBrowser{
	
	static Logger logger = Logger.getLogger(BaseBrowser.class);
	private WebDriver driver;
	public void open(String url) {
		driver.get(url);
	}

	public void scrollTo(WebElement element) {
		ProxyRunnerListener.getDispatcher().beforescrollTo();
		Point point = element.getLocation();
	    int x =point.getX();
	    int y =point.getY();
	    runJavaScript("window.scrollTo("+x+","+y+")");
	    ProxyRunnerListener.getDispatcher().afterscrollTo();
	}

	public void scrollTo(Contorl c){
		ProxyRunnerListener.getDispatcher().beforescrollTo();
		Point point = c.getLocatorValue().getLocation();
	    int x =point.getX();
	    int y =point.getY();
	    runJavaScript("window.scrollTo("+x+","+y+")");
	    ProxyRunnerListener.getDispatcher().afterscrollTo();
	}
	public void back() {
		ProxyRunnerListener.getDispatcher().beforeback();
		driver.navigate().back();
		ProxyRunnerListener.getDispatcher().afterback();
	}

	public void refresh() {
		ProxyRunnerListener.getDispatcher().beforerefresh();
		driver.navigate().refresh();
		ProxyRunnerListener.getDispatcher().afterrefresh();
	}

	public void focusWindow(String url) {
		ProxyRunnerListener.getDispatcher().beforeselectWindow();
		Set<String> windows = AutoBase.driver().getWindowHandles();
		for(String window : windows){
			driver.switchTo().window(window);
			String titleName = AutoBase.driver().getCurrentUrl();
			if(titleName.equals(url)){
				break;
			}
		}
		ProxyRunnerListener.getDispatcher().afterselectWindow();
	}

	public void focusWindowByTitle(String title) {
		ProxyRunnerListener.getDispatcher().beforeselectWindow();
		Set<String> windows = AutoBase.driver().getWindowHandles();
		for(String window : windows){
			driver.switchTo().window(window);
			String titleName = AutoBase.driver().getTitle();
			if(titleName.equals(title)){
				break;
			}
		}
		ProxyRunnerListener.getDispatcher().afterselectWindow();
		
	}

	public void frame(WebElement element) {
		driver.switchTo().frame(element);
		
	}

	public void frame(int index) {
		driver.switchTo().frame(index);
		
	}

	
	public void frame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		
	}

	
	public void frame(Contorl c) {
		driver.switchTo().frame(c.getLocatorValue());		
	}
	
	
	public void closeAllWindow() {
		driver.quit();
	}

	
	public void closeCurrentWindow() {
		driver.close();
		
	}

	
	public void runJavaScript(String js) {
		ProxyRunnerListener.getDispatcher().beforerunJS();
		((JavascriptExecutor)driver).executeScript(js);
		ProxyRunnerListener.getDispatcher().afterrunJS();
		
	}

	
	public void runJavaScript(String js,Object... args) {
		ProxyRunnerListener.getDispatcher().beforerunJS();
		((JavascriptExecutor)driver).executeScript(js,args);
		ProxyRunnerListener.getDispatcher().afterrunJS();
		
	}

	
	public void currentPageTitle() {
		driver.getTitle();
		
	}

	
	public void currentPageUrl() {
		driver.getCurrentUrl();
		
	}

	
	public void dealAlter() {
		ProxyRunnerListener.getDispatcher().beforedealAlert();	
		try{
			Alert alert=driver.switchTo().alert();
			alert.accept();
			ProxyRunnerListener.getDispatcher().afterdealAlert();
		}catch (Exception e) {
			System.out.println("没有找到Alert窗口!");
		}
		
		
	}

	
	public String dealPrompt(String input,boolean isYes) {
		String promptMessage=null;
		try{
			Alert alert=driver.switchTo().alert();
			alert.sendKeys(input);
			promptMessage= alert.getText();
			if(isYes)
				alert.accept();
			else 
				alert.dismiss();
			return promptMessage;
		}catch (Exception e) {
			System.out.println("没有找到Prompt窗口!");
		}return null;
	}

	
	public String dealConfirm(boolean isYes) {
		ProxyRunnerListener.getDispatcher().beforedealConfirm();
		String confirmMessage=null;
		try{
			Alert alert=driver.switchTo().alert();
			confirmMessage=alert.getText();
			if(isYes)alert.accept();
			else alert.dismiss();
			alert.accept();
			ProxyRunnerListener.getDispatcher().afterdealConfirm();
			return confirmMessage;
		}catch (Exception e) {
			System.out.println("没有找到Prompt窗口!");
		}return null;
	}

	
	public void takeScreenShot() {
		ProxyRunnerListener.getDispatcher().beforetakeScreenShot();
		String path = ConfigParser.getScreenShotPath();
		TakesScreenshot tss = (TakesScreenshot)driver;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String time = sdf.format(new Date());
		File file = tss.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(path+File.separator+time+"png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ProxyRunnerListener.getDispatcher().aftertakeScreenShot();
		
	}

	
	public void maxWindow() {
		ProxyRunnerListener.getDispatcher().beforemaxWindow();
		driver.manage().window().maximize();
		ProxyRunnerListener.getDispatcher().aftermaxWindow();
	}

	
	public Page  page() {	
		return new Page(this);
	}

	
	public WebElement element(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Contorl Contorl(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setLogProperties(String path) {
		PropertyConfigurator.configure(path);
		
	}

	
	public void setDefaultLogConfigure() {
		PropertyConfigurator.configure("resource"+File.separator+"log4j.properties");	
	}



	
	public ListElement listElement(String id) {
		
		return null;
	}

	
	public Button button(String id) {
		Button bt = new Button(this,element(id));
		logger.info("按钮["+id+"]-->");
		return bt;
	}

	
	public CheckBox checkBox(String id) {
		CheckBox cb =new CheckBox(this,element(id));
		logger.info("CheckBox["+id+"]-->");
		return cb;
	}

	
	public ComoboBox comoboBox(String id) {
		ComoboBox cb = new ComoboBox(this,element(id));
		logger.info("comoboBox["+id+"]-->");
		return cb;
	}

	
	public Image image(String id) {
		Image i = new Image(this,element(id));
		logger.info("Image["+id+"]-->");
		return i;
	}

	
	public Link link(String id) {
		Link l = new Link(this,element(id));
		logger.info("Link["+id+"]-->");
		return l;
	}

	
	public RadioButton radioButton(String id) {
		RadioButton rb = new RadioButton(this, element(id));
		logger.info("RadioButton["+id+"]-->");
		return rb;
	}

	
	public RichTextField richTextField(String id) {
		RichTextField rtf =new RichTextField(this, element(id));
		logger.info("RichTextField["+id+"]-->");
		return rtf;
	}

	
	public Table table(String id) {
		Table t = new Table(this,element(id));
		logger.info("Table["+id+"]-->");
		return t;
	}

	
	public TextField textField(String id) {
		TextField tf = new TextField(this,element(id));
		logger.info("TextField["+id+"]-->");
		return tf;
	}

	
	public WebDriver browser() {
		return getDriver();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	class SimpleActions implements IActions{
		private Actions action = new Actions(driver);
		
		public void keyDown(Keys key) {
			ProxyRunnerListener.getDispatcher().beforekeyDown();
			action.keyDown(key);
			ProxyRunnerListener.getDispatcher().afterkeyDown();
			
		}

		
		public void keyDown(WebElement element, Keys key) {
			ProxyRunnerListener.getDispatcher().beforekeyDown();
			action.keyDown(element, key);
			ProxyRunnerListener.getDispatcher().afterkeyDown();
		}

		
		public void click(WebElement element) {
			ProxyRunnerListener.getDispatcher().beforeClickOn();
			action.click(element);
			ProxyRunnerListener.getDispatcher().afterClickOn();
			
		}

		
		public void dragAndDrop(WebElement fromElement, WebElement toElement) {
			ProxyRunnerListener.getDispatcher().beforedragAndDrop();
			action.dragAndDrop(fromElement, toElement);
			ProxyRunnerListener.getDispatcher().afterdragAndDrop();
		}

		
		public void dragAndDrop(WebElement fromElement, int xlocation,
				int ylocation) {
			ProxyRunnerListener.getDispatcher().beforedragAndDrop();
			action.dragAndDropBy(fromElement, xlocation, ylocation);
			ProxyRunnerListener.getDispatcher().afterdragAndDrop();
		}

		
		public void keyUp(Keys key) {
			ProxyRunnerListener.getDispatcher().beforekeyUp();
			action.keyUp(key);
			ProxyRunnerListener.getDispatcher().beforekeyUp();
		}

		
		public void keyUp(WebElement element, Keys key) {
			ProxyRunnerListener.getDispatcher().beforekeyUp();
			action.keyUp(element,key);
			ProxyRunnerListener.getDispatcher().beforekeyUp();
		}

		
		public void focus(WebElement element) {
			action.sendKeys("");
			
		}

		
		public void leftMouseDown(WebElement element) {
			ProxyRunnerListener.getDispatcher().beforeleftDown();
			action.clickAndHold(element);
			ProxyRunnerListener.getDispatcher().afterleftDown();
		}

		
		public void leftMouseUp(WebElement onElement) {
			ProxyRunnerListener.getDispatcher().beforeleftUp();
			action.release(onElement);
			ProxyRunnerListener.getDispatcher().afterleftUp();
		}

		
		public void doubleClick(WebElement element) {
			ProxyRunnerListener.getDispatcher().beforedoubleClick();
			action.doubleClick(element);
			ProxyRunnerListener.getDispatcher().afterdoubleClick();
		}

		
		public void sendKeys(WebElement element, CharSequence... charSequences) {
			ProxyRunnerListener.getDispatcher().beforeSendkeys();
			action.doubleClick();
			ProxyRunnerListener.getDispatcher().afterSendkeys();
		}

		
		public void moveToElement(WebElement element) {
			ProxyRunnerListener.getDispatcher().beforemoveToElement();
			action.moveToElement(element);
			ProxyRunnerListener.getDispatcher().aftermoveToElement();
		}
		
	}

	
	public IActions action() {	
		return new SimpleActions();
	}

	
	public void selectDefaultWindow() {
		driver.switchTo().defaultContent();
		
	}
	
}
