package learn.menu;

public enum MenuStatus {
	RET_VAL_INVLID (4001),
	RET_VAL_QUIT (4002),
	RET_VAL_BACK (4003);

	private final int retVal;

	MenuStatus(int retVal) {
        this.retVal = retVal;
    }
    
    public int getLevelCode() {
        return this.retVal;
    }
}
