package com.example.geektrust.model;

public abstract class Split {
	private Member member;
	double amount;

	public Split(Member member) {

		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}