package org.sky.auto.report;

import org.sky.auto.result.Environment;
import org.sky.auto.result.MyDescription;

public class CaseInfo {
	private Environment env;
	private MyDescription description;
	public Environment getEnviroment() {
		return env;
	}
	public void setEnviroment(Environment env) {
		this.env = env;
	}
	
	public CaseInfo(Environment env, MyDescription des) {
		this.env = env;
		this.setDescription(des);

	}
	public MyDescription getDescription() {
		return description;
	}
	public void setDescription(MyDescription description) {
		this.description = description;
	}
	
}
