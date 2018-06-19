package learn.design.creational;

// Thread Safe singleton
public class Singleton {

	private static Singleton instance = null;

	private Singleton()
	{
		// do some stuff
	}

	public static Singleton getInstance()
	{
		// Double Checked Locking
		if (instance == null)
		{
			synchronized (instance) {
				// Lazy initialization
				if (instance == null)
				{
					instance = new Singleton();
				}
			}
		}
		
		return instance;
	}
}
 