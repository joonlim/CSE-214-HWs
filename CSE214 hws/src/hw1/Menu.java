package hw1;

import java.text.DecimalFormat;

/**
 * The actual menu of the restaurant. It contains up to 50 items, each of which has a name,
 * description, and price.
 * 
 * The <code>Menu</code> class implements a heap of <code>MenuItem</code> objects.
 *
 * @author Joon Hyung Lim
 *   	   e-mail: joonhyung.lim@stonybrook.edu
 *    	  Stony Brook ID: 109558002
 **/
public class Menu implements Cloneable {
	final int MAX_ITEMS = 50; // The total number of items that can be allowed on the menu.
	MenuItem[] itemArray; // An array that holds the menu items.
	private int numOfItems; // The number of items currently on the menu.
	
	/**Gets the number of items currently on this Menu. 
	 * @return this Menu's numOfItems.
	 */
	public int getNumOfItems() {
		return numOfItems;
	}
	
    /**Returns an instance of <code>Menu</code>.
     *
     * <dt>Postcondition:
     *     <dd>This <code>Menu</code> is initialized to an empty list of <code>MenuItem</code>s.
     */
	public Menu() {
		itemArray = new MenuItem[MAX_ITEMS];
		numOfItems = 0;
	}
	
	/**Overrided clone method. Clones all data from a particular Menu into a new Menu reference.
	 * @return a copy of a Menu as an **Object**.
	 */
	public Object clone() {
		Menu menu = null;
		MenuItem[] cloneArray = new MenuItem[MAX_ITEMS];
		for (int i = 0; i < numOfItems; i++)
			cloneArray[i] = itemArray[i];
		menu.numOfItems = this.numOfItems;
		
		return menu;
	}
	
	/**Overrided equals method. Compares a particular object with a Menu object; Uses MenuItem's equals method to check if item in the array is logically equivalent.
	 * 
	 * @param obj
	 * 		the object to be compared with the current Menu.
	 * @return true if obj refers to a Menu object with the same MenuItems in the same order as this Menu. Otherwise, return false.
	 */
	public boolean equals (Object obj) {
		if(this == obj)
			return true;
		if( !(obj instanceof Menu) )
			return false;
		Menu menu = (Menu) obj;
		boolean b = false;
		for(int i = 0; i < MAX_ITEMS; i++) {
			b = this.itemArray[i].equals(menu.itemArray[i]);
			if (!b)
				return false;
		}
		return true;
	}
	
	/**Determines the number of items currently in this Menu.
	 * 
     * <dt>Precondition:
     *     <dd>This <code>Menu</code> has been instantiated.
	 * 
	 * @return the number of items in the array.
	 */
	public int size() {
		return numOfItems;
	}
	
	/**Adds a MenuItem to itemArray in the index of (position - 1).
	 * 
	 * @param item
	 * 		MenuItem being added to itemArray.
	 * 
	 * @param position
	 * 		The position of the menu that the MenuItem is being added to. The index of the array it is added to is (position - 1).
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated and position is equal to or greater than 1 and equal to or less than (numOfItems + 1).
	 * 
	 * <dt>Postcondition:
	 * 		<dd>The new <code>MenuItem is now stored at the desired position in the <code>Menu</code>.
	 * 		All the <code>MenuItem</code>s that were originally in positions greater than or equal to position are moved back one position.
	 * 
	 * @throws IllegalArgumentException
	 *  	Indicates that position is not within the valid range.
	 *  
	 * @throws FullListException
	 * 		Indicates that the list contains <code>MAX_ITEMS</code> number of items and therefore is full.
	 */
	public void addItem(MenuItem item, int position) throws IllegalArgumentException, FullListException {
		try {
			if (position < 1 || position > numOfItems + 1)
				throw new IllegalArgumentException();
			if (numOfItems == MAX_ITEMS)
				throw new FullListException(numOfItems);
			} catch (IllegalArgumentException e) {
				System.out.print("\nEnter number between 1 and " + (numOfItems + 1));
			} catch (FullListException e) {
				System.out.print("\nThe Menu is full with " + numOfItems + " items.");
			}
		
		for (int i = numOfItems; i > (position - 1); i--)
			itemArray[i] = itemArray[i - 1];
		
		itemArray[position - 1] = item;
		numOfItems++;
		
	}
	
