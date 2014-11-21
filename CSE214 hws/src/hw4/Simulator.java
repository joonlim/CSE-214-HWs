package hw4;
////////////////////////////////////////////////////////////////////////////////
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

import javax.annotation.processing.FilerException;

/**This program simulates the staff of a large company as they complete several 
 * projects over the course of several days. Each day, there is a certain
 * probability that a new project will come in. New projects are placed on a
 * queue to be completed when employees become available. The company has
 * vacation days which are specified prior to running the simulation. The time
 * unit of this simulation is one day.
 * 
 * @author Joon Hyung Lim 
 *   	   e-mail: joonhyung.lim@stonybrook.edu
 *   	   Stony Brook ID: 109558002
 */
public class Simulator {
	int numEmployees = 0; // The number of employees working for the company.
	int minProjectTime = 0; // The minimum amount of time a project must require.
	int maxProjectTime = 0; // The maximum amount of time a project can require.
	int maxProjectEmployees = 0; // The maximum number of employees a project can require.
	double arrivalProb = -1; // The probability of a project arriving each day.
	int[] vacationDays; // A list of days where no work is done.
	int duration = 0; // The duration of the simulation, given by the user.
	
	int numFreeEmployees; // The number of employees who are currently not assigned to any project.
	int projectsCompleted = 0; // The number of projects completed in the simulation so far.
	int totalTimeWaited = 0; // The total time waited in the queue by all projects in the simulation so far.
	ArrayList<Project> activeProjects; // The list of projects currently being worked on.
	ProjectQueue queue = new ProjectQueue(); // Queue of projects to do.

    /**Returns an instance of <code>Simulator</code>. Prompts the user to enter
     * the number of employees in the company, the minimum number of days a
     * project can take, the maximum number of days a project can take, the
     * maximum number of employees a project can require, the arrival probability of
     * a project, the duration of the simulation, and the vacation days.
	 * 
	 * <dt>Postcondition:
	 * 		<dd>A Simulator object is created with numEmployees, minProjectTime,
	 * 		maxProjectTime, maxProjectEmployees, and duration greater than 0.
	 * 		<code>arrivalProb</code> is set to a number between 0 and 1.
	 * 		The vacation days must be within the duration.
	 */
	public Simulator(){
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.print("Enter the number of employees: ");
			numEmployees = input.nextInt();
			if(numEmployees <= 0) 
				System.out.println("Enter a number greater than 0.");
		} while (numEmployees <= 0);
		
		do {
			System.out.print("Enter the minimum project time: ");
			minProjectTime = input.nextInt();
			if(minProjectTime <= 0) 
				System.out.println("Enter a number greater than 0.");
		} while (minProjectTime <= 0);
		
		do {
			System.out.print("Enter the maximum project time: ");
			maxProjectTime = input.nextInt();
			if(numEmployees <= 0) 
				System.out.println("Enter a number greater than 0.");
			if(maxProjectTime < minProjectTime)
				System.out.println("Maximum project time cannot be less than the minimum project time.");
		} while (maxProjectTime <= 0 || maxProjectTime < minProjectTime);
		
		do {
			System.out.print("Enter the maximum number of employees for a project: ");
			maxProjectEmployees = input.nextInt();
			if(maxProjectEmployees <= 0)
				System.out.println("Enter a number greater than 0.");
		} while (maxProjectEmployees <= 0);
		
		do {
			System.out.print("Enter the arrival probability: ");
			arrivalProb = input.nextDouble();
			if(arrivalProb < 0 || arrivalProb > 1)
				System.out.println("Enter a probability between 0 and 1.");
		} while (arrivalProb < 0 || arrivalProb > 1);
		
		System.out.print("Enter the list of vacation days, separated by space: ");
		input.nextLine();
		String data = input.nextLine();
		
		do {
			System.out.print("Enter the duration of the simulation: ");
			duration = input.nextInt();
			if(duration <= 0)
				System.out.println("Enter a number greater than 0.");
		} while(duration <= 0);
		
		vacationDays = new int[duration];

		String[] days = data.split(" ");
		for(int i = 0; i < days.length; i++) {
			vacationDays[i] = Integer.parseInt(days[i]);
		}
		
