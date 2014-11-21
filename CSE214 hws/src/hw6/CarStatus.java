package hw6;

import java.io.Serializable;

/**
 * <code>CarStatus</code> contains information about a car and its speed on
 * roads. This class is used by the <code>TrafficMonitor</code> data structure.
 * 
 * @author Joon Hyung Lim
 * 
 *         e-mail: joonhyung.lim@stonybrook.edu
 * 
 *         Stony Brook ID: 109558002
 */

public class CarStatus implements Serializable {

	private int id; // A unique identifier for this car
	private String road; // The name of the road the car is currently on
	private double position; // The position of the car along the road (the cars
								// distance from some predefined point on the
								// road)
	private double time; // The time that this data was captured
	private double speed; // The speed of the car, if known (to calculate the
							// speed of a car requires its position at two
							// different times on the same road). Otherwise, -1.

	/**
	 * Returns an instance of <code>CarStatus</code>
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
	 *             Indicates that the car's ID, position, and/or time is an
	 *             illegal argument.
	 */
	public CarStatus(int id, String road, double position, double time)
			throws IllegalArgumentException {

		if (id <= 0 || position < 0 || time < 0) {
			throw new IllegalArgumentException(
					"ID must be an integer greater than 0/Position cannot be "
							+ "negative/Time cannot be negative");
		}
		speed = -2;
		this.id = id;
		this.road = road;
		this.position = position;
		this.time = time;
	}

	/**
	 * Gets a car's ID number.
	 * 
	 * @return The car's ID number.
	 */
	public int getId() {

		return id;
	}

	/**
	 * Sets a car's ID number.
	 * 
	 * @param id
	 *            The car's new ID number.
	 * 
	 * @throws IllegalArgumentException
	 *             Indicates that the car's ID is not a positive integer.
	 */
	public void setId(int id) throws IllegalArgumentException {

		if (id <= 0) {
			throw new IllegalArgumentException(
					"ID must be an integer greater than 0.");
		} else this.id = id;
	}

	/**
	 * Gets the road a car is on.
	 * 
	 * @return The road the car is on.
	 */
	public String getRoad() {

		return road;
	}

	/**
	 * Sets the road a car is on.
	 * 
	 * @param road
	 *            The name of the new road the car is on.
	 */
	public void setRoad(String road) {

		this.road = road;
	}

	/**
	 * Gets the position of the car on the road.
	 * 
	 * @return The position of the car.
	 */
	public double getPosition() {

		return position;
	}

	/**
	 * Sets the position of the car on the road.
	 * 
	 * @param position
	 *            The new position of the car.
	 * 
	 * @throws IllegalArgumentException
	 *             Indicates that the car's position is not zero or a positive
	 *             integer.
	 */
	public void setPosition(double position) throws IllegalArgumentException {

		if (position < 0) {
			throw new IllegalArgumentException("Position cannot be negative.");
		} else this.position = position;
	}

	/**
	 * Gets the time of a car's data.
	 * 
	 * @return The time of a car's data.
	 */
	public double getTime() {

		return time;
	}

	/**
	 * Sets the time of a car's data.
	 * 
	 * @param time
	 *            The new time of a car's data.
	 * 
	 * @throws IllegalArgumentException
	 *             Indicates that the time is not zero or a positive integer.
	 */
	public void setTime(double time) throws IllegalArgumentException {

		if (time < 0) {
			throw new IllegalArgumentException("Time cannot be negative.");
		} else this.time = time;
	}

	/**
	 * Gets the speed of a car.
	 * 
	 * @return The speed of a car.
	 */
	public double getSpeed() {

		return speed;
	}

	/**
	 * Sets the speed of a car.
	 * 
	 * @param speed
	 *            The new speed of a car.
	 */
	public void setSpeed(double speed) {

		this.speed = speed;
	}
}
