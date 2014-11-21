package hw6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * <code>TrafficDriver</code> contains methods to allow a user to add and
 * display information about traffic speeds. The tree is saved as a binary file
 * named "traffic.obj" in the current working directory when the user quits the
 * program. If there is already an binary file named "traffic.obj" defined in
 * the current working directory, the program loads a tree from that file if it
 * exists. If not the program starts with a new <code>TrafficMonitor</code>.
 * 
 * @author Joon Hyung Lim
 * 
 *         e-mail: joonhyung.lim@stonybrook.edu
 * 
 *         Stony Brook ID: 109558002
 */
public class TrafficDriver implements Serializable {

	public static void main(String[] args) throws FileNotFoundException,
			ClassNotFoundException, IOException {

		TrafficMonitor t = TrafficMonitor.deserialize("traffic.obj");

		Scanner input = new Scanner(System.in);
		char c = ' ';

		while (c != 'Q') {
			printMenu();
			System.out.print("\nEnter a selection: ");
			c = Character.toUpperCase(input.next().charAt(0));

			switch (c) {
				case 'A':
					addData(t, input);
					break;
				case 'R':
					removeCar(t, input);
					break;
				case 'D':
					getRoad(t, input);
					break;
				case 'S':
					getSpeed(t, input);
					break;
				case 'T':
					getAllSpeeds(t, input);
					break;
				case 'U':
					getAvgSpeed(t, input);
					break;
				case 'Q':
					quit(t);
					break;
				default:
					break;
			}
		}

		input.close();

	}

	/**
	 * Method that prints out an operations menu for the user.
	 */
	public static void printMenu() {

		System.out.println("\n	A) Add data");
		System.out.println("	R) Remove car");
		System.out.println("	D) Get road");
		System.out.println("	S) Get speed");
		System.out.println("	T) Get all speeds on a road");
		System.out.println("	U) Get average speed");
		System.out.println("	Q) Quit and save to “tree.obj”");
	}

	/**
	 * Prompts the user to enter a car's ID, road name, car's position, and
	 * time. This method calls the addData method from
	 * <code>TrafficMoniter</code> and adds the new data.
	 * 
	 * @param t
	 *            The <code>TrafficMoniter</code> being operated on.
	 * 
	 * @param input
	 *            <code>Scanner</code> object to take in user input.
	 * 
	 * @throws IllegalArgumentException
	 *             Indicates that the user's input for the car's ID, position,
	 *             and/or time an illegal argument.
	 */
	public static void addData(TrafficMonitor t, Scanner input)
			throws IllegalArgumentException {

		System.out.print("\nEnter the car ID number: ");
		int id = input.nextInt();

		input.nextLine();

		System.out.print("Enter the road name: ");
		String road = input.nextLine();

		System.out.print("Enter the position: ");
		double position = input.nextDouble();

		System.out.print("Enter the time: ");
		double time = input.nextDouble();

		try {
			t.addData(id, road, position, time);
			double speed = t.getSpeed(id);

			if (speed == -2) System.out.println("\nAdded car #" + id + " on "
					+ road + ", position = " + position + ", time = " + time);
			else System.out.println("\nAdded car #" + id + " on " + road
					+ ", position = " + position + ", time = " + time
					+ ", speed = " + speed);
		} catch (IllegalArgumentException e) {
			System.out
					.println("\nID must be an integer greater than 0/Position"
							+ " cannot be negative/Time cannot be negative");
		}

	}

	/**
	 * Prompts the user to enter a car's ID. This method calls the remove method
	 * from <code>TrafficMoniter</code> and removes the data.
	 * 
	 * @param t
	 *            The <code>TrafficMoniter</code> being operated on.
	 * 
	 * @param input
	 *            <code>Scanner</code> object to take in user input.
	 * 
	 * @throws IllegalArgumentException
	 *             Indicates that the user's input for the car's ID is an
	 *             illegal argument.
	 * 
	 * @throws NullPointerException
	 *             Indicates that the program attempted to access a null value.
	 * 
	 */
	public static void removeCar(TrafficMonitor t, Scanner input)
			throws IllegalArgumentException, NullPointerException {

		System.out.print("\nEnter the car number: ");
		int id = input.nextInt();

		try {
			t.remove(id);

			System.out.println("\nRemoved car #" + id);
		} catch (NullPointerException e) {
			System.out.println("\nCar not found.");
		}

	}

