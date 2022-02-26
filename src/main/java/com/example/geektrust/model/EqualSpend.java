package com.example.geektrust.model;

import java.util.List;

public class EqualSpend extends Spend {
	public EqualSpend(double amount, Member paidBy, List<Split> splits) {
        super(amount, paidBy, splits);
    }

	@Override
	public boolean validate() {
		for (Split split : getSplits()) {
			if (!(split instanceof EqualSplit)) {
				return false;
			}
		}

		return true;
	}
}
