package com.example.demo;

import java.util.List;

public class Message_List<T> {

	private String message;
	private List<T> data;

	public Message_List() {
		// TODO Auto-generated constructor stub
	}

	public Message_List(String message, List<T> data) {
		super();
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
