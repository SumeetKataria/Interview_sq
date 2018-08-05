package org.test.checker;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.json.JSONObject;
import org.json.XML;
import org.test.tiaa.pojos.CmfoodchainType;

public class ReadAllFiles {

	public List<CmfoodchainType> captureInputFilesToPojos(String inputFolderPath){
		File folder = new File(inputFolderPath);
		List<CmfoodchainType> listOfFoodHotelsDetails = new ArrayList<>();
		File[] fileNames = folder.listFiles();
		if(null != fileNames) {
			for(File file : fileNames){
				try {
					System.out.println("Processing file : " + file.getName());
					CmfoodchainType readFile = readFile(file.getAbsolutePath());
					listOfFoodHotelsDetails.add(readFile);
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		}
		return listOfFoodHotelsDetails;
	}

	private CmfoodchainType readFile(String fileName) {
		String extension = getFileExtension(fileName);

		if("json".equalsIgnoreCase(extension)) {
			return readJson(fileName);
		}
		else if("xml".equalsIgnoreCase(extension)) {
			return readXml(fileName);
		}
		return null;
	}

	private String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	private CmfoodchainType readJson(String fileName){
		CmfoodchainType cmfoodchainType = null;

		try {
			String content = new String(Files.readAllBytes(Paths.get(fileName)));
			JSONObject json = new JSONObject(content);
			String xml = XML.toString(json);
			InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
			JAXBContext jaxbContext = JAXBContext.newInstance(CmfoodchainType.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cmfoodchainType = (CmfoodchainType) jaxbUnmarshaller.unmarshal(stream);

		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		return cmfoodchainType;
	}

	private CmfoodchainType readXml(String fileName) {
		CmfoodchainType cmfoodchainType = null;
		try {
			File file = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(CmfoodchainType.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cmfoodchainType = (CmfoodchainType) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return cmfoodchainType;
	}
}
