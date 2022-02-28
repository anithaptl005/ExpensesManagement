package com.example.geektrust;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.geektrust.manager.ExpenseManager;
import com.example.geektrust.model.EqualSplit;
import com.example.geektrust.model.Member;
import com.example.geektrust.model.Split;

public class ExpenseManagerTest {

	@Test
	public void removeMember() {

		ExpenseManager manager = new ExpenseManager();

		manager.addMember(new Member(1, "ANDY"));
		manager.addMember(new Member(1, "BO"));
		manager.addMember(new Member(1, "REX"));

		manager.removeMember("ANDY");

		Assertions.assertEquals(2, manager.getMemberMap().size());

	}

	@Test
	public void showDues() {

		ExpenseManager manager = new ExpenseManager();

		manager.addMember(new Member(1, "ANDY"));
		manager.addMember(new Member(2, "BO"));
		manager.addMember(new Member(3, "REX"));

		List<Split> splits = new ArrayList<>();
		splits.add(new EqualSplit(new Member(1, "WOODY")));
		splits.add(new EqualSplit(new Member(2, "BO")));

		manager.addExpense(6000, "ANDY", splits);

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("BO", 1000);
		manager.getBalanceSheet().put("ANDY", map);

		manager.showDues("ANDY");

		Assertions.assertEquals(true, manager.getBalanceSheet().containsKey("ANDY"));

	}

	@Test
	public void clearDues() {

		ExpenseManager manager = new ExpenseManager();

		manager.addMember(new Member(1, "ANDY"));
		manager.addMember(new Member(1, "BO"));
		manager.addMember(new Member(1, "REX"));

		List<Split> splits = new ArrayList<>();
		splits.add(new EqualSplit(new Member(1, "WOODY")));
		splits.add(new EqualSplit(new Member(2, "BO")));

		manager.addExpense(6000, "ANDY", splits);

		manager.clearDues("ANDY", "BO", 1000);

		Assertions.assertEquals(true, manager.getBalanceSheet().containsKey("ANDY"));

	}

	@Test
	public void addMember() {

		ExpenseManager manager = new ExpenseManager();

		manager.addMember(new Member(1, "ANDY"));

		Assertions.assertEquals(1, manager.getMemberMap().size());

	}
}