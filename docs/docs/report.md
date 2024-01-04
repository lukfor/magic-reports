The `report` keyword in Magic-Reports serves as the main entry point for defining the structure and content of your reports. It encapsulates the entire report and allows you to specify the title, layout, and various components. The HTML builder in Magic-Reports utilizes Groovy's DSL (Domain-Specific Language) capabilities to provide a concise and expressive way of constructing HTML content.

## Structure of `report` Keyword

The `report` keyword is used within a Groovy script and takes a closure as an argument. This closure encapsulates the entire report structure. Here is a basic example:

```groovy
report {
    title "My Magic-Report"

    content {
        p "This is a paragraph in my report."
    }
}
```

In this example, the `report` keyword defines a report with the title "My Magic-Report" and includes a paragraph in the content section.


## Defining a Layout Template

To define a layout template, use the `template` function within your report:

```groovy
template "bootstrap"
```

In this example, the template named "bootstrap" is specified. Magic-Reports supports various templates, and you can create or customize templates based on your needs.

## Utilizing `$baseDir`

TODO: use baseDir to load datasets

To dynamically set the location of templates using `$baseDir`, you can structure your project like this:

```
project/
|-- templates/
|   |-- my-template.html
|-- your_report.report
```

In your report script (`your_report.groovy`), use the `template` function with `$baseDir`:

```groovy
template "$baseDir/templates/my-template.html"
```

This sets the template location based on the current directory structure.

### Complete Example

Putting it all together:

```groovy

report {
    title "Custom Template Example"
    template "$baseDir/templates/my-template.html"

    content {
        p "This report uses a custom template for styling."
    }
}
```

In this example, the report utilizes the "bootstrap" template located in the "templates" directory relative to the script's location.

By combining the `template` function with the `$baseDir` variable, Magic-Reports provides a flexible way to define layout templates and adapt the template location based on your project structure.