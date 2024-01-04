Magic-Reports leverages the power of Bootstrap components to enhance the visual appeal and responsiveness of your reports. Bootstrap is a popular front-end framework that provides a comprehensive set of CSS and JavaScript tools for building modern and responsive web pages. In Magic-Reports, you can seamlessly integrate Bootstrap components into your reports using a Groovy-based DSL. These components include headers, tables, cards, tabs, and footers, allowing you to structure and present your data in a clean and organized manner. Whether you're creating a simple dataset overview or a complex interactive dashboard, Bootstrap components in Magic-Reports provide a consistent and professional look while ensuring a user-friendly experience for your audience. This introduction sets the stage for utilizing Bootstrap components to craft compelling and visually engaging reports within the Magic-Reports framework.

!!! note "Attention"

    In order to leverage the Bootstrap components seamlessly within your Magic-Reports,
    ensure that you are utilizing either the `bootstrap` template or that the template
    being used includes the necessary Bootstrap CSS and JavaScript scripts.

## Header

##### Description
The Bootstrap Header component creates a header with a title, description, and optional background color.

##### Include Command
```groovy
include "components/bootstrap_header.report"
```

##### Options

| Option       | Description                       | Example Value   |
|--------------|-----------------------------------|-----------------|
| title        | Title of the header               | "Iris Dataset"  |
| description  | Description text                  | "Description..." |
| color        | Background color (hex)            | "#546d78"       |

##### Example
```groovy
bootstrap_header (
    title: "Iris Dataset",
    description: "Description of the dataset...",
    color: "#546d78"
)
```

## Card

##### Description
The Bootstrap Card component creates a card with a title and content.

##### Include Command
```groovy
include "components/bootstrap_card.report"
```

##### Options

| Option       | Description                       | Example Value     |
|--------------|-----------------------------------|-------------------|
| title        | Title of the card                 | "Dataset"         |

##### Example
```groovy
bootstrap_card (
    title: "Dataset"
) {
    // Card content goes here
}
```

I appreciate your observation. Apologies for the oversight. Below is the missing documentation for the `bootstrap_tabs` component:


## Tabs

##### Description
The Bootstrap Tabs component organizes content into tabbed sections, allowing users to switch between different views.

##### Include Command
```groovy
include "components/bootstrap_tabs.report"
```

##### Options

The `bootstrap_tabs` component accepts nested `bootstrap_tab` components.

##### Example
```groovy
bootstrap_tabs {
    bootstrap_tab(title: "Tab 1") {
        // Content for Tab 1
    }

    bootstrap_tab(title: "Tab 2") {
        // Content for Tab 2
    }
}
```

The `bootstrap_tabs` component allows you to organize content into tabs, providing a clean and organized structure for presenting information.

## Table

##### Description
The Bootstrap Table component displays a DataTable with the specified data.

##### Include Command
```groovy
include "components/bootstrap_table.report"
```

##### Options

| Option       | Description                              | Example Value    |
|--------------|------------------------------------------|------------------|
| data         | Table data (Tech Tablesaw Table object)  | iris             |

##### Example
```groovy
bootstrap_table (
    data: iris
) {
    // Additional options and content for the table
}
```
## Footer

##### Description
The Bootstrap Footer component adds a footer with optional text and background color.

##### Include Command
```groovy
include "components/bootstrap_footer.report"
```

##### Options

| Option       | Description                       | Example Value                                     |
|--------------|-----------------------------------|---------------------------------------------------|
| text         | Footer text                       | "This report was created by John Doe on 2022-01-01" |
| color        | Background color (hex or primary) | "#546d78"                                         |

##### Example
```groovy
bootstrap_footer (
    text: "This report was created by John Doe on 2022-01-01",
    color: "#546d78"
)
```
