package hw6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * <code>TrafficMonitor</code> uses hashtable data structures to store and
 * manipulate data about the speeds of cars on roads.
 * 
 * @author Joon Hyung Lim
 * 
 *         e-mail: joonhyung.lim@stonybrook.edu
 * 
 *         Stony Brook ID: 109558002
 */

public class TrafficMonitor implements Serializable {

	private Hashtable<Integer, CarStatus> cars; // a Hashtable mapping car ID
												// numbers to CarStatus objects
												// representing the last known
												// status of that car.
	private Hashtable<String, List<Integer>> roads; // a Hashtable mapping road
													// names to a list of the ID
													// numbers of all cars on
													// that road.

	/**
	 * Gets the cars hashtable.
	 * 
	 * @return the cars hashtable
	 */
	public Hashtable<Integer, CarStatus> getCars() {

		return cars;
	}

	/**
	 * Sets the cars hashtable.
	 * 
	 * @param cars
	 *            The new cars hashtable.
	 */
	public void setCars(Hashtable<Integer, CarStatus> cars) {

		this.cars = cars;
	}

	/**
	 * Gets the roads hashtable.
	 * 
	 * @return the roads hashtable.
	 */
	public Hashtable<String, List<Integer>> getRoads() {

		return roads;
	}

	/**
	 * Sets the roads hashtable.
	 * 
	 * @param roads
	 *            The new roads hashtable.
	 */
	public void setRoads(Hashtable<String, List<Integer>> roads) {

		this.roads = roads;
	}

	/**
	 * Returns an instance of <code>TrafficMonitor</code>.
	 * 
	 * <dt>Postcondition:
	 *         <dd>The cars hashtable and the roads hashtable are initialized as
	 *         empty hashtables.
	 * 
	 */
	public TrafficMonitor() {

		cars = new Hashtable<Integer, CarStatus>();
		roads = new Hashtable<String, List<Integer>>();
	}

	/**
	 * Adds the data of a car and its speed to the cars hashtable and the roads
	 * hashtable.
	 * 
	 * @param id
	 *            The car's ID number.
	 * 
	 * @param road
	 *            The road the car belongs to.
	 * 
	 * @param position
	 *            The position of the car on the road.
	 * 
	 * @param time
	 *            The time that this data was prevalent.
	 * 
	 * <dt>Precondition:
	 *            <dd>id must be greater than 0. position and time must be
	 *            nonnegative.
	 * 
	 * @throws IllegalArgumentException
	 *             Indicates that the car's ID, position, and/or time is an illegal
	 *             argument.
	 */
	public void addData(int id, String road, double position, double time)
			throws IllegalArgumentException {

		road = road.toLowerCase();
		if (cars.containsKey(id)) { // car exists already
			if (cars.get(id).getRoad().equalsIgnoreCase(road)) { // on same road

				double speed = (position - cars.get(id).getPosition())
						/ (time - cars.get(id).getTime());
				cars.get(id).setSpeed(speed);

			}

			else { // road change

				for (int i = 0; i < roads.get(cars.get(id).getRoad()).size(); i++)
					if (roads.get(cars.get(id).getRoad()).get(i).equals(id)) {
						roads.get(cars.get(id).getRoad()).remove(i);
					}

				// remove from
				// list in
				// original road
				if (roads.containsKey(road)) { // new road exists in roads
												// hashtable

					roads.get(road).add(id);
				} else { // must create new road record and put id in it
					List<Integer> r = new LinkedList<Integer>();
					r.add(id);
					roads.put(road, r);
				}
				cars.get(id).setRoad(road);
			}

			cars.get(id).setPosition(position);
			cars.get(id).setTime(time);

		}

		else { // new car

			CarStatus s = new CarStatus(id, road, position, time);

			if (roads.containsKey(road)) { // road exists
				roads.get(road).add(id);
				cars.put(id, s);

			} else { // road must be created
				List<Integer> r = new LinkedList<Integer>();
				r.add(id);
				roads.put(road, r);
				cars.put(id, s);

			}

		}

	}

