package com.capg.jdbc.newaddressbook;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddressBookSystemRestApiTest {

	AddressBookSystemRestApi addressBookSystemRestApi;
	List<Contacts> contacts;
	
	@Before
	public void Setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;
		addressBookSystemRestApi = new AddressBookSystemRestApi(getContactsList());
	}

	private List<Contacts> getContactsList() {
		Response response = RestAssured.get("/contacts");
		Contacts[] contacts = new Gson().fromJson(response.asString(), Contacts[].class);
		return Arrays.asList(contacts);
	}
	
	private Response addNewContactToJsonServer(Contacts contact) {
		String newContactJson = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(newContactJson);
		return request.post("/contacts");
	}
	
	@Ignore
	@Test
	public void givenJSONServer_whenRetrievedContacts_ShouldMatchContactsCount() {
		int countOfContacts = addressBookSystemRestApi.getSizeOfList();
		Assert.assertEquals(3, countOfContacts);
	}
	
	
    @Test 
    public void givenNewContact_WhenAdded_ShouldGetAddedToJSONServerANdMatchWithStatusCode() {
    	Contacts newContact = new Contacts(4,"Nishant", "Chauhan", "Goraul","Vaishali", "Bihar", "844118", "9835386938", "nishant@gmail.com");
	  addressBookSystemRestApi.addNewContactToList(newContact);
	  Response response = addNewContactToJsonServer(newContact); 
	  boolean check = (response.getStatusCode() ==201); 
	  int count = addressBookSystemRestApi.getSizeOfList();
	  Assert.assertTrue(check);
	  Assert.assertEquals(4,count);
    }
}

