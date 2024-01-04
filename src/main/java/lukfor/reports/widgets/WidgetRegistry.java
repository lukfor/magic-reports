package lukfor.reports.widgets;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class WidgetRegistry {

    private static WidgetRegistry instance;

    private Map<String, Class<? extends IWidget>> widgets = new HashMap<>();

    public static WidgetRegistry getInstance(){
        if (instance == null){
            instance = new WidgetRegistry();
        }
        return instance;
    }

    private WidgetRegistry(){

    }

    public void register(String keyword, Class<? extends IWidget> widget){
        System.out.println("Register component " + keyword + "...");
        widgets.put(keyword, widget);
    }

    public boolean contains(String keyword){
        return widgets.containsKey(keyword);
    }

    public IWidget getInstance(String keyword){
        System.out.println("Create instance of " + keyword);
        Class<? extends IWidget> clazz = widgets.get(keyword);
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
