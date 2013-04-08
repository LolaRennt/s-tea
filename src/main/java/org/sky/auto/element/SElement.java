package org.sky.auto.element;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.sky.auto.base.AutoBase;
import org.sky.auto.exception.MyAutoException;
import org.sky.auto.proxy.ProxyRunnerListener;
//import org.sky.auto.log.MyLogger;
import org.sky.auto.window.Window;
/**这个Element类是一个辅助操作的类，可以混用的一个类型，里面定义了常用的元素方法，这样的话可以节约自己封装了<br>
 * 这个element类不支持HtmlUnit的元素定义
 * @author 王天庆
 * */

public class SElement{
	Logger logger = Logger.getLogger(SElement.class);
	WebElement element;
	/**元素说明*/
	private String id="Element";
	public SElement(WebElement element){
		this.element=element;
	}
	/**构造方法，通过By的方式来构造Element元素*/
	public SElement(By by){
		this.element=AutoBase.driver().findElement(by);
	}
	/**最常用的Element的构造方法*/
	public SElement(){
		this.element=new RemoteWebElement();
	}
	
	public WebElement getElement() {
		return element;
	}
	public void setElement(WebElement element) {
		this.element = element;
	}
	public SElement addLocator(By by){
		this.element=AutoBase.driver().findElement(by);
		return this;
	}
	public SElement addLocator(WebElement element){
		this.element=element;
		return this;
	}
	public SElement addLocator(Locator locator,String value){
		ProxyRunnerListener.getDispatcher().beforeaddLocator();
		switch(locator){
			case Id:
				this.element=AutoBase.driver().findElement(By.id(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Xpath:
				this.element=AutoBase.driver().findElement(By.xpath(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case LinkText:
				this.element=AutoBase.driver().findElement(By.linkText(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case PartialLinkText:
				this.element=AutoBase.driver().findElement(By.partialLinkText(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case TagName:
				this.element=AutoBase.driver().findElement(By.tagName(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Class:
				this.element=AutoBase.driver().findElement(By.className(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Css:
				this.element=AutoBase.driver().findElement(By.cssSelector(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Name:
				this.element=AutoBase.driver().findElement(By.name(value));
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			default:
				logger.error("定位方式选择错误！没有加入成功！");
				throw new MyAutoException("选择的定位方式不支持！");
		}
		return this;
	}
	public SElement addLocator(Locator locator,String value, int index){
		ProxyRunnerListener.getDispatcher().beforeaddLocator();
		switch(locator){
			case Id:
				this.element=AutoBase.driver().findElements(By.id(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Xpath:
				this.element=AutoBase.driver().findElements(By.xpath(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case LinkText:
				this.element=AutoBase.driver().findElements(By.linkText(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case PartialLinkText:
				this.element=AutoBase.driver().findElements(By.partialLinkText(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case TagName:
				this.element=AutoBase.driver().findElements(By.tagName(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Class:
				this.element=AutoBase.driver().findElements(By.className(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Css:
				this.element=AutoBase.driver().findElements(By.cssSelector(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			case Name:
				this.element=AutoBase.driver().findElements(By.name(value)).get(index);
				ProxyRunnerListener.getDispatcher().afteraddLocator();
				break;
			default:
				logger.error("定位方式选择错误！没有加入成功！");
				throw new MyAutoException("选择的定位方式不支持！");
		}
		if(element==null){
			throw new NoSuchElementException("在定位元素的时候发生了错误，很有可能是元素没有定位到！请检查自己配置的元素定位方式是否正确！");
		}
		return this;
	}
	
	public SElement addLocator(By by,int index){
		ProxyRunnerListener.getDispatcher().beforeaddLocator();
		this.element=AutoBase.driver().findElements(by).get(index);
		ProxyRunnerListener.getDispatcher().afteraddLocator();
		return this;
	}
	/**点击操作*/
	public void click(){
		Window.updateWindow();
		ProxyRunnerListener.getDispatcher().beforeClickOn();
		if(isExist()){
			getElement().click();
			logger.info(">>["+this.getId()+"]点击成功！");
		}else{
			logger.error(">>没有找到元素，点击失败！");
			throw new MyAutoException("["+this.getId()+"]进行点击操作的时候出现错误！");
		}
		ProxyRunnerListener.getDispatcher().afterClickOn();
		
		
	}
	/**清除操作*/
	public void clear(){
		Window.updateWindow();
		ProxyRunnerListener.getDispatcher().beforeclear();
		if(isExist()){
			getElement().clear();
			logger.info(">>["+this.getId()+"]输入框内容清理成功！");
		}else{
			logger.error(">>输入框内容清理失败！没有找到元素！");
			throw new MyAutoException("["+this.getId()+"]清理输入框失败！没有找到元素！");
		}
		ProxyRunnerListener.getDispatcher().afterclear();
	}
	/**双击操作*/
	public void doubleClick(){
		Window.updateWindow();
		ProxyRunnerListener.getDispatcher().beforedoubleClick();
		if(isExist()){
			AutoBase.doubleClick(getElement());
			logger.info(">>["+this.getId()+"]双击成功！");
		}else{
			logger.error(">>双击失败！没有找到元素！");
			throw new MyAutoException("["+this.getId()+"]进行双击操作的时候失败！很有可能的原因是自己定义的元素没有被找到元素！");
		}
		ProxyRunnerListener.getDispatcher().beforedoubleClick();
		
	}
	/**在元素上面悬停*/
	public void mouseOver(){
		Window.updateWindow();
		ProxyRunnerListener.getDispatcher().beforemouserOver();
		if(isExist()){
			AutoBase.moveToElement(getElement());
			logger.info(">>["+this.getId()+"]鼠标悬停成功！");
		}else{
			logger.error(">>没有找到元素，鼠标悬停失败！");
			throw new MyAutoException("["+this.getId()+"]处鼠标进行悬停操作的时候失败了！很有可能的原因是没有找到元素！");
		}
		ProxyRunnerListener.getDispatcher().beforemouserOver();
	}
	/**在元素上面按下某一个键*/
	public void keyDown(Keys key){
		Window.updateWindow();
		ProxyRunnerListener.getDispatcher().beforekeyDown();
		if(isExist()){
			AutoBase.keyDown(getElement(),key);
			logger.info(">>["+this.getId()+"]按下按钮"+key.toString()+"成功！");
		}else{
			logger.error("没有找到元素，按下按钮失败！");
			throw new MyAutoException("["+this.getId()+"]进行按键操作的失败！很有可能的原因是没有找到元素！");
		}
		ProxyRunnerListener.getDispatcher().afterkeyDown();
	}
	/**在元素上面松开某一个键*/
	public void keyUp(Keys key){
		Window.updateWindow();
		ProxyRunnerListener.getDispatcher().beforekeyUp();
		if(isExist()){
			AutoBase.keyUp(getElement(), key);
			logger.info(">>["+this.getId()+"]"+key+"松开成功！");
		}else{
			logger.error(">>按钮松开失败！没有找到元素！");
			throw new MyAutoException("["+this.getId()+"]进行松开按钮操作的失败！很有可能的原因是没有找到这个元素！");
		}
		ProxyRunnerListener.getDispatcher().beforekeyUp();
	}
	/***在元素上按下左键*/
	public void leftDown(){
		Window.updateWindow();
		ProxyRunnerListener.getDispatcher().beforeleftDown();
		if(isExist()){
			AutoBase.leftMouseDown(getElement());
			logger.info(">>["+this.getId()+"]按下左键成功！");
		}else{
			logger.error(">>按下左键失败！没有找到元素！");
			throw new MyAutoException("["+this.getId()+"]进行按下左键操作的时候失败了！很有可能的原因是没有找到此元素！");
		}
		ProxyRunnerListener.getDispatcher().afterleftDown();
	}
	/**在元素上松开左键*/
	public void leftUp(){
		Window.updateWindow();
		ProxyRunnerListener.getDispatcher().beforeleftUp();
		if(isExist()){
			AutoBase.leftMouseUp(getElement());
			logger.info(">>["+this.getId()+"]左键松开成功！");
		}else{
			logger.error(">>左键松开失败！");
			throw new MyAutoException("进行松开左键操作的时候失败了！很有可能的原因是没有找到此元素！");
		}
		ProxyRunnerListener.getDispatcher().afterleftUp();
	}
	/**拖拽到指定的位置*/
	public void dragAndDrop(Point location){
		Window.updateWindow();
		ProxyRunnerListener.getDispatcher().beforedragAndDrop();
		if(isExist()){
			AutoBase.dragAndDrop(getElement(), location.getX(), location.getY());
			logger.info(">>["+this.getId()+"]拖拽成功！");
		}else{
			logger.error(">>拖拽失败！没有找到元素！");
			throw new MyAutoException("["+this.getId()+"]进行拖拽操作的时候失败了！可能的原因是没有找到元素！");
		}
		ProxyRunnerListener.getDispatcher().afterdragAndDrop();
	}
	/**拖拽到指定的元素位置上*/
	public void dragAndDrop(SElement element){
		Window.updateWindow();
		if(isExist()){
			AutoBase.dragAndDrop(getElement(), element.getElement());
			logger.info(">>["+this.getId()+"]拖拽到指定元素上成功！");
		}else{
			logger.error(">>拖拽到指定的元素上面失败！没有找到元素！");
			throw new MyAutoException("["+this.getId()+"]进行拖拽操作的时候失败了！拖拽到指定元素失败！没有找到指定元素！");
		}
		
	}
	/**得到元素的大小,得到高和宽的数组*/
	public int[] getSize(){
		if(isExist()){
			Dimension ds=getElement().getSize();
			int height=ds.getHeight();
			int width=ds.getWidth();
			int[] size =new int[]{height,width};
			logger.info(">>["+this.getId()+"]得到尺寸成功！");
			return size;
		}else{
			logger.error(">>得到尺寸失败！没有找到元素！");
			throw new MyAutoException("["+this.getId()+"]获取元素的尺寸失败了！没有找到元素！");
		}
		//return null;
	}
	/**得到元素的位置*/
	public Point getLocation(){
		if(isExist()){
			logger.info(">>["+this.getId()+"]得到元素位置成功！");
			return getElement().getLocation();	
		}else{
			logger.error(">>得到元素的位置失败！没有找到元素！");
			throw new MyAutoException("["+this.getId()+"]得到元素位置失败！没有找到元素！");
		}
	//	return null;
	}
	/**得到属性值*/
	public String getAttribute(String name){
		if(isExist()){
			logger.info("["+this.getId()+"]得到属性值为"+getElement().getAttribute(name));
			return getElement().getAttribute(name);
		}else{
			logger.error("获得属性值"+name+"失败！没有找到元素！");
			throw new MyAutoException("["+this.getId()+"]没有获取到元素属性！元素没有被找到！");
		}
		//return null;
	}
	/**得到标签的名字*/
	public String getTagName(){
		if(isExist()){
			logger.info("["+this.getId()+"]得到标签值为"+getElement().getTagName());
			return getElement().getTagName();
		}else{
			logger.error("得到标签的名字失败！没有找到元素！");
			throw new MyAutoException("["+this.getId()+"]没有获得到tagname！元素没有找到！");
		}
		//return null;
	}
	/**得到css的值*/
	public String getCssValue(String name){
		if(isExist()){
			logger.info("["+this.getId()+"]得到css值为"+getElement().getCssValue(name));
			return getElement().getCssValue(name);
		}else{
			logger.error("得到css的值失败！没有找到元素！");
			throw new MyAutoException("["+this.getId()+"]获取css值失败！元素没有找到！");
		}
		//return null;
	}
	/**得到标签内的文本内容*/
	public String getText(){
		if(isExist()){		
			logger.info("["+this.getId()+"]得到文本内容为["+getElement().getText()+"]");
			return getElement().getText();
		}else{
			logger.error("得到文本内的内容失败！元素不存在！");
			throw new MyAutoException("["+this.getId()+"]得到文本内容失败！没有找到元素！");
		//	return null;
		}
		
	}
	/**元素是否可见的*/
	public boolean isDisplay(){
		if(isExist()){
			return getElement().isDisplayed();
		}else{
			logger.info("判断元素是否可见失败！元素不存在！");
			throw new MyAutoException("["+this.getId()+"]可见性判断失败！元素不存在！");
			//return false;
		}
		
	}
	/**是否可以编辑的*/
	public boolean isEnable(){
		if(isExist()){
			return getElement().isEnabled();
		}else{
			logger.error("元素不可以被编辑，元素不存在！");
			throw new MyAutoException("["+this.getId()+"]判断可编辑性失败！元素不存在！");
			//return false;
		}
		
	}
	/**是否被选择的*/
	public boolean isSelected(){
		if(isExist()){
			return getElement().isSelected();
		}else{
			logger.error("元素可选择属性判断失败！元素不存在！");
			throw new MyAutoException("["+this.getId()+"]元素可选择性判断失败！元素不存在！！");
			//return false;
		}
		
	}
	/**视角移动到当前元素*/
	public void scroll(){
		Window.updateWindow();
		ProxyRunnerListener.getDispatcher().beforescroll();
		if(isExist()){
			Window.scrollTo(getElement());
			logger.info(">>["+this.getId()+"]视角滚动成功！");
		}else{
			logger.error("视角滚动失败！元素不存在！");
			throw new MyAutoException("["+this.getId()+"]视角滚动失败！元素没有找到！！！");
		}
		ProxyRunnerListener.getDispatcher().afterscroll();
	}
	
	/**在此元素的基础上添加子元素
	 * @throws Exception */
	public SElement childElement(Locator locator,String value) {
		switch(locator){
		case Id:
			return new SElement(getElement().findElement(By.id(value)));
		case Xpath:
			return new SElement(getElement().findElement(By.xpath(value)));
		case Name:
			return new SElement(getElement().findElement(By.name(value)));
		case LinkText:
			return new SElement(getElement().findElement(By.linkText(value)));
		case PartialLinkText:
			return new SElement(getElement().findElement(By.partialLinkText(value)));
		case Css:
			return new SElement(getElement().findElement(By.cssSelector(value)));
		case Class:
			return new SElement(getElement().findElement(By.className(value)));
		case TagName:
			return new SElement(getElement().findElement(By.tagName(value)));
		default:
			throw new MyAutoException("定位方式错误，没有找到子元素");
		}
	}
	/**在此元素的基础上添加子元素
	 * @throws Exception */
	public SElement childElement(Locator locator,String value,int index) {
		SElement se;
		if(getElement()==null){
			throw new NoSuchElementException("此元素还未进行指定locator，请先指定元素具体位置！");
		}
		switch(locator){
		case Id:
			se= new SElement(getElement().findElements(By.id(value)).get(index));
			break;
		case Xpath:
			se= new SElement(getElement().findElements(By.xpath(value)).get(index));
			break;
		case Name:
			se= new SElement(getElement().findElements(By.name(value)).get(index));
			break;
		case LinkText:
			se= new SElement(getElement().findElements(By.linkText(value)).get(index));
			break;
		case PartialLinkText:
			se= new SElement(getElement().findElements(By.partialLinkText(value)).get(index));
			break;
		case Css:
			se= new SElement(getElement().findElements(By.cssSelector(value)).get(index));
			break;
		case Class:
			se= new SElement(getElement().findElements(By.className(value)).get(index));
			break;
		case TagName:
			se= new SElement(getElement().findElements(By.tagName(value)).get(index));
			break;
		default:
			throw new MyAutoException("定位方式错误，没有找到子元素");
		}
		if(se.getElement()==null){
			throw new NoSuchElementException("["+this.getId()+"]在定位元素的时候发生了错误，很有可能是元素没有定位到！请检查自己配置的元素定位方式是否正确！");
		}
		return se;
		
	}
	/**通过by的方式来获得子元素*/
	public SElement childElement(By by,int index){
		return new SElement(getElement().findElements(by).get(index));
	}
	/**通过by的方式来活的子元素*/
	public SElement childElement(By by){
		return new SElement(getElement().findElement(by));
	}
	
	public SElement childElement(SElement se){
		return new SElement(se.getElement());
	}
	
	/**输入*/
	public void sendKeys(String text){
		Window.updateWindow();
		if(isExist()){
			getElement().sendKeys(text);
			logger.info(">>["+this.getId()+"]输入值["+text+"]成功！");
		}else{
			logger.error(">>没有找到元素，输入失败！");
			throw new MyAutoException("["+getId()+"]进行输入操作失败！很有可能的原因是在配置元素的时候发生错误。没有找到元素！");
		}
	}
	/**表单内的确认*/
	public void submit(){
		Window.updateWindow();
		if(isExist()){
			getElement().submit();
			logger.info(">>["+this.getId()+"]提交表单成功！");
		}else{
			logger.error(">>提交表单失败！元素没有找到！");
			throw new MyAutoException("["+this.getId()+"]没有找到这个元素！，进行提交操作的时候出现错误！");
		}
		
	}
	/**判断元素是否存在！*/
	public boolean isExist(){
		if(getElement()==null){
			return false;
		}else{
			return true;
		}
	}
	/**得到元素的id值*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**设置焦点在元素上面*/
	public void focus(){
		Window.updateWindow();
		AutoBase.focus(getElement());
	}
	
}
