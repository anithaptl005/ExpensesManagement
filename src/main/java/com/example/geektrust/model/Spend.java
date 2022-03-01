package com.example.geektrust.model;

import java.util.List;

/**
 * @author apatil12
 *
 */
public abstract class Spend {

	private String id;
	private final Integer amount;
	private final Member paidBy;
	private final List<Split> splits;

	public Spend(Integer amount, Member paidBy, List<Split> splits) {
		this.amount = amount;
		this.paidBy = paidBy;
		this.splits = splits;

	}

	public List<Split> getSplits() {
		return splits;
	}

}