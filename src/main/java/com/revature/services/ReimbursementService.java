package com.revature.services;



import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;

public class ReimbursementService {

//	private static Logger log = Logger.getLogger(ReimbursementService.class);
	
	private ReimbursementDAO reimDao = new ReimbursementDAO();
	
	public List<Reimbursement> getAll(){
		return reimDao.getAll();
	}
	
	public Reimbursement getById(int reimbId) {
		return reimDao.getById(reimbId);
	}
	
	public List<Reimbursement> getByAuthor(int reimAuthor) {
		return reimDao.getByAuthor(reimAuthor);
	}
	
	public  Reimbursement add(Reimbursement newReimbursement) {
		//check if all fields are not empty, except for receipt
		if(newReimbursement.getAmount() == 0 || newReimbursement.getSubmitted().equals("") 
				|| newReimbursement.getDescription().equals("") || newReimbursement.getAuthor() == 0
				|| newReimbursement.getReimbStatus().equals(null) || newReimbursement.getReimbType().equals(null)) {
	//		log.info("New Reimbursement has empty fields!");
			return null;
		}
		return reimDao.add(newReimbursement);
	}
	
	public Reimbursement update(Reimbursement newReimbursement) {
        reimDao.update(newReimbursement);
        return newReimbursement; 
	}
}
	
