package com.example.geektrust.model;

import java.util.List;

public abstract class Spend {

	private String id;
	private double amount;
	private Member paidBy;
	private List<Split> splits;

	public Spend(double amount, Member paidBy, List<Split> splits) {
		this.amount = amount;
		this.paidBy = paidBy;
		this.splits = splits;

	}

	public abstract boolean validate();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Member getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(Member paidBy) {
		this.paidBy = paidBy;
	}

	public List<Split> getSplits() {
		return splits;
	}

	public void setSplits(List<Split> splits) {
		this.splits = splits;
	}

}