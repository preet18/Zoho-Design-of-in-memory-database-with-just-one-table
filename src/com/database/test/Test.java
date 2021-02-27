package com.database.test;

import com.database.model.Table;

public class Test {

	public static void main(String[] args) throws CloneNotSupportedException {
		Table emp = new Table("Preeti");
		Table test = (Table) emp.clone();
		System.out.println(emp+"---"+test);
	}

}
