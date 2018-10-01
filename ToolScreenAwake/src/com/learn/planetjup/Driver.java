package com.learn.planetjup;

public class Driver {

	public static void main(String[] args) {
		MouseGui gui = new MouseGui();

		if (args.length > 0 && args[0].equalsIgnoreCase("0"))
		{
			gui.awake();
		}
	}
}
