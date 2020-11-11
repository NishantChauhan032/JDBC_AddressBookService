package com.capg.jdbc.newaddressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBookSystemRestApi {
	
	public static List<Contacts> contactsList;
	
	public AddressBookSystemRestApi(List<Contacts> contacts) {
		contactsList = new ArrayList<Contacts>(contacts);
	}

	public int getSizeOfList() {
	  return contactsList.size();
	}
	public void addNewContactToList(Contacts contact) {
		contactsList.add(contact);
	}

}
