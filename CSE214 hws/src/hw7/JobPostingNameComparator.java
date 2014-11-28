package hw7;

import java.util.Comparator;

public class JobPostingNameComparator implements Comparator<JobPosting> {

	public int compare(JobPosting first, JobPosting second) {

		return first.getTitle().compareToIgnoreCase(second.getTitle());
	}

}
