package com.github.lmm.story;

import java.lang.reflect.Method;
import java.util.Stack;

public class Steps {
	private Stack<Method> steps;
	public Steps(){
		this.steps=new Stack<Method>();
	}
	
	public Steps addStep(Method m){
		this.steps.add(m);
		return this;
	}
}
