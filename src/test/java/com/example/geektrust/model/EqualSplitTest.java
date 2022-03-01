package com.example.geektrust.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author apatil12
 *
 */
public class EqualSplitTest {

	@Test
	public void validate() {

		Split split = new EqualSplit(new Member("1", "WOODY"));

		Assertions.assertEquals("WOODY", split.getMember().getMemberName());
	}

}
