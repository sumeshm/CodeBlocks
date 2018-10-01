package com.learn.spring.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarPojo {
	private int version;
	private String model;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "CarPojo { version : " + version + ", model : " + model + " }";
	}
}