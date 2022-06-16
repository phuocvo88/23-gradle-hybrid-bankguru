package com.hrm.dataTest;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.JsonAdapter;

import commons.GlobalConstants;

public class EmployeeData {

	public static EmployeeData getEmployee() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + "\\testData\\com\\hrm\\dataTest\\Employee.json"), EmployeeData.class);

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	@JsonProperty("firstname")
	private String firstname;
	
	@JsonProperty("lastname")
	private String lastname;
	
	@JsonProperty("fullname")
	private String fullname;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getFullname() {
		return fullname;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	@JsonProperty("ContactDetail")
	ContactDetail contactDetail;
	
	public static class ContactDetail {
		
		@JsonProperty("editFirstName")
		private String editFirstName;
		
		@JsonProperty("editLastName")
		private String editLastName;
	}

	public String getEditFirstName() {
		return contactDetail.editFirstName;
	}
	
	public String getEditLastName() {
		return contactDetail.editLastName;
	}
}
