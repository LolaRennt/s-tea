package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.runtime.RuntimeMethod;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:31
 * To change this template use File | Settings | File Templates.
 */
public class TextField extends Element {
    private Logger logger = Logger.getLogger(TextField.class);
    public TextField(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }


    public TextField(IBrowser browser) {
        super(browser);
    }

    public void setText(String text){
        if(isExist()){
            getElement().clear();
            getElement().sendKeys(text);
            logger.info("["+RuntimeMethod.getName()+"]"+">>["+this.getId()+"]输入成功！");
        }else{
            logger.error("["+RuntimeMethod.getName()+"]"+">>["+this.getId()+"]找不到元素！输入值失败！");
        }
    }

    public void clear(){
        if(isExist()){
            getElement().clear();
            logger.info("["+ RuntimeMethod.getName()+"]"+">>["+this.getId()+"]输入框清理值成功了！");
        }else{
            logger.error("["+RuntimeMethod.getName()+"]"+"查找元素失败！没有找到元素！");
        }
    }

    @Override
    public String getText(){
        String content=null;
        if(isExist()){
            content=getElement().getAttribute("value");
            logger.info("["+RuntimeMethod.getName()+"]"+">>["+this.getId()+"]获得输入框文本内容成功");
        }else{
            logger.error("["+RuntimeMethod.getName()+"]"+"没有找到元素，获得值失败！");
        }
        return content;
    }

}
