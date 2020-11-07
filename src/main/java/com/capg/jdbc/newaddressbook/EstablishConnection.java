package com.capg.jdbc.newaddressbook;

import java.sql.*;

public class EstablishConnection {

	public static final String URL = "jdbc:mysql://localhost:3306/AddressBookService";
	public static final String USER = "root";
	public static final String PASSWORD = "Password@mysql1";
	private static Connection connection = null;

	public static Connection getConnection() throws CustomExceptions {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Succesfully!!");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connection established successfully!" + connection);

		} catch (ClassNotFoundException e) {
			throw new CustomExceptions("Can not find driver!");
		} catch (SQLException e) {
			throw new IllegalStateException("Connection failed to establish!");
		}
		return connection;
	}
	
}
