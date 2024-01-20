package lukfor.reports.widgets;

import java.util.HashMap;
import java.util.Map;

public class ComponentRegistry {

    private static ComponentRegistry instance;

    private Map<String, Component> components = new HashMap<>();

    public static ComponentRegistry getInstance(){
        if (instance == null){
            instance = new ComponentRegistry();
        }
        return instance;
    }

    private ComponentRegistry(){

    }

    public void register(String keyword,Component component){
        //System.out.println("Register custom component " + keyword + "...");
        components.put(keyword, component);
    }

    public boolean contains(String keyword){
        return components.containsKey(keyword);
    }

    public Component getInstance(String keyword){
        return components.get(keyword);
    }

}
