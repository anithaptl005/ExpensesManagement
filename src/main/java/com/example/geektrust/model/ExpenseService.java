package com.example.geektrust.model;

import java.util.List;

public class ExpenseService {
	public static Spend createExpense(double amount, Member paidBy, List<Split> splits) {

		int totalSplits = splits.size();
		double splitAmount = ((double) Math.round(amount * 100 / totalSplits)) / 100.0;
		for (Split split : splits) {
			split.setAmount(splitAmount);
		}
		splits.get(0).setAmount(splitAmount + (amount - splitAmount * totalSplits));
		return new EqualSpend(amount, paidBy, splits);

	}
}
