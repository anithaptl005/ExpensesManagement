package com.example.geektrust.manager;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.example.geektrust.model.DuesCheck;
import com.example.geektrust.util.MemberUtil;

public class ExpenseManagerLogic {

	public static void printDues(List<DuesCheck> values) {
		Collections.sort(values, Collections.reverseOrder());
		int i = 0;
		while (i < values.size() - 1) {
			DuesCheck curVal = values.get(i);
			DuesCheck nextVal = values.get(i + 1);
			if (curVal.getAmount().equals(nextVal.getAmount())) {
				i = i + 2;
				if (curVal.getName().compareTo(nextVal.getName()) > 0) {
					print(nextVal.getName(), nextVal.getAmount());
					print(curVal.getName(), curVal.getAmount());
					continue;
				} else {
					print(curVal.getName(), curVal.getAmount());
					print(nextVal.getName(), nextVal.getAmount());
				}
			} else {
				print(curVal.getName(), curVal.getAmount());
				i++;
			}
		}
		if (values.size() != i) {
			DuesCheck curVal = values.get(i);
			System.out.println(curVal.getName() + " " + curVal.getAmount());
		}
	}

	public static void print(String name, Integer amt) {
		String msg = "%s%s%s";
		System.out.println(String.format(msg, name, MemberUtil.Const.SINGLE_SPACE, amt));
	}
	
//	public static void adjustDue(String paidBy, String paidTo,ExpenseManager manager) {
//		
//		Map<String, Map<String, Integer>> balanceSheet=manager.getBalanceSheet();
//		
//		Map<String, Integer> paidByDue = balanceSheet.get(paidBy);
//		Map<String, Integer> paidToDue = balanceSheet.get(paidTo);
//		if (paidByDue.size() == 0) {
//			return;
//		}
//		String paidBykey = (String) paidByDue.keySet().toArray()[0];
//		Integer paidByValue = paidByDue.get(paidBykey);
//		if (paidToDue.containsKey(paidBykey)) {
//			Integer val = paidToDue.get(paidBy);
//			Integer balance = paidByValue - val;
//
//			if (Math.signum(balance) == -1) {
//
//				paidToDue.put(paidBy, paidToDue.get(paidBy) - val - balance);
//				paidToDue.put(paidBykey, paidToDue.get(paidBykey) + val + balance);
//				paidByDue.put(paidBykey, 0);
//
//			} else {
//
//				paidToDue.put(paidBy, paidToDue.get(paidBy) - val);
//				paidToDue.put(paidBykey, paidToDue.get(paidBykey) + val);
//				paidByDue.put(paidBykey, balance);
//			}
//		} else if (paidByDue.containsKey(paidBykey)) {
//
//			Integer val = paidToDue.get(paidBy);
//			Integer balance = paidByValue - val;
//
//			paidByDue.put(paidBykey, balance);
//			paidToDue.put(paidBy, 0);
//
//		}
//	}

	
}
