package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

	List<Spend> expenses;
	Map<String, Member> memberMap;
	Map<String, Map<String, Double>> balanceSheet;

	public ExpenseManager() {
		expenses = new ArrayList<Spend>();
		memberMap = new HashMap<String, Member>();
		balanceSheet = new HashMap<String, Map<String, Double>>();
	}

	public void addMember(Member member) {
		if (memberMap.size() > 2) {
			System.out.println("HOUSEFUL");
		} else {
			memberMap.put(member.getMemberName(), member);
			balanceSheet.put(member.getMemberName(), new HashMap<String, Double>());

			System.out.println("Member is added " + member.getMemberId() + " : " + member.getMemberName());

			System.out.println("SUCCESS");
		}

	}

	public void removeMember(String member) {
		memberMap.remove(member);
		balanceSheet.remove(member, new HashMap<String, Double>());
	}

	public void addExpense(double amount, String paidBy, List<Split> splits) {
		Spend spend = ExpenseService.createExpense(amount, memberMap.get(paidBy), splits);
		expenses.add(spend);
		for (Split split : spend.getSplits()) {
			String paidTo = split.getMember().getMemberName();
			Map<String, Double> balances = balanceSheet.get(paidBy);
			if (!balances.containsKey(paidTo)) {
				balances.put(paidTo, 0.0);
			}
			balances.put(paidTo, balances.get(paidTo) + split.getAmount());

			balances = balanceSheet.get(paidTo);
			if (!balances.containsKey(paidBy)) {
				balances.put(paidBy, 0.0);
			}
			balances.put(paidBy, balances.get(paidBy) - split.getAmount());
		}
	}

	public void showDues(String memberId) {
		boolean isEmpty = true;
		for (Map.Entry<String, Double> userBalance : balanceSheet.get(memberId).entrySet()) {
			if (userBalance.getValue() != 0) {
				isEmpty = false;
				printBalance(memberId, userBalance.getKey(), userBalance.getValue());
			}
		}

		if (isEmpty) {
			System.out.println("No balances");
		}
	}

	public void showBalances() {
		boolean isEmpty = true;
		for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
			for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
				if (userBalance.getValue() > 0) {
					isEmpty = false;
					printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
				}
			}
		}

		if (isEmpty) {
			System.out.println("No balances");
		}
	}

	private void printBalance(String member1, String member2, double amount) {
		String member1Name = memberMap.get(member1).getMemberName();
		String member2Name = memberMap.get(member2).getMemberName();
		if (amount < 0) {
			System.out.println(member1Name + " owes " + member2Name + ": " + Math.abs(amount));
		} else if (amount > 0) {
			System.out.println(member1Name + " owes " + member2Name + ": " + Math.abs(amount));
		}
	}

}
