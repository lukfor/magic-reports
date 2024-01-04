# Creating Components

In Magic-Reports, the `component` keyword serves as a powerful tool for creating reusable and customizable components. Components are essentially functions that encapsulate HTML structure and logic, allowing you to abstract away complex code into modular and easily maintainable units. Let's delve into the creation of components using the `component` keyword and explore how options can be utilized:

## Creating a Component

To define a component, use the `component` keyword followed by the component's name and a closure containing the HTML structure. Here's a simple example:

```groovy
component "my_custom_component" {
    // HTML structure for the component
    div(class: "custom-component") {
        h3 "This is my custom component!"
    }
}
```

## Using Options

Components often include customizable options to enhance their flexibility. Options are defined within the component's closure, allowing users to tailor the component based on specific needs. For instance:

```groovy
component "customizable_component" {
    // HTML structure with customizable options
    div(class: "customizable-component") {
        h3 option.title
        p options.content
    }
}
```

In this example, the `customizable_component` includes two options, `title` and `content`, which are then used in the HTML structure.

## Using Body

TODO


## Utilizing Components in Reports

Once defined, components can be easily incorporated into your reports using the component's name. Here's how you can use the previously created components in a report:

```groovy
report {
    title "Report with Components"

    content {
        // Using the first component
        my_custom_component

        // Using the second component with custom options
        customizable_component(title: "Custom Title", content: "Custom Content")
    }
}
```

By integrating the `component` keyword and options, Magic-Reports facilitates the creation of modular, reusable, and customizable components, contributing to cleaner and more maintainable report structures.
