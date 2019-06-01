package com.javalec.ex;

public class MemberDto {

	private String id;
	private String pw;
	private String name;
	private String eMail;

//	public MemberDto(String id, String pw, String name, String eMail, Timestamp rDate, String address) {
//		this.id = id;
//		this.pw = pw;
//		this.name = name;
//		this.eMail = eMail;
//		this.rDate = rDate;
//		this.address = address;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
}
