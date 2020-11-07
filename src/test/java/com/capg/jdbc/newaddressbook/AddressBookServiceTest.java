package com.capg.jdbc.newaddressbook;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressBookServiceTest 
{
	public static AddressBookSystemDB addressBookSystem;
	public static List<Contacts> contactsList;
	
	@BeforeClass
	public static void setUp() {
	      addressBookSystem = new AddressBookSystemDB();
	      contactsList = new ArrayList<>();
	}
	  @Test
	  public void givenAddressBookServiceDB_whenRetrieved_shouldMatchCountOfContacts() throws CustomExceptions{
	  contactsList = addressBookSystem.getContactsList();
	  Assert.assertEquals(7,contactsList.size()); }
}
