package lukfor.reports.widgets.plots;

import groovy.lang.Closure;
import lukfor.reports.widgets.IWidgetConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class PlotlyConfig implements IWidgetConfig {

    List<HashMap<String, Object>> traces = new Vector<HashMap<String, Object>>();

    Map<String, Object> layout = new HashMap<String, Object>();

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

}
