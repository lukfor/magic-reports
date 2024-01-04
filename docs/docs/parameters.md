# Parameters

To pass a file name for a dataset via the command-line interface (CLI) and make it accessible in the report script, you can leverage the `params` map in Magic-Reports. Here's an example:

Assume your report script (`dataset.report`) looks like this:

```groovy
report {
    title "Dataset Report"

    content {
        h2 "Dataset Analysis"

        // Access the file name from the CLI parameters
        p "Analyzing dataset from file: ${params.file}"

        // You can continue with the rest of your report content...
    }
}
```

Now, when you run the Magic-Reports command, you can pass the file name as a parameter:

```bash
magic-reports -i dataset.report -o output.html --file data/dataset.csv
```

In this example, `--file data/dataset.csv` is a CLI parameter specifying the file name, and it will be automatically loaded into the `params` map. The report script then accesses this file name using `params.file` and incorporates it into the report content.

----