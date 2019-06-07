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
		j.info("ReimbursementService.getAll() : VALUE was returned");
		return reimDao.getAll();
	}
	
	public Reimbursement getById(int reimbId) {
		j.info("ReimbursementService.getById(" + reimbId + ")");
		j.info("ReimbursementService.getById(" + reimbId + ") : VALUE was returned");
		return reimDao.getById(reimbId);
	}
	
	public List<Reimbursement> getByAuthor(int reimAuthor) {
		j.info("ReimbursementService.getByAuthor(" + reimAuthor + ")");
		j.info("ReimbursementService.getByAuthor(" + reimAuthor + ") : VALUE was returned");
		return reimDao.getByAuthor(reimAuthor);
	}
	
	public  Reimbursement add(Reimbursement newReimbursement) {
		j.info("ReimbursementService.add(" + newReimbursement + ")");
		//check if all fields are not empty, except for receipt
		if(newReimbursement.getAmount() == 0 || newReimbursement.getSubmitted().equals("") 
				|| newReimbursement.getDescription().equals("") || newReimbursement.getAuthor() == 0
				|| newReimbursement.getReimbStatus().equals(null) || newReimbursement.getReimbType().equals(null)) {
			j.info("ReimbursementService.add(" + newReimbursement + ") : Variable == False :if block entered.");
			//		log.info("New Reimbursement has empty fields!");
			j.info("ReimbursementService.add(" + newReimbursement + ") : VALUE was returned");
			return null;
		}
		j.info("ReimbursementService.add(" + newReimbursement + ") : VALUE was returned");
		return reimDao.add(newReimbursement);
	}
	
	public Reimbursement update(Reimbursement newReimbursement) {
		j.info("ReimbursementService.update(" + newReimbursement + ")");
        reimDao.update(newReimbursement);
        j.info("ReimbursementService.update(" + newReimbursement + ") : VALUE was returned");
        return newReimbursement; 
	}
}
	
