package hw5;
////////////////////////////////////////////////////////////////////////////////
import hw1.FullListException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**TrafficDriver contains methods to allow a user to add and display information 
 * about traffic speeds. The tree is saved as a binary file named "tree.obj" in 
 * the current working directory when the user quits the program. If there is 
 * already an binary file named "tree.obj" defined in the current working 
 * directory, the program loads a tree from that file if it exists. If not the 
 * program starts with an empty tree. 
 * 
 * @author Joon Hyung Lim 
 *   	   e-mail: joonhyung.lim@stonybrook.edu
 *   	   Stony Brook ID: 109558002
 */
public class TrafficDriver {

	private static final int NORTH = 0; // Integer value indicating whether a speed directed north.
	private static final int SOUTH = 180; // Integer value indicating whether a speed directed south.

	/**Method that prints out an operations menu for the user.
	 */
	public static void printMenu() {
		System.out.println("\n	A) add a speed");
		System.out.println("	F) load a text file");
		System.out.println("	R) find a road");
		System.out.println("	I) print all roads (inorder)");
		System.out.println("	P) print all roads (preorder)");
		System.out.println("	D) depth");
		System.out.println("	Q) quit and save to “tree.obj”");
	}

	/**Prompts the user to enter the road name, speed, and direction of a 
	 * new TrafficNode. Then this method calls TrafficTree's addSpeed() method 
	 * and adds a new speed to a new node TrafficTree if there is no node with 
	 * the given road name or to an existing node if the name is found. The 
	 * program then prints out a confirmation statement.
	 * 
	 * @param t
	 * 		The TrafficTree being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated and 
	 * 		speed is greater than 0 and direction is either 'N' or 'C'.
	 * 
	 * <dt>Postcondition:
	 * 		<dd> If a <code>TrafficNode</code> with the name value is found, the 
	 *		speed is now added to that <code>TrafficNode</code>'s list of speeds 
	 * 		based on direction. If a <code>TrafficNode</code> with the name 
	 * 		value is not found, a new <code>TrafficNode</code> is created with 
	 * 		values based on the user's input.
	 * 
	 * @throws IllegalArgumentException
	 *  	Indicates that the user's input for speed is less than 0, or that 
	 *  	the user's input for direction is not 'N' or 'S'.
	 */
	public static void addSpeed(TrafficTree t) throws IllegalArgumentException{
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter the road name: ");
		String name = input.nextLine();
		
		System.out.print("Enter the speed: ");
		double speed = input.nextDouble();

		System.out.print("Enter the direction (N or S): ");
		char c = Character.toUpperCase(input.next().charAt(0));
		int direction = 0;
		if (c == 'N') direction = NORTH;
		else if (c == 'S') direction = SOUTH;
		t.addSpeed(name, speed, direction);

		System.out.println("\nAdded speed " + speed + ", direction " + c + " to " + name);
	}

	/**Prompts the user to enter a binary file name. The method then loads the 
	 * file using the TrafficTree's addSpeeds() method and adds the tree given 
	 * into the current tree. The program then prints out a confirmation 
	 * statement.
	 * 
	 * @param t
	 * 		The TrafficTree being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated. 
	 *		Also, the binary file being searched for exists and the data format
	 *		is one speed per line, with the road name, speed, and direction
	 *		(either 'N' or 'S') separated by commas.
	 * 
	 * <dt>Postcondition:
	 * 		<dd> The tree that was stored on the file is now added to the
	 * 		current tree.
	 * 
	 * @throws FileNotFoundException
	 * 		Indicates that the binary file being searched for was not found.
	 * 
	 * @throws IOException
	 * 		Indicates that an I/O operation has failed or been interrupted.
	 * 
	 * @throws IllegalArgumentException
	 *  	Indicates that the file contained input for speed less than 0, 
	 *  	or input for direction not "N" or "S".
	 */
	public static void addSpeeds(TrafficTree t) throws FileNotFoundException, IOException, IllegalArgumentException {
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter the text file name: ");
		String filename = input.nextLine();
		
		t.addSpeeds(filename);
		
		System.out.println("Loaded data from \"" + filename + "\" into the tree");
	}
	
	/**Prompts the user to enter the road name and direction. The method then
	 * calls TrafficTree's findSpeed() method to search the current tree for
	 * a node containing the given name. Then it prints out the average speeds
	 * of cars on the given direction.
	 * 
	 * @param t
	 * 		The TrafficTree being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated and 
	 * 		direction is either NORTH or SOUTH.
	 * 
	 * @throws IllegalArgumentException
	 *  	Indicates that the user's input for speed is less than 0, or that 
	 *  	the user's input for direction is not 'N' or 'S'.
	 */
	public static void findSpeed(TrafficTree t) throws IllegalArgumentException {
		Scanner input = new Scanner(System.in);
		System.out.print("\nEnter the road name: ");
		String name = input.nextLine();
		System.out.print("Enter the direction: ");
		char c = Character.toUpperCase(input.next().charAt(0));
		int direction = 0;
		if (c == 'N') {
			direction = NORTH;
			if (t.findSpeed(name, NORTH) == -2) 
				System.out.println("\nThere are no cars traveling northbound on " + name);
			else
				System.out.println("\nThe average speed of northbound cars on " + name + " is " + t.findSpeed(name, NORTH));
		}
		else if (c == 'S') {
			direction = SOUTH;
			if (t.findSpeed(name, SOUTH) == -2) 
				System.out.println("\nThere are no cars traveling southbound on " + name);
			else
				System.out.println("\nThe average speed of southbound cars on " + name + " is " + t.findSpeed(name, SOUTH));
		}
	}
	
	/**Prints the inorder traversal of the current tree using the TrafficTree's
	 * printInorder() method on the root of t.
	 * 
	 * @param t
	 * 		The TrafficTree being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated.
	 */
	public static void printInorder(TrafficTree t) {
		t.printInorder(t.root);
		System.out.println();
	}
	
	/**Prints the preorder traversal of the current tree using the TrafficTree's
	 * printInorder() method on the root of t.
	 * 
	 * @param t
	 * 		The TrafficTree being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated.
	 */
	public static void printPreorder(TrafficTree t) {
		t.printPreorder(t.root);
		System.out.println();
	}
	
	/**Prints the maximum depth of the current tree using TrafficTree's depth()
	 * method on the root.
	 * 
	 * @param t
	 * 		The TrafficTree being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated.
	 */
	public static void depth(TrafficTree t) {
		int depth = t.depth(t.root)-1;
		if(depth < 0) depth = 0;
		
		System.out.println("\nThe maximum depth of the tree is " + depth);
	}
	
	/**Saves the tree onto binary file "tree.obj" and terminate the program.
	 * 
	 * @param t
	 * 		The TrafficTree being operated on.
	 * 
	 */
	public static void quit(TrafficTree t) throws FileNotFoundException, IOException {
		t.serialize("tree.obj");
	}
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, IllegalArgumentException {
		TrafficTree t = TrafficTree.deserialize("tree.obj");	
		
		Scanner input = new Scanner(System.in);
		char c = ' ';
		
		while (c != 'Q'){
			printMenu();
			System.out.print("\nEnter a selection: ");
			c = Character.toUpperCase(input.next().charAt(0));
		
			switch (c) {
			case 'A':addSpeed(t);
					 break;
			case 'F':addSpeeds(t);
					 break;
			case 'R':findSpeed(t);
			 		 break;
			case 'I':printInorder(t);
					 break;
			case 'P':printPreorder(t);
					 break;
			case 'D':depth(t);
					 break;
			case 'Q':quit(t);
					 break;
			default: break;
			}	
		}
	}
}
