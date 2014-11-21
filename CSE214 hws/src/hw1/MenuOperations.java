package hw1;

import java.text.DecimalFormat;
import java.util.Scanner;

/**Main program that contains menu operations.
 * 
 * @author Joon Hyung Lim 
 *   	   e-mail: joonhyung.lim@stonybrook.edu
 *   	   Stony Brook ID: 109558002
 */
public class MenuOperations {
	
	/**Method that prints out an operations menu for the user.
	 */
	public static void printMenu() {
		System.out.println("\n\nMain menu:\n");
		System.out.println("A) Add Item"); // This choice calls the addItem method.
		System.out.println("G) Get Item"); // This choice calls the getItem method.
		System.out.println("R) Remove Item"); // This choice calls the removeItem method.
		System.out.println("P) Print All Items"); // This choice calls the printAllItems method.
		System.out.println("S) Size"); // This choice calls the size method.
		System.out.println("D) Update description"); // This choice calls the updateDescription method.
		System.out.println("C) Update price"); // This choice calls the updatePrice method.
		System.out.println("O) Add to order"); // This choice calls the addToOrder method.
		System.out.println("I) Remove from order"); // This choice calls the removeFromOrder method.
		System.out.println("V) View order"); // This choice calls the viewOrder method.
		System.out.println("Q) Quit"); // This choice exits the while loop contained in the main method and terminates the program.
	}
	
	/**Prompts the user to enter the name, description, price, and position of a new MenuItem.
	 * Then this method calls Menu's addItem method and adds a MenuItem to the Menu's itemArray in the index of (position - 1).
	 * 
	 * @param m
	 * 		The menu being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated and position is equal to or greater than 1 and equal to or less than (numOfItems + 1).
	 * 
	 * <dt>Postcondition:
	 * 		<dd>The new <code>MenuItem is now stored at the desired position in the <code>Menu</code>.
	 * 		All the <code>MenuItem</code>s that were originally in positions greater than or equal to position are moved back one position.
	 * 
	 * @throws IllegalArgumentException
	 *  	Indicates that the position is not within the valid range.
	 *  
	 * @throws FullListException
	 * 		Indicates that the list contains <code>MAX_ITEMS</code> number of items and therefore is full.
	 */
	public static void addItem(Menu m) throws IllegalArgumentException, FullListException {
		Scanner input = new Scanner(System.in);
		DecimalFormat moneyFormat = new DecimalFormat("$0.00");
		
		System.out.print("\nEnter the name: ");
		String name = input.nextLine();
		
		System.out.print("\nEnter the description: ");
		String description = input.nextLine();
		
		System.out.print("\nEnter the price: ");
		double price = input.nextDouble();
		
		int position;
		
		if (m.size() != 0) {
			System.out.print("\nEnter the position (between 1 and "+ (m.size() + 1) + "): ");
			position = input.nextInt();
		}
		else {
			position = 1;
		}
		
		MenuItem i = new MenuItem(name, description, price);
		
		m.addItem(i, position);
		
		System.out.print("\nAdded \"" + i.getName() + ": " + i.getDescription() + "\" for " + moneyFormat.format(price) + ".");
	}
	
	
	/**Prompts the user to enter the position of the MenuItem they desire to see.
	 * Then this method displays the MenuItem in a neatly formatted table.
	 * This method does nothing if there are no MenuItems in the Menu.
	 * 
	 * @param m
	 * 		The menu being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated and position is equal to or greater than 1 and equal to or less than (numOfItems + 1).
	 * 
	 * @throws IllegalArgumentException
	 * 		Indicates that position is not within the valid range.
	 */
	public static void getItem (Menu m) throws IllegalArgumentException {
		if (m.size() < 1 )
			return;
		
		Scanner input = new Scanner(System.in);

		if (m.size() == 0)
			System.out.print("\nEnter the position(1): ");
		else {
			System.out.print("\nEnter the position(between 1 and "+ (m.size()) + "): ");
		}
		int position = input.nextInt();
		
		DecimalFormat moneyFormat = new DecimalFormat("$0.00");
		String leftAlignFormat = "%-4d %-20s %-51s %-4s%n";
		
		System.out.print("\n#    Name                 Description                                         Price\n");
		System.out.print("------------------------------------------------------------------------------------\n");
		System.out.printf(leftAlignFormat, position, m.getItem(position).getName(), m.getItem(position).getDescription(), moneyFormat.format(m.getItem(position).getPrice()));
	}
	
