package org.test.checker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.test.tiaa.pojos.output.Branch;
import org.test.tiaa.pojos.output.Cmfoodchain;

public class Seggregators {

	private static final String UNMATCHED = "UNMATCHED";
	private static final String MATCHED = "MATCHED";

	public Map<String, Cmfoodchain> findMatchAndMismatchOfOrders(List<Branch> branches) {
		Map<String, Cmfoodchain> result = new HashMap<String, Cmfoodchain>();
		Cmfoodchain matched = new Cmfoodchain();
		Cmfoodchain unmatched = new Cmfoodchain();
		
		if(null != branches) {
			for (Branch branch : branches) {
				if(branch.getSumoforder() == branch.getTotalcollection()) {
					matched.getBranch().add(branch);
				}
				else {
					unmatched.getBranch().add(branch);
				}
			}
		}

		if(matched.getBranch() != null && matched.getBranch().size() > 0)
			result.put(MATCHED, matched);
		if(unmatched.getBranch() != null && unmatched.getBranch().size() > 0)
			result.put(UNMATCHED, unmatched);
		return result;
	}
}
