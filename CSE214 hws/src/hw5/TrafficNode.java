package hw5;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
////////////////////////////////////////////////////////////////////////////////
/**Nodes of a TrafficTree class that stores the name and speeds of a road.
 * 
 * @author Joon Hyung Lim 
 *   	   e-mail: joonhyung.lim@stonybrook.edu
 *   	   Stony Brook ID: 109558002
 */
public class TrafficNode implements Serializable{


	
	
	static final int NORTH = 0; // Integer value indicating whether a speed directed north.
	static final int SOUTH = 180; // Integer value indicating whether a speed is directed south.
	String name; // The name of a street. It is the key of a Binary Search Tree and should be case insensitive.
	TrafficNode left, right; // The left and right nodes of a node in a Binary Search Tree. They are initialized as null.
	List<Double> north =  new ArrayList<Double>(); // Speeds directed north stored as a List<Double>.
	List<Double> south =  new ArrayList<Double>(); // Speeds directed south stored as a List<Double>.

	/**Returns an instance of TrafficNode. 
	 * 
	 * @param name
	 * 		The name of the street.
	 * 
	 * @param speed
	 * 		The first speed added to the node.
	 * 
	 * @param direction
	 * 		The direction of the speed.
	 * 
	 * <dt>Precondition:
	 * 		<dd>Speed is greater than 0 and direction is either NORTH or SOUTH.
	 * 
	 * @throws IllegalArgumentException
	 *  	Indicates thatspeed is less than 0, or that direction is not NORTH
	 *  	or SOUTH.
	 */
	public TrafficNode(String name, double speed, int direction) throws IllegalArgumentException {
		this.name = name;
		if (speed <= 0 || (direction != NORTH && direction != SOUTH))
			throw new IllegalArgumentException();
		if (direction == NORTH)
			north.add(speed);
		if (direction == SOUTH)
			south.add(speed);
	}
////////////////////////////////////////////////////////////////////////////////
	/**Adds a speed to a node.
	 * 
	 * @param speed
	 * 		The first speed added to the node.
	 * 
	 * @param direction
	 * 		The direction of the speed.
	 * 
	 * <dt>Precondition:
	 * 		<dd>The TrafficNode object has been instantiated and speed is 
	 * 		greater than 0 and direction is either NORTH or SOUTH.
	 * 
	 * @throws IllegalArgumentException
	 *  	Indicates thatspeed is less than 0, or that direction is not NORTH
	 *  	or SOUTH.
	 */
	public void addSpeed(double speed, int direction) throws IllegalArgumentException{
		if (speed <= 0 || (direction != NORTH && direction != SOUTH))
			throw new IllegalArgumentException();
		if (direction == NORTH)
			north.add(speed);
		if (direction == SOUTH)
			south.add(speed);
	}
	
	
	
	
}