	/**
	 * Prompts the user to enter a car's ID. This method calls the getRoad
	 * method from <code>TrafficMoniter</code> and retrieves the road
	 * information.
	 * 
	 * @param t
	 *            The <code>TrafficMoniter</code> being operated on.
	 * 
	 * @param input
	 *            <code>Scanner</code> object to take in user input.
	 */
	public static void getRoad(TrafficMonitor t, Scanner input) {

		System.out.print("\nEnter the car ID number: ");
		int id = input.nextInt();

		if (t.getCars().containsKey(id)) {
			String road = t.getCars().get(id).getRoad();
			System.out.println("\nCar #" + id + " is on " + road);
		} else {
			System.out.println("\nCar #" + id + " is not found");
		}

	}

	/**
	 * Prompts the user to enter a car's ID. This method calls the getSpeed
	 * method from <code>TrafficMoniter</code> and retrieves the average speed.
	 * 
	 * @param t
	 *            The <code>TrafficMoniter</code> being operated on.
	 * 
	 * @param input
	 *            <code>Scanner</code> object to take in user input.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             Indicates that the program attempted to access an array's
	 *             index that doesn't exist.
	 */
	public static void getSpeed(TrafficMonitor t, Scanner input)
			throws IndexOutOfBoundsException {

		System.out.print("\nEnter the car ID number: ");
		int id = input.nextInt();

		if (t.getSpeed(id) == -2) System.out.println("\nThe speed of car #"
				+ id + " is unknown");
		else if (t.getSpeed(id) == -1) System.out.println("\nCar #" + id
				+ " is unknown");
		else System.out.println("\nThe speed of car #" + id + " is "
				+ t.getSpeed(id));

	}

	/**
	 * Prompts the user to enter a road name. This method calls methods from
	 * <code>TrafficMonitor</code> and retrieves all average speeds of cars
	 * currently on the road.
	 * 
	 * @param t
	 *            The <code>TrafficMoniter</code> being operated on.
	 * 
	 * @param input
	 *            <code>Scanner</code> object to take in user input.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             Indicates that the program attempted to access an array's
	 *             index that doesn't exist.
	 */
	public static void getAllSpeeds(TrafficMonitor t, Scanner input)
			throws IndexOutOfBoundsException {

		input.nextLine();

		System.out.print("\nEnter the road: ");
		String road = input.nextLine();

		System.out.println();

		List list = t.getKnownSpeeds(road);

		List<Integer> list2 = new LinkedList();

		int i = 0;
		Integer id = t.getRoads().get(road).get(0);
		while (id != null) {
			if (t.getCars().get(id).getSpeed() == -1
					|| t.getCars().get(id).getSpeed() == -1) {
				// do nothing
			} else {
				list2.add(id);
			}
			i++;

			try {
				id = t.getRoads().get(road).get(i);
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

		if (list.size() <= 0) {
			System.out.println("\nThere are no moving cars");
		}

		else for (int j = 0; j < list.size(); j++) {
			System.out.println("Car #" + list2.get(j) + ": " + list.get(j));
		}

	}

	/**
	 * Prompts the user to enter a road name. This method calls the
	 * getKnownSpeeds method from <code>TrafficMonitor</code> and retrieves the
	 * average speed of cars currently on the road.
	 * 
	 * @param t
	 *            The <code>TrafficMoniter</code> being operated on.
	 * 
	 * @param input
	 *            <code>Scanner</code> object to take in user input.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             Indicates that the program attempted to access an array's
	 *             index that doesn't exist.
	 */
	public static void getAvgSpeed(TrafficMonitor t, Scanner input)
			throws IndexOutOfBoundsException {

		input.nextLine();

		System.out.print("\nEnter the road: ");
		String road = input.nextLine();

		List list = t.getKnownSpeeds(road);

		if (list.size() <= 0) {
			System.out.println("\nThere are no moving cars");
		} else {
			System.out.println("Average speed of cars on " + road + " is "
					+ t.getAverageSpeed(road));
		}

	}

	/**
	 * Saves the tree onto binary file "traffic.obj" and terminate the program.
	 * 
	 * @param t
	 *            The <code>TrafficMoniter</code> being operated on.
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
	public static void quit(TrafficMonitor t) throws FileNotFoundException,
			IOException {

		t.serialize("traffic.obj");
	}
}