	/**Prompts the user to enter the position of the MenuItem they want to remove.
	 * Then this method calls Menu's removeItem method and removes the MenuItem from the Menu's itemArray in the index of (position - 1).
	 * This method does nothing if there are no MenuItems in the Menu.
	 * 
	 * @param m
	 * 		The menu being operated on.
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
	public static void removeItem(Menu m) throws IllegalArgumentException {
		if (m.size() < 1 )
			return;
		
		Scanner input = new Scanner(System.in);
		
		if (m.size() == 1) 
			System.out.print("\nEnter the position(1): ");
		else {
		System.out.print("\nEnter the position(between 1 and "+ (m.size()) + "): ");
		}
		int position = input.nextInt();
		
		String s = m.getItem(position).toString();
		
		m.removeItem(position);
		System.out.print("\nRemoved \"" + s + "\"");
		
	}
	
	/**Prints the menu by calling Menu's printAllItems method.
	 * 
	 * @param m
	 * 		The menu being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated.
	 * 
	 * <dt>PostCondition:
	 * 		<dd>A neatly formatted table of each MenuItem is displayed on its own line with its position number displayed to the user.
	 */
	public static void printAllItems(Menu m) {
		m.printAllItems();
	}
	
	/**Prints the number of MenuItems stored in the Menu's itemArray.
	 * 
	 * @param m
	 * 		The menu being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated.
	 */
	public static void size(Menu m) {
		if (m.size() == 1)
			System.out.print("\nThere is 1 item in the menu.");
		System.out.print("\nThere are " + m.getNumOfItems() + " items in the menu.");
	}
	
	/**Prompts the user to enter the position or name of a MenuItem on the menu.
	 * The method uses a try catch block to check to see if the user input is the position(integer) or name(String).
	 * Then uses the given value to find the MenuItem. The position is 1 more than the index of the Menu's itemArray.
	 * It then prompts the user to enter a new description that replaces the old one.
	 * This method does nothing if there are no MenuItems in the Menu.
	 * 
	 * @param m
	 * 		The menu being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated and position is equal to or greater than 1 and equal to or less than (numOfItems + 1).
	 * 
	 * @throws IllegalArgumentException
	 *		Indicates that position is not within the valid range.
	 *
	 * @throws NumberFormatException
	 * 		Indicates that the String being attempted to be converted to an int variable does not contain an integer.
	 */
	public static void updateDescription(Menu m) throws IllegalArgumentException, NumberFormatException {
		if (m.size() < 1 )
			return;
		
		Scanner input = new Scanner(System.in);
	
		if (m.size() == 1)
			System.out.print("\nEnter the position(1) or name of the item: ");
		else {
			System.out.print("\nEnter the position(between 1 and " + m.size() + ") or name of the item: ");
		}
		String a = input.nextLine();
			try {
				int position = Integer.parseInt(a);
				
				System.out.print("\nEnter the new description: ");
				String s = input.nextLine();
				
				m.getItem(position).setDescription(s);
			} catch (NumberFormatException e) {
				
				System.out.print("\nEnter the new description: ");
				String s = input.nextLine();
				
				m.getItemByName(a).setDescription(s);
			}

		System.out.print("\nNew description set.");
			
	}
	
	/**Prompts the user to enter the position or name of a MenuItem on the menu.
	 * The method uses a try catch block to check to see if the user input is the position(integer) or name(String).
	 * Then uses the given value to find the MenuItem. The position is 1 more than the index of the Menu's itemArray.
	 * It prompts the user to enter a new price that replaces the old one.
	 * This method does nothing if there are no MenuItems in the Menu.
	 * 
	 * @param m
	 * 		The menu being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated and position is equal to or greater than 1 and equal to or less than (numOfItems + 1).
	 * 
	 * @throws IllegalArgumentException
	 *		Indicates that position is not within the valid range.
	 *
	 * @throws NumberFormatException
	 * 		Indicates that the String being attempted to be converted to an int variable does not contain an integer.
	 */
	public static void updatePrice(Menu m) throws IllegalArgumentException, NumberFormatException{
		if (m.size() < 1 )
			return;
		
		Scanner input = new Scanner(System.in);
		DecimalFormat moneyFormat = new DecimalFormat("$0.00");
		
		if (m.size() == 1)
			System.out.print("\nEnter the position(1) or name of the item: ");
		else {
			System.out.print("\nEnter the position(between 1 and " + m.size() + ") or name of the item: ");
		}
		String a = input.nextLine();
			try {
				int position = Integer.parseInt(a);
				
				System.out.print("\nEnter the new price: ");
				double p = input.nextDouble();
				
				m.getItem(position).setPrice(p);
				
				System.out.print("\nChanged the price of \"" + m.getItem(position) + "\" to " + moneyFormat.format(m.getItem(position).getPrice()) + ".");
			} catch (NumberFormatException e) {
				
				System.out.print("\nEnter the new price: ");
				double p = input.nextDouble();
				
				m.getItemByName(a).setPrice(p);
				
				System.out.print("\nChanged the price of \"" + m.getItemByName(a) + "\" to " + moneyFormat.format(m.getItemByName(a).getPrice()) + ".");
			}
	}
	
