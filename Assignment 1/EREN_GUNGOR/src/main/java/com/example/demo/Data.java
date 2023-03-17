package com.example.demo;

public class Data {

	String id;
	Account from;
	Account to;
	Double amount;

	public Data() {
		// TODO Auto-generated constructor stub
	}

	public Data(String id, Account from, Account to, Double amount) {
		super();
		this.from = from;
		this.to = to;
		this.id = id;
		this.amount = amount;
	}

	public Account getFrom() {
		return from;
	}

	public void setFrom(Account from) {
		this.from = from;
	}

	public Account getTo() {
		return to;
	}

	public void setTo(Account to) {
		this.to = to;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
