package org.sky.auto.page.source;

import java.util.List;

public abstract class HttpHtml {
	/**
	 * @return 返回页面的实体内容，就是html的所有内容
	 * */
	abstract public String getContext();
	/**
	 * @return 返回请求页面的响应码
	 * */
	abstract public int getStatusCode();
	/**
	 * @return 返回页面的所有的csslink连接
	 * */
	abstract public List<String> getCssLink();
	/**
	 * @return 返回页面所有的js
	 * */
	abstract public List<String> getJavaScriptURL();
	/**
	 * 判断页面是否被压缩过
	 * */
	abstract boolean isGzip();
}
