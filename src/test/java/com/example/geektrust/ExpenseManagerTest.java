package com.example.geektrust;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.geektrust.manager.ExpenseManager;
import com.example.geektrust.model.Member;

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

//	@Test
//	public void showDues() {
//
//		ExpenseManager manager = new ExpenseManager();
//
//		manager.showDues("ANDY");
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		map.put("ANDY", 1000);
//
//		Assertions.assertEquals(false, manager.getBalanceSheet().containsKey("ANDY"));
//
//	}

//	@Test
//	public void clearDues() {
//
//		ExpenseManager manager = new ExpenseManager();
//
//		manager.clearDues("ANDY", "BO", 1000);
//
//		Assertions.assertEquals(1, manager.getMemberMap().size());
//
//	}

	@Test
	public void addMember() {

		ExpenseManager manager = new ExpenseManager();

		manager.addMember(new Member(1, "ANDY"));

		Assertions.assertEquals(1, manager.getMemberMap().size());

	}

}
