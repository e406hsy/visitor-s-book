package server.vo;

import java.sql.Timestamp;;

public class Book {
	protected String email;
	protected String password;
	protected String content;
	protected Timestamp gen_time;
	protected Timestamp change_time;
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getContent() {
		return content;
	}
	public Timestamp getGen_time() {
		return gen_time;
	}
	public Timestamp getChange_time() {
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
	public Book setGenTime(Timestamp gen_time) {
		this.gen_time= gen_time;
		return this;
	}
	public Book setChangeTime(Timestamp change_time) {
		this.change_time= change_time;
		return this;
	}
}
