package com.xtgj.j2ee.chapter06.entity;


import java.io.Serializable;

public class JD implements Serializable {
	private Integer jdid=1;
	private String jd;
	

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public int getJdid() {
		return jdid;
	}

	public void setJdid(int jdid) {
		this.jdid = jdid;
	}
}

