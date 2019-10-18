/**
 * 
 */
package com.tenable.task.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.tenable.task.model.request.ExtendedFamilyRequestModel;
import com.tenable.task.model.response.ExtendedFamilyResponseModel;
import com.tenable.task.model.response.SecretSantaResponseModel;
import com.tenable.task.model.response.SecretSantasResponseModel;
import com.tenable.task.service.SecretSantaService;

/**
 * @author TusharG
 *
 */
class SecretSantaControllerTest {

	@InjectMocks
	SecretSantaController ssController;

	@Mock
	SecretSantaService secretSantaService;

	ExtendedFamilyRequestModel extendedFamily;
	HashMap<String, String> secretSantas;
	final String name = "Tushar";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	
		extendedFamily = new ExtendedFamilyRequestModel();
		HashSet<String> extendedFamilySet = new HashSet<String>();
		extendedFamilySet.add("Tushar");
		extendedFamilySet.add("Ashwini");
		extendedFamilySet.add("Rohini");
		extendedFamilySet.add("Bhushan");
		extendedFamily.setExtendedFamilySet(extendedFamilySet);
		
		secretSantas = new HashMap<String, String>();
		secretSantas.put("Tushar", "Rohini");
		secretSantas.put("Ashwini", "Bhushan");
		secretSantas.put("Rohini", "Ashwini");
		secretSantas.put("Bhushan", "Tushar");		
	}

	/**
	 * Test method for
	 * {@link com.tenable.task.controller.SecretSantaController#postFamilyMembers(com.tenable.task.model.request.ExtendedFamilyRequestModel)}.
	 * @throws Exception 
	 */
	@Test
	void testPostFamilyMembers() throws Exception {
		when(secretSantaService.getFamilyMembers()).thenReturn(secretSantas.keySet());
		ExtendedFamilyResponseModel resp = ssController.postFamilyMembers(extendedFamily);

		assertNotNull(resp);
		assertTrue(resp.getExtendedFamily().containsAll(secretSantas.keySet()));
	}

	/**
	 * Test method for
	 * {@link com.tenable.task.controller.SecretSantaController#getFamilyMembers()}.
	 * @throws Exception 
	 */
	@Test
	void testGetFamilyMembers() throws Exception {
		
		when(secretSantaService.getSecretSantas()).thenReturn(secretSantas);
		when(secretSantaService.getFamilyMembers()).thenReturn(secretSantas.keySet());
		
		ExtendedFamilyResponseModel resp = ssController.getFamilyMembers();

		assertNotNull(resp);
		assertTrue(resp.getExtendedFamily().containsAll(secretSantas.keySet()));
	}

	/**
	 * Test method for
	 * {@link com.tenable.task.controller.SecretSantaController#getSecretSantas()}.
	 * @throws Exception 
	 */
	@Test
	void testGetSecretSantas() throws Exception {
		when(secretSantaService.getSecretSantas()).thenReturn(secretSantas);
		SecretSantasResponseModel resp = ssController.getSecretSantas();
		
		assertNotNull(resp);
		assertEquals(resp.getSecretSantas().size(),secretSantas.size());
		assertTrue(resp.getSecretSantas().keySet().containsAll(secretSantas.keySet()));
				
	}

	/**
	 * Test method for
	 * {@link com.tenable.task.controller.SecretSantaController#getMySecretSanta(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	void testGetMySecretSanta() throws Exception {
		when(secretSantaService.getSecretSantas()).thenReturn(secretSantas);
		when(secretSantaService.getMySecretSanta(name)).thenReturn(secretSantas.get(name));
		
		SecretSantaResponseModel resp = ssController.getMySecretSanta(name);
		assertNotNull(resp);
		assertEquals(resp.getSanta(),secretSantas.get(name));
		assertNotEquals(resp.getSanta(),name);
		
	}
}