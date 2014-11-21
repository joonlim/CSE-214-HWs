package hw5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

////////////////////////////////////////////////////////////////////////////////
/**A Binary Search Tree of streets with speeds and direction.
* 
* @author Joon Hyung Lim 
*   	   e-mail: joonhyung.lim@stonybrook.edu
*   	   Stony Brook ID: 109558002
*/
public class TrafficTree implements Serializable{

	static final int NORTH = 0; // Integer value indicating whether a speed directed north.
	static final int SOUTH = 180; // Integer value indicating whether a speed is directed south.
	TrafficNode root; // The root node of the TrafficTree
	
	/**Returns an instance of TrafficTree. 
	 * 
	 * <dt>Precondition:
	 * 		<dd>root is initialized to null.
	 */
	public TrafficTree() {
		root = null;
	}
	
////////////////////////////////////////////////////////////////////////////////
	/**Finds the node representing the specified road, if it exists and adds a
	 * speed to the appropriate direction list in that node. If the TrafficNode
	 * representing the specified road does not exist, a new TrafficNode object
	 * is created with the speed and it is added to the tree.
	 * 
	 * @param road
	 * 		The name of the street.
	 * 
	 * @param speed
	 * 		The first speed added to the node.
	 * 
	 * @param direction
	 * 		The direction of the speed.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated and 
	 * 		speed is greater than 0 and direction is either NORTH or SOUTH.
	 * 
	 * <dt>Postcondition:
	 * 		<dd> If a <code>TrafficNode</code> with the name value is found, the 
	 *		speed is now added to that <code>TrafficNode</code>'s list of speeds 
	 * 		based on direction. If a <code>TrafficNode</code> with the name 
	 * 		value is not found, a new <code>TrafficNode</code> is created.
	 * 
	 * @throws IllegalArgumentException
	 *  	Indicates that the speed is less than 0, or that the direction is 
	 *  	not NORTH or SOUTH.
	 */
	public void addSpeed(String road, double speed, int direction) throws IllegalArgumentException{
		
		TrafficNode newNode;
		TrafficNode cursor;
		boolean done = false;
		
		if (root == null) { // empty tree
			newNode = new TrafficNode(road, speed, direction);
			root = newNode;
		}
		
		else { // not empty			
			cursor = root;
			while (!done) {
				if (road.equalsIgnoreCase(cursor.name)) {
					cursor.addSpeed(speed, direction);
					done = true;
				}
				else if (road.compareToIgnoreCase(cursor.name) < 0) {
					if (cursor.left == null) {
						newNode = new TrafficNode(road, speed, direction);
						cursor.left = newNode;
						done = true;
					}
					else cursor = cursor.left;
				}
				else if (road.compareToIgnoreCase(cursor.name) > 0) {
					if (cursor.right == null) {
						newNode = new TrafficNode(road, speed, direction);
						cursor.right = newNode;
						done = true;
					}
					else cursor = cursor.right;
				}
				else done = true;
			}
		}
	}
	
	

	/**Loads an input text file and adds all its speeds to the tree. It closes the 
	 * file at the end of the method.
	 * 
	 * @param filename
	 * 		The name of the file.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated and 
	 * 		speeds from the file are greater than 0 and directions from the file are 
	 * 		either NORTH or SOUTH. The data format will be one speed per line, with 
	 * 		the road name, speed, and direction (either “N” or “S”) separated by 
	 * 		commas.
	 * 
	 * <dt>Postcondition:
	 * 		<dd> The data from the binary file is added to the current tree and
	 * 		the file is closed.
	 * 
	 * @throws FileNotFoundException
	 * 		Indicates that the binary file being searched for was not found.
	 * 
	 * @throws IOException
	 * 		Indicates that an I/O operation has failed or been interrupted.
	 * 
	 * @throws IllegalArgumentException
	 *  	Indicates that a speed from the file is less than 0, or that a 
	 *  	direction is not NORTH or SOUTH.
	 */
	public void addSpeeds(String filename) throws FileNotFoundException, IOException, IllegalArgumentException {
		FileInputStream fis = null; // Object that obtains information from file.
	    InputStreamReader inStream; // Object that reads and decodes information from file.
	    BufferedReader reader; //  Object that reads the text from the InputStreamReader.
		String data[];
		
		try { // write data from file onto String data.
			fis = new FileInputStream(filename);
		} catch(FileNotFoundException e) {
			System.out.println("No such file found.");
		}
		
		inStream = new InputStreamReader(fis);
		reader = new BufferedReader(inStream);
	    
	    String nextLine = reader.readLine();
	    while(nextLine != null) {
	    	
	    	data = nextLine.split(",");
	    	
	    	if(data[2].trim().equalsIgnoreCase("N"))
	    		this.addSpeed(data[0], Double.parseDouble(data[1]), NORTH);
	    	else if(data[2].trim().equalsIgnoreCase("S"))
	    		this.addSpeed(data[0], Double.parseDouble(data[1]), SOUTH);
	    	
	    	nextLine = reader.readLine();
	    }
	    System.out.println();
	    fis.close();
	    inStream.close();
	    reader.close();
	}

