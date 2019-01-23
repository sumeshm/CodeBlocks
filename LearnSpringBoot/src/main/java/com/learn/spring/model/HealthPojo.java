package com.learn.spring.model;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HealthPojo {

	private Map<String, Boolean> healthMap;

	public Map<String, Boolean> getHealthMap() {
		return healthMap;
	}

	public void setHealthMap(Map<String, Boolean> healthMap) {
		this.healthMap = healthMap;
	}

	public void addToMap(String name, boolean isUp) {
		if (null == healthMap) {
			healthMap = new LinkedHashMap<>();
		}
		
		healthMap.put(name, isUp);
	}

	@Override
	public String toString() {
		return "HealthPojo { healthMap : " + healthMap.toString() + " }";
	}
}