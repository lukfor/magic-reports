package lukfor.magic.reports;

import java.util.List;
import java.util.Vector;
import java.util.function.Function;

import lukfor.magic.reports.functions.IncludeScriptFunction;
import lukfor.magic.reports.functions.IncludeStyleFunction;

public class MagicReport extends HtmlReport {

	private List<String> styles = new Vector<String>();

	private List<String> scripts = new Vector<String>();

	private List<String> activationCodes = new Vector<String>();

	public MagicReport(String inputDirectory) {

		super(inputDirectory);

		final IncludeStyleFunction styleFunction = new IncludeStyleFunction(this);

		set("include_widgets", new Function<String, String>() {

			@Override
			public String apply(String arg0) {
				String html = "<!-- Widgets styles -->\n";
				for (String style : styles) {
					html += styleFunction.apply(style) + "\n";
				}
				return html;
			}

		});

		final IncludeScriptFunction scriptFunction = new IncludeScriptFunction(this);

		set("activate_widgets", new Function<String, String>() {

			@Override
			public String apply(String arg0) {

				String html = "<!-- Widgets scripts -->\n";
				for (String script : scripts) {
					html += scriptFunction.apply(script) + "\n";
				}

				html += "\n";
				html += "<!-- Widgets activation scripts -->\n";
				html += "<script>\n";
				html += "$(document).ready( function () {\n";
				for (String code : activationCodes) {
					html += code + "\n";
				}
				html += "});\n";
				html += "</script>";

				return html;

			}

		});

	}

	public void addStyle(String style) {
		styles.add(style);
	}

	public void addScript(String script) {
		scripts.add(script);
	}

	public void addActivationCode(String code) {
		activationCodes.add(code);
	}

}
