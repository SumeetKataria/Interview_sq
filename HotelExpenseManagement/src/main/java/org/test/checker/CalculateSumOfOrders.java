package org.test.checker;

import java.util.ArrayList;
import java.util.List;

import org.test.tiaa.pojos.CmfoodchainType;
import org.test.tiaa.pojos.OrderdetailType;
import org.test.tiaa.pojos.output.Branch;

public class CalculateSumOfOrders {

	public List<Branch> calculateSumOfOrdersForFoodChains(List<CmfoodchainType> allHotelsDetails) {
		List<Branch> result = new ArrayList<Branch>();

		if(null != allHotelsDetails) {
			for (CmfoodchainType cmfoodchainType : allHotelsDetails) {
				Branch branch = processFoodChain(cmfoodchainType);
				result.add(branch);
			}
		}
		return result;
	}

	private Branch processFoodChain(CmfoodchainType cmfoodchainType) {
		Branch result = new Branch();
		result.setLocation(cmfoodchainType.getBranch().getLocation());
		result.setLocationid(cmfoodchainType.getBranch().getLocationid());
		result.setTotalcollection(cmfoodchainType.getBranch().getTotalcollection());

		float totalOrdersSum = 0.0f;
		if(null != cmfoodchainType.getOrders()) {
			List<OrderdetailType> orders = cmfoodchainType.getOrders().getOrderdetail();

			if(orders != null) {
				for (OrderdetailType orderdetailType : orders) {
					totalOrdersSum += orderdetailType.getBillamount();
				}
			}
		}
		result.setSumoforder(totalOrdersSum);
		return result;
	}

}
