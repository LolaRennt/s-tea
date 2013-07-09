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
public class RichTextField extends Element {
    private Logger logger = Logger.getLogger(RichTextField.class);
    public RichTextField(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }

    public RichTextField(IBrowser browser) {
        super(browser);
    }
    
    public RichTextField(IBrowser browser,String cssSelector){
    	super(browser,cssSelector);
    }
    public RichTextField(IBrowser browser,String cssSelector,int index){
    	super(browser,cssSelector,index);
    }

    /**在富文本框里面输入内容*/
    public void setText(String text){
        if(isExist()){
            String js="contentWindow.document.body.innerText=\'"+text+"\';";
            getBrowser().runJavaScript(js);
        }else{
            logger.error("["+RuntimeMethod.getName()+"]"+">>["+this.getId()+"]在富文本框内输入内容失败！");
            throw new NoSuchElementException("["+ RuntimeMethod.getName()+"]"+"["+this.getId()+"]进行输入的时候出现错误，可能这个元素没有定位正确，元素不存在！");
        }
    }
    public void clearBodyText(){
        if(isExist()){
            String js="contentWindow.document.body.innerText=\'\';";
            getBrowser().runJavaScript(js);
            logger.info("["+RuntimeMethod.getName()+"]"+">>["+this.getId()+"]富文本框内容清理成功！");
        }else{
            logger.error("["+RuntimeMethod.getName()+"]"+"找到元素失败！清理失败！");
            throw new NoSuchElementException("["+RuntimeMethod.getName()+"]"+"["+this.getId()+"]进行清除操作的时候失败了，可能是这个元素定位失败！元素不存在！");
        }
    }
}
