# Examples

Explore these examples to understand the syntax, structure, and features of Magic-Reports, and adapt them to your specific needs. Each example includes not only the report script but also the generated HTML report for reference.

## Iris Dataset Report

The Iris Dataset Report serves as an illustrative example showcasing the capabilities of Magic-Reports. This report utilizes the Magic-Reports DSL to present insights into the well-known Iris dataset. It features Bootstrap components, including headers, cards, tables, and visualizations generated using Plotly. The report provides an engaging exploration of the dataset, offering descriptive statistics, scatter plots, and distribution analyses.

To generate the Iris Dataset Report, run the following command:

```bash
magic-reports -i examples/iris.report -o iris.html
```

[:fontawesome-solid-file-image: View Report](examples/iris.html){ .md-button .md-button--primary}
[:fontawesome-solid-gear: View Source](https://github.com/lukfor/magic-reports/tree/master/examples/iris.report){ .md-button .md-button--primary}

## Whisky Dataset Report

The Whisky Dataset Report demonstrates how Magic-Reports can be applied to analyze and visualize data from a Whisky dataset. Leveraging Bootstrap components and Plotly visualizations, the report presents a detailed examination of whisky ratings, prices, and relationships between variables. This example showcases the versatility of Magic-Reports in crafting informative and visually appealing reports for diverse datasets.

Generate the Whisky Dataset Report using the following command:

```bash
magic-reports -i examples/whisky.report -o whisky.html
```

[:fontawesome-solid-file-image: View Report](examples/whisky.html){ .md-button .md-button--primary}
[:fontawesome-solid-gear: View Source](https://github.com/lukfor/magic-reports/tree/master/examples/whisky.report){ .md-button .md-button--primary}


## Multi Page Reports

You can create a single Groovy file containing multiple reports by setting the output within each `report` block. Here's an example:

```groovy
// Report 1  
report {  
    template "bootstrap"  
    output "${params.output}/pages/report1.html"  

    content {  
        h2 "Content of Report 1"  
        p "This is the content of Report 1."  
    }  
}  
  
// Report 2  
report {  
    template "bootstrap"  
    output "${params.output}/pages/report2.html"  

    content {  
        h2 "Content of Report 2"  
        p "This is the content of Report 2."  
    }  
}  
  
// Index Page  
report {  
    template "bootstrap"  
    output "${params.output}/index.html"
  
    content {  
        div(class: "container-fluid") {  
            div(class: "row") {  
                // Sidebar  
                div(class: "col-md-3") {  
                    h3 "Navigation"  
                    ul {  
                        li {  
                            a(href: "pages/report1.html", target: "iframeContent", "Report 1")  
                        }  
                        li {  
                            a(href: "pages/report2.html", target: "iframeContent", "Report 2")  
                        }  
                    }  
                }  
                // Iframe  
                div(class: "col-md-9") {  
                    iframe(name: "iframeContent", id: "iframeContent",  
                        width: "100%", height: "500px", src: "pages/report1.html")  
                }  
            }  
        }  
    }  
}
```

In this example:

- Three reports are defined in the same file: "Report 1," "Report 2," and the "Index Page".
- The `output` keyword within each `report` block specifies the output file for that particular report.
- When you run the command:

```bash
magic-reports -i multi_reports.groovy --output results
```

It will generate three HTML files: "results/pages/report1.html," "results/pages/report2.html," and "results/index.html," each containing the respective content.

After generating the reports, opening `results/index.html` in a web browser should display the index page with the sidebar, and the iframe should load the content of the selected report when clicking the links.