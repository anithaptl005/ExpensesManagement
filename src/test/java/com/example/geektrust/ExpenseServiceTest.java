package com.example.geektrust;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.geektrust.model.EqualSplit;
import com.example.geektrust.model.Member;
import com.example.geektrust.model.Spend;
import com.example.geektrust.model.Split;
import com.example.geektrust.service.ExpenseService;

public class ExpenseServiceTest {

	@Test
	public void createExpense() {

		List<Split> splits = new ArrayList<>();
		splits.add(new EqualSplit(new Member(1, "ANDY")));
		splits.add(new EqualSplit(new Member(1, "BO")));

		Spend spend = ExpenseService.createExpense(3000, new Member(1, "WOODY"), splits);

		Assertions.assertEquals(3000, spend.getAmount());

	}
}
