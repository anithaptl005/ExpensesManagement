package com.example.geektrust.service;

import java.util.List;

import com.example.geektrust.model.EqualSpend;
import com.example.geektrust.model.Member;
import com.example.geektrust.model.Spend;
import com.example.geektrust.model.Split;

public class ExpenseService {
	public static Spend createExpense(Integer amount, Member paidBy, List<Split> splits) {

		int totalSplits = splits.size();
		Integer splitAmount = (int) (amount / totalSplits);
		System.out.println("splitAmount " + splitAmount);
		for (Split split : splits) {
			split.setAmount(splitAmount);
		}
		splits.get(0).setAmount(splitAmount + (amount - splitAmount * totalSplits));
		return new EqualSpend(amount, paidBy, splits);

	}
}
