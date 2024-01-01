package lukfor.reports.widgets.components;

import groovy.lang.Closure;
import lukfor.reports.HtmlWidgetsReport;
import lukfor.reports.widgets.HtmlBlock;
import lukfor.reports.widgets.IWidgetConfig;

public class CardConfig implements IWidgetConfig {

    private HtmlWidgetsReport report;

    private String title;

    private String description;

    private String body;

    public CardConfig(HtmlWidgetsReport report) {
        this.report = report;
    }

    public void title(String title){
        this.title = title;
    }

    public void description(String description){
        this.description = description;
    }

    public void body(Closure closure) {
        HtmlBlock block = new HtmlBlock(report, "card-body");
        block.build(closure);
        body = block.getContent();
    }

   public String getHtml(){
        String html = "<div class=\"card\"><h4>" + title + "</h4>";
        if (description != null){
            html += "<p>" + description + "</p>";
        }
        html += body + "</div>";
        return html;
   }


}
