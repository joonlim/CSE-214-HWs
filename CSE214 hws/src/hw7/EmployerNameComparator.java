package hw7;

import java.util.Comparator;

public class EmployerNameComparator implements Comparator<Employer> {

	public int compare(Employer first, Employer second) {

		return first.getName().compareToIgnoreCase(second.getName());
	}

	public static void main(String[] args) {
		//test
		EmployerNameComparator c = new EmployerNameComparator();

		Employer a = new Employer("Amanda");
		Employer b = new Employer("Betty");

		System.out.println(c.compare(a, b));
	}
}