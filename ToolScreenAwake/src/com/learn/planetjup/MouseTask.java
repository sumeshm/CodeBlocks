package com.learn.planetjup;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

public class MouseTask extends TimerTask {

	final int DELAY = 5000;

    @Override
    public void run() {
		PointerInfo info = MouseInfo.getPointerInfo(); 
		Robot robot;
		try {
			robot = new Robot();
			while (true) {
				Point point = info.getLocation();
				robot.mouseMove(point.x + 1, point.y + 1);
				System.out.println("MOVE MOUSE: ### point.x=" + point.x + ", point.y=" + point.y);
				Thread.sleep(DELAY);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void main(String args[]){
        TimerTask timerTask = new MouseTask();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 5000, 10*1000);
        System.out.println("TimerTask started");

        synchronized (timer) {
            try {
                timer.wait();
                System.out.println("TimerTask cancelled");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
    }

}