		input.close();
	 }
	
	/**Runs the actual simulation over the given duration and returns the
	 * average waiting time for each project.
	 * 
	 * @param duration
	 * 		The duration of the simulation in days.
	 * 
	 * <dt>Precondition:
	 * 		<dd>This <code>Simulator</code> object has been instantiated and
	 *		its values for numEmployees, minProjectTime, maxProjectTime,
	 *		maxProjectEmployees, and duration are greater than 0. Its
	 *		<code>arrivalProb</code> is set to a number between 0 and 1.
	 *		The vacation days must be within the duration.
	 * 
	 * @return the average waiting time for each project.
	 */
	 public double simulate(int duration) {
		 numFreeEmployees = numEmployees;
		 projectsCompleted = 0;
		 totalTimeWaited = 0;
		 activeProjects = new ArrayList(); // ArrayList of Project.
		 boolean vacaDay = false;
		 int employeesRequired;
		 int daysRequired;
		 int numberOfProjects = 0;
		 ArrayList<Project> completed = new ArrayList();
		 
		 for(int day = 1; day <= duration; day++) { // length of the simuation.
			 System.out.println("\nDay " + day);
			 
			 for(int i = 0; i < vacationDays.length; i++) { // check if vacation day.
				 if(day == vacationDays[i])
					 vacaDay = true;
			 }
			 if(vacaDay == true) { 
				 vacaDay = false;
				 System.out.println("Vacation day");
				 continue;
			 }
			 
			 // not a vacation day
			 if(Math.random() <= arrivalProb) { // create a random Project object and enqueue it.
				 numberOfProjects++;
				 employeesRequired = (int)(Math.random()*(maxProjectEmployees) + 1);
				 daysRequired = (int)(Math.random()*(maxProjectTime - minProjectTime + 1)) + minProjectTime;
				 
				 System.out.println("A project arrives requiring " + employeesRequired + " employees for " + daysRequired + " days");
			 
				 queue.enqueue(new Project(day, employeesRequired, daysRequired));
			 }
			 
			 if(!(queue.isEmpty())){ // if there are projects in the queue, add them to the active projects list if the required number of employees is met.
				// System.out.println("not empty");
				 if(numFreeEmployees >= queue.peek().numOfEmployees) {
					 numFreeEmployees -= queue.peek().numOfEmployees;
					 activeProjects.add(queue.dequeue());
				 }
			 }

			 
			 for(int i = 0; i < activeProjects.size(); i++) {
				 activeProjects.get(i).numOfDays--;
				 if (activeProjects.get(i).numOfDays == 0) {
					 completed.add(activeProjects.get(i));
					 activeProjects.remove(i);
					 i--;		 
				 }
				 
			 }
			 
			 System.out.print("Active projects: [");
			 for(int i = 0; i < activeProjects.size(); i++) {
				 if(i > 0)
					 System.out.print(", ");
				 System.out.print("(" + activeProjects.get(i).dayQueued + ", " + activeProjects.get(i).numOfEmployees + ", " + activeProjects.get(i).numOfDays + ")");
			 }
			 System.out.println("]");
			 
			 
			 
			 for(int i = 0; i < completed.size(); i++) { // completed projects
				 int daysAgo = day - completed.get(i).dayQueued;
				 System.out.println("A project was completed. It arrived in the queue " + daysAgo + " days ago.");
				 
				 System.out.println("Projects completed: " + ++projectsCompleted);
				 totalTimeWaited += daysAgo;
				 System.out.println("Total time waited: " + totalTimeWaited);
				 numFreeEmployees += completed.get(i).numOfEmployees;
			 }
			 
			 completed = new ArrayList();
			 
			 
			 
			 System.out.println("Free Employees: " + numFreeEmployees);
			 
			 System.out.print("Projects queue: [");
			 for(int i = 0; i < queue.queue.size(); i++) {
				 if(i > 0)
					 System.out.print(", ");
				 System.out.print("(" + queue.queue.get(i).dayQueued + ", " + queue.queue.get(i).numOfEmployees + ", " + queue.queue.get(i).numOfDays + ")");
			 }
			 System.out.println("]");
		 } 
		 return totalTimeWaited * 1.0 / projectsCompleted;
	 }
	 
	 /**Checks to see if the current day is a vacation day.
	  * 
	  * @return true if the current day matches one of the given vacation days.
	  */
	 public boolean isVacationDay() {
		 return false;
	 }

	 
	 public static void main(String[] args) {
		Simulator s = new Simulator();
		
		double average = s.simulate(s.duration);
		
		System.out.println("\n");
		System.out.println(s.projectsCompleted + " projects completed. Average waiting time was " + average + " days per project.");
	 }
}
