package org.sky.auto.browser.element;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.sky.auto.browser.IBrowser;
import org.sky.auto.element.Locator;
import org.sky.auto.exception.MyAutoException;
import org.sky.auto.exception.MyElementNotFoundException;
import org.sky.auto.proxy.ProxyRunnerListener;

public class PageContorl extends Contorl{
	private WebElement element;
	private IBrowser browser;
	public PageContorl(IBrowser browser,WebElement webElement) {
		this.browser=browser;
		this.element=webElement;
	}
	
	public PageContorl(IBrowser browser){
		this.setBrowser(browser);
		this.element=new RemoteWebElement();
	}

	@Override
	public Contorl addLocator(By by) {
		try{
			ProxyRunnerListener.getDispatcher().beforeaddLocator();
			if(element==null){
				Contorl c= new PageContorl(getBrowser(),getBrowser().browser().findElement(by));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				return c;
			}else{
				Contorl c= new PageContorl(getBrowser(),getLocatorValue().findElement(by));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				return c;
			}
		}catch(NoSuchElementException e){
			//logger.error("定位元素的时候出现错误！");
			throw new MyElementNotFoundException("通过"+by.toString()+"定位元素的时候出现错误！");
		}
		//return null;
		
	}

	@Override
	public Contorl addLocator(WebElement we) {	
		ProxyRunnerListener.getDispatcher().beforeaddLocator();
		PageContorl pc= new PageContorl(getBrowser(),we);
		ProxyRunnerListener.getDispatcher().afteraddLocator();
		return pc;
	}

	@Override
	public WebElement getLocatorValue() {
		
		return this.element;
	}



