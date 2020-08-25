package lukfor.reports.functions;

import java.util.function.Function;

import lukfor.reports.data.ArrayWrapper;

public class ArrayHelperFunction implements Function<Object, ArrayWrapper> {

	@Override
	public ArrayWrapper apply(Object object) {
		return new ArrayWrapper(object); 
	}

}
