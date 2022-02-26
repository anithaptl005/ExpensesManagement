package com.example.geektrust.model;

public class Member {

	private Integer memberId;
	private final String memberName;

	public Member(Integer memberId, String memberName) {
		this.memberId = memberId;
		this.memberName = memberName;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

}
