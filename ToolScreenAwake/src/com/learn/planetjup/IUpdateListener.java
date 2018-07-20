package com.learn.planetjup;

public interface IUpdateListener {

	public void timeUpdate(Integer lapsedTime, Integer maxTime);
	
	public void timeCompleted();
}