	/**Removes the MenuItem at the given position. It is located in the index of (position - 1) in the itemArray.
	 * 
	 * @param position
	 * 		The position of the menu that contains the MenuItem that is being removed . The index of the array is (position - 1).
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated and position is equal to or greater than 1 and equal to or less than (numOfItems + 1).
	 * 
	 * <dt>Postcondition:
	 * 		<dd>The <code>MenuItem</code> at the desired position in the <code>Menu</code> has been removed.
	 * 		All <code>MenuItem</code>s that were originally in positions greater than or equal to position are moved forward one position.
	 * 
	 * @throws IllegalArgumentException
	 * 		Indicates that position is not within the valid range.
	 */
	public void removeItem(int position) throws IllegalArgumentException {
		try {
			if (position < 1 || position > numOfItems)
				throw new IllegalArgumentException("\nEnter number between 1 and " + numOfItems);
			} catch (IllegalArgumentException e) {
				System.out.print("\nEnter number between 1 and " + numOfItems);
			}
		
		if (position == MAX_ITEMS)
			itemArray[MAX_ITEMS - 1] = null;
		else {
			for (int i = (position - 1); i < numOfItems; i++)
				itemArray[i] = itemArray[i+1];
		}
		numOfItems--;
	}
	
	/**Return the MenuItem at the given position in this Menu object.
	 * 
	 * @param position
	 * 		The position of the menu that contains the MenuItem to be retrieved. The index of the array is (position - 1).
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated and position is equal to or greater than 1 and equal to or less than (numOfItems + 1).
	 * 
	 * @return the MenuItem at located in the index of (position - 1) of itemArray.
	 * 
	 * @throws IllegalArgumentException
	 * 		Indicates that position is not within the valid range.
	 */
	public MenuItem getItem (int position) throws IllegalArgumentException {
		try {
			if (position < 1 || position > numOfItems)
				throw new IllegalArgumentException();
			} catch (IllegalArgumentException e) {
				System.out.println("Enter number between 1 and " + numOfItems);
			}
		
		return itemArray[position - 1];
	}

	/**Return the MenuItem with the given name.
	 * 
	 * @param name
	 * 		The name of the MenuItem being searched for.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated.
	 * 
	 * @return the MenuItem with that matches the given name.
	 * 
	 * @throws IllegalArgumentException
	 * 		Indicates that the given name does not match any names of MenuItems on the menu.
	 */
	public MenuItem getItemByName (String name) throws IllegalArgumentException {
		for (int i = 0; i < numOfItems; i++)
			if (itemArray[i].getName().equalsIgnoreCase(name))
				return itemArray[i];
		throw new IllegalArgumentException("Item not found.");
	
	}
	
	/**Uses the toString method to print out a neatly formatted table of each MenuItem in the Menu in order.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated.
	 * 
	 * <dt>PostCondition:
	 * 		<dd>A neatly formatted table of each MenuItem is displayed on its own line with its position number displayed to the user.
	 */
	public void printAllItems() {
		String s = "\nMENU:\n\n" + toString();
		
		System.out.println(s);
	}
	
	/**Uses the toString method to print out a neatly formatted table of each MenuItem in the Menu in order.
	 * This method is used to print the order, which made using Menu. It differs from printAllItems in that
	 * it displays the total number of MenuItems and the total price.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated.
	 * 
	 * <dt>PostCondition:
	 * 		<dd>A neatly formatted table of each MenuItem is displayed on its own line with its position number displayed to the user.
	 */
	public void printOrder() {
		DecimalFormat moneyFormat = new DecimalFormat("$0.00");
		String leftAlignFormat = "%-4d %-20s %-51s %-4s%n";
		double total = 0;
		
		for(int i = 1; i <= this.numOfItems; i++)
			total += this.getItem(i).getPrice();
		
		String s = String.format(leftAlignFormat, this.numOfItems, "Total", " ", moneyFormat.format(total));
				
		s = "\nORDER:\n\n" + toString() + "------------------------------------------------------------------------------------\n" + s;
		
		System.out.print(s);
		
	}
	
	/**Overrided toString method. Returns the String representation of this Menu object, which is a neatly formatted table of each MenuItem in the Menu in order.
	 * 
	 * @return a String that contains a natly formatted table of each MenuItem in the Menu in order.
	 */
	public String toString() {
		DecimalFormat moneyFormat = new DecimalFormat("$0.00");
		
		String leftAlignFormat = "%-4d %-20s %-51s %-4s%n";
		
		String s = "#    Name                 Description                                         Price\n";
		s +=("------------------------------------------------------------------------------------\n");
		for (int i = 0; i < numOfItems; i++)
			s += String.format(leftAlignFormat, i + 1, itemArray[i].getName(), itemArray[i].getDescription(), moneyFormat.format(itemArray[i].getPrice()));
		return s;
	}
	
	
	
	/**test
	 */
	
	
	public static void main(String[] args) throws IllegalArgumentException, FullListException {

		
		
		Menu m = new Menu();
		MenuItem i1 = new MenuItem("Buffalo Wings", "Deep fried chicken wings in buffalo sauce.", 10);
		MenuItem i2 = new MenuItem("California Roll", "Crab, avocado, and cucumber in a roll", 6);
		MenuItem i3 = new MenuItem("Hamburger", "100% beef burger", 7.5);

		
		
		m.addItem(i1, 1);
		m.addItem(i2, 2);
		m.addItem(i3, 3);
		

		m.printAllItems();
	}
	
}