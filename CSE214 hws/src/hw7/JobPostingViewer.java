package hw7;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Implementing these comparators allows you to use Collections.sort(List,
 * Comparator) to sort a given list in an order other than the one defined by
 * the compareTo() method of the List elements (their natural ordering).
 */
public class JobPostingViewer implements Serializable {

	static List<Employer> Employers;
	static int[] customPreference;

	public static void main(String[] args) throws FileNotFoundException,
			ClassNotFoundException, IOException {

		Employers = deserialize("employers.obj");

		Scanner input = new Scanner(System.in);
		char c = ' ';

		while (c != 'Q') {
			printMenu();
			System.out.print("\nEnter a selection: ");
			c = Character.toUpperCase(input.next().charAt(0));

			switch (c) {
				case 'A':
					addEmployer(input);
					break;
				case 'B':
					addJob(input);
					break;
				case 'C':
					removeEmployer(input);
					break;
				case 'D':
					removeJob(input);
					break;
				case 'E':
					sortEmployers(input);
					break;
				case 'F':
					sortJobs(input);
					break;
				case 'Q':
					// quit();
					break;
				default:
					break;
			}
		}

		input.close();

	}

	public static void addEmployer(Scanner input) {

		input.nextLine();
		boolean exists = false;

		System.out.print("\nEnter the employer's name: ");
		String name = input.nextLine();

		// if name already exists
		for (Employer e : Employers) {
			if (e.getName().equalsIgnoreCase(name))
				exists = true;
		}

		if (exists)
			System.out.println("" + name + " already exists.");

		else {

			Employers.add(new Employer(name));
			//
			System.out.println(name + " was added.");
		}
		//
	}

	/**
	 * Add job: prompt for the number of an employer, the job title, salary,
	 * hours/week, and vacation days/year, and add a new JobPosting with the
	 * specified values to the specified employer (at the end of the list).
	 * 
	 * 
	 * 
	 * @param input
	 */
	public static void addJob(Scanner input) {

		input.nextLine();

		System.out.print("\nEnter the number of the employer: ");
		int index = input.nextInt() - 1;

		if (index >= Employers.size() || index < 0)
			System.out.println("\nEnter a correct selection.");
		else {

			input.nextLine();

			System.out.print("Enter the job title: ");
			String title = input.nextLine();

			System.out.print("Enter the salary: ");
			int salary = input.nextInt();

			System.out.print("Enter the number of hours per week: ");
			int hrsPerWeek = input.nextInt();

			System.out.print("Enter the number of vacation days per year: ");
			int vacationDays = input.nextInt();

			// add job here
			Employers.get(index).add(
					new JobPosting(title, salary, hrsPerWeek, vacationDays));

			System.out.println("\nAdded “" + title + "” to "
					+ Employers.get(index).getName());
		}
	}

	/**
	 * Remove employer: prompt for the number of an employer and remove that
	 * employer from the list.
	 */
	public static void removeEmployer(Scanner input) {

		input.nextLine();

		System.out.print("\nEnter the number of the employer to remove: ");
		int index = input.nextInt() - 1;

		if (index >= Employers.size() || index < 0)
			System.out.println("\nEnter a correct selection.");
		else {

			String name = Employers.get(index).getName();

			Employers.remove(index);

			System.out.println("\nRemoved employer “" + name + "”");
		}

	}

	/**
	 * Remove job: prompt for the number of an employer, and the number of a job
	 * within that employer’s list of postings, and remove that job from the
	 * list.
	 * 
	 * @param input
	 */
	public static void removeJob(Scanner input) {

		input.nextLine();

		System.out.print("\nEnter the number of the employer: ");
		int index = input.nextInt() - 1;

		if (index >= Employers.size() || index < 0)
			System.out.println("\nEnter a correct selection.");
		else {

			System.out.print("\nEnter the number of the job to remove: ");
			int jobIndex = input.nextInt() - 1;

			if (jobIndex >= Employers.get(index).size() || jobIndex < 0)
				System.out.println("\nEnter a correct selection.");
			else {

				String title = Employers.get(index).get(jobIndex).getTitle();

				System.out.println("\nRemoved job “" + title
						+ "” from employer “" + Employers.get(index).getName()
						+ "”");
			}
		}

	}

	/**
	 * Sort and print the employers list: present the user with the following
	 * submenu
	 * 
	 * A) Sort by name
	 * 
	 * B) Sort by number of postings
	 * 
	 * When the user has made a choice, instantiate the appropriate Comparator,
	 * sort the employers list using Collections.sort(), and print the sorted
	 * list.
	 * 
	 * @param input
	 */
	public static void sortEmployers(Scanner input) {

		System.out.println("\n	A) Sort by name");
		System.out.println("	B) Sort by number of jobs");

		System.out.print("\nEnter a selection: ");
		char c = Character.toUpperCase(input.next().charAt(0));

		switch (c) {
			case 'A':
				Collections.sort(Employers, new EmployerNameComparator());
				break;
			case 'B':
				Collections.sort(Employers, new EmployerNumJobsComparator());
				break;
			default:
				break;
		}
		// sort
		printEmployers();

	}

