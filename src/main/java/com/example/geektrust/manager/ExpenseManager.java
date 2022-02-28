package com.example.geektrust.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
			Map<String, Integer> balances = balanceSheet.get(split.getMember().getMemberName());

			if (!balances.containsKey(paidBy)) {
				balances.put(paidBy, 0);
			}
			balances.put(paidBy, balances.get(paidBy) + split.getAmount());
		}
		if (splits.size() == 1) {
			adjustDue(paidBy, splits.get(0).getMember().getMemberName());
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

	public void showDues(String memberName) {
		Map<String, Integer> m = balanceSheet.get(memberName);
		if (m == null) {
			System.out.println("MEMBER_NOT_FOUND");
			return;
		}

		Set<Entry<String, Integer>> memberBalance = balanceSheet.get(memberName).entrySet();

	}

	private void printBalance(String member1, String member2, Integer amount) {

		String member1Name = memberMap.get(member1).getMemberName();
		String member2Name = memberMap.get(member2).getMemberName();
		if (amount < 0) {
			System.out.println("**** " + member1Name + " " + Math.abs(amount));
		} else if (amount >= 0) {
			System.out.println("**** " + member2Name + " " + Math.abs(amount));
		}
	}

	private void printDues(String memberName, Integer amount) {
		System.out.println("**** " + memberName + " " + Math.abs(amount));
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

	private void adjustDue(String paidBy, String paidTo) {
		Map<String, Integer> paidByDue = balanceSheet.get(paidBy);
		Map<String, Integer> paidToDue = balanceSheet.get(paidTo);
		String paidBykey = (String) paidByDue.keySet().toArray()[0];
		Integer paidByValue = paidByDue.get(paidBykey);
		if (paidToDue.containsKey(paidBykey)) {
			Integer val = paidToDue.get(paidBy);
			Integer balance = paidByValue - val;

			if (Math.signum(balance) == -1) {

				paidToDue.put(paidBy, paidToDue.get(paidBy) - val - balance);
				paidToDue.put(paidBykey, paidToDue.get(paidBykey) + val + balance);
				paidByDue.put(paidBykey, 0);

			} else {

				paidToDue.put(paidBy, paidToDue.get(paidBy) - val);
				paidToDue.put(paidBykey, paidToDue.get(paidBykey) + val);
				paidByDue.put(paidBykey, balance);
			}
		} 
		else {


			
		}
	}

}
