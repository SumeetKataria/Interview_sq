package org.test.checker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.test.tiaa.pojos.output.Cmfoodchain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class SaveOutput {

	private static final String UNMATCHED = "UNMATCHED";
	private static final String MATCHED = "MATCHED";
	
	public void saveResultsToFiles(String outputFolderPath, Map<String, Cmfoodchain> input) {
		saveFile(input.get(MATCHED), MATCHED, outputFolderPath);
		saveFile(input.get(UNMATCHED), UNMATCHED, outputFolderPath);
	}
	
	private void saveFile(Cmfoodchain cmfoodchain, String resultType, String outputFolderPath) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String fileName;
		if(resultType.equals(MATCHED)) {
			fileName = outputFolderPath + "Match.json";
		}
		else {
			fileName = outputFolderPath + "Mismatch.json";
		}
		try {
			String json = cmfoodchain != null ? gson.toJson(cmfoodchain) : "";
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(json);
		    writer.close();
		    System.out.println("OutputFile : " + fileName + " is ready");
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
