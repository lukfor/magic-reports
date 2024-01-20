package lukfor.reports.widgets.plots;

import groovy.lang.Closure;
import lukfor.reports.widgets.IWidgetConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class PlotlyConfig implements IWidgetConfig {

    private List<HashMap<String, Object>> traces = new Vector<HashMap<String, Object>>();

    private Map<String, Object> layout = new HashMap<String, Object>();

    public void trace(HashMap<String, Object> params){
        traces.add(params);
    }

    public void layout(HashMap<String, Object> params){
        layout.putAll(params);
    }

    public HashMap<String, Object> getMap() {
        HashMap<String, Object> config = new HashMap<String, Object>();
        config.put("traces", traces);
        config.put("layout", layout);
        return config;
    }

    public void scatter(HashMap<String, Object> params){
        HashMap<String, Object> defaultParams = new HashMap<String, Object>();
        defaultParams.put("type", "scatter");
        defaultParams.put("mode", "markers");
        defaultParams.putAll(params);
        parseXY(defaultParams);
        traces.add(defaultParams);
    }

    public void box(HashMap<String, Object> params) {
        HashMap<String, Object> defaultParams = new HashMap<String, Object>();
        defaultParams.put("type", "box");
        defaultParams.putAll(params);
        traces.add(defaultParams);
    }

    public void histogram(HashMap<String, Object> params){
        HashMap<String, Object> defaultParams = new HashMap<String, Object>();
        defaultParams.put("type", "histogram");
        defaultParams.putAll(params);
        traces.add(defaultParams);
    }

    public void bar(HashMap<String, Object> params){
        HashMap<String, Object> defaultParams = new HashMap<String, Object>();
        defaultParams.put("type", "bar");
        defaultParams.putAll(params);
        parseXY(defaultParams);
        traces.add(defaultParams);
    }
    public List<HashMap<String, Object>> getTraces() {
        return traces;
    }

    public Map<String, Object> getLayout() {
        return layout;
    }

    private void parseXY(HashMap<String, Object> params) {
        Object data = params.get("xy");
        if (data != null){
            if (data instanceof  Map){
                Map map = (Map) data;
                List x = new Vector();
                List y = new Vector();
                for (Object key: map.keySet()){
                    x.add(key);
                    y.add(map.get(key));
                }
                if (params.containsKey("x")){
                    throw new RuntimeException("property xy not supported. x already set.");
                }
                params.put("x", x);
                if (params.containsKey("y")){
                    throw new RuntimeException("property xy not supported. y already set.");
                }
                params.put("y", y);
                params.remove("xy");
            } else {
                throw new RuntimeException("property xy doesn't support object of type " + data.getClass());
            }
        }
    }
}
