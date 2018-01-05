package learn.common;

public interface IHandler {
	public Menu getMenu();
	
	public IHandler handle(int index);
}
