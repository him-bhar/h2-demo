/*
 * Copyright 2013 Himanshu Bhardwaj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.himanshu.poc.h2.standalone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StandaloneDemo {
	public static void main(String[] args) {
		String connectionUrl = "jdbc:h2:mem:sample;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'\\;RUNSCRIPT FROM 'classpath:scripts/create_2.sql'";
		String userName = "sa";
		String password = "";
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmt2 = null;
		ResultSet rs2 = null;
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection(connectionUrl, userName, password);
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select id,name from dummy_tbl");
			while (rs.next()) {
				System.out.println("Record from db : "+rs.getInt("id")+"\t"+rs.getString("name"));
			}
			stmt2 = connection.createStatement();
			rs2 = stmt2.executeQuery("select id,name,age from person");
			while (rs2.next()) {
				System.out.println("Record from person db : "+rs2.getInt("id")+"\t"+rs2.getString("name")+"\t"+rs2.getInt("age"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				rs2.close();
				stmt2.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
