package hw7;

import java.util.Comparator;

public class JobPostingSalaryComparator implements Comparator<JobPosting> {

	public int compare(JobPosting first, JobPosting second) {

		return first.getSalary() - second.getSalary();
	}

}
