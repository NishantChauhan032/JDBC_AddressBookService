package com.capg.jdbc.newaddressbook;

public class Contacts {

	private int person_id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailID;
	private String address_id;

	public Contacts(int person_id, String firstName, String lastName, String phoneNumber, String emailID,
			String address_id) {
		this.person_id = person_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailID = emailID;
		this.address_id = address_id;

	}
	

	@Override
	public String toString() {
		return "Contacts [person_id=" + person_id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", emailID=" + emailID + ", address_id=" + address_id + "]";
	}


	public String getAddress_id() {
		return address_id;
	}

	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

}