	/**
	 * Sort and print one employer’s job postings: prompt for the number of an
	 * employer, then present the user with the following submenu
	 * 
	 * A) Sort by name
	 * 
	 * B) Sort by salary
	 * 
	 * C) Sort by hours per week
	 * 
	 * D) Sort by vacation days per year
	 * 
	 * E) Sort by custom preference score: if the user selects this option,
	 * prompt for the three weights
	 * 
	 * When the user has made a choice (and entered the three weights, if he/she
	 * chose option E), instantiate the appropriate Comparator (if the user
	 * chose option E, you must pass the weights as arguments to the
	 * JobPostingWeightedComparator), sort the employer’s job postings using
	 * Collections.sort(), and print the sorted list.
	 * 
	 * @param input
	 */
	public static void sortJobs(Scanner input) {

		input.nextLine();

		System.out.print("\nEnter the employer number: ");
		int index = input.nextInt() - 1;

		if (index >= Employers.size() || index < 0)
			System.out.println("\nEnter a correct selection.");

		else {

			System.out.println("\n	A) Sort by name");
			System.out.println("	B) Sort by salary");
			System.out.println("	C) Sort by hours per week");
			System.out.println("	D) Sort by vacation days per year");
			System.out.println("	E) Sort by custom preference score");

			System.out.print("\nEnter a selection: ");
			char c = Character.toUpperCase(input.next().charAt(0));

			switch (c) {
				case 'A':
					Collections.sort(Employers.get(index),
							new JobPostingNameComparator());
					break;
				case 'B':
					Collections.sort(Employers.get(index),
							new JobPostingSalaryComparator());
					break;
				case 'C':
					Collections.sort(Employers.get(index),
							new JobPostingHoursComparator());
					break;
				case 'D':
					Collections.sort(Employers.get(index),
							new JobPostingVacationComparator());
					break;
				case 'E':
					customPreference = customPreference(input);
					Collections.sort(Employers.get(index),
							new JobPostingWeightedComparator(
									customPreference[0], customPreference[1],
									customPreference[2]));
					break;
				default:
					break;
			}
			// sort

			printJobs(index);
		}

	}

	public static int[] customPreference(Scanner input) {

		int[] preferences = new int[3];

		System.out.println("\nEnter the salary weight: ");
		preferences[0] = input.nextInt();

		System.out.println("Enter the hours/week weight: ");
		preferences[1] = input.nextInt();

		System.out.println("Enter the vacation days/year weight: ");
		preferences[2] = input.nextInt();

		return preferences;

	}

	/**
	 * Method that prints out an operations menu for the user.
	 */

	public static void printMenu() {

		System.out.println("\n	A) Add Employer");
		System.out.println("	B) Add Job");
		System.out.println("	C) Remove Employer");
		System.out.println("	D) Remove Job");
		System.out.println("	E) Sort and print Employers");
		System.out.println("	F) Sort and print Jobs");
		System.out.println("	Q) Quit");
	}

	/**
	 * Saves the list onto binary file "employers.obj" and terminate the
	 * program.
	 * 
	 * @throws FileNotFoundException
	 *             Indicates that the binary file being searched for was not
	 *             found.
	 * 
	 * @throws IOException
	 *             Indicates that an I/O operation has failed or been
	 *             interrupted.
	 * 
	 */
	public static void quit() throws FileNotFoundException, IOException {

		serialize("employers.obj");
	}

	public static void printEmployers() {

		System.out.print("\n#   Employer               # Jobs");
		System.out.print("\n--  --------               ------");
		for (int i = 1; i <= Employers.size(); i++) {
			System.out.print("\n");
			System.out.printf("%-4d%-23s%-5d", i, Employers.get(i - 1)
					.getName(), Employers.get(i - 1).size());

		}
		System.out.println();
	}

	public static void printJobs(int index) {

		Employer e = Employers.get(index);

		System.out
				.print("\n#   Job Title                   Salary   Hours   Vacation");
		System.out
				.print("\n--  ---------                   ------   -----   --------");
		for (int i = 1; i <= e.size(); i++) {
			System.out.print("\n");
			System.out.printf("%-4d%-28s%-9d%-8d%-8d", i, e.get(i - 1)
					.getTitle(), e.get(i - 1).getSalary(), e.get(i - 1)
					.getHrsPerWeek(), e.get(i - 1).getVacationDays());
		}
		System.out.println();
	}

	/**
	 * Stores the list as a binary file onto the current working directory.
	 * 
	 * @param filename
	 *            The name of the binary file.
	 * 
	 * @throws FileNotFoundException
	 *             Indicates that the binary file being searched for was not
	 *             found.
	 * 
	 * @throws IOException
	 *             Indicates that an I/O operation has failed or been
	 *             interrupted.
	 */
	public static void serialize(String filename) throws FileNotFoundException,
			IOException {

		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new FileOutputStream(filename));
			os.writeObject(Employers); // write object
			os.close();
		} catch (FileNotFoundException e) {
			System.out.println("\"" + filename + "\" not found.\n");
		} catch (IOException e) {
		}
		System.out.println("File saved.");
	}

	/**
	 * Retrieves a list from a binary file in the current working directory and
	 * stores it in a Employees object.
	 * 
	 * @param filename
	 *            The name of the binary file.
	 * 
	 * @return A List containing the Employees given in the binary file.
	 * 
	 * @throws FileNotFoundException
	 *             Indicates that the binary file being searched for was not
	 *             found.
	 * 
	 * @throws IOException
	 *             Indicates that an I/O operation has failed or been
	 *             interrupted.
	 * 
	 * @throws ClassNotFoundException
	 *             Indicates a specific class could not be found.
	 */
	public static List<Employer> deserialize(String filename)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		List<Employer> t = null;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					filename));
			t = (List<Employer>) is.readObject(); // read object
			System.out.println("Loaded list from \"" + filename + "\"");

			is.close();
		} catch (FileNotFoundException e) {
			System.out.println("\"" + filename
					+ "\" not found. Using blank list.\n");
			t = new ArrayList();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return t;
	}
}
