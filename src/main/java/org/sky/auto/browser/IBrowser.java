package org.sky.auto.browser;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


public interface IBrowser {
	public void selectDefaultWindow();
	public WebDriver browser();
	public void open(String url);
	public void scrollTo(WebElement element);
	public void scrollTo(Contorl c);
	public void frame(Contorl c);
	public void back();
	public void refresh();
	public void focusWindow(String url);
	public void focusWindowByTitle(String title);
	public void frame(WebElement element);
	public void frame(int index);
	public void frame(String name);
	public void closeAllWindow();
	public void closeCurrentWindow();
	public void runJavaScript(String js);
	public void runJavaScript(String js,Object...args);
	public void currentPageTitle();
	public void currentPageUrl();
	public void dealAlter();
	public String dealPrompt(String input,boolean isYes);
	public String dealConfirm(boolean isYes);
	public void takeScreenShot();
	public void maxWindow();
	public org.sky.auto.browser.page.Page page();
	public WebElement element(String id);
	public Contorl Contorl(String id);
	public void setLogProperties(String path);
	public void setDefaultLogConfigure();
	public ListElement listElement(String id);
	public Button button(String id);
	public CheckBox checkBox(String id);
	public ComoboBox comoboBox(String id);
	public Image image(String id);
	public Link link(String id);
	public RadioButton radioButton(String id);
	public RichTextField richTextField(String id);
	public Table table(String id);
	public TextField textField(String id);
	public IActions action();
//	public WebDriver driver();
	public interface IActions{
		/**按下指定键操作*/
		public void keyDown(Keys key);
		/**在某个元素上面进行指定键的按下操作*/
		public void keyDown(WebElement element,Keys key);
		/**点击某个元素*/
		public void click(WebElement element);
		/**拖拽某个元素*/
		public void dragAndDrop(WebElement fromElement,WebElement toElement);
		/**拖拽某个元素到指定的位置*/
		public void dragAndDrop(WebElement fromElement,int xlocation, int ylocation);
		/**将按下的键松开*/
		public void keyUp(Keys key);
		/**在指定的元素上面进行按键松开*/
		public void keyUp(WebElement element,Keys key);
		/**获取元素的焦点，主要是输入框的焦点*/
		public void focus(WebElement element);
		/**按下鼠标左键在指定的元素位置上*/
		public void leftMouseDown(WebElement element);
		/**松开在指定元素位置的鼠标左键*/
		public void leftMouseUp(WebElement onElement);
		/**在指定元素的位置双击操作*/
		public void doubleClick(WebElement element);
		/**在指定的元素处输入*/
		public void sendKeys(WebElement element,java.lang.CharSequence...charSequences);
		/**将鼠标移动到指定的元素位置上面*/
		public void moveToElement(WebElement element);
	}
}
