package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

public class AccountSummary {

	String id;
	String owner;
	LocalDateTime createTime;
	List<Data> transactionsOut;
	List<Data> transactionsIn;
	double balance;

	public AccountSummary() {
		// TODO Auto-generated constructor stub
	}

	public AccountSummary(String id, String owner, LocalDateTime createTime, List<Data> transactionsOut,
			List<Data> transactionsIn, double balance) {
		super();
		this.id = id;
		this.owner = owner;
		this.createTime = createTime;
		this.transactionsOut = transactionsOut;
		this.transactionsIn = transactionsIn;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public List<Data> getTransactionsOut() {
		return transactionsOut;
	}

	public void setTransactionsOut(List<Data> transactionsOut) {
		this.transactionsOut = transactionsOut;
	}

	public List<Data> getTransactionsIn() {
		return transactionsIn;
	}

	public void setTransactionsIn(List<Data> transactionsIn) {
		this.transactionsIn = transactionsIn;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
