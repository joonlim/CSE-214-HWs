package hw4;

/**Projects that are created by <code>Simulator</code>. Each project keeps track
 * of the time on the simulation clock when it entered the queue of pending
 * projects, the number of employees required to work on the project, and the
 * number of days it will take to complete the project.
 * 
 * @author Joon Hyung Lim 
 *   	   e-mail: joonhyung.lim@stonybrook.edu
 *   	   Stony Brook ID: 109558002
 */
public class Project {
	int dayQueued; // The time on the simulation clock when the project enters a queue.
	int numOfEmployees; // The number of employees required to work on the project.
	int numOfDays; // The number of days it will take to complete the project.
	
    /**Returns an instance of <code>Project</code>.
     * 
     * @param dayQueued
     * 		The time on the simulation clock when the new project enters a queue.
     * 
     * @param numOfEmployees
     * 		The number of employees required to work on the new project.
     * 
     * @param numOfDays
     * 		The number of days it will take to complete the new project.
     *
     * <dt>Precondition:
     * 		dayQueued, numOfEmployees, and numOfDays are greater than 0.
     *     
     * @exception IllegalArgumentException
     * 		Indicates that any of the parameters entered are 0 or less than 0.
     */
	public Project(int dayQueued, int numOfEmployees, int numOfDays) throws IllegalArgumentException{
		if(dayQueued <= 0 || numOfEmployees <= 0 || numOfDays <= 0)
			throw new IllegalArgumentException();
		this.dayQueued = dayQueued;
		this.numOfEmployees = numOfEmployees;
		this.numOfDays = numOfDays;
	}	
}