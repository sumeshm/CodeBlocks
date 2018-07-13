package com.learn.planetjup;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.util.concurrent.atomic.AtomicBoolean;

public class MouseThread extends Thread {
	
	private final int DELAY = 30 * 1000;
	private final int MAX_COUNT;
	private int count = 0;
	private AtomicBoolean isDone;

	public MouseThread(int minutes) {
		isDone = new AtomicBoolean(false);
		this.MAX_COUNT = minutes;
	}

	public void setDone() {
		this.isDone.set(true);
		interrupt();
	}

	@Override
	public void run() {
		System.out.println("START MOUSE: ### minutes=" + MAX_COUNT);

		Robot robot;
		try {
			robot = new Robot();
			while (isDone.get() == false && count++ < MAX_COUNT) 
			{
				PointerInfo info = MouseInfo.getPointerInfo(); 
				Point point = info.getLocation();
				robot.mouseMove(point.x + 1, point.y + 1);
				System.out.println("MOVE MOUSE: ### point.x=" + point.x + ", point.y=" + point.y);
				Thread.sleep(DELAY);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("STOP MOUSE FORCED: ###");
		}
		
		System.out.println("STOP MOUSE: ###");
	}
}
