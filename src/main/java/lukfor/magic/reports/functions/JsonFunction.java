package lukfor.magic.reports.functions;

import java.util.function.Function;

import lukfor.magic.reports.data.DataWrapper;

public class JsonFunction implements Function<Object, String> {

	@Override
	public String apply(Object object) {
		return new DataWrapper(object).json(); 
	}

}
