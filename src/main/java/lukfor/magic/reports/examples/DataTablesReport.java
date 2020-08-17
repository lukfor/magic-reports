package lukfor.magic.reports.examples;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import lukfor.magic.reports.MagicReport;
import lukfor.magic.reports.widgets.DataTableWidget;

public class DataTablesReport {

	public static void main(String[] args) throws IOException {

		String caption = "My Friends";

		List<Person> persons = new Vector<Person>();

		for (int i = 0; i < 10000; i++) {
			persons.add(new Person("Max" + i, "Mustermann" + i, "max" + i + ".mustermann@mail.com"));
		}

		MagicReport report = new MagicReport("/data-tables");
		report.setSelfContained(true);
		report.set("caption", caption);
		report.set("persons", persons);
		
		new DataTableWidget(report); 
		
		report.generate(new File("data-tables.html"));
	}

	public static class Person {

		private String firstName;

		private String lastName;

		private String email;

		public Person(String firstName, String lastName, String email) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	}

}
