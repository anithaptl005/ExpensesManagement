package com.example.geektrust.service;

import java.util.List;

import com.example.geektrust.model.EqualSpend;
import com.example.geektrust.model.Member;
import com.example.geektrust.model.Spend;
import com.example.geektrust.model.Split;

/**
 * @author apatil12
 *
 */
public class ExpenseService {
	public static Spend createExpense(Integer amount, Member paidBy, List<Split> splits) {

		int totalSplits = splits.size() + 1;
		Integer splitAmount = (int) (amount / totalSplits);
		for (Split split : splits) {
			split.setAmount(splitAmount);
		}
		return new EqualSpend(amount, paidBy, splits);

	}
}
