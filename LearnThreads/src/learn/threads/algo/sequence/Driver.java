package learn.threads.algo.sequence;

import java.util.Scanner;

public class Driver {
	
	private static final Object lock = new Object();
	private static Scanner scanner;

	public static void main(String[] args)  {
		System.out.println("Press any key to exit...");

		PrinterThread even = new PrinterThread(lock, true);
		PrinterThread odd = new PrinterThread(lock, false);
		even.start();
		odd.start();

		scanner = new Scanner(System.in);
		String strInput = scanner.nextLine();
		scanner.close();
		System.out.println("EXIT: with key " + strInput);
		
		even.interrupt();
		odd.interrupt();
	}
}
