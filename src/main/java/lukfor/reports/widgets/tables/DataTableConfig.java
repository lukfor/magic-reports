package lukfor.reports.widgets.tables;

import lukfor.reports.widgets.IWidgetConfig;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.*;

public class DataTableConfig implements IWidgetConfig {

    private List<Map<String, Object>> data = null;

    private List<Map<String, Object>> columns = null;

    public DataTableConfig() {

    }

    public DataTableConfig(HashMap<String, Object> config) {
        if (config.containsKey("data")){
            Object _data = config.get("data");
            if (_data instanceof Table) {
                setData((Table) _data);
            } else if (_data instanceof List){
                setData((List<Map<String, Object>>) _data);
            } else {
                throw new RuntimeException("Class " + data.getClass() + " not supported.");
            }
        }
        if (config.containsKey("columns")){
            Object _columns = config.get("columns");
            if (_columns instanceof List) {
                setColumns((List<Map<String, Object>>) _columns);
            }
        }
    }

    public void setData(List<Map<String, Object>> objects){
        data = objects;
    }

    public void setData(Table table){
        columns = new Vector<Map<String, Object>>();
        for (String column: table.columnNames()){
            Map<String, Object> item = new HashMap<>();
            item.put("data",column);
            item.put("title",column);
            columns.add(item);
        }

        data = new Vector<Map<String, Object>>();
        for (Row row: table){
            Map<String, Object> item = new HashMap<>();
            for (String column: row.columnNames()){
                item.put(column, row.getObject(column));
            }
            data.add(item);
        }
    }

    public void setColumns(List<Map<String, Object>> columns) {
        this.columns = columns;
    }

    public HashMap<String, Object> getOptions() {
        if (data == null) {
            throw new RuntimeException("Please specify `data`.");
        }
        if (columns == null) {
            throw new RuntimeException("Please specify `columns`.");
        }
        HashMap<String, Object> config = new HashMap<String, Object>();
        config.put("data", data);
        config.put("columns", columns);
        return config;
    }

}
