package org.sky.auto.browser.element;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sky.auto.browser.IBrowser;

public class Table extends PageContorl{
	
	static Logger logger =Logger.getLogger(Table.class);
	public Table(IBrowser browser) {
		super(browser);
	}
	
	public Table(IBrowser browser,WebElement element) {
		super(browser,element);
	}
	
	/**
	 *获得table的第row行，col列的值 
	 * 行号和列号都从0开始
	 * @param row 行号
	 * @param col 列号
	 * @return 此table的指定行，指定列的值
	 * */
	public String getTableContent(int row,int col){
		String content=null;
		if(isExist()){
			try{
				content=getLocatorValue().findElement(By.xpath(".//tr["+(row)+"]/td{"+(col)+"]")).getText();
				logger.info("第"+(row+1)+"行，第"+(col+1)+"列的元素获取成功！");
			}catch(Exception e){
				logger.info("第"+(row+1)+"行，第"+(col+1)+"列的元素获取失败！找不到这个table元素！");
			}
			return content;
		}else{
			logger.error("第"+(row+1)+"行，第"+(col+1)+"列的元素获取成功！");
			return null;
		}
		
	}
	
	/**
	 * 获得此table第col列所有行的值，列号从0开始
	 * @param col列号
	 * @return 字符串list，col列的所有行的信息
	 * */
	public List<String> getTableContentByColumn(int col){
		try{
			List<String> contents =new ArrayList<String>();
			List<WebElement> cells=getLocatorValue().findElements(By.xpath(".//td{"+(col+1)+"]"));
			for(WebElement cell : cells){
				contents.add(cell.getText());
			}
			logger.info("第"+col+"列的信息获得成功！");
			return contents;
		}catch(Exception e){
			logger.error("第"+col+"列的信息获得失败！！");
		}
		return null;
	}
	/**
	 * 获得此table的所有行和所有列的值
	 * @return 获得所有行和所有列的值，如果table是空的，那么返回null
	 * */
	public List<List<String>> getAllTableContext(){
		List<List<String>> contents = new ArrayList<List<String>>();
		int rowCount=0;
		if(isExist()){
			try{
				while(true){
					if(getLocatorValue().findElements(By.xpath(".//tr["+(rowCount+1)+"]")).size()==0){
						break;
					}
					contents.add(getTableContentByRow(rowCount));
					rowCount++;
					logger.info("第"+rowCount+"行的所有信息获得成功！");
				}
			}catch(Exception e){
				logger.info("第"+rowCount+"行的所有信息获得失败！没有找到这个table内的要查找的元素！");
			}
			return contents;
		}else{
			logger.info("第"+rowCount+"行的所有信息获得失败！没有找到这个table元素！");
			return null;
		}
		
	}
	/**
	 * 获得table第row行所有的值，行号从0开始
	 * @param row 行号
	 * @return 字符串list，row列所有的信息
	 * */
	private List<String> getTableContentByRow(int row) {
		List<String> contents=new ArrayList<String>();
		if(isExist()){
			try{
				WebElement rowCell = getLocatorValue().findElement(By.xpath(".//tr["+(row+1)+"]"));
				List<WebElement> cells  = rowCell.findElements(By.xpath(".//td"));
				for(WebElement cell : cells){
					contents.add(cell.getText());
				}
				logger.info("第"+row+"行的信息获得成功！");
			}catch(Exception e){
				logger.info("第"+row+"行的信息获得失败！找不到table内的要查找的的元素！");
			}
			return null;
		}else{
			logger.info("第"+row+"行的信息获得失败！找不到这个table的元素！");
			return null;
		}
	
	}
	
	
	
	
}
