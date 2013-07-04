package org.sky.auto.base;

import com.github.lmm.core.ConfigParser;

public class Configure {
	private String basename;
	//private String browserpath;
	private String logdir;
	private String errorLogDir;
	private String screenShotDir;
	private String nodeIE;
	private String nodeFirefox;
	private String nodeSafari;
	private String nodeHtmlUnit;
	private String nodeChrome;
	
	public String getBasename() {
		if(ConfigParser.getBaseName()!=""&&ConfigParser.getBaseName()!=null){
			return ConfigParser.getBaseName();
		}else{
			return basename;
		}
	}
	public void setBasename(String basename) {
		this.basename=basename;
		
	}
	public String getLogdir() {
		return logdir;
	}
	public void setLogdir(String logdir) {
		this.logdir = logdir;
	}
	public String getErrorLogDir() {
		if(ConfigParser.getBaseName()!=""&&ConfigParser.getBaseName()!=null){
			return ConfigParser.getErrorLogDir();
		}else{
			return errorLogDir;
		}
	}
	public void setErrorLogDir(String errorLogDir) {
		this.errorLogDir = errorLogDir;
	}
	public String getScreenShotDir() {
		if(ConfigParser.getBaseName()!=""&&ConfigParser.getBaseName()!=null){
			return ConfigParser.getScreenShotPath();
		}else{
			return screenShotDir;
		}
	}
	public void setScreenShotDir(String screenShotDir) {
		this.screenShotDir = screenShotDir;
	}
	public String getNodeIE() {
		if(ConfigParser.getBaseName()!=""&&ConfigParser.getBaseName()!=null){
			return ConfigParser.getIENode();
		}else{
			return nodeIE;
		}
	}
	public void setNodeIE(String nodeIE) {
		this.nodeIE = nodeIE;
	}
	public String getNodeFirefox() {
		if(ConfigParser.getBaseName()!=""&&ConfigParser.getBaseName()!=null){
			return ConfigParser.getFirefoxNode();
		}else{
			return nodeFirefox;
		}
	}
	public void setNodeFirefox(String nodeFirefox) {
		this.nodeFirefox = nodeFirefox;
	}
	public String getNodeSafari() {
		if(ConfigParser.getBaseName()!=""&&ConfigParser.getBaseName()!=null){
			return ConfigParser.getSafariNode();
		}else{
			return nodeSafari;
		}
	}
	public void setNodeSafari(String nodeSafari) {
		this.nodeSafari = nodeSafari;
	}
	public String getNodeHtmlUnit() {
		if(ConfigParser.getBaseName()!=""&&ConfigParser.getBaseName()!=null){
			return ConfigParser.getHtmlUnitNode();
		}else{
			return nodeHtmlUnit;
		}
	}
	public void setNodeHtmlUnit(String nodeHtmlUnit) {
		this.nodeHtmlUnit = nodeHtmlUnit;
	}
	public String getNodeChrome() {
		if(ConfigParser.getBaseName()!=""&&ConfigParser.getBaseName()!=null){
			return ConfigParser.getChromeNode();
		}else{
			return nodeChrome;
		}
	}
	public void setNodeChrome(String nodeChrome) {
		this.nodeChrome = nodeChrome;
	}
	
	
	
}
