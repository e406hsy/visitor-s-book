package server.vo;

import java.util.Date;

public class Book {
	protected String email;
	protected String password;
	protected String content;
	protected Date gen_time;
	protected Date change_time;
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getContent() {
		return content;
	}
	public Date getGen_time() {
		return gen_time;
	}
	public Date getChange_time() {
		return change_time;
	}

	public Book setEmail(String email) {
		this.email= email;
		return this;
	}
	public Book setPassword(String password) {
		this.password= password;
		return this;
	}
	public Book setContent(String content) {
		this.content= content;
		return this;
	}
	public Book setGenTime(Date gen_time) {
		this.gen_time= gen_time;
		return this;
	}
	public Book setChangeTime(Date change_time) {
		this.change_time= change_time;
		return this;
	}
}
