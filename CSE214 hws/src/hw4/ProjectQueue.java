package hw4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**This class represents the list of pending projects as a queue.
 * 
 * @author Joon Hyung Lim 
 *   	   e-mail: joonhyung.lim@stonybrook.edu
 *   	   Stony Brook ID: 109558002
 */
public class ProjectQueue {
	ArrayList<Project> queue;
	int size; // The number of <code>Project</code> objects in the queue.
	int head;
	int tail;
	
	
    /**Returns an instance of <code>ProjectQueue</code>.
    *
    * <dt>Postcondition:
    * 		<dd> A new empty ProjectQueue object is created.
    */
	public ProjectQueue() {
		queue = new ArrayList();
		size = 0;
		head = 0;
		tail = 0;
	}
	
	/**Add a <code>Project</code> to the tail of the queue.
	 * 
	 * @param c
	 * 		The <code>Project</code> being enqueued.
	 */
	public void enqueue(Project c) {
		queue.add(c);
		tail = tail + 1;
		size++;
	}
	
	/**Remove and return the <code>Project</code> at the head of the queue.
	 * 
	 * @return the <code>Project</code> that is dequeued.
	 */
	public Project dequeue() {
		size--;
		if (head == tail) {// list is now empty.
			System.out.println("empty queue.");
		}
		return queue.remove(head);
	}
	
	/**Return the <code>Project</code> at the head of the queue without removing it.
	 * 
	 * @return the <code>Project</code> at the head of the queue.
	 */
	public Project peek() {
		return queue.get(head);
	}
	
	/**Return the number of projects in the queue.
	 * 
	 * @return the size of the queue.
	 */
	public int size() {
		return size;
	}

	/**Overrided isEmpty() method. Checks to see if a ProjectQueue is empty.
	 * 
	 * @return true if the ProjectQueue does not contain any Project objects
	 * 		in the queue, false if <code>size</code> is greater than 0.
	 */
	public boolean isEmpty() {
		if(size > 0) {
			return false;
		}
		else {
			return true;
		}
	}
		
	/**Overrided equals() method. Compares a particular object with a ProjectQueue
	 * object; Checks to see if both queues have <code>Project</code> objects
	 * with the same values for dayQueued, numOfDays, and numOfEmployees and are
	 * in the same order in the queue.
	 * 
	 * @param obj
	 * 		the object to be compared with current ProjectQueue.
	 * 
	 * @return true if obj refers to a ProjectQueue object that is logically equivalent.
	 * 	
	 */
	public boolean equals(Object obj) {
		ProjectQueue a = (ProjectQueue)obj;
		for(int i = head; i < tail; i++) {
			if (!(queue.get(i).dayQueued == a.queue.get(i).dayQueued && queue.get(i).numOfDays == a.queue.get(i).numOfDays && queue.get(i).numOfEmployees == a.queue.get(i).numOfEmployees)){
				return false;
			}
		}
		return true;
	}
}