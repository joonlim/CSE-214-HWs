package hw7;

import java.util.Comparator;

public class JobPostingWeightedComparator implements Comparator<JobPosting> {

	int salaryWeight;
	int hoursWeight;
	int vacationWeight;

	public JobPostingWeightedComparator(int salaryWeight, int hoursWeight,
			int vacationWeight) {

		this.salaryWeight = salaryWeight;
		this.hoursWeight = hoursWeight;
		this.vacationWeight = vacationWeight;
	}

	public int compare(JobPosting first, JobPosting second) {

		int preferenceFirst = first.getSalary() * salaryWeight
				+ first.getHrsPerWeek() * hoursWeight + first.getVacationDays()
				* vacationWeight;

		int preferenceSecond = second.getSalary() * salaryWeight
				+ second.getHrsPerWeek() * hoursWeight
				+ second.getVacationDays() * vacationWeight;

		return preferenceFirst - preferenceSecond;
	}

}
