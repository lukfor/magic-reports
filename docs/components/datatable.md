The DataTable component generates an interactive and customizable table that displays data in rows and columns. It utilizes the [DataTables](https://datatables.net/) library to enhance the user experience by adding features like sorting, searching, and pagination.
The DataTable component is useful for displaying structured data with interactive features, providing a user-friendly way to explore and analyze tabular information within the Magic-Report.

##### Include Command
```groovy
include "components/datatable.report"
```

##### Options

The `datatable` component accepts the following options:

| Option          | Description                                           | Example Value      |
|-----------------|-------------------------------------------------------|--------------------|
| data            | Table data (Tech Tablesaw Table object)               | iris               |
| bLengthChange   | Enable or disable the option to change the page length | false              |
| bFilter         | Enable or disable the search/filter input box         | false              |
| Additional Options | Additional options supported by DataTables library | [DataTable Options](https://datatables.net/manual/options) |

##### Example
```groovy
datatable (
    data: iris,
    bLengthChange: false,
    bFilter: false
)
```

This example creates a DataTable for the `iris` dataset with options to disable page length change and the search/filter input box.

##### Notes
- The `data` option should be a Tech Tablesaw Table object or a list of objects.
- Additional customization options can be included based on the DataTables library documentation.
