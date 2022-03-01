package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author apatil12
 *
 */
public class EqualSpendTest {

	@Test
	public void validate() {
		List<Split> splits = new ArrayList<>();
		splits.add(new EqualSplit(new Member("1", "ANDY")));
		splits.add(new EqualSplit(new Member("2", "BO")));

		Integer amount = 6000;

		EqualSpend spend = new EqualSpend(amount, new Member("3", "WOODY"), splits);

		Assertions.assertEquals("ANDY", spend.getSplits().get(0).getMember().getMemberName());
	}

}
