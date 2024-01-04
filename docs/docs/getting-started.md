# Getting Started

Magic-Reports is a powerful and flexible reporting tool that allows you to create interactive and visually appealing reports using a Groovy-based DSL. Follow this guide to get started with Magic-Reports:

## Prerequisites

Before you begin, ensure you have the following prerequisites installed:

- Java Development Kit (JDK) 11 or later: [Download JDK](https://adoptium.net/)

## Install Magic-Reports

TODO: install

## Create Your First Report

Create a new Groovy script (e.g., `MyReport.report`) and import the Magic-Reports DSL:

```groovy
// Define the structure of your report
report {
    title "My First Magic-Report"

    content {
        h2("Hello, Magic-Reports!")

        p("This is a simple Magic-Report. You can add content, charts, and more.")
    }
}
```

## Build Your Report

Save the script and run it using the magic-reports interpreter:

```bash
magic-reportrs -i MyReport.report -o MyReport.html
```

This will generate an HTML report:

TODO: image

## Explore Features

Congratulations! You've successfully created your first Magic-Report. Explore the documentation and examples to unlock the full potential of Magic-Reports for your reporting needs.

Explore the Magic-Reports documentation to discover more features and components you can use to enhance your reports. You can include tables, charts, Bootstrap components, and more.

For more detailed information and examples, refer to the [Magic-Reports GitHub repository](https://github.com/lukfor/magic-reports) and the official documentation.