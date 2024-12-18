package br.com.domain.intelbras.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserIncludeRequest {

	@JsonProperty("UserList")
	private List<User> userList;

	// Getters e Setters
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public static class User {

		@JsonProperty("UserID")
		private String userID;

		@JsonProperty("UserName")
		private String userName;

		@JsonProperty("UserType")
		private int userType;

		@JsonProperty("Authority")
		private int authority;

		@JsonProperty("Password")
		private String password;

		@JsonProperty("Doors")
		private List<Integer> doors;

		@JsonProperty("TimeSections")
		private List<Integer> timeSections;

		@JsonProperty("ValidFrom")
		private String validFrom;

		@JsonProperty("ValidTo")
		private String validTo;

		// Getters e Setters
		public String getUserID() {
			return userID;
		}

		public void setUserID(String userID) {
			this.userID = userID;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public int getUserType() {
			return userType;
		}

		public void setUserType(int userType) {
			this.userType = userType;
		}

		public int getAuthority() {
			return authority;
		}

		public void setAuthority(int authority) {
			this.authority = authority;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public List<Integer> getDoors() {
			return doors;
		}

		public void setDoors(List<Integer> doors) {
			this.doors = doors;
		}

		public List<Integer> getTimeSections() {
			return timeSections;
		}

		public void setTimeSections(List<Integer> timeSections) {
			this.timeSections = timeSections;
		}

		public String getValidFrom() {
			return validFrom;
		}

		public void setValidFrom(String validFrom) {
			this.validFrom = validFrom;
		}

		public String getValidTo() {
			return validTo;
		}

		public void setValidTo(String validTo) {
			this.validTo = validTo;
		}
	}
}
