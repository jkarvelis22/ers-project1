package com.revature.models;

import com.revature.utils.Josh4J;

public class ReimbursementInputs {
	static Josh4J j = Josh4J.getInstance();

	private double amount;
	private String description;
	private int author;
	private int typeId;
	
	public ReimbursementInputs() {
		super();
	}

	public ReimbursementInputs(double amount, String description, int author, int typeId) {
		super();
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.typeId = typeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + author;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + typeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementInputs other = (ReimbursementInputs) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author != other.author)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementInputs [amount=" + amount + ", description=" + description + ", author=" + author
				+ ", typeId=" + typeId + "]";
	}
	
	
	
}
