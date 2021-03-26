package com.supermarket.bean;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 02:21
 * @Description: 用户登录
 */
public class User {

	private int id;
	private String userName;
	private String passWord;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", passWord='" + passWord + '\'' +
				'}';
	}
}