	/**Searches the tree for the given road and returns the average speed in the
	 * given direction, which will be one of NORTH or SOUTH. If the road was not
	 * found, return -1. If the road was found, but there were no cars traveling
	 * in the specified direction, return -2.
	 * 
	 * @param road
	 * 		The name of the street.
	 * 
	 * @param direction
	 * 		The direction of the speed.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated and 
	 * 		direction is either NORTH or SOUTH.
	 * 
	 * @return the average speed in the given direction. If the road was not
	 * 		found, return -1. If the road was found, but there were no cars 
	 * 		traveling in the specified direction, return -2.
	 * 
	 * @throws IllegalArgumentException
	 *  	Indicates that direction is not NORTH or SOUTH.
	 */
	public double findSpeed(String road, int direction) throws IllegalArgumentException {
		if (direction != NORTH && direction != SOUTH)
			throw new IllegalArgumentException();
		double total = 0;
		Object[] speedArray;
		
		TrafficNode newNode;
		TrafficNode cursor;
		boolean done = false;
		
		if (root == null) { // empty tree
			return -1;
		}
		
		else { // not empty			
			cursor = root;
			while (!done) {
				if (road.equalsIgnoreCase(cursor.name)) {
					if(direction == NORTH) {
						if (cursor.north.size() == 0) return -2;
						speedArray = cursor.north.toArray();
						for(int i = 0; i < speedArray.length; i++) {
							total += (double)speedArray[i];
						}
						return total / speedArray.length;
					}
					if(direction == SOUTH) {
						if (cursor.south.size() == 0) return -2;
						speedArray = cursor.south.toArray();
						for(int i = 0; i < speedArray.length; i++) {
							total += (double)speedArray[i];
						}
						return total / speedArray.length;
					}				
					done = true;
				}
				
				
				else if (road.compareToIgnoreCase(cursor.name) < 0) {
					if (cursor.left == null) {
						return -1;
					}
					else cursor = cursor.left;
				}
				
				else if (road.compareToIgnoreCase(cursor.name) > 0) {
					if (cursor.right == null) {
						return -1;
					}
					else cursor = cursor.right;
				}
				else done = true;
			}
		}
		return -1;
	}
	
	/**Recursively returns the depth of a tree at the given root.
	 * 
	 * @param root
	 * 		The root node of the TrafficTree.
	 *
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated.
	 * 
	 * @return the depth of the tree, accessed by recursively calling this
	 * 		method repeatedly.
	 */
	public int depth(TrafficNode root) {
		if(root == null) return 0;
		return  1 + Math.max(depth(root.left), depth(root.right));


	}
	
	/**Uses an inorder traversal to print the name of each road in the tree
	 * along with the average of the northbound and southbound speeds stored in
	 * each node.
	 * 
	 * @param root
	 * 		The root node of the TrafficTree.
	 *
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated.
	 */
	public void printInorder(TrafficNode root) {
		double speedNorth;
		double speedSouth;
		
		if (root.left != null) printInorder(root.left);
		
		speedNorth = this.findSpeed(root.name, NORTH);
		speedSouth = this.findSpeed(root.name, SOUTH);
		System.out.print("\n" + root.name + ": Avg speed North = ");
		if (speedNorth == -2) System.out.print("(none) + . Avg speed South = ");
		else System.out.print(speedNorth + ". Avg speed South = ");
		if (speedSouth == -2) System.out.print("(none).");
		else System.out.print(speedSouth + ".");
		
		if (root.right != null) printInorder(root.right);
	}
	
	/**Uses a preorder traversal to print the name of each road in the tree
	 * along with the average of the northbound and southbound speeds stored in
	 * each node.
	 * 
	 * @param root
	 * 		The root node of the TrafficTree.
	 *
	 * <dt>Precondition:
	 * 		<dd>This <code>TrafficTree</code> object has been instantiated.
	 */
	public void printPreorder(TrafficNode root) {
		double speedNorth;
		double speedSouth;

		speedNorth = this.findSpeed(root.name, NORTH);
		speedSouth = this.findSpeed(root.name, SOUTH);
		System.out.print("\n" + root.name + ": Avg speed North = ");
		if (speedNorth == -2) System.out.print("(none) + . Avg speed South = ");
		else System.out.print(speedNorth + ". Avg speed South = ");
		if (speedSouth == -2) System.out.print("(none).");
		else System.out.print(speedSouth + ".");
		
		if (root.left != null) printPreorder(root.left);
		
		if (root.right != null) printPreorder(root.right);
	}
	
	/**Stores the tree as a binary file onto the current working directory.
	 * 
	 * @param filename 
	 * 		The name of the binary file.
	 * 
	 * @throws FileNotFoundException
	 * 		Indicates that the binary file being searched for was not found.
	 * 
	 * @throws IOException
	 * 		Indicates that an I/O operation has failed or been interrupted.
	 */
	public void serialize(String filename) throws FileNotFoundException, IOException{
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
			os.writeObject(this); // write object
			os.close();
		} catch (FileNotFoundException e) {
			System.out.println("\"" + filename + "\" not found.\n");
		} catch (IOException e) {
		}
		System.out.println("File saved.");	
	}
	
	/**Retrieves a tree from a binary file in the current working directory and
	 * stores it in a TrafficTree object.
	 * 
	 * @param filename 
	 * 		The name of the binary file.
	 * 
	 * @return A TrafficTree containing the tree given in the binary file.
	 * 
	 * @throws FileNotFoundException
	 * 		Indicates that the binary file being searched for was not found.
	 * 
	 * @throws IOException
	 * 		Indicates that an I/O operation has failed or been interrupted.
	 * 
	 * @throws ClassNotFoundException
	 * 		Indicates a specific class could not be found.
	 */
	public static TrafficTree deserialize(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
		TrafficTree t = null;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
			t = (TrafficTree)is.readObject(); // read object
			System.out.println("Loaded tree from \"" + filename + "\"");
			
			is.close();
		} catch (FileNotFoundException e) {
			System.out.println("\"" + filename + "\" not found. Using new tree.\n");
			t = new TrafficTree();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		return t;
	}
	
	
	
	//debug
	public static void main(String[] args) {
		
		TrafficTree t = new TrafficTree();
		t.addSpeed("Main Street", 50, NORTH);
		System.out.println("yes");
		t.printInorder(t.root);
	}	
	
}
