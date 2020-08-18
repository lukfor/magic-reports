package lukfor.magic.reports.data;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

public class ArrayWrapper {

	private Object object;

	public ArrayWrapper(Object object) {
		this.object = object;
	}

	public List<Object> extract(String property)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if (object == null) {
			return new Vector<Object>();
		} else {

			Iterable<Object> iterarable = (Iterable<Object>) object;

			List<Object> result = new Vector<Object>();

			for (Object _o : iterarable) {
				Field field = _o.getClass().getField(property);
				Object value = field.get(_o);
				result.add(value);
			}

			return result;
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
