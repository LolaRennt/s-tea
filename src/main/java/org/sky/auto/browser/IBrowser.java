package org.sky.auto.browser;

import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sky.auto.element.Button;
import org.sky.auto.element.CheckBox;
import org.sky.auto.element.ComoboBox;
import org.sky.auto.element.Image;
import org.sky.auto.element.ListElement;
import org.sky.auto.element.RadioButton;
import org.sky.auto.element.RichTextField;
import org.sky.auto.element.SElement;
import org.sky.auto.element.Table;
import org.sky.auto.element.TextField;

public interface IBrowser {
	public void open(String str);
	public void close();
	public interface ICurrentPage{
		public String getTitle();
		public String getUrl();
		public String getPageSource();
		public Header[] getHeaders();
		public String getHeaderValue();
		public Map<String,String> getHeaderMap();
		/**获取外部引用的js*/
		public List<String> getJavaScripts();
		public List<String> getCssLink();
		public boolean isGzip();
		public boolean close();
		public IBrowser browser();
	}
	
	public ICurrentPage currentpage();
	public void maxWindow();
	public void scroll(SElement se);
	public SElement sElement(String id);
	public SElement sElement();
	public SElement sElement(By by);
	public Table table(String id);
	public Table table(By by);
	public CheckBox checkBox(String id);
	public CheckBox checkBox(By by);
	public CheckBox checkBox();
	public Table table();
	public org.sky.auto.element.Link Link();
	public org.sky.auto.element.Link Link(String id);
	public org.sky.auto.element.Link Link(By by);
	public RadioButton radioButton();
	public RadioButton radioButton(String id);
	public RadioButton radioButton(By by);
	public Image image();
	public Image image(String id);
	public Image image(By by);
	public TextField textField(String id);
	public TextField textField();
	public TextField textField(By by);
	public RichTextField richTextField();
	public RichTextField richTextField(String id);
	public RichTextField richTextField(By by);
	public ComoboBox comoboBox();
	public ComoboBox comoboBox(String id);
	public ComoboBox comoboBox(By by);
	public ListElement listElement(String id);
	public ListElement listElement(By by);
	public Button button();
	public Button button(String id);
	public Button button(By by);
	public void back();
	public void dealAlert();
	public void runJavaScript(String js);
	public void runJavaScript(String js,Object...objects );
	public void scrollTo(SElement se);
	public void selectDefaultWindow();
	public void selectFrame(By by);
	public void selectFrameByName(String name);
	public void selectFrame(WebElement we);
	public void selectWindowByTitle(String title);
	public void selectWindowByUrl(String url);
	public void selectWindowContainTitle(String title);
	public void selectWindowContainUrl(String url);
	public void refresh();
	public void dealConfirm(boolean isYes);
	public void dealPrompt(String str,boolean isYes);
	public void selectNewWindow();
	public void takeScreenShot();
}
