package com.example.geektrust.data;

import java.util.ArrayList;
import java.util.List;

import com.example.geektrust.manager.ExpenseManager;
import com.example.geektrust.model.EqualSplit;
import com.example.geektrust.model.Split;


/**
 * @author apatil12
 *
 */
public class TestExpenseData {

	public static interface Const {
	    String USER1 = "ANDY";
	    String USER2 = "WOODY";
	    String USER3 = "BO";
	    String USER4 = "RX";
	  }

	  /**
	 * @param expenseManager
	 * @return
	 */
		public static List<Split> getSplitList(ExpenseManager expenseManager) {
	    List<Split> spendForList = new ArrayList<>();
		spendForList.add(new EqualSplit(expenseManager.getMemberMap().get(Const.USER2)));
	    spendForList.add(new EqualSplit(expenseManager.getMemberMap().get(Const.USER3)));
	    return spendForList;
	  }
	
}
