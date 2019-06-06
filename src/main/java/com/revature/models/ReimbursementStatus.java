package com.revature.models;

import com.revature.utils.Josh4J;

public class ReimbursementStatus {
	static Josh4J j = Josh4J.getInstance();
	
	private int reimbStatusId;
	private String reimbStatusName;
	
	public ReimbursementStatus() {
		super();
	}
	
	public ReimbursementStatus(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
		
		switch(reimbStatusId) {
		case 1:
			j.info("ReimbursementStatus(" + reimbStatusId + ") : Variable == False :switch casek entered.");
			this.reimbStatusName = "pending"; break;
			case 2:
				j.info("ReimbursementStatus(" + reimbStatusId + ") : Variable == False :switch casek entered.");
			this.reimbStatusName = "approved"; break;
		case 3:
			j.info("ReimbursementStatus(" + reimbStatusId + ") : Variable == False :switch casek entered.");
			this.reimbStatusName = "denied"; break;
		default:
			j.info("ReimbursementStatus(" + reimbStatusId + ") : Variable == False :switch casek entered.");
			this.reimbStatusName = null;
		}
	}

	
	public ReimbursementStatus(String reimbStatusName) {
		super();
		this.reimbStatusName = reimbStatusName;
		
		switch(reimbStatusName) {
		case "pending":
			j.info("ReimbursementStatus(" + reimbStatusName + ") : Variable == False :switch case entered.");
			this.reimbStatusId = 1; break;
		case "approved":
			j.info("ReimbursementStatus(" + reimbStatusName + ") : Variable == False :switch case entered.");
			this.reimbStatusId = 2; break;
		case "denied":
			j.info("ReimbursementStatus(" + reimbStatusName + ") : Variable == False :switch case entered.");
			this.reimbStatusId = 3; break;
		default:
			j.info("ReimbursementStatus(" + reimbStatusName + ") : Variable == False :switch case entered.");
			this.reimbStatusId = 1;
		}
	}
	
	public ReimbursementStatus(int reimbStatusId, String reimbStatusName) {
		super();
		this.reimbStatusId = reimbStatusId;
		this.reimbStatusName = reimbStatusName;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public String getReimbStatusName() {
		return reimbStatusName;
	}

	public void setReimbStatusName(String reimbStatusName) {
		this.reimbStatusName = reimbStatusName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbStatusId;
		result = prime * result + ((reimbStatusName == null) ? 0 : reimbStatusName.hashCode());
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
		ReimbursementStatus other = (ReimbursementStatus) obj;
		if (reimbStatusId != other.reimbStatusId)
			return false;
		if (reimbStatusName == null) {
			if (other.reimbStatusName != null)
				return false;
		} else if (!reimbStatusName.equals(other.reimbStatusName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [reimbStatusId=" + reimbStatusId + ", reimbStatusName=" + reimbStatusName + "]";
	}
	
}
