package com.example.geektrust.model;

/**
 * @author apatil12
 *
 */
public class Member {

	private String memberId;
	private final String memberName;

	public Member(String memberId, String memberName) {
		this.memberId = memberId;
		this.memberName = memberName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

}
