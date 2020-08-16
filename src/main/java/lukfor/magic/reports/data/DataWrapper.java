package lukfor.magic.reports.data;

import com.google.gson.Gson;

public class DataWrapper {

	private Object object;

	public DataWrapper(Object object) {
		this.object = object;
	}

	public String json() {
		if (object == null) {
			return "undefined";
		} else {
			Gson gson = new Gson();
			return gson.toJson(object);
		}
	}

	public String toString() {
		if (object != null) {
			return object.toString();
		} else {
			return "undefined";
		}
	}

}
