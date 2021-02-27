package com.database.service;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.database.model.Criteria;
import com.database.model.Row;
import com.database.model.Table;

public class DatabaseSystem {

	public List<Row> getAllRows(Table employee) {
		return employee.getRowList();
	}

	public Table searchData(Table employee, List<Criteria> criteriaList) throws CloneNotSupportedException {
		Table res = (Table) employee.clone();
		for(Criteria criteria : criteriaList) {
			switch(criteria.getOperation()) {
				case "equals":
					res = performEquals(res, criteria);
					break;
				case "notEquals":
					res = performNotEquals(res, criteria);
					break;
				case "contains":
					res = performContains(res, criteria);
					break;
				case "notContains":
					res = performNotContains(res, criteria);
					break;
				case "startsWith":
					res = performStartsWith(res, criteria);
					break;
				case "endsWith":
					res = performEndsWith(res, criteria);
					break;
				case ">":
					res = performGreater(res,criteria);
					break;
				case "<":
					res = performLesser(res,criteria);
					break;
				case "=":
					res = performSame(res,criteria);
					break;
				case "!=":
					res = performNotSame(res,criteria);
					break;
				case "between":
					res = performBetween(res,criteria);
					break;
			}
		}
		return res;
	}


	private Table performBetween(Table employee, Criteria criteria) {
		Table res = new Table("EmployeeResult");
		for(Row row:employee.getRowList()) {
			if(Integer.parseInt(row.getColumnValueMap().get(criteria.getColumn()))>Integer.parseInt(criteria.getValue1()) && Integer.parseInt(row.getColumnValueMap().get(criteria.getColumn()))<Integer.parseInt(criteria.getValue2())) {
				res.getRowList().add(row);
			}
		}
		return res;
	}

	private Table performNotSame(Table employee, Criteria criteria) {
		Table res = new Table("EmployeeResult");
		for(Row row:employee.getRowList()) {
			if(Integer.parseInt(row.getColumnValueMap().get(criteria.getColumn()))!=Integer.parseInt(criteria.getValue1())) {
				res.getRowList().add(row);
			}
		}
		return res;
	}

	private Table performSame(Table employee, Criteria criteria) {
		Table res = new Table("EmployeeResult");
		for(Row row:employee.getRowList()) {
			if(Integer.parseInt(row.getColumnValueMap().get(criteria.getColumn()))==Integer.parseInt(criteria.getValue1())) {
				res.getRowList().add(row);
			}
		}
		return res;
	}

	private Table performLesser(Table employee, Criteria criteria) {
		Table res = new Table("EmployeeResult");
		for(Row row:employee.getRowList()) {
			if(Integer.parseInt(row.getColumnValueMap().get(criteria.getColumn()))<Integer.parseInt(criteria.getValue1())) {
				res.getRowList().add(row);
			}
		}
		return res;
	}

	private Table performGreater(Table employee, Criteria criteria) {
		Table res = new Table("EmployeeResult");
		for(Row row:employee.getRowList()) {
			if(Integer.parseInt(row.getColumnValueMap().get(criteria.getColumn()))>Integer.parseInt(criteria.getValue1())) {
				res.getRowList().add(row);
			}
		}
		return res;
	}

	private Table performEndsWith(Table employee, Criteria criteria) {
		Table res = new Table("EmployeeResult");
		for(Row row:employee.getRowList()) {
			if(row.getColumnValueMap().get(criteria.getColumn()).endsWith(criteria.getValue1())) {
				res.getRowList().add(row);
			}
		}
		return res;
	}

	private Table performStartsWith(Table employee, Criteria criteria) {
		Table res = new Table("EmployeeResult");
		for(Row row:employee.getRowList()) {
			if(row.getColumnValueMap().get(criteria.getColumn()).startsWith(criteria.getValue1())) {
				res.getRowList().add(row);
			}
		}
		return res;
	}

	private Table performNotContains(Table employee, Criteria criteria) {
		Table res = new Table("EmployeeResult");
		for(Row row:employee.getRowList()) {
			if(!row.getColumnValueMap().get(criteria.getColumn()).contains(criteria.getValue1())) {
				res.getRowList().add(row);
			}
		}
		return res;
	}

	private Table performContains(Table employee, Criteria criteria) {
		Table res = new Table("EmployeeResult");
		for(Row row:employee.getRowList()) {
			if(row.getColumnValueMap().get(criteria.getColumn()).contains(criteria.getValue1())) {
				res.getRowList().add(row);
			}
		}
		return res;
	}

	private Table performNotEquals(Table employee, Criteria criteria) {
		Table res = new Table("EmployeeResult");
		for(Row row:employee.getRowList()) {
			if(!row.getColumnValueMap().get(criteria.getColumn()).equals(criteria.getValue1())) {
				res.getRowList().add(row);
			}
		}
		return res;
	}

	private Table performEquals(Table employee, Criteria criteria) {
		Table res = new Table("EmployeeResult");
		for(Row row:employee.getRowList()) {
			if(row.getColumnValueMap().get(criteria.getColumn()).equals(criteria.getValue1())) {
				res.getRowList().add(row);
			}
		}
		return res;
	}

	public List<String> getEmployeeReportingTree(String employeeName, Table employee) {
		List<String> res = new ArrayList<>();
		for(Row row:employee.getRowList()) {
			if(row.getColumnValueMap().get("EmpName").equals(employeeName)) {
				res.add(employeeName);
				res.add(row.getColumnValueMap().get("ReportingTo"));
				getReportingAuthority(employee, res);
				
				return res;
			}
		}
		return res;
	}

	private void getReportingAuthority(Table employee, List<String> res) {
		String employeeName = res.get(res.size()-1);
		for(Row row:employee.getRowList()) {
			if(row.getColumnValueMap().get("EmpName").equals(employeeName)) {
				res.add(row.getColumnValueMap().get("ReportingTo"));
				getReportingAuthority(employee, res);
				return;
			}
		}
		return;
	}

	public List<String> getReportingMangerEmployees(String reportingManager, Table employee) {
		List<String> res = new ArrayList<>();
		for(Row row:employee.getRowList()) {
			if(row.getColumnValueMap().get("ReportingTo").equals(reportingManager)) {
				res.add(row.getColumnValueMap().get("EmpName"));
			}
		}
		return res;
	}

	public Map<String, Integer> getSummary(Table employee, String columnName) {
		Map<String, Integer> res = new HashMap<>();
		for(Row row: employee.getRowList()) {
				res.put(row.getColumnValueMap().get(columnName), res.getOrDefault(row.getColumnValueMap().get(columnName),0)+1);
		}
		return res;
	}

	
}
