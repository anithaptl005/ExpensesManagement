package com.example.geektrust;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.example.geektrust.manager.ExpenseManager;
import com.example.geektrust.model.Member;
import com.example.geektrust.util.MemberUtil;
import com.example.geektrust.util.MemberUtil.Const;

public class Main {
	public static void main(String[] args) {
		ExpenseManager expenseManager = new ExpenseManager();
	    if (args.length > 0) {
	      String filePath = args[0];
	      readInput(expenseManager, filePath);
	    }
	}

	private static void readInput(ExpenseManager expenseManager, String filePath) {

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = br.readLine();
			int count = 1;
			while (line != null) {
				List<String> words = Arrays.asList(line.split(MemberUtil.Const.SINGLE_SPACE));
				if (words.contains(MemberUtil.Const.MOVE_IN)) {
					expenseManager.addMember(new Member(Integer.toString(count++),
							words.get(MemberUtil.Const.MOVE_IN_MEMBER_NAME_INDEX)));
				}
				if (words.contains(MemberUtil.Const.SPEND)) {
					MemberUtil.spend(words, expenseManager);
				}
				if (words.contains(MemberUtil.Const.DUES)) {
					expenseManager.showDues(words.get(MemberUtil.Const.DUES_MEMBER_NAME_INDEX));
				}
				if (words.contains(MemberUtil.Const.CLEAR_DUE)) {
					expenseManager.clearDues(words.get(MemberUtil.Const.CLEAR_DUE_PAID_BY_INDEX),
							words.get(Const.CLEAR_DUE_PAID_TO_INDEX),
							Integer.parseInt(words.get(Const.CLEAR_DUE_PAID_AMT_INDEX)));
				}
				if (words.contains(MemberUtil.Const.MOVE_OUT)) {
					expenseManager.removeMember(words.get(MemberUtil.Const.MOVE_OUT_MEMBER_NAME_INDEX));
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
