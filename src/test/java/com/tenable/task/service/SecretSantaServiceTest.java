package com.tenable.task.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SecretSantaServiceTest {

	SecretSantaService secretSanta;
	ArrayList<String> members = new ArrayList<String>();
	
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

		//Negetive test case. Secret santa received/assigned should be null
		assertNull(secretSanta.getMySecretSanta("NotInMembersListName"));
		
		//Positive test case. Secret santa received/assigned is not null
		assertNotNull(secretSanta.getMySecretSanta("Tushar"));

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