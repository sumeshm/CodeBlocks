package learn.ds.util;

public class TemplateSample<T> {
	private T t;

	public TemplateSample(T t) {
		this.t = t;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public void showType() {
		System.out.println("\nI'm of type " + t.getClass().getSimpleName());
		System.out.println("My value: " + t);
	}

	public static void main(String[] args) {
		TemplateSample<String> gt = new TemplateSample<String>("Hello");
		TemplateSample<Integer> gt2 = new TemplateSample<Integer>(10);

		gt.showType();
		gt2.showType();
	}
}