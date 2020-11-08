package com.capg.jdbc.newaddressbook;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class AddressBookSystemTest {
	public static AddressBookSystemDB addressBookSystem;
	public static List<Contacts> contactsList;
	public static Map<String,Integer> contactsCount;

	@BeforeClass
	public static void setUp() {
		addressBookSystem = new AddressBookSystemDB();
		contactsList = new ArrayList<>();
	}
    @Ignore
	@Test
	public void givenAddressBookServiceDB_whenRetrieved_shouldMatchCountOfContacts() throws CustomExceptions {
		contactsList = addressBookSystem.getContactsList();
		Assert.assertEquals(7, contactsList.size());
	}
 	@Ignore
   	@Test
   	public void updatedContastsList_whenRetrieved_shouldBeSyncedWithDB() throws SQLException, CustomExceptions {
   		AddressBookSystemDB.updateContactDetails("8709702154","nishantkr0210@gmail.com","Nishant");
   		String phone = addressBookSystem.validateUpdationOfPhoneNo("Nishant");
   		Assert.assertEquals("8709702154", phone);
   	}
   	@Ignore
	@Test
	public void givenADateRange_whenRetrievedContacts_shouldGiveCountOfContacts() throws CustomExceptions {
		contactsList = addressBookSystem.getContactListInADateRange(LocalDate.of(2019, 05, 01),LocalDate.now());
		Assert.assertEquals(4, contactsList.size());
	}
	
	@Test
	public void givenACity_whenRetrievedContacts_shouldGiveCountOfContacts() throws CustomExceptions {
		contactsCount = addressBookSystem.getContactsByCityOrStateName("City");
		int count = contactsCount.get("Adityapur");
		Assert.assertEquals(2, count);
	}

	@Test
	public void givenAState_whenRetrievedContacts_shouldGiveCountOfContacts() throws CustomExceptions {
		contactsCount = addressBookSystem.getContactsByCityOrStateName("State");
		int count = contactsCount.get("Bihar");
		Assert.assertEquals(4, count);
	}
}
