package learn;

import learn.redis.RedisTester;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Well well....here we go Redis...");

		RedisTester util = new RedisTester();
		util.getSomeModelList();
		util.updateSomeModel();
	}
}
