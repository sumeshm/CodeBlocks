package learn.algo.interview;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class WordSetFinder {

	private Scanner scanner;
	
	private final String UP = "AT";
	private final String DOWN = "BAT";
	private final String LEFT = "CAT";
	private final String BOTTOM = "DAY";
	
	private WordTracker up = new WordTracker(UP);
	private WordTracker down = new WordTracker(DOWN);
	private WordTracker top = new WordTracker(LEFT);
	private WordTracker bottom = new WordTracker(BOTTOM);

	private TreeSet<WordTracker> sortedList;
	
	public WordSetFinder()
	{
		System.out.println("A paragraph has several occurances of four words (AT, BAT, CAT, DAY), find shortest span of words where all four words occur");
		System.out.println("e.g. AT blah blah bla CAT blah AT blah blah BAT blah blah blah DAY blah AT blah CAT blah blah blah");
		System.out.println("\t [AT blah blah bla CAT blah AT blah blah BAT blah blah blah DAY] blah AT blah CAT blah blah blah ==> span = 14 words");
		System.out.println("\t AT blah blah bla CAT blah AT blah blahh [BAT blah blah blah DAY blah AT blah CAT] blah blah blah ==> span = 9 words");
		System.out.println("\t etc...");
	}
	
	public void start()
	{
		System.out.println("\n\n Enter your paragraph:");

		// read sentence
		scanner = new Scanner(System.in);
		String input = scanner.nextLine().trim();

		// create array and populate custom data strucutre
		int index = 0;
		StringTokenizer tokenizer = new StringTokenizer(input, " ");
		while (tokenizer.hasMoreTokens())
		{
			String current = tokenizer.nextToken();
			if (current.equalsIgnoreCase(UP))
			{
				up.add(index);
			}
			else if (current.equalsIgnoreCase(DOWN))
			{
				down.add(index);
			}
			else if (current.equalsIgnoreCase(LEFT))
			{
				top.add(index);
			}
			else if (current.equalsIgnoreCase(BOTTOM))
			{
				bottom.add(index);
			}
			
			index++;
		}

		// start creating sets
		createSets();
	}

	protected void createSets()
	{
		int startIndex = -1;
		int endIndex = -1;
		int smallestLength = -1;
		while (true)
		{
			// prefered way would be to maintain simple Vector and call custom/optimized sort algorithm
			sortedList = new TreeSet<WordTracker>();
			sortedList.add(up);
			sortedList.add(down);
			sortedList.add(top);
			sortedList.add(bottom);
			
			printState();

			int small = sortedList.first().peek().intValue();
			int large = sortedList.last().peek().intValue();
			if (small == -1 || large == -1)
			{
				break;
			}

			int length = large - small + 1;
			System.out.println("\t Span= [" + small + " to " + large + "] of lenght= " + length  + "\n");

			if (smallestLength == -1 || smallestLength > length)
			{
				smallestLength = length;
				startIndex = small;
				endIndex = large;
			}

			sortedList.first().pop();
		}

		System.out.println("\n FINAL RESULT: smallest span");
		System.out.println("\t start-index = " + startIndex);
		System.out.println("\t end-index = " + endIndex);
		System.out.println("\t lenght = " + smallestLength);
	}
	
	private void printState() {
		Iterator<WordTracker> iterator = sortedList.iterator();
		while (iterator.hasNext())
		{
			iterator.next().printData();
		}
	}
	
	
	
	/*
	 * This class will track one word and all its occurrences within a paragraph
	 */
	public class WordTracker implements Comparator<WordTracker>, Comparable<WordTracker> {

		private String word;

		LinkedList<Integer> indexQueue;

		public WordTracker(String word) {

			this.word = word;
			indexQueue = new LinkedList<Integer>();
		}

		public String getWord() {
			return this.word;
		}

		public Integer peek() {
			if (!indexQueue.isEmpty()) {
				return indexQueue.get(0);
			} else {
				return -1;
			}
		}

		public void pop() {
			if (!indexQueue.isEmpty()) {
				indexQueue.removeFirst();
			}
		}

		public void add(int index) {
			indexQueue.addLast(new Integer(index));
		}

		public void printData() {
			System.out.println(" \"" + word + "\" --> " + indexQueue);
		}

		@Override
		public int compareTo(WordTracker arg0) {
			return this.peek().compareTo(arg0.peek());
		}

		@Override
		public int compare(WordTracker arg0, WordTracker arg1) {
			return (arg0.peek().intValue() - arg1.peek().intValue() + arg0.getWord().compareTo(arg1.getWord()));
		}
	}

	public static void main(String[] args) {
		WordSetFinder manager = new WordSetFinder();
		manager.start();
	}
}
