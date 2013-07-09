package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:33
 * To change this template use File | Settings | File Templates.
 */
public class RadioButton extends CheckBox {

    public RadioButton(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }

    public RadioButton(IBrowser browser) {
        super(browser);
    }
    
    public RadioButton(IBrowser browser,String cssSelector){
    	super(browser,cssSelector);
    }
    public RadioButton(IBrowser browser,String cssSelector,int index){
    	super(browser,cssSelector,index);
    }

    public boolean isChecked(){
        return super.isChecked();
    }

    public void setStatus(boolean status){
        super.setStatus(status);
    }
}
