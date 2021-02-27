package com.database.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTypeOperations {
	
	private static Map<String, List<String>> dataTypeOperations = new HashMap<>();
	
	static DataTypeOperations dataType = null;
	private DataTypeOperations() {};
	static {
		dataTypeOperations.put("String",Arrays.asList("equals","notEquals","contains","notContains","startsWith","endsWith"));
		dataTypeOperations.put("Integer", Arrays.asList(">","<","=","!=","between"));
	}
	public static DataTypeOperations getInstance() {
		if(dataType==null) {
			return new DataTypeOperations();
		}
		return dataType;
	}
	/*public List<String> getDataTypes(){
		return new ArrayList<String>(dataTypeOperations.keySet());
	}*/
	public List<String> getStringDataTypeOperations() {
		return dataTypeOperations.get("String");  
	}
	
	public List<String> getIntegerDataTypeOperations() {
		return dataTypeOperations.get("Integer");  
	}
	
}
