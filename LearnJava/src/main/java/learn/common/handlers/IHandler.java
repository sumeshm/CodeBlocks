package learn.common.handlers;

import learn.common.Menu;

public interface IHandler {
	public Menu getMenu();
	
	public IHandler handle(int index);
}
