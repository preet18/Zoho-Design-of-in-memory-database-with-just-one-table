package com.database.model;

import java.util.HashMap;
import java.util.Map;

public class Row {

	private Map<String, String> columnValueMap = new HashMap<>();

	public Map<String, String> getColumnValueMap() {
		return columnValueMap;
	}

	public void setColumnValueMap(Map<String, String> columnValueMap) {
		this.columnValueMap = columnValueMap;
	}
	
}
