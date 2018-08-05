package org.test.checker;

import java.util.List;
import java.util.Map;

import org.test.tiaa.pojos.CmfoodchainType;
import org.test.tiaa.pojos.output.Branch;
import org.test.tiaa.pojos.output.Cmfoodchain;

public class CashCollectionCheckerMainClass {
	static String inputFolderPath = ".\\src\\main\\resources\\inputFiles";
	static String outputFolderPath = ".\\src\\main\\java\\org\\test\\result\\";

	public static void main(String[] args) {
		CashCollectionCheckerMainClass checker = new CashCollectionCheckerMainClass();
		checker.cashCollectionChecker();
	}

	// MAIN API
	public void cashCollectionChecker() {
		ReadAllFiles allFiles = new ReadAllFiles();
		List<CmfoodchainType> allFoodChainsDetails = allFiles.captureInputFilesToPojos(inputFolderPath);

		if(allFoodChainsDetails.size() > 0) {
			CalculateSumOfOrders calculateSumOfOrders = new CalculateSumOfOrders();
			List<Branch> sumOfOrderDetailsForBranches = calculateSumOfOrders.calculateSumOfOrdersForFoodChains(allFoodChainsDetails);

			Seggregators seggregators = new Seggregators();
			Map<String, Cmfoodchain> result = seggregators.findMatchAndMismatchOfOrders(sumOfOrderDetailsForBranches);

			SaveOutput output = new SaveOutput();
			output.saveResultsToFiles(outputFolderPath, result);
		}
	}
}
