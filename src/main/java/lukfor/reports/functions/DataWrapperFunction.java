package lukfor.reports.functions;

import java.util.function.Function;

import lukfor.reports.data.DataWrapper;

public class DataWrapperFunction implements Function<Object, DataWrapper> {

	@Override
	public DataWrapper apply(Object object) {
		return new DataWrapper(object); 
	}

}
