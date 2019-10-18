package com.tenable.task.model.response;

public enum ErrorMessages {
	
	FAMILY_MEMBERS_LIST_EMPTY("Error: Input family members list is empty. Please refer http://localhost:8080/swagger-ui.html for more details on how to input the names"),
	FAMILY_MEMBERS_LIST_INSUFFICIENT("Error: Input family members list contains only 2 member names. Please make sure there are atleast 3 names in the input list. Please refer http://localhost:8080/swagger-ui.html for more details on how to input the names"),
	MISSING_FAMILY_MEMBERS_LIST("Error: Names of family members are missing. Please refer http://localhost:8080/swagger-ui.html for more details on how to input the names"),
	MISSING_FAMILY_MEMBER("Error: The name input does not exist. Names are case-sensitive, please ensure correct names are input. Refer http://localhost:8080/swagger-ui.html for more details"),
	INTERNAL_SERVER_ERROR("Some other internal server error. Please check logs saved in /logs folder");
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
