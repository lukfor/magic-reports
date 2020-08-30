# magic-reports

> A java library to generate self-containing html reports and dashboards

Reporting and data visualization is key in data science. `magic-reports` provides a simple template based library to create self-containing html reports.

## Installation

Add the following dependency to your Maven Project:

    <dependency>
      <groupId>com.github.lukfor</groupId>
      <artifactId>magic-reports</artifactId>
      <version>0.0.1</version>
    </dependency>

Or include it as a dependency in your Gradle project:

    compile 'com.github.lukfor:magic-reports:0.0.1'


## Create your first report

```java

List<Person> persons = ...;

HtmlWidgetReport report = new HtmlWidgetReport("/example");
report.set("persons", persons);
report.generate(new File("example.html"));
```

## Widgets

### datatables

```
{{import_widget("data_table")}}
```

```
{{
	data_table({
		columns: [
		   		{data: "firstName", title: "First Name"},
		   		{data: "lastName", title: "Last Name"},
		   		{data: "email", title: "E-Mail"}
		],
		data: persons
	})
}}
```

### plotly

```
{{import_widget("plotly")}}
```

```
{{
	plotly({
		traces: [{
		  x: array(persons).extract("age"),
		  y: array(persons).extract("salary"),
		  mode: "markers",
		  type: "scatter"
		}],
		layout: {
			plot_bgcolor: "rgba(255,255,255,1)"
		}
	})
}}
```

### vega-lite

```
{{import_widget("vega_lite")}}
```

```
{{
  vega_lite({
      $schema: "https://vega.github.io/schema/vega-lite/v4.json",
      data: {
        values: {{json(persons)}}
      },
      mark: "point",
      encoding: {
        x: {
          field: "age",
          type: "quantitative"
        },
        y: {
          field: "salary",
          type: "quantitative"
        }
      },
      width: 1000
  })
}}
```

## Helpers

### Javascript Helpers

- `import_script("path_or_url/to/script.js")`
- `array(object).extract("property")`
- `json(object)`

### CSS Helpers

- `import_style("path_or_url/to/style.css")`

### CSS Helpers

- `import_style("path_or_url/to/style.css")`



## License

`magic-reports` is MIT Licensed.
