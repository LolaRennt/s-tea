package com.github.lmm.element;

import com.github.lmm.browser.IBrowser;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
public class Form extends Element {
    private ListElements<Element> formElements;
    Element user;
    Element password;
    Element submit;
    public Form(IBrowser browser, TempElement tempElement) {
        super(browser, tempElement);
        this.formElements=this.getOptions("input");
        if(this.formElements.get(0).getAttribute("type").equals("text"))
            this.user=this.formElements.get(0);
        Iterator<Element> iterator=this.formElements.iterator();
        while(iterator.hasNext()){
            Element element=iterator.next();
            if(element.getAttribute("type").equals("password")){
                this.password=element;
            }else if(element.getAttribute("type").equals("submit")){
                this.submit=element;
            }
        }
    }

    public List<Element> getAllInputs(){
        return this.formElements.getAllElements();
    }
    public Form(IBrowser browser) {
        super(browser);
    }

    public Set<IElement> addElement(){
          return null;
    }

    public void user(String username){
        this.user.input(username);
    }

    public void password(String password){
        this.password.input(password);
    }

    public void submit(String user,String password){
        user(user);
        password(password);
        this.submit.submit();
    }

    public void init(String json){

    }
}
