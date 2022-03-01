package com.example.geektrust.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.example.geektrust.model.DuesCheck;
import com.example.geektrust.model.Member;
import com.example.geektrust.model.Spend;
import com.example.geektrust.model.Split;
import com.example.geektrust.service.ExpenseService;

/**
 * @author apatil12
 *
 */

public class ExpenseManager {

	private final int MAXIMUM_PEOPLE_IN_ROOM = 3;
	private Map<String, Member> memberMap;
	private Map<String, Map<String, Integer>> balanceSheet;
	private List<Spend> expenses;

	public ExpenseManager() {
		expenses = new ArrayList<>();
		memberMap = new HashMap<String, Member>();
		balanceSheet = new HashMap<String, Map<String, Integer>>();
	}

	public List<Spend> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Spend> expenses) {
		this.expenses = expenses;
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

	private boolean validateMaxCount() {
		return memberMap.size() < MAXIMUM_PEOPLE_IN_ROOM;
	}

	public void addMember(Member member) {
		if (!validateMaxCount()) {
			System.out.println("HOUSEFUL");
			return;
		}
		memberMap.put(member.getMemberName(), member);
		balanceSheet.put(member.getMemberName(), new HashMap<String, Integer>());

		System.out.println("SUCCESS");

	}

	public void addExpense(Integer amount, String paidBy, List<Split> splits) {
		Spend spend = ExpenseService.createExpense(amount, memberMap.get(paidBy), splits);
		for (Split split : spend.getSplits()) {
			Map<String, Integer> balances = balanceSheet.get(split.getMember().getMemberName());

			if (!balances.containsKey(paidBy)) {
				balances.put(paidBy, 0);
			}
			balances.put(paidBy, balances.get(paidBy) + split.getAmount());
		}
//		ExpenseManagerLogic.adjustDue(paidBy, splits.get(0).getMember().getMemberName(),this);
	}

	public void showDues(String memberName) {
		List<String> s = new ArrayList<String>(memberMap.keySet());
		s.remove(memberName);
		Map<String, Integer> m = balanceSheet.get(memberName);
		if (m == null) {
			System.out.println("MEMBER_NOT_FOUND");
			return;
		}
		List<DuesCheck> values = new ArrayList<>();
		for (Map.Entry<String, Integer> memberBalance : balanceSheet.get(memberName).entrySet()) {
			s.remove(memberBalance.getKey());
			Integer v = memberBalance.getValue();
			values.add(new DuesCheck(v, memberBalance.getKey()));
		}
		if (s.size() == 1) {
			values.add(new DuesCheck(0, s.get(0)));
		}
		ExpenseManagerLogic.printDues(values);

	}

	public void clearDues(String paidBy, String paidTo, Integer amount) {
		if (!(memberMap.containsKey(paidBy) && memberMap.containsKey(paidTo))) {
			System.out.println("MEMBER_NOT_FOUND");
			return;
		}
		Map<String, Integer> balances = balanceSheet.get(paidBy);
		Integer dues = balances.get(paidTo);
		if (Objects.nonNull(dues) && dues >= amount) {
			balances.put(paidTo, dues - amount);
			System.out.println(dues - amount);
		} else {
			System.out.println("INCORRECT_PAYMENT");
		}
	}

	public void removeMember(String memberName) {
		for (Map.Entry<String, Integer> memberBalance : balanceSheet.get(memberName).entrySet()) {
			if (memberBalance.getValue() != 0) {
				System.out.println("FAILURE");
				return;
			}
		}
		for (Map.Entry<String, Member> memberEntry : memberMap.entrySet()) {
			Map<String, Integer> balance = balanceSheet.get(memberEntry.getKey());
			if (balance.containsKey(memberName)) {
				if (balance.get(memberName) > 0) {
					System.out.println("FAILURE");
					return;
				}
			}
		}
		System.out.println("SUCCESS");
	}

}
