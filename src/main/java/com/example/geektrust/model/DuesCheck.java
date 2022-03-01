package com.example.geektrust.model;

/**
 * @author apatil12
 *
 */
public class DuesCheck implements Comparable<Object> {
	Integer amount;
	String name;

	public DuesCheck(Integer amount, String name) {
		this.amount = amount;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public int compareTo(Object o) {
		return this.amount - ((DuesCheck) o).getAmount();
	}
}