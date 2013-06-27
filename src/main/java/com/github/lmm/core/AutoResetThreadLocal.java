package com.github.lmm.core;




public class AutoResetThreadLocal<T> extends ThreadLocal<T>{

	public AutoResetThreadLocal() {
		super();
	}

	public void set(T initialValue){
		ThreadLocalRegistry.registerThreadLocal(this);
		super.set(initialValue);
	}
	
	@Override
	protected T initialValue() {
		ThreadLocalRegistry.registerThreadLocal(this);
		return super.initialValue();
	}
	
}
