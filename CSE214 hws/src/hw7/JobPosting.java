package hw7;

import java.io.Serializable;

public class JobPosting implements Serializable {

	private String title;
	private int salary;
	private int hrsPerWeek;
	private int vacationDays;

	public JobPosting(String title, int salary, int hrsPerWeek, int vacationDays) {

		this.title = title;
		this.salary = salary;
		this.hrsPerWeek = hrsPerWeek;
		this.vacationDays = vacationDays;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public int getSalary() {

		return salary;
	}

	public void setSalary(int salary) {

		this.salary = salary;
	}

	public int getHrsPerWeek() {

		return hrsPerWeek;
	}

	public void setHrsPerWeek(int hrsPerWeek) {

		this.hrsPerWeek = hrsPerWeek;
	}

	public int getVacationDays() {

		return vacationDays;
	}

	public void setVacationDays(int vacationDays) {

		this.vacationDays = vacationDays;
	}
	
	
	
	
}
