package com.example.geektrust;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.example.geektrust.model.ExpenseManager;
import com.example.geektrust.model.Member;
import com.example.geektrust.model.Split;

public class Main {
	public static void main(String[] args) {
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
						List<Split> splits=new ArrayList<Split>();
//						for (int i = values.length-1; i>1 ; i--) {
//							splits.add(new S)
//							
//						}
//						expenseManager.addExpense(values[1], values[2], null);
					} else if (values[0].contains("DUES")) {

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
