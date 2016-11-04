package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private String mobiles;
	private String nickName;
	private String userName;
	private String avatars;
	private Integer testBindIfa;
	private Integer identityAuth;
	private String idCard;

	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatars() {
		return avatars;
	}

	public void setAvatars(String avatars) {
		this.avatars = avatars;
	}

	public Integer getTestBindIfa() {
		return testBindIfa;
	}

	public void setTestBindIfa(Integer testBindIfa) {
		this.testBindIfa = testBindIfa;
	}

	public Integer getIdentityAuth() {
		return identityAuth;
	}

	public void setIdentityAuth(Integer identityAuth) {
		identityAuth = identityAuth;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
