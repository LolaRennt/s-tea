package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
public class AjaxElement extends Element{

    public AjaxElement(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
    }

    public AjaxElement(IBrowser browser) {
        super(browser);
    }
}
