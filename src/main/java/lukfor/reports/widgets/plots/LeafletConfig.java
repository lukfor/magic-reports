package lukfor.reports.widgets.plots;

import lukfor.reports.widgets.IWidgetConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class LeafletConfig implements IWidgetConfig {

    private List<Double[]> markers = new Vector<Double[]>();

    public void marker(HashMap<String, Object> params){
        HashMap<String, Object> defaultParams = new HashMap<String, Object>();
        if (!params.containsKey("lat")){
            throw new RuntimeException("property 'lat' missing");
        }
        if (!params.containsKey("long")){
            throw new RuntimeException("property 'long' missing");
        }
        double _lat = Double.parseDouble(params.get("lat").toString());
        double _long = Double.parseDouble(params.get("long").toString());
        markers.add(new Double[]{_lat, _long});
    }

    public List<Double[]> getMarkers() {
        return markers;
    }
}
