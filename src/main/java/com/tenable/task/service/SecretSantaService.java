package com.tenable.task.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SecretSantaService {

	private final Logger log = LoggerFactory.getLogger(SecretSantaService.class);
	
	private HashMap<String, String> secretSantas;

	public SecretSantaService() {
		// TODO Auto-generated constructor stub
		this.secretSantas = new HashMap<String,String>();
	}
	
	public SecretSantaService(ArrayList<String> members) {
		super();
		log.info("Creating the SecretSantaService object and running the secret santa algorithm to assign santas");
		this.secretSantas = SecretSantaService.assignSecretSantas(members);
	}

	public HashMap<String, String> getSecretSantas() {
		return secretSantas;
	}
	
	public String getMySecretSanta(String name) {
		return secretSantas.get(name);
	}

	public Set<String> getFamilyMembers() {
		return (secretSantas.keySet());
	}

	public void setSecretSantas(HashMap<String, String> secretSantas) {
		this.secretSantas = secretSantas;
	}

	public static HashMap<String,String> assignSecretSantas(ArrayList<String> members) {
		HashMap<String, String> secretSantas = new HashMap<String,String>();

		Random rnd = new Random();
		final int n = members.size();
		
		boolean[] picked = new boolean[n];

		for (int i = 0; i < n; i++) {
			int j;
			do {
				j = rnd.nextInt(n);
				if (i == n - 1 && j == i) {
					return assignSecretSantas(members);		//Last element
				}
			} while (j == i || picked[j]); // rejection
			secretSantas.put(members.get(i), members.get(j));
			picked[j] = true;
		}
		return secretSantas;
	}
}
