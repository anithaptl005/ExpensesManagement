package com.example.geektrust.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.geektrust.model.Member;
import com.example.geektrust.model.Spend;
import com.example.geektrust.model.Split;
import com.example.geektrust.service.ExpenseService;

public class ExpenseManager {

	private Map<String, Member> memberMap;
	private Map<String, Map<String, Integer>> balanceSheet;

	public void addExpense(Integer amount, String paidBy, List<Split> splits) {
		Spend spend = ExpenseService.createExpense(amount, memberMap.get(paidBy), splits);
		for (Split split : spend.getSplits()) {
			String paidTo = split.getMember().getMemberName();
			System.out.println("paid to : " + paidTo);
			Map<String, Integer> balances = balanceSheet.get(paidBy);
			if (!balances.containsKey(paidTo)) {
				balances.put(paidTo, 0);
			}
			balances.put(paidTo, balances.get(paidTo) + split.getAmount());

			balances = balanceSheet.get(paidTo);
			if (!balances.containsKey(paidBy)) {
				balances.put(paidBy, 0);
			}
			balances.put(paidBy, balances.get(paidBy) - split.getAmount());
		}
	}

	public Map<String, Member> getMemberMap() {
		return memberMap;
	}

	public void setMemberMap(Map<String, Member> memberMap) {
		this.memberMap = memberMap;
	}

	public Map<String, Map<String, Integer>> getBalanceSheet() {
		return balanceSheet;
	}

	public void setBalanceSheet(Map<String, Map<String, Integer>> balanceSheet) {
		this.balanceSheet = balanceSheet;
	}

	public ExpenseManager() {
		memberMap = new HashMap<String, Member>();
		balanceSheet = new HashMap<String, Map<String, Integer>>();
	}

	public void addMember(Member member) {
		if (memberMap.size() > 2) {
			System.out.println("HOUSEFUL");
		} else {
			memberMap.put(member.getMemberName(), member);
			balanceSheet.put(member.getMemberName(), new HashMap<String, Integer>());

			System.out.println("SUCCESS");
		}

	}

	public void showBalances() {
		boolean isEmpty = true;
		for (Map.Entry<String, Map<String, Integer>> allBalances : balanceSheet.entrySet()) {
			for (Map.Entry<String, Integer> memberBalance : allBalances.getValue().entrySet()) {
				if (memberBalance.getValue() > 0) {
					isEmpty = false;
					printBalance(allBalances.getKey(), memberBalance.getKey(), memberBalance.getValue());
				}
			}
		}

		if (isEmpty) {
			System.out.println("No balances");
		}
	}

	public void showDues(String memberName) {
		boolean isEmpty = true;
		for (Map.Entry<String, Integer> memberBalance : balanceSheet.get(memberName).entrySet()) {
			if (memberBalance.getValue() != 0) {
				isEmpty = false;
				printBalance(memberName, memberBalance.getKey(), memberBalance.getValue());
			}
		}

		if (isEmpty) {
			System.out.println("No balances");
		}
	}

	private void printBalance(String user1, String user2, Integer amount) {
		String member1Name = memberMap.get(user1).getMemberName();
		String member2Name = memberMap.get(user2).getMemberName();
		if (amount < 0) {
			System.out.println(member1Name + " " + Math.abs(amount));
		} else if (amount > 0) {
			System.out.println(member2Name + " " + Math.abs(amount));
		}
	}

	public void clearDues(String paidBy, String paidTo, Integer amount) {
		Map<String, Integer> balances = balanceSheet.get(paidBy);
		Integer dues = balances.get(paidTo);
		if (dues >= amount) {
			balances.put(paidTo, dues - amount);
			System.out.println(dues - amount);
		} else {
			System.out.println("INCORRECT_PAYMENT");
		}
	}

	public void removeMember(String memberName) {
		for (Map.Entry<String, Integer> userBalance : balanceSheet.get(memberName).entrySet()) {
			if (userBalance.getValue() != 0) {
				System.out.println("FAILURE");
				return;
			}
		}
		memberMap.remove(memberName);
		balanceSheet.remove(memberName, new HashMap<String, Double>());
		System.out.println("SUCCESS");
	}

}
