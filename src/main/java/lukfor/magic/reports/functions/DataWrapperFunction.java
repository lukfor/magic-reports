package lukfor.magic.reports.functions;

import java.util.function.Function;

import lukfor.magic.reports.data.DataWrapper;

public class DataWrapperFunction implements Function<Object, DataWrapper> {

	@Override
	public DataWrapper apply(Object object) {
		return new DataWrapper(object); 
	}

}
