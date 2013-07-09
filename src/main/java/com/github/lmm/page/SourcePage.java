package com.github.lmm.page;

import com.github.lmm.annotation.At;
import com.github.lmm.annotation.FindBy;
import com.github.lmm.annotation.Commit;
import com.github.lmm.annotation.Title;
import com.github.lmm.browser.IBrowser;
import com.github.lmm.core.Auto;
import com.github.lmm.element.ElementManager;
import com.github.lmm.element.JSoupElement;
import com.github.lmm.source.ElementInfo;
import com.github.lmm.source.Source;
import com.github.lmm.element.Element;
import com.github.lmm.source.TempChainElement;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.NoSuchElementException;

/**
 * @author 王天庆
 */
public class SourcePage extends CurrentPage {
    private Logger logger = Logger.getLogger(SourcePage.class);
    private Document doc;
    private ElementManager elementManager;
    private Source source;
    private String pageCommit;
    public SourcePage(IBrowser browser) {
        super(browser);
        this.doc=Jsoup.parse(browser.getCurrentBrowserDriver().getPageSource());
        this.elementManager=new ElementManager();
        classAnnotationsTools();
        fieldAnnotationsTools();
    }

    public SourcePage(IBrowser browser,Source source){
        super(browser);
        classAnnotationsTools();
        this.elementManager=new ElementManager();
        loadSource(source);
        fieldAnnotationsTools();
    }

    public SourcePage(){
        this(Auto.browser());
    }

    private void loadSource(Source source){
        this.source=source;
        this.elementManager.loadPageSource(source,this);
    }

    public Element element(String id){
        return new Element(getBrowser(),this.elementManager.getTempElement(id));
    }

    public ElementManager getElementManager() {
        return elementManager;
    }

    public void setElementManager(ElementManager elementManager) {
        this.elementManager = elementManager;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getPageCommit() {
        return pageCommit;
    }

    public void setPageCommit(String pageCommit) {
        this.pageCommit = pageCommit;
    }
    
    private void classAnnotationsTools(){
    	Annotation[] annos = this.getClass().getAnnotations();
    	for(Annotation anno : annos){
    		if(anno instanceof At){
    			String tempurl = ((At) anno).value();
    			this.getBrowser().selectWindowContainsUrl(tempurl);
    		}
    		if(anno instanceof Title){
    			String temptitle = ((Title) anno).value();
    			this.getBrowser().selectWindowContainsTitle(temptitle);
    		}
    		if(anno instanceof Commit){
    			this.pageCommit=((Commit) anno).value();
    		}
    	}
    }
    
    private void fieldAnnotationsTools(){
    	Field[] fields=this.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(FindBy.class)){
                ElementInfo elementInfo=new ElementInfo();
                FindBy elementLocator=field.getAnnotation(FindBy.class);
                String commit = elementLocator.commit();
                Integer index=elementLocator.index();
                String value=elementLocator.value();
                org.jsoup.nodes.Element htmlelement = this.doc.select(value).get(index);
                if(htmlelement==null){
                	throw new NoSuchElementException("没有找到这种定位方式的元素");
                }
                JSoupElement je = new JSoupElement(htmlelement);
                By jby = By.xpath(je.toXpath());
                elementInfo.setIndex(0);
                elementInfo.setLocator(jby);
                TempChainElement tempChainElement=new TempChainElement(elementInfo);
                this.elementManager.addElement(tempChainElement);
                logger.info("收集了当前页面的注解属性元素"+commit);
            }
        }
    }

}
