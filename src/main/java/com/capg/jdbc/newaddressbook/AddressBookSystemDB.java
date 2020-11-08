package com.capg.jdbc.newaddressbook;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			}
		} catch (SQLException e) {
			throw new CustomExceptions("SQL Exception..Failed to perform task!!");
		}
		return contactsList;
	}

	public static void updateContactDetails(String phoneNumber, String emailID, String firstName)
			throws CustomExceptions {

		String updateQuery = "update personal_details SET phoneNumber = ? , emailID = ? where firstName = ?";
		try (Connection connection = EstablishConnection.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, phoneNumber);
			preparedStatement.setString(2, emailID);
			preparedStatement.setString(3, firstName);
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected == 1) {
				System.out.println("Contact details updated!");
			} else {
				throw new CustomExceptions("SQL Exception..Failed to update record!!");
			}
		} catch (SQLException e) {
			throw new CustomExceptions("Error in establishing connection!!");
		}

	}

	String validateUpdationOfPhoneNo(String firstName) throws CustomExceptions {
		String phoneNumber = null;
		String validateQuery = "select phoneNumber from Personal_Details where firstName = ?";
		try (Connection connection = EstablishConnection.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(validateQuery);
			preparedStatement.setString(1, firstName);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				phoneNumber = result.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phoneNumber;
	}
	
	public List<Contacts> getContactListInADateRange(LocalDate start_Date, LocalDate end_Date) throws CustomExceptions {
		List<Contacts> contactsListInADateRange = new ArrayList<>();
		String retrievalQuery = "select * from Personal_Details where entry_date between ? AND ? ";
		try (Connection connection = EstablishConnection.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(retrievalQuery);
			preparedStatement.setDate(1, Date.valueOf(start_Date));
			preparedStatement.setDate(2, Date.valueOf(end_Date));
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				int person_id = result.getInt(1);
				Date entry_date = result.getDate(2);
				String firstName = result.getString(3);
				String lastName = result.getString(4);
				String phoneNumber = result.getString(5);
				String emailID = result.getString(6);
				String address_id = result.getString(7);
				Contacts contacts = new Contacts(person_id, entry_date, firstName, lastName, phoneNumber, emailID,
						address_id);
				contactsListInADateRange.add(contacts);
			}
		} catch (SQLException e) {
			throw new CustomExceptions("SQL Exception..Failed to perform task!!");
		}
		return contactsListInADateRange;
	}

	public Map<String, Integer> getContactsByCityOrStateName(String nameOfCityOrState) throws CustomExceptions {

		Map<String, Integer> contactsGroupByCityOrState = new HashMap<>();
		String countByStateOrCityQuery = String.format(
				"SELECT %s,COUNT(*) FROM Personal_Details Join Address ON Personal_details.Address_ID = Address.Address_ID GROUP BY %s",
				nameOfCityOrState, nameOfCityOrState);
		try (Connection connection = EstablishConnection.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(countByStateOrCityQuery);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				contactsGroupByCityOrState.put(result.getString(1), result.getInt(2));
			}
		} catch (Exception e) {
			throw new CustomExceptions("Failed to retrieve Contacts!");
		}
		return contactsGroupByCityOrState;
	}
}
