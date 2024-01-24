package lukfor.reports.dsl;

import groovy.lang.Closure;
import org.codehaus.groovy.runtime.InvokerHelper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParamsMap extends LinkedHashMap<String, Object> {

    public ParamsMap() {
        super();
    }

    public ParamsMap(Map<String, Object> params) {
        putAll(params);
    }

    @Override
    public Object put(String key, Object value) {
        return super.putIfAbsent(key, value);
    }

    public static ParamsMap buildFromArgs(Object args) {
        ParamsMap options = new ParamsMap();

        List list = InvokerHelper.asList(args);

        if (list.size() > 2) {
            throw new RuntimeException("More than one parameter: " + list);
        }

        if (!list.isEmpty()) {
            Object arg1 = list.get(0);
            if (!(arg1 instanceof Map) && !(arg1 instanceof Closure)) {
                throw new RuntimeException("Provided argument is not a map or closure: " + arg1.getClass());
            }

            if (arg1 instanceof Map){
                options = new ParamsMap((Map) arg1);
            } else{
                options.put("body", arg1);
            }

            if (list.size() == 2) {
                Object arg2 = list.get(1);
                if (!(arg2 instanceof Closure)) {
                    throw new RuntimeException("Body is not a closure: " + arg2.getClass());
                }
                options.put("body", arg2);
            }
        }

        return options;
    }


    public static ParamsMap buildFromArgs(List<String> unmatchedParams) {
        ParamsMap paramMap = new ParamsMap();

        // Add unmatched parameters starting with --
        if (unmatchedParams == null) {
            return paramMap;
        }

        for (int i = 0; i < unmatchedParams.size(); i++) {
            String param = unmatchedParams.get(i);
            if (param.startsWith("--")) {
                String key = param.substring(2);
                String value = (i + 1 < unmatchedParams.size()) ? unmatchedParams.get(i + 1) : null;
                paramMap.put(key, value);
                i++; // Skip the next element as it has been consumed as the value
            }
        }

        return paramMap;
    }

}
