package hw1;

/**Exception that is thrown if a MenuObject is attempted to be added into a full Menu.
 * 
 * @author Joon Hyung Lim 
 *   	   e-mail: joonhyung.lim@stonybrook.edu
 *   	   Stony Brook ID: 109558002
 */
public class FullListException  extends Exception {
	private int max; // The total number of items that can be allowed on the menu.
	
	/**Returns an instance of <code>FullListException</code>.
	 * 
	 * @param max
	 * 		The total number of items that can be allowed on the menu.
	 */
	public FullListException(int max) {
		super("The list is full with " + max + " items.");
		this.max = max;
	}	
}