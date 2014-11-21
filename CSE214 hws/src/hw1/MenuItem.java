package hw1;

/**Items that are contained on the menu.
 * 
 * @author Joon Hyung Lim 
 *   	   e-mail: joonhyung.lim@stonybrook.edu
 *   	   Stony Brook ID: 109558002
 */

public class MenuItem {
	String name; // The name of the menu item.
	String description; // A description of the menu item.
	double price; // The price of the menu item.
	
	/**Gets the name of this menu item.
	 * 
	 * @return this MenuItem's name.
	 */
	public String getName() {
		return name;
	}
	
	/**Sets the name of this menu item.
	 * 
	 * @param name
	 * 		This menu item's new name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**Gets the description of this menu item.
	 * @return this MenuItem's description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**Sets the description of this menu item.
	 * 
	 * @param description
	 * 		This menu item's new description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**Gets the price of this menu item.
	 * @return this MenuItem's price.
	 */
	public double getPrice() {
		return price;
	}
	
	/**Sets the price of this menu item.
	 * 
	 * @param price 
	 * 		This menu item's new price.
	 * 		Should not be a negative number.
	 *     
	 * @exception IllegalArgumentException
     *     Indicates that <code>price</code> is less than zero.
	 */
	public void setPrice(double price) throws IllegalArgumentException{
		if (price < 0)
			throw new IllegalArgumentException("Price out of range.");	
	
		this.price = price;
	}
	
    /**Returns an instance of <code>MenuItem</code>.
     *
     * @param name
     * 		The name of the menu item.
     *  
     * @param description
     * 		The description of the menu item.
     * 
     * @param price
     * 		The price of the menu item.
     *
     * <dt>Precondition:
     * 		<dd><code>price</code> must be greater than or equal to 0.
     * 
     * @exception IllegalArgumentException
     * 		Indicates that <code>price</code> is less than zero.
     */
	public MenuItem(String name, String description, double price) throws IllegalArgumentException {
		if (price < 0)
			throw new IllegalArgumentException("Price out of range.");
		
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	/**Overrided toString method. Returns the name of the MenuItem.
	 * 
	 * @return this MenuItem's name.
	 */
	public String toString() {
		return name;
	}
	
	/**Overrided equals method. Compares a particular object with a MenuItem object; Checks to see if name, description, and price are logically equivalent.
	 * 
	 * @param obj
	 * 		the object to be compared with the current MenuItem.
	 * @return true if obj refers to a MenuItem object with the same name, description, and price. Otherwise, return false.
	 */
	
	public boolean equals (Object obj) {
		if(this == obj)
			return true;
		if( !(obj instanceof MenuItem) )
			return false;
		MenuItem m = (MenuItem) obj;
		if (this.getName().equals(m.getName()) && this.getDescription().equals(m.getDescription()) && this.getPrice() == m.getPrice())
			return true;
		else
			return false;
	}
	
}