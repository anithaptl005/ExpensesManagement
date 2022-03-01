package com.example.geektrust.model;

import java.util.List;

/**
 * @author apatil12
 *
 */
public class EqualSpend extends Spend {
	public EqualSpend(Integer amount, Member paidBy, List<Split> splits) {
		super(amount, paidBy, splits);
	}

}
