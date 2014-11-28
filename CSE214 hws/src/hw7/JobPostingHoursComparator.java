package hw7;

import java.util.Comparator;


public class JobPostingHoursComparator implements Comparator<JobPosting> {

	public int compare(JobPosting first, JobPosting second) {

		return first.getHrsPerWeek() - second.getHrsPerWeek();
	}

}
