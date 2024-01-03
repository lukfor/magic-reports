package lukfor.reports.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataWrapper {

	private Object object;

	public DataWrapper(Object object) {
		this.object = object;
	}

	public String json() {
		if (object == null) {
			return "undefined";
		} else {
			GsonBuilder builder = new GsonBuilder();
			builder.serializeNulls();
			Gson gson = builder.create();
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
