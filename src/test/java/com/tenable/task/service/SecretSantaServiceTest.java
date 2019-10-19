package com.tenable.task.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SecretSantaServiceTest {

	SecretSantaService secretSanta;
	ArrayList<String> members = new ArrayList<String>();
	final String BLANK = "";
	final String TUSHAR = "Tushar";
	
	@BeforeEach
	void setUp() throws Exception {
		members.add("Tushar");
		members.add("Ashwini");
		members.add("Rohini");
		members.add("Yogini");
		
		secretSanta = new SecretSantaService(members);
	}

	@Test
	void testAssignSecretSantas() {
		//Check if returned value is not null
		
		HashMap<String,String> result = SecretSantaService.assignSecretSantas(members);		

		//Check for null value
		assertNotNull(result);
		
		//Check if returned result has assigned for every family member
		assertEquals(result.entrySet().size(),members.size());
		assertEquals(result.values().size(), members.size());
		assertTrue(result.keySet().containsAll(members));
		assertTrue(result.values().containsAll(members));

		//Check if member is not assigned santa as himself
		for(int i = 0; i<members.size(); i++) 
			assertNotEquals(result.get(members.get(i)), members.get(i));
	}
	
	@Test
	void testGetMySecretSanta() {

		//Negetive test case. Check if the returned value is empty/blank but not null
		assertNotNull(secretSanta.getMySecretSanta("NotInMembersListName"));
		assertEquals(secretSanta.getMySecretSanta("NotInMembersListName"),BLANK);
		
		//Positive test case. Secret santa received/assigned is not null
		assertNotNull(secretSanta.getMySecretSanta(TUSHAR));

		//Secret santa is not the same member as input name
		for(int i=0; i<members.size(); i++)
			assertNotEquals(secretSanta.getMySecretSanta(members.get(i)), members.get(i));
	}
	
	@Test
	void testGetFamilyMembers() {
		
		//Positive test cases
		assertNotNull(secretSanta.getFamilyMembers());
		
		assertEquals(secretSanta.getFamilyMembers().size(),members.size());
		
		assertTrue(secretSanta.getFamilyMembers().containsAll(members));
		
	}

}