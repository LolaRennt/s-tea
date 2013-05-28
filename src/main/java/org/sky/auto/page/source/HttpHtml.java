package org.sky.auto.page.source;

import java.util.List;

public abstract class HttpHtml {
	/**
	 * @return 返回页面的实体内容，就是html的所有内容
	 * */
	abstract public String getContext();
	/**
	 * @return 返回页面的所有的csslink连接
	 * */
	abstract public List<String> getCssLink();
	/**
	 * @return 返回页面所有的js
	 * */
	abstract public List<String> getJavaScriptURL();
}