	/**
	 * Removes all data for the car with the ID number id from the cars
	 * hashtable and roads hashtable.
	 * 
	 * @param id
	 *            The car's ID number.
	 * 
	 *            <dt>Postcondition:
	 *            <dd>The desired car's data is removed from the both
	 *            hashtables.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             Indicates that the program attempted to access an array's
	 *             index that doesn't exist.
	 * 
	 * @throws NullPointerException
	 *             Indicates that the program attempted to access a null value.
	 */
	public void remove(int id) throws IllegalArgumentException,
			NullPointerException {

		try {
			String road = cars.get(id).getRoad();
			cars.remove(id);

			for (int i = 0; i < roads.get(road).size(); i++)
				if (roads.get(road).get(i) == id) roads.get(road).remove(i);

		} catch (IllegalArgumentException e) {
			System.out.println("Car not found.");
		}
	}

	/**
	 * Returns the name of the road that the car with ID number id is on, or
	 * null if there is no car with that ID number.
	 * 
	 * @param id
	 *            The car's ID number.
	 * 
	 * @return the name of the road that the car with ID number id is on, or
	 *         null if there is no car with that ID number.
	 */
	public String getRoad(int id) {

		return cars.get(id).getRoad();
	}

	/**
	 * Returns the speed of the car with ID number id, or -1 if there is no car
	 * with that ID number, or -2 if there is a car with that ID number, but its
	 * speed is unknown.
	 * 
	 * @param id
	 *            The car's ID number.
	 * 
	 * @return the speed of the car.
	 */
	public double getSpeed(int id) {

		try {
			return cars.get(id).getSpeed();
		} catch (NullPointerException e) {
			return -1;
		}
	}

	/**
	 * Returns a <code>List</code> of the speed of each car currently on the
	 * indicated road, or null if the road doesn't exist.
	 * 
	 * @param road
	 *            The name of the accessed road.
	 * 
	 * @return a list of the speed of each car currently on the indicated road.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             Indicates that the program attempted to access an array's
	 *             index that doesn't exist.
	 */
	public List<Double> getKnownSpeeds(String road)
			throws IndexOutOfBoundsException {

		List list = new LinkedList();
		if (roads.get(road) == null) return null;

		int i = 0;
		Integer id = roads.get(road).get(0);

		while (id != null) {
			if (cars.get(id).getSpeed() == -1 || cars.get(id).getSpeed() == -2) {
				// do nothing
			} else {
				list.add(cars.get(id).getSpeed());

			}

			i++;
			try {
				id = roads.get(road).get(i);
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

		return list;
	}

	/**
	 * Returns the average speed of cars on the indicated road.
	 * 
	 * @param road
	 *            The name of the accessed road.
	 * 
	 * @return the average speed of cars on the indicated road.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             Indicates that the program attempted to access an array's
	 *             index that doesn't exist.
	 */
	public double getAverageSpeed(String road) throws IndexOutOfBoundsException {

		double total = 0;
		List list = getKnownSpeeds(road);
		int i = 0;
		Double addMe = (Double) list.get(0);
		while (addMe != null) {
			total += addMe;
			i++;
			try {
				addMe = (Double) list.get(i);
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

		if (list.size() == 0) return 0;
		return total / list.size();

	}

	/**
	 * Stores the tree as a binary file onto the current working directory.
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
	public void serialize(String filename) throws FileNotFoundException,
			IOException {

		try {
			ObjectOutputStream os = new ObjectOutputStream(
					new FileOutputStream(filename));
			os.writeObject(this); // write object
			os.close();
		} catch (FileNotFoundException e) {
			System.out.println("\"" + filename + "\" not found.\n");
		} catch (IOException e) {
		}
		System.out.println("File saved.");
	}

	/**
	 * Retrieves a tree from a binary file in the current working directory and
	 * stores it in a TrafficTree object.
	 * 
	 * @param filename
	 *            The name of the binary file.
	 * 
	 * @return A TrafficTree containing the tree given in the binary file.
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
	public static TrafficMonitor deserialize(String filename)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		TrafficMonitor t = null;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					filename));
			t = (TrafficMonitor) is.readObject(); // read object
			System.out.println("Loaded hashtable from \"" + filename + "\"");

			is.close();
		} catch (FileNotFoundException e) {
			System.out.println("\"" + filename
					+ "\" not found. Using new TrafficMonitor.\n");
			t = new TrafficMonitor();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return t;
	}

}
