package com.capg.jdbc.newaddressbook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressBookSystemDB {
	public static Connection connection = null;

	EstablishConnection myConnection = new EstablishConnection();

	public List<Contacts> getContactsList() throws CustomExceptions {

		List<Contacts> contactsList = new ArrayList<>();
		String query = "select * from Personal_Details";
		try (Connection connection = EstablishConnection.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				int person_id = result.getInt(1);
				String firstName = result.getString(2);
				String lastName = result.getString(3);
				String phoneNumber = result.getString(4);
				String emailID = result.getString(5);
				String address_id = result.getString(6);
				Contacts contacts = new Contacts(person_id, firstName, lastName, phoneNumber, emailID, address_id);
				contactsList.add(contacts);
				System.out.println(contacts);
			}
		} catch (SQLException e) {
			throw new CustomExceptions("SQL Exception..Failed to perform task!!");
		}
		return contactsList;
	}
}
