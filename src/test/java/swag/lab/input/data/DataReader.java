package swag.lab.input.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class DataReader {
	
	// /Swag_Lab/src/test/java/swag/lab/input/data/DataReader.java
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String path) throws IOException{
		
		// /Swag_Lab/src/test/java/swag/lab/input/data/Errors.json
		
		// 1) Reading JSON To String 
		// 1)  reading JSON to String 
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"src\\test\\java\\input\\data\\PurchaseOrder.json"),
			StandardCharsets.UTF_8);
		
		// 2) Convert String to HashMap, we need one dependency JSON Databind add that dependency into pom.xml
		ObjectMapper mapper = new ObjectMapper();
		
		// data = {{map_1}, {msp_2}}
		List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
					
		});
		
		
		return data;
	}
	
	

}
