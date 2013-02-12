package com.iso.claimsearch.test.ui.domain;

import java.io.Serializable;

public class EnvironmentCS implements Serializable{
	private static final long serialVersionUID = 9175203939736767772L;
	private String environment;
	private String url;
	private String uid;
    private String pwd; 
    
    public EnvironmentCS() 
	{	
		this.environment = "";
		this.url = "";
		this.uid = "";
		this.pwd = "";
	}	
	
    public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
