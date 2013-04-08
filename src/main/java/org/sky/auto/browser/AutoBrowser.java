package org.sky.auto.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sky.auto.driver.AutoDriver;
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

public class AutoBrowser implements IBrowser{

	private AutoDriver<?> driver;
	
	public AutoBrowser(AutoDriver<?> driver){
		this.driver=driver;
	}
	
	@Override
	public void open(String url) {
		driver.getDriver().get(url);
	}

	@Override
	public void close() {
		driver.getDriver().quit();
	}

	@Override
	public ICurrentPage currentpage() {
		
		return null;
	}

	@Override
	public void maxWindow() {
		driver.getDriver().manage().window().maximize();
		
	}

	@Override
	public void scroll(SElement se) {
		se.scroll();
	}

	@Override
	public SElement sElement(String id) {
		return null;
	}

	@Override
	public SElement sElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SElement sElement(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table table(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table table(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckBox checkBox(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckBox checkBox(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckBox checkBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table table() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public org.sky.auto.element.Link Link() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public org.sky.auto.element.Link Link(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public org.sky.auto.element.Link Link(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RadioButton radioButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RadioButton radioButton(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RadioButton radioButton(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image image() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image image(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image image(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TextField textField(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TextField textField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TextField textField(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RichTextField richTextField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RichTextField richTextField(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RichTextField richTextField(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComoboBox comoboBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComoboBox comoboBox(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComoboBox comoboBox(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListElement listElement(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListElement listElement(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button button() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button button(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button button(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dealAlert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runJavaScript(String js) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runJavaScript(String js, Object... objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scrollTo(SElement se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectDefaultWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectFrame(By by) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectFrameByName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectFrame(WebElement we) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectWindowByTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectWindowByUrl(String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectWindowContainTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectWindowContainUrl(String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dealConfirm(boolean isYes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dealPrompt(String str, boolean isYes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectNewWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeScreenShot() {
		// TODO Auto-generated method stub
		
	}

}
