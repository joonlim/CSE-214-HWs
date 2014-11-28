package hw7;

import java.io.Serializable;
import java.util.ArrayList;


public class Employer extends ArrayList<JobPosting> implements Serializable {

	private String name;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}
	
	public Employer(String name) {
		this.name = name;
	}

}
