package hw7;

import java.util.Comparator;

public class JobPostingVacationComparator implements Comparator<JobPosting> {

	public int compare(JobPosting first, JobPosting second) {

		return first.getVacationDays() - second.getVacationDays();
	}

}
