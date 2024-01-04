## Plotly Component

### Description
The `plotly` component in Magic-Report integrates the Plotly library to create interactive and visually appealing visualizations within your reports. Plotly is a versatile JavaScript charting library that supports a wide range of chart types.

### Include Command
```groovy
include "components/plotly.report"
```

### Options
The `plotly` component is designed to support various Plotly chart types, each requiring specific options. Below are examples of different Plotly chart types:

#### 1. Histogram

```groovy
plotly {
    histogram (
        x: filteredData.column("sepallength").asList(),
        marker: [
            color: "#aaaaaa"
        ]
    )
}
```

#### 2. Box Plot

```groovy
plotly {
    box (
        y: iris.column("sepallength").asList(),
        x: iris.column("class").asList()
    )
}
```

#### 3. Scatter Plot

```groovy
plotly {
    scatter(
        x: whisky.column("Rating").asList(),
        y: whisky.column("Price").asList(),
        text: whisky.column("Name").asList()
    )
}
```

### Common Options

Each chart type may have specific options, but some common options include:

- `x`: Data for the x-axis.
- `y`: Data for the y-axis.
- `text`: Text to display when hovering over data points.
- Additional options specific to each chart type (e.g., `marker` for color customization).

### Example Usage

The `plotly` component allows you to create various visualizations, including histograms, box plots, scatter plots, and more. Customize the options based on your dataset and the type of chart you want to generate.

### Notes
- Refer to the [Plotly documentation](https://plotly.com/javascript/) for detailed options and customization possibilities for each chart type.
- Ensure that the required data columns are specified appropriately for the chosen chart type.

The `plotly` component empowers you to integrate sophisticated interactive visualizations seamlessly into your Magic-Reports, enhancing data exploration and analysis capabilities.


----


Certainly! Let's enhance the documentation to include a generic `trace` function within the `plotly` component. This `trace` function allows you to create a variety of Plotly traces by specifying the chart type and additional options.

```groovy
## Plotly Component

### Description
The `plotly` component in Magic-Report integrates the Plotly library to create interactive and visually appealing visualizations within your reports. Plotly is a versatile JavaScript charting library that supports a wide range of chart types.

### Include Command
```groovy
include "components/plotly.report"
```

### Options
The `plotly` component includes a generic `trace` function that supports various Plotly chart types. Examples of different trace usages are provided below:

#### Trace Function

```groovy
plotly {
    trace(
        type: "scatter", // Specify the chart type
        x: whisky.column("Rating").asList(),
        y: whisky.column("Price").asList(),
        text: whisky.column("Name").asList(),
        mode: "markers",
        marker: [
            size: 10,
            color: "rgba(255, 0, 0, 0.7)"
        ]
    )
}
```

### Common Options

- `type`: Specifies the chart type (e.g., "scatter", "bar", "histogram").
- `x`: Data for the x-axis.
- `y`: Data for the y-axis.
- `text`: Text to display when hovering over data points.
- Additional options supported by the chosen chart type.

### Example Usage

The `trace` function allows you to create a variety of Plotly traces with flexibility in specifying the chart type and additional options. Customize the options based on your dataset and the type of trace you want to generate.

### Notes
- Refer to the [Plotly documentation](https://plotly.com/javascript/) for detailed options and customization possibilities for each chart type.
- The `trace` function provides a generic approach to creating traces, offering flexibility and extensibility.

The `plotly` component, with the addition of the `trace` function, empowers you to integrate sophisticated interactive visualizations seamlessly into your Magic-Reports, enhancing data exploration and analysis capabilities.