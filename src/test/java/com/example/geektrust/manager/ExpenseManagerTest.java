package com.example.geektrust.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.geektrust.data.TestExpenseData;
import com.example.geektrust.data.TestExpenseData.Const;
import com.example.geektrust.model.EqualSplit;
import com.example.geektrust.model.Member;
import com.example.geektrust.model.Split;

import junit.framework.Assert;

/**
 * @author apatil12
 *
 */
public class ExpenseManagerTest {

	@Test
	public void addMember() {

		ExpenseManager manager = new ExpenseManager();

		manager.addMember(new Member("1", "ANDY"));

		Assertions.assertEquals(1, manager.getMemberMap().size());

	}
	

	@Test
	public void addMemberExceeded() {

		ExpenseManager manager = new ExpenseManager();

		manager.addMember(new Member("1", "ANDY"));
		manager.addMember(new Member("2", "BO"));
		manager.addMember(new Member("3", "WOODY"));
		manager.addMember(new Member("4", "REX"));

		Assertions.assertEquals(3, manager.getMemberMap().size());

	}

	ExpenseManager expenseManager = new ExpenseManager();

	@Test
	public void testAddMember() {
		expenseManager.addMember(new Member("1", TestExpenseData.Const.USER1));
		expenseManager.addMember(new Member("2", TestExpenseData.Const.USER2));
		expenseManager.addMember(new Member("3", TestExpenseData.Const.USER3));
		Assert.assertTrue(expenseManager.getMemberMap().size() == 3);
		expenseManager.addMember(new Member("4", TestExpenseData.Const.USER4));
		Assert.assertTrue(expenseManager.getMemberMap().size() == 3);
	}

	@Test
	public void testAddExpense() {
		testAddMember();
		expenseManager.addExpense(3000, Const.USER1, TestExpenseData.getSplitList(expenseManager));
		Assert.assertTrue(expenseManager.getBalanceSheet().get(Const.USER2).get(Const.USER1) == 1000);
	}

	@Test
	public void testMemberDues() {
		testAddExpense();
		expenseManager.showDues(Const.USER2);
	}

	@Test
	public void testClearDues() {
		testAddExpense();
		expenseManager.clearDues(Const.USER2, Const.USER1, 500);
	}

	/**
	 * 
	 */
	@Test
	public void testRemoveMember() {
		testAddExpense();
		expenseManager.removeMember(Const.USER1);
	}

	@Test
	public void testAddExpenseTwo() {
		testAddExpense();
		Split s1 = new EqualSplit(new Member("2", Const.USER3));
		List<Split> spendForList = new ArrayList<>();
		spendForList.add(s1);
		expenseManager.addExpense(300, Const.USER2, spendForList);

	}

}