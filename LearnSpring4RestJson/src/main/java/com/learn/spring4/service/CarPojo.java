package com.learn.spring4.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarPojo {
	private int version;
	private String model;

	@XmlElement
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@XmlElement
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public CarPojo(int version, String model) {
		super();
		this.version = version;
		this.model = model;
	}

	public CarPojo() {
		super();
	}
}