package com.database.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table implements Cloneable{

	private String tableName;
	private List<Row> rowList = new ArrayList<>();
	private Map<String, String> columnsDataType = new HashMap<>();
	
	public Table(String string) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Row> getRowList() {
		return rowList;
	}

	public void setRowList(List<Row> rowList) {
		this.rowList = rowList;
	}

	public Map<String, String> getColumnsDataType() {
		return columnsDataType;
	}

	public void setColumnsDataType(Map<String, String> columnsDataType) {
		this.columnsDataType = columnsDataType;
	}

	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	/*@Override
	   public Object clone() throws CloneNotSupportedException {
	      Table cloned = (Table)super.clone();
	      cloned.setDummy(cloned.getDummy());
	      // the above is applicable in case of primitive member types like String 
	      // however, in case of non primitive types
	      cloned.setNonPrimitiveType(cloned.getNonPrimitiveType().clone());
	      return cloned;
	   }

	private Object getNonPrimitiveType() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}
