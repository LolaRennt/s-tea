package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.runtime.RuntimeMethod;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:33
 * To change this template use File | Settings | File Templates.
 */
public class CheckBox extends Element {
    private Logger logger = Logger.getLogger(CheckBox.class);
    public CheckBox(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }

    public CheckBox(IBrowser browser) {
        super(browser);
    }
    public CheckBox(IBrowser browser,String cssSelector){
    	super(browser,cssSelector);
    }
    public CheckBox(IBrowser browser,String cssSelector,int index){
    	super(browser,cssSelector,index);
    }

    public boolean isCheck(){
        return false;
    }

    /**
     * 检验这个checkBox多选框是否被选中
     * @return true被选中
     * 	       false没有被选中
     * */
    public boolean isChecked(){
        if(isExist()){
            return getElement().isSelected();
        }else{
            logger.error("["+ RuntimeMethod.getName()+"]"+"元素不存在，校验失败！");
            throw new NoSuchElementException("["+this.getId()+"]判断元素是否被选中的时候出现了错误，可能的原因是这个元素没有被找到！");
        }


    }

    /**设置多选框的状态，true是选中，false是取消选中
     * @param status 设置选中状态
     * */
    public void setStatus(boolean status){
        if(isExist()){
            if(getElement().isSelected()!=status){
                getElement().click();
            }
        }else{
            logger.error("["+RuntimeMethod.getName()+"]"+"没有找到元素，设定值失败！");
            throw new NoSuchElementException("["+this.getId()+"]设置状态的时候出现了错误，可能的原因是这个元素没有被找到！");
        }

    }
}
