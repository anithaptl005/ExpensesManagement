package com.example.geektrust.model;

public abstract class Split {
	private Member member;
	Integer amount;

	public Split(Member member) {

		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}