package com.example.geektrust;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.example.geektrust.manager.ExpenseManager;
import com.example.geektrust.model.EqualSplit;
import com.example.geektrust.model.Member;
import com.example.geektrust.model.Split;

public class Main {
	public static void main(String[] args) {
//		String filePath = args[0];
		ExpenseManager expenseManager = new ExpenseManager();
		readInput(expenseManager, "input1.txt");
	}

	private static void readInput(ExpenseManager expenseManager, String fileName) {
		try (BufferedReader br = new BufferedReader(
				new FileReader(String.format("sample_input%s%s", File.separator, fileName)))) {

			Stream<String> sCurrentLine = null;
//			Integer count = 1;
			while ((sCurrentLine = br.lines()) != null) {
				Stream<String> lines = br.lines();

				lines.forEach(l -> {
					Integer count = 1;
					String[] values = l.split(" ");
					if (values[0].contains("MOVE_IN")) {
						expenseManager.addMember(new Member((count++), values[1]));
					} else if (values[0].contains("SPEND")) {
						List<Split> splits = new ArrayList<Split>();
						for (int i = values.length - 1; i > 2; i--) {

							Member member = expenseManager.getMemberMap().get(values[i]);

							if (member == null) {
								System.out.println("MEMBER_NOT_FOUND");
							} else {
								splits.add(new EqualSplit(member));
							}

						}
						expenseManager.addExpense(Integer.parseInt(values[1]), values[2], splits);


					} else if (values[0].contains("DUES")) {
						
						expenseManager.showDues(values[1]);
						
					} else if (values[0].contains("CLEAR_DUE")) {

						expenseManager.clearDues(values[1], values[2], Integer.parseInt(values[3]));

					} else if (values[0].contains("MOVE_OUT")) {

						Member member = expenseManager.getMemberMap().get(values[1]);

						if (member == null) {
							System.out.println("MEMBER_NOT_FOUND");
						} else {
							expenseManager.removeMember(values[1]);
						}

					}
				});

			}

			/**
			 * String line = br.readLine(); Integer count = 1; while (line != null) {
			 * expenseManager.addMember(new Member((count++), line)); line = br.readLine();
			 * }
			 **/
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
}
