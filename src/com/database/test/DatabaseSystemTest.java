package com.database.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.database.model.Criteria;
import com.database.model.Row;
import com.database.model.Table;
import com.database.service.DatabaseSystem;

public class DatabaseSystemTest {

	public static void main(String[] args) {
		try {
			DatabaseSystem db = new DatabaseSystem();
			Table employee = new Table("Employee");
			insertData(employee);
			//Map<String, List<String>> operations = new HashMap<>();
			//initializePossibleOperation(operations);
			
			
			boolean exit = false;
			Scanner sc = new Scanner(System.in);
			while(!exit) {
				System.out.println("Select any of the options :: " + "\n1.Display all rows "+"\n2.Search records based on criteria "+
			"\n3.Display Reporting Tree of Employee "+"\n4.Display Employees Reporting to a Manager"+
						"\n5.Display Summary of Department, Designation, ReportingTo");
				
				int option = sc.nextInt();
				sc.nextLine();
				if(option==1) {
					System.out.println("*******************START OF DISPLAYING ALL THE RECORDS**********************");
					List<Row> rowList = db.getAllRows(employee);
					List<String> columns = new ArrayList<>(employee.getColumnsDataType().keySet());
					System.out.println("Keyset :: " + columns.size());
					for(String column: columns) {
						System.out.print(column+" | ");
					}
					System.out.println();
					for(Row row: rowList) {
						for(String column : columns) {
							System.out.print(row.getColumnValueMap().get(column)+ " | ");
						}
						System.out.println();
					}
					System.out.println("*******************END OF DISPLAYING ALL THE RECORDS**********************");
				}else if(option==2) {
					System.out.println("*******************START OF SEARCH BASED ON CRITERIA**********************");
					
					//System.out.println("Select columns option :: \na.Multiple Columns \nb.Single Column");
					//char ch = sc.next().charAt(0);
					System.out.println("No of conditions");
					int n = sc.nextInt();
					sc.nextLine();
					System.out.println("Select the criteria :: ");
					for(String column:employee.getColumnsDataType().keySet()) {
						System.out.print(column+"\n");
					}
					List<Criteria> criteriaList = new ArrayList<>();
					while(n-->0) {
						//int i=1;
						Criteria criteria = new Criteria();
						System.out.println("Enter the Column Name :: ");
						criteria.setColumn(sc.nextLine());
						System.out.println("Enter the Operation :: ");
						criteria.setOperation(sc.nextLine());
						System.out.println("Enter the value :: ");
						criteria.setValue1(sc.nextLine());
						if(criteria.getOperation().equals("between")) {
							System.out.println("Enter the other value :: ");
							System.out.println(sc.nextLine());
						}
						criteriaList.add(criteria);
					}
					Table result = db.searchData(employee, criteriaList);
					List<Row> rowList = db.getAllRows(result);
					List<String> columns = new ArrayList<>(employee.getColumnsDataType().keySet());
					System.out.println("Keyset :: " + columns.size());
					for(String column: columns) {
						System.out.print(column+" | ");
					}
					System.out.println();
					for(Row row: rowList) {
						for(String column : columns) {
							System.out.print(row.getColumnValueMap().get(column)+ " | ");
						}
						System.out.println();
					}
					System.out.println("\n*******************END OF SEARCH BASED ON CRITERIA**********************");
				}else if(option==3) {
					System.out.println("*******************START OF REPORTING TREE OF EMPLOYEE**********************");
					System.out.println("Enter name of employee :: ");
					String employeeName = sc.nextLine();
					List<String> lst = db.getEmployeeReportingTree(employeeName, employee);
					if(lst.size()==0) {
						System.out.println("Employee Details not found with the given employee Name...");
					}else{
						for(String str:lst) {
							System.out.println(str);
						}
					}
					System.out.println("*******************END OF REPORTING TREE OF EMPLOYEE**********************");
				}else if(option==4) {
					System.out.println("*******************START OF DISPLAY EMPLOYEES REPORTING TO A MANAGER**********************");
					System.out.println("Enter name of reporting Manager :: ");
					String reportingManager = sc.nextLine();
					List<String> lst = db.getReportingMangerEmployees(reportingManager, employee);
					if(lst.size()==0) {
						System.out.println("No Employees Under this Manager...");
					}else{
						for(String str:lst) {
							System.out.println(str);
						}
					}
					System.out.println("*******************END OF DISPLAY EMPLOYEES REPORTING TO A MANAGER**********************");
				}else if(option==5) {
					System.out.println("*******************START OF SUMMARY OF A COLUMN**********************");
					System.out.println("Enter One of the Columns");
					List<String> columns = new ArrayList<>(employee.getColumnsDataType().keySet());
					for(String column: columns) {
						System.out.println(column);
					}
					String columnName = sc.nextLine();
					Map<String, Integer> map = db.getSummary(employee, columnName);
					if(map.size()==0) {
						System.out.println("There is no such Column :: ");
					}else {
						for(String str: map.keySet()) {
							System.out.println(str+" - " + map.get(str));
						}
					}
					System.out.println("*******************END OF SUMMARY OF A COLUMN**********************");
				}
			
			
			
			}
		}catch(Exception ex) {
			System.out.println("In Exception :: " + ex.getMessage());
		}
	}

	

	private static void searchData(Table employee, List<Criteria> criteriaList) {
		// TODO Auto-generated method stub
		
	}



	private static void initializePossibleOperation(Map<String, List<String>> operations) {
		List<String> stringOperations = Arrays.asList("Equals","NotEquals","Contains","NotContains","StartsWith","EndsWith");
		List<String> integerOperations = Arrays.asList(">","<","=","!=","between");
		operations.put("String", stringOperations);
		operations.put("Integer", integerOperations);
		
	}



	private static void insertData(Table employee) {
		employee.getColumnsDataType().put("EmpName", "String");
		employee.getColumnsDataType().put("Age", "int");
		employee.getColumnsDataType().put("Designation", "String");
		employee.getColumnsDataType().put("Department", "String");
		employee.getColumnsDataType().put("ReportingTo", "String");
		
		Row row1 = new Row();
		row1.getColumnValueMap().put("EmpName","Ava");
		row1.getColumnValueMap().put("Age","24");
		row1.getColumnValueMap().put("Designation", "Software Developer");
		row1.getColumnValueMap().put("Department", "Engineering");
		row1.getColumnValueMap().put("ReportingTo", "John");
		
		Row row2 = new Row();
		row2.getColumnValueMap().put("EmpName","Maudi");
		row2.getColumnValueMap().put("Age","25");
		row2.getColumnValueMap().put("Designation", "Software Developer");
		row2.getColumnValueMap().put("Department", "Engineering");
		row2.getColumnValueMap().put("ReportingTo", "Anna");
		
		
		Row row3 = new Row();
		row3.getColumnValueMap().put("EmpName","Kyle");
		row3.getColumnValueMap().put("Age","28");
		row3.getColumnValueMap().put("Designation", "Business Analyst");
		row3.getColumnValueMap().put("Department", "Management");
		row3.getColumnValueMap().put("ReportingTo", "Anna");
		
		employee.getRowList().add(row1);
		employee.getRowList().add(row2);
		employee.getRowList().add(row3);
		
		
	}
}
