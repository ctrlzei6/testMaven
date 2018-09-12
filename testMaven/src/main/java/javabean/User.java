package javabean;


public class User {
	private String userId;
	private String userType;
	private String username;
	private String userImage;
	private String password;
	private String registerDate;
	private String userGender;
	private String userHobby;
	private String userEnable;
	
	public User(){}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserEnable() {
		return userEnable;
	}

	public void setUserEnable(String userEnable) {
		this.userEnable = userEnable;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * @return the userHobby
	 */
	public String getUserHobby() {
		return userHobby;
	}

	/**
	 * @param userHobby the userHobby to set
	 */
	public void setUserHobby(String userHobby) {
		this.userHobby = userHobby;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.username+"--"+this.password+"--"+this.userType;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
