package learn.redis;

public class SomeModel {

	private String modelName;
	private String modelURL;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelURL() {
		return modelURL;
	}

	public void setModelURL(String modelURL) {
		this.modelURL = modelURL;
	}

	@Override
	public String toString() {
		return "[ SomeModel {modelName=" + modelName  + 
				", modelURL=" + modelURL +
				"} ]";
	}
}
