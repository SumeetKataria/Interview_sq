package org.test.tiaa.pojos.output;

import java.util.ArrayList;
import java.util.List;

public class Cmfoodchain {
	
	private List<Branch> branch;

	public Cmfoodchain() {
		branch = new ArrayList<>();
	}
	
	public List<Branch> getBranch() {
		return branch;
	}

	public void setBranch(List<Branch> branch) {
		this.branch = branch;
	}
}
