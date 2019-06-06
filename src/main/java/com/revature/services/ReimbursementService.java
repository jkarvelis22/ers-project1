package com.revature.services;



import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.utils.Josh4J;

public class ReimbursementService {
	static Josh4J j = Josh4J.getInstance();

//	private static Logger log = Logger.getLogger(ReimbursementService.class);
	
	private ReimbursementDAO reimDao = new ReimbursementDAO();
	
	public List<Reimbursement> getAll(){
		j.info("ReimbursementService.getAll()");
		return reimDao.getAll();
	}
	
	public Reimbursement getById(int reimbId) {
		j.info("ReimbursementService.getById(" + reimbId + ")");
		return reimDao.getById(reimbId);
	}
	
	public List<Reimbursement> getByAuthor(int reimAuthor) {
		j.info("ReimbursementService(" + reimAuthor + ")");
		return reimDao.getByAuthor(reimAuthor);
	}
	
	public  Reimbursement add(Reimbursement newReimbursement) {
		j.info("ReimbursementService.add(" + newReimbursement + ")");
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
		j.info("ReimbursementService.update(" + newReimbursement + ")");
        reimDao.update(newReimbursement);
        return newReimbursement; 
	}
}
	
