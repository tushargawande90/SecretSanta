package com.tenable.task.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tenable.task.model.request.ExtendedFamilyRequestModel;
import com.tenable.task.model.response.ErrorMessages;
import com.tenable.task.model.response.ExtendedFamilyResponseModel;
import com.tenable.task.model.response.SecretSantaResponseModel;
import com.tenable.task.model.response.SecretSantasResponseModel;
import com.tenable.task.service.SecretSantaService;

import io.swagger.annotations.ApiOperation;

/**
 * @author TusharG
 *
 */
@RestController
public class SecretSantaController {

	private final Logger log = LoggerFactory.getLogger(SecretSantaController.class);

	@Autowired
	SecretSantaService secretSantaService;

	@ApiOperation(value = "Input the list of extended family members")
	@RequestMapping(method = RequestMethod.POST, value = "/extendedfamily")
	public @ResponseBody ExtendedFamilyResponseModel postFamilyMembers(@RequestBody ExtendedFamilyRequestModel req)
			throws Exception {
		ExtendedFamilyResponseModel resp = new ExtendedFamilyResponseModel();
		log.info("Inside putFamilyMembers(@RequestBody ExtendedFamilyRequestModel req) ");

		if (req.getExtendedFamilySet().isEmpty())
			throw new Exception(ErrorMessages.FAMILY_MEMBERS_LIST_EMPTY.getErrorMessage());
		if (req.getExtendedFamilySet().size() < 3)
			throw new Exception(ErrorMessages.FAMILY_MEMBERS_LIST_INSUFFICIENT.getErrorMessage());

		ArrayList<String> members = new ArrayList<String>(req.getExtendedFamilySet());

		log.info("Creating secretSantaService object using the extended family member arraylist ");
		secretSantaService = new SecretSantaService(members);

		log.info("Set of extended family members returned as response ");
		resp.setExtendedFamily(secretSantaService.getFamilyMembers());
		return resp;

	}

	@ApiOperation(value = "View the extended family member list")
	@RequestMapping(method = RequestMethod.GET, value = "/extendedfamily")
	public @ResponseBody ExtendedFamilyResponseModel getFamilyMembers() throws Exception {
		log.info("Inside getFamilyMembers() ");
		ExtendedFamilyResponseModel resp = new ExtendedFamilyResponseModel();

		if (secretSantaService.getSecretSantas().isEmpty())
			throw new Exception(ErrorMessages.MISSING_FAMILY_MEMBERS_LIST.getErrorMessage());

		log.info("Set of extended family members returned as response ");
		resp.setExtendedFamily(secretSantaService.getFamilyMembers());
		return resp;
	}

	@ApiOperation(value = "View all the secret santa assignments")
	@RequestMapping(method = RequestMethod.GET, value = "/secretsanta")
	public @ResponseBody SecretSantasResponseModel getSecretSantas() throws Exception {
		log.info("Inside getSecretSantas() ");
		SecretSantasResponseModel resp = new SecretSantasResponseModel();

		if (secretSantaService.getSecretSantas().isEmpty())
			throw new Exception(ErrorMessages.MISSING_FAMILY_MEMBERS_LIST.getErrorMessage());

		resp.setSecretSantas(secretSantaService.getSecretSantas());
		log.info("Response with secret santas for everyone returned as response");
		return resp;
	}

	@ApiOperation(value = "View the secret santa assigned to input family member")
	@RequestMapping(method = RequestMethod.GET, value = "/secretsanta/{name}")
	public @ResponseBody SecretSantaResponseModel getMySecretSanta(@PathVariable String name) throws Exception {
		log.info("Inside getMySecretSanta(@PathVariable String name)");
		SecretSantaResponseModel resp = new SecretSantaResponseModel();

		if (secretSantaService.getSecretSantas().isEmpty())
			throw new Exception(ErrorMessages.MISSING_FAMILY_MEMBERS_LIST.getErrorMessage());

		String santa = secretSantaService.getMySecretSanta(name);

		if (null == santa || "" == santa)
			throw new Exception(ErrorMessages.MISSING_FAMILY_MEMBER.getErrorMessage());

		resp.setSanta(santa);
		log.info("Response with assigned secret santa returned as response ");
		return resp;
	}
}