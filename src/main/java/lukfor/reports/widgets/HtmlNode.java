package lukfor.reports.widgets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class HtmlNode {

    private String tag;

    private Map<String, String> attributes = new HashMap<String, String>();

    private String content;

    private List<HtmlNode> childs = new Vector<HtmlNode>();

    public HtmlNode(String tag){
        this. tag = tag;
    }

    public void setAttribute(String attribute, String value){
        attributes.put(attribute, value);
    }

    public void addChild(HtmlNode node){
        childs.add(node);
    }

    public List<HtmlNode> getChilds() {
        return childs;
    }

    public String getTag() {
        return tag;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
