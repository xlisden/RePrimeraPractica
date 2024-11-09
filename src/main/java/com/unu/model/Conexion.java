package com.unu.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static String url = "jdbc:mysql://localhost:3366/primerapracticapooii";
	private static String user = "root";
	private static String password = "123456";
	protected static Connection conexion;

	public static Connection openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, password);
			System.out.println("connection open!");
		} catch (Exception e) {
			System.out.println("openConnection() " + e.getMessage());
		}
		return conexion;
	}

	public static Connection closeConnection() {
		try {
			if (conexion != null && !conexion.isClosed()) {
				conexion.close();
				System.out.println("connection close.");
			}
		} catch (SQLException e) {
			System.out.println("closeConnection() " + e.getMessage());
		}
		return conexion;
	}

}