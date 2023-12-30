package lukfor.reports.widgets.tables;

import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class DataTableConfig {

    HashMap<String, Object> config = new HashMap<String, Object>();
    public void data(Table table){
        List<Map<String, Object>> data = new Vector<Map<String, Object>>();
        List<Map<String, Object>> columns = new Vector<Map<String, Object>>();

        for (String column: table.columnNames()){
            Map<String, Object> item = new HashMap<>();
            item.put("data",column);
            item.put("title",column);
            columns.add(item);
        }

        for (Row row: table){
            Map<String, Object> item = new HashMap<>();
            for (String column: row.columnNames()){
                item.put(column, row.getObject(column));
            }
            data.add(item);
        }
        config.put("data", data);
        config.put("columns", columns);
    }

    public HashMap<String, Object> getMap() {
        return config;
    }

}
