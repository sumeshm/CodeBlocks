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
	private IUpdateListener listener;

	public MouseThread(int minutes, IUpdateListener listener) {
		isDone = new AtomicBoolean(false);
		this.MAX_COUNT = minutes;
		this.listener = listener;
	}

	public void setDone() {
		this.isDone.set(true);
		interrupt();
	}

	@Override
	public void run() {
		System.out.println("START MOUSE: ### minutes=" + MAX_COUNT);

		try {
			Robot robot = new Robot();
			while (isDone.get() == false && isCountValid(count++)) 
			{
				PointerInfo info = MouseInfo.getPointerInfo(); 
				Point point = info.getLocation();
				robot.mouseMove(point.x + 1, point.y + 1);
				System.out.println("MOVE MOUSE: ### point.x=" + point.x + ", point.y=" + point.y);
				Thread.sleep(DELAY);
				listener.timeUpdate(new Integer(count), new Integer(MAX_COUNT));
			}
		} catch (AWTException e) {
			System.err.println("UI ERROR: " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("STOP MOUSE FORCED: ###");
		}

		listener.timeCompleted();
		System.out.println("STOP MOUSE: ###");
	}

	private Boolean isCountValid(int count)
	{
		if (MAX_COUNT < 0)
		{
			return Boolean.FALSE;
		}
		else if (MAX_COUNT == 0 || count < MAX_COUNT)
		{
			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}
}
