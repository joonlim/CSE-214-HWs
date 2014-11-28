package hw7;

import java.util.Comparator;

public class EmployerNumJobsComparator implements Comparator<Employer> {

	public int compare(Employer first, Employer second) {

		return first.size() - second.size();
	}

}
