# magic-reports

> A java library to generate beautiful html reports


## Installation

Add the following dependency to your Maven Project:

    <dependency>
      <groupId>com.github.lukfor</groupId>
      <artifactId>magic-reports</artifactId>
      <version>0.0.1</version>
    </dependency>

0r include it as a dependency in your Gradle project:

    compile 'com.github.lukfor:magic-reports:0.0.1'


## Create your first report

### Html template


### Generate report from Java

```java
HtmlReport report = new HtmlReport("/example");
report.set("name", "Lukas");
report.generate(new File("example.html"));
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