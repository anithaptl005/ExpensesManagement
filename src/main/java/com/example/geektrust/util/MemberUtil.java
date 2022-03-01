package com.example.geektrust.util;

import java.util.ArrayList;
import java.util.List;

import com.example.geektrust.manager.ExpenseManager;
import com.example.geektrust.model.EqualSplit;
import com.example.geektrust.model.Member;
import com.example.geektrust.model.Split;

/**
 * @author apatil12
 *
 */
public class MemberUtil {

	public static void spend(List<String> words, ExpenseManager expenseManager) {
		List<Split> spendForList = new ArrayList<>();
		boolean isMemberExists = true;
		for (int i = words.size() - 1; i > 2; i--) {
			Member member = expenseManager.getMemberMap().get(words.get(i));
			if (member != null) {
				spendForList.add(new EqualSplit(expenseManager.getMemberMap().get(words.get(i))));
			} else {
				isMemberExists = false;
				System.out.println("MEMBER_NOT_FOUND");
			}
		}
		if (isMemberExists) {
			expenseManager.addExpense(Integer.parseInt(words.get(1)), words.get(2), spendForList);
			System.out.println("SUCCESS");
		}
	}

	public interface Const {

		/**
		 * The constant SINGLE_SPACE.
		 */
		String SINGLE_SPACE = " ";
		/**
		 * The constant MOVE_IN.
		 */
		String MOVE_IN = "MOVE_IN";
		/**
		 * The constant SPEND.
		 */
		String SPEND = "SPEND";
		/**
		 * The constant DUES.
		 */
		String DUES = "DUES";
		/**
		 * The constant CLEAR_DUE.
		 */
		String CLEAR_DUE = "CLEAR_DUE";
		/**
		 * The constant MOVE_OUT.
		 */
		String MOVE_OUT = "MOVE_OUT";
		/**
		 * The constant DUES_MEMBER_NAME_INDEX.
		 */
		int DUES_MEMBER_NAME_INDEX = 1;
		/**
		 * The constant CLEAR_DUE_PAID_BY_INDEX.
		 */
		int CLEAR_DUE_PAID_BY_INDEX = 1;
		/**
		 * The constant CLEAR_DUE_PAID_TO_INDEX.
		 */
		int CLEAR_DUE_PAID_TO_INDEX = 2;
		/**
		 * The constant CLEAR_DUE_PAID_AMT_INDEX.
		 */
		int CLEAR_DUE_PAID_AMT_INDEX = 3;
		/**
		 * The constant MOVE_OUT_MEMBER_NAME_INDEX.
		 */
		int MOVE_OUT_MEMBER_NAME_INDEX = 1;
		/**
		 * The constant MOVE_IN_MEMBER_NAME_INDEX.
		 */
		int MOVE_IN_MEMBER_NAME_INDEX = 1;
	}
}