	/**Prompts the user to enter the position of the MenuItem they want to add to the order.
	 * The position is 1 more than the index of the Menu's itemArray.
	 * The method adds the MenuItem to the next empty position in the order's itemArray an displays that it was added.
	 * This method does nothing if there are no MenuItems in the Menu.
	 * 
	 * @param m
	 * 		The menu being operated on.
	 * 
	 * @param order
	 * 		The customer's order as a Menu object being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated and position is equal to or greater than 1 and equal to or less than (numOfItems + 1).
	 * 
	 * <dt>Postcondition:
	 * 		<dd>The <code>MenuItem is now stored at the desired position in the new <code>Menu</code>.
	 * 
	 * @throws IllegalArgumentException
	 * 		Indicates that position is not within the valid range.
	 * 
	 * @throws FullListException
	 * 		Indicates that the list contains <code>MAX_ITEMS</code> number of items and therefore is full.
	 */		
	public static void addToOrder(Menu m, Menu order) throws IllegalArgumentException, FullListException {
		if (m.size() < 1 )
			return;
		
		Scanner input = new Scanner(System.in);
		
		if (m.size() == 1)
			System.out.print("\nEnter the position(1) of item to add to order: ");
		else {
			System.out.print("\nEnter the position(between 1 and "+ (m.size()) + ") of item to add to order: ");
		}
		int position = input.nextInt();
		
		order.addItem(m.getItem(position), order.getNumOfItems() + 1);
		
		System.out.print("\nAdded \"" + m.getItem(position) + "\" to order.");
	}
	
	/**Prompts the user to enter the position of the MenuItem they want to remove from the order.
	 * This method does nothing if there are no MenuItems in the order Menu.
	 * 
	 * @param order
	 * 		The customer's order as a Menu object being operated on.
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
	public static void removeFromOrder(Menu order) throws IllegalArgumentException {
		if (order.size() < 1)
			return;
		
		Scanner input = new Scanner(System.in);
		
		if (order.size() == 1)
			System.out.print("\nEnter the position(1): ");
		else {
			System.out.print("\nEnter the position(between 1 and "+ (order.size()) + "): ");
		}
		int position = input.nextInt();
		
		order.removeItem(position);
	}
	
	/**Prints the order by calling Menu's printOrder method.
	 * It displays the order and the total number of items along with the total price.
	 * 
	 * @param order
	 * 		The customer's order as a Menu object being operated on.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Menu</code> object has been instantiated.
	 * 
	 * <dt>PostCondition:
	 * 		<dd>A neatly formatted table of each MenuItem is displayed on its own line with its position number displayed to the user.
	 */
	public static void viewOrder(Menu order) {
		order.printOrder();
	}
	
	public static void main(String[] args) throws IllegalArgumentException, FullListException {
		
		Menu m = new Menu();
		Menu order = new Menu(); // Creates a new instance of the Menu object to store MenuItems in an order.
		
		Scanner input = new Scanner(System.in);
		char c = ' ';
		
		while (c != 'Q') {
			printMenu();
			System.out.print("\nSelect an operation: ");
			
			c = Character.toUpperCase(input.next().charAt(0));
	
			switch (c) {
			case 'A':addItem(m);
					 break;
			case 'G':getItem(m);
					 break;
			case 'R':removeItem(m);
			 		 break;
			case 'P':printAllItems(m);
					 break;
			case 'S':size(m);
					 break;
			case 'D':updateDescription(m);
					 break;
			case 'C':updatePrice(m);
					 break;
			case 'O':addToOrder(m, order);
					 break;
			case 'I':removeFromOrder(order);
					 break;
			case 'V':viewOrder(order);
					 break;
			default: break;
			}	
		}
	}	
}
