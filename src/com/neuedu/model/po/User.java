package com.neuedu.model.po;

public class User {
	
	private int id;
	private String username;
	private String password;
	private int age;
	private String phonenumber;
	private String email;
	private String photourl;
	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}

	/*public User(String username, String password, String age,
			String phonenumber, String email) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.phonenumber = phonenumber;
		this.email = email;
	}*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int i) {
		this.age = i;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
