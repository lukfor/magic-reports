# Content Builder

Magic-Reports leverages Groovy's HTML builder, which allows you to create HTML tags and attributes in a concise and readable manner. Here's how it works:

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


## Creating HTML Tags

HTML tags are created by invoking methods with the tag name. For example, to create a paragraph (`<p>`) tag, you can simply use:

```groovy
p "This is a paragraph."
```

## Nesting Tags

Tags can be nested within each other to create the desired HTML structure. For example:

```groovy
div {
    h1 "Heading 1"
    p "This is a paragraph inside a div."
}
```

This creates a `<div>` tag containing an `<h1>` tag and a `<p>` tag.

## Adding Attributes

HTML attributes can be added by providing named arguments to the tag methods. For example:

```groovy
img(src: "image.jpg", alt: "An image")
```

This creates an `<img>` tag with the `src` and `alt` attributes.

Here's an example that includes the `class` attribute in a `div` tag:

```groovy
div(class: "my-div") {
    p "This div has a custom class."
}
```

This creates a `<div>` with the attribute `class="my-div"`.

## Dynamic Content

Magic-Reports allows you to include dynamic content within HTML tags using Groovy's string interpolation. For example:

```groovy
def dynamicText = "Hello, Magic-Reports!"
p "Dynamic content: ${dynamicText}"
```

This dynamically inserts the content of the `dynamicText` variable into the paragraph.

Understanding the `report` keyword and the HTML builder enables you to create flexible and dynamic reports using Magic-Reports. Leverage Groovy's concise syntax and powerful DSL features to craft compelling HTML content with ease.


## Loops

You can use Groovy's standard loop constructs within the HTML builder to dynamically generate content. Here's an example using a `for` loop to create an unordered list:

```groovy
ul {
    for (i in 1..3) {
        li "Item ${i}"
    }
}
```

This generates an unordered list (`<ul>`) with three list items (`<li>`).

## Conditions

Conditional statements can be used to include or exclude content based on specific conditions. For example:

```groovy
def condition = true

div {
    if (condition) {
        p "This paragraph is included based on the condition."
    } else {
        p "This paragraph is excluded."
    }
}
```

This creates a `<div>` with a paragraph inside, depending on the value of the `condition` variable.


## Components

Magic-Reports introduces components that seamlessly integrate into the HTML builder, behaving like regular HTML tags. These components offer a higher level of abstraction, encapsulating complex structures and functionalities with a simplified syntax.

In this example, `bootstrap_card` is a Magic-Reports component that generates the necessary HTML code for a Bootstrap card. This approach allows you to leverage the power of components while maintaining the familiar tag-based structure in your reports.

The `include` command in Magic-Reports allows you to import external components or templates into your report. The syntax is straightforward:

```groovy
include "components/bootstrap_card.report"

report {
    title "Report with Bootstrap Card"
    content {
        // Using a Bootstrap card component
        bootstrap_card(title: "Example Card") {
            p "Content of the card"
            // Additional content or nested components can be added here
        }
    }
}
```

In this example, `bootstrap_card` is a Magic-Reports component that generates the necessary HTML code for a Bootstrap card. This approach allows you to leverage the power of components while maintaining the familiar tag-based structure in your reports.

## Example Combining Loops, Conditions, and Attributes

Now, let's combine these concepts in a more complex example:

```groovy
div(class: "container") {
    h2 "Dynamic Content Example"

    ul {
        for (i in 1..5) {
            if (i % 2 == 0) {
                li(class: "even") "Item ${i}"
            } else {
                li(class: "odd") "Item ${i}"
            }
        }
    }

    if (condition) {
        p "This content is included based on the condition."
    } else {
        p "This content is excluded."
    }
}
```

In this example, a `<div>` with a class of "container" contains an `<h2>` tag, a dynamically generated `<ul>` with alternating classes for even and odd list items, and a conditional paragraph based on the value of `condition`.
