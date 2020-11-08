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
   	@Ignore
	@Test
	public void givenACity_whenRetrievedContacts_shouldGiveCountOfContacts() throws CustomExceptions {
		contactsCount = addressBookSystem.getContactsByCityOrStateName("City");
		int count = contactsCount.get("Adityapur");
		Assert.assertEquals(2, count);
	}
	@Ignore
	@Test
	public void givenAState_whenRetrievedContacts_shouldGiveCountOfContacts() throws CustomExceptions {
		contactsCount = addressBookSystem.getContactsByCityOrStateName("State");
		int count = contactsCount.get("Bihar");
		Assert.assertEquals(4, count);
	}
	@Ignore
	@Test
	public void addedNewContact_whenContactsCounted_shouldGiveCurrentCountOfContacts() throws CustomExceptions {
		addressBookSystem.addNewContactToAddressBookDB("Amit","Shakya","Kannauj","Kanpur","UP","885634","9000087654","shakya@gmail.com","AD06");
		contactsList = addressBookSystem.getContactsList();
		Assert.assertEquals(8, contactsList.size());
	}
	
	@Test
	public void addedMultipleContacts_whenContactsCounted_shouldGiveCurrentContactsCount() throws CustomExceptions {
		Contacts[] multipleContacts= {
				new Contacts("Suraj","Asthana","Motihari",
						"Champaran","Bihar","878675","9507028511", "suraj@gmail.com","AD07"),
				new Contacts("Rajeev","Kumar","Chainpur","Goraul",
						"Bihar","836367","9567538676","rajeev@gmail.com","AD02"),
				new Contacts("Jeet","Yadav","Abc","Dhanbad",
						"Jharkhand", "998786","7648794749","jeet@gmail.com","AD08"),
		};
		addressBookSystem.addMultipleContactsToAddressBookDBUsingThreads(Arrays.asList(multipleContacts));
		contactsList = addressBookSystem.getContactsList();
		Assert.assertEquals(11, contactsList.size());
	}
	
}