	@Override
	public Contorl addLocator(Locator locator, String value) {
		ProxyRunnerListener.getDispatcher().beforeaddLocator();
		if(getLocatorValue()==null){
			switch(locator){
			case Id:
				this.element=getLocatorValue().findElement(By.id(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Xpath:
				this.element=getLocatorValue().findElement(By.xpath(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case LinkText:
				this.element=getLocatorValue().findElement(By.linkText(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case PartialLinkText:
				this.element=getLocatorValue().findElement(By.partialLinkText(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case TagName:
				this.element=getLocatorValue().findElement(By.tagName(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Class:
				this.element=getLocatorValue().findElement(By.className(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Css:
				this.element=getLocatorValue().findElement(By.cssSelector(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Name:
				this.element=getLocatorValue().findElement(By.name(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			default:
				logger.error("定位方式选择错误！没有加入成功！");
				throw new MyAutoException("选择的定位方式不支持！");
			}
		}else{
			switch(locator){
			case Id:
				this.element=getLocatorValue().findElement(By.id(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Xpath:
				this.element=getLocatorValue().findElement(By.xpath(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case LinkText:
				this.element=getLocatorValue().findElement(By.linkText(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case PartialLinkText:
				this.element=getLocatorValue().findElement(By.partialLinkText(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case TagName:
				this.element=getLocatorValue().findElement(By.tagName(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Class:
				this.element=getLocatorValue().findElement(By.className(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Css:
				this.element=getLocatorValue().findElement(By.cssSelector(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Name:
				this.element=getLocatorValue().findElement(By.name(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			default:
				logger.error("定位方式选择错误！没有加入成功！");
				throw new MyAutoException("选择的定位方式不支持！");
			}
		}	
		return new PageContorl(getBrowser(),getLocatorValue());
	}

	@Override
	public Contorl addLocator(Locator locator, String value, int index) {
		
		ProxyRunnerListener.getDispatcher().beforeaddLocator();
		if(getLocatorValue()==null){
			switch(locator){
			case Id:
				this.element=getLocatorValue().findElements(By.id(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Xpath:
				this.element=getLocatorValue().findElements(By.xpath(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case LinkText:
				this.element=getLocatorValue().findElements(By.linkText(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case PartialLinkText:
				this.element=getLocatorValue().findElements(By.partialLinkText(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case TagName:
				this.element=getLocatorValue().findElements(By.tagName(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Class:
				this.element=getLocatorValue().findElements(By.className(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Css:
				this.element=getLocatorValue().findElements(By.cssSelector(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Name:
				this.element=getLocatorValue().findElements(By.name(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			default:
				logger.error("定位方式选择错误！没有加入成功！");
				throw new MyAutoException("选择的定位方式不支持！");
			}
		}else{
			switch(locator){
			case Id:
				this.element=getLocatorValue().findElements(By.id(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Xpath:
				this.element=getLocatorValue().findElements(By.xpath(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case LinkText:
				this.element=getLocatorValue().findElements(By.linkText(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case PartialLinkText:
				this.element=getLocatorValue().findElements(By.partialLinkText(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case TagName:
				this.element=getLocatorValue().findElements(By.tagName(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Class:
				this.element=getLocatorValue().findElements(By.className(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Css:
				this.element=getLocatorValue().findElements(By.cssSelector(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Name:
				this.element=getLocatorValue().findElements(By.name(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			default:
				logger.error("定位方式选择错误！没有加入成功！");
				throw new MyAutoException("选择的定位方式不支持！");
			}
		}	
		return new PageContorl(getBrowser(),getLocatorValue());
	}

	@Override
	public Contorl addLocator(By by, int index) {
		ProxyRunnerListener.getDispatcher().beforeaddLocator();
		if(getLocatorValue()==null){
			Contorl c= new PageContorl(getBrowser(),getBrowser().browser().findElements(by).get(index));
			ProxyRunnerListener.getDispatcher().afteraddLocator();
			return c;
		}else{
			Contorl c = new PageContorl(getBrowser(),getLocatorValue().findElements(by).get(index));
			ProxyRunnerListener.getDispatcher().afteraddLocator();
			return c;
		}
		
	}

	public IBrowser getBrowser() {
		return browser;
	}

	public void setBrowser(IBrowser browser) {
		this.browser = browser;
	}

	@Override
	public void setLocatorValue(WebElement we) {
		this.element=we;
		
	}
	/**清除操作*/
	public void clear(){
		ProxyRunnerListener.getDispatcher().beforeclear();
		if(isExist()){
			getLocatorValue().clear();
			logger.info("输入框内容清理成功！");
		}else{
			logger.error("输入框内容清理失败！没有找到元素！");
		}
		ProxyRunnerListener.getDispatcher().afterclear();
	}
	
	
	/**双击操作*/
	public void doubleClick(){
		ProxyRunnerListener.getDispatcher().beforedoubleClick();
		if(isExist()){
			getBrowser().action().doubleClick(getLocatorValue());
			logger.info("双击成功！");
		}else{
			logger.error("双击失败！没有找到元素！");
		}
		ProxyRunnerListener.getDispatcher().beforedoubleClick();
		
	}
	/**在元素上面悬停*/
	public void mouseOver(){
		ProxyRunnerListener.getDispatcher().beforemouserOver();
		if(isExist()){
			getBrowser().action().moveToElement(getLocatorValue());
			logger.info("鼠标悬停成功！");
		}else{
			logger.error("没有找到元素，鼠标悬停失败！");
		}
		ProxyRunnerListener.getDispatcher().beforemouserOver();
	}
	/**在元素上面按下某一个键*/
	public void keyDown(Keys key){
		ProxyRunnerListener.getDispatcher().beforekeyDown();
		if(isExist()){
			getBrowser().action().keyDown(getLocatorValue(),key);
			logger.info("按钮"+key+"成功！");
		}else{
			logger.error("没有找到元素，按下按钮失败！");
		}
		ProxyRunnerListener.getDispatcher().afterkeyDown();
	}
	/**在元素上面松开某一个键*/
	public void keyUp(Keys key){
		ProxyRunnerListener.getDispatcher().beforekeyUp();
		if(isExist()){
			getBrowser().action().keyUp(getLocatorValue(), key);
			logger.info("按钮"+key+"松开成功！");
		}else{
			logger.error("按钮松开失败！没有找到元素！");
		}
		ProxyRunnerListener.getDispatcher().beforekeyUp();
	}
	/***在元素上按下左键*/
	public void leftDown(){
		ProxyRunnerListener.getDispatcher().beforeleftDown();
		if(isExist()){
			getBrowser().action().leftMouseDown(getLocatorValue());
			logger.info("按下左键成功！");
		}else{
			logger.error("按下左键失败！没有找到元素！");
		}
		ProxyRunnerListener.getDispatcher().afterleftDown();
	}
	/**在元素上松开左键*/
	public void leftUp(){
		ProxyRunnerListener.getDispatcher().beforeleftUp();
		if(isExist()){
			getBrowser().action().leftMouseUp(getLocatorValue());
			logger.info("左键松开成功！");
		}else{
			logger.error("左键松开失败！");
		}
		ProxyRunnerListener.getDispatcher().afterleftUp();
	}
	/**拖拽到指定的位置*/
	public void dragAndDrop(Point location){
		ProxyRunnerListener.getDispatcher().beforedragAndDrop();
		if(isExist()){
			getBrowser().action().dragAndDrop(getLocatorValue(), location.getX(), location.getY());
			logger.info("拖拽成功！");
		}else{
			logger.error("拖拽失败！没有找到元素！");
		}
		ProxyRunnerListener.getDispatcher().afterdragAndDrop();
	}
	/**拖拽到指定的元素位置上*/
	public void dragAndDrop(Contorl element){
		if(isExist()){
			getBrowser().action().dragAndDrop(getLocatorValue(), element.getLocatorValue());
			logger.info("拖拽到指定元素上成功！");
		}else{
			logger.error("拖拽到指定的元素上面失败！没有找到元素！");
		}
		
	}
	/**得到元素的大小,得到高和宽的数组*/
	public int[] getSize(){
		if(isExist()){
			Dimension ds=getLocatorValue().getSize();
			int height=ds.getHeight();
			int width=ds.getWidth();
			int[] size =new int[]{height,width};
			logger.info("得到尺寸成功！");
			return size;
		}else{
			logger.error("得到尺寸失败！没有找到元素！");
		}
		return null;
	}
	/**得到元素的位置*/
	public Point getLocation(){
		if(isExist()){
			logger.info("得到元素位置成功！");
			return getLocatorValue().getLocation();	
		}else{
			logger.error("得到元素的位置失败！没有找到元素！");
		}
		return null;
	}
	/**得到属性值*/
	public String getAttribute(String name){
		if(isExist()){
			return getLocatorValue().getAttribute(name);
		}else{
			logger.error("获得属性值"+name+"失败！没有找到元素！");
		}
		return null;
	}
	/**得到标签的名字*/
	public String getTagName(){
		if(isExist()){
			return getLocatorValue().getTagName();
		}else{
			logger.error("得到标签的名字失败！没有找到元素！");
		}
		return null;
	}
	/**得到css的值*/
	public String getCssValue(String name){
		if(isExist()){
			return getLocatorValue().getCssValue(name);
		}else{
			logger.error("得到css的值失败！没有找到元素！");
		}
		return null;
	}
	/**得到标签内的内容*/
	public String getText(){
		if(isExist()){
			return getLocatorValue().getText();
		}else{
			logger.error("得到标签内的内容失败！元素不存在！");
			return null;
		}
		
	}
	/**元素是否可见的*/
	public boolean isDisplay(){
		if(isExist()){
			return getLocatorValue().isDisplayed();
		}else{
			logger.info("判断元素是否可见失败！元素不存在！");
			return false;
		}
		
	}
	/**是否可以编辑的*/
	public boolean isEnable(){
		if(isExist()){
			return getLocatorValue().isEnabled();
		}else{
			logger.error("元素不可以被编辑，元素不存在！");
			return false;
		}
		
	}
	/**是否被选择的*/
	public boolean isSelected(){
		if(isExist()){
			return getLocatorValue().isSelected();
		}else{
			logger.error("元素可选择属性判断失败！元素不存在！");
			return false;
		}
		
	}
	/**视角移动到当前元素*/
	public void scroll(){
		ProxyRunnerListener.getDispatcher().beforescroll();
		if(isExist()){
			getBrowser().scrollTo(getLocatorValue());
			logger.info("视角滚动成功！");
		}else{
			logger.error("视角滚动失败！元素不存在！");
		}
		ProxyRunnerListener.getDispatcher().afterscroll();
	}
	

}
