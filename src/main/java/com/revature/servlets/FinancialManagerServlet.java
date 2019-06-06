package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.utils.Josh4J;

public class FinancialManagerServlet extends HttpServlet {
	static Josh4J j = Josh4J.getInstance();

	private static final long serialVersionUID = 1L;
	
	private ReimbursementService rs = new ReimbursementService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		j.info("FinancialManagerServlet.doGet(" + request + "," + response + ")");
		// populate the page with all the tickets that exist in the data base
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<Reimbursement> allReimbs = rs.getAll();
		String reimbJSON = mapper.writeValueAsString(allReimbs);
		writer.write(reimbJSON);
		// principle object would be a roleId and userId
		// create a new user object and populate with the two values
	}


	
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		// update the selected ticket to approved/denied
//		ObjectMapper mapper = new ObjectMapper();
//		Reimbursement update = null;
//		boolean updateStatus = false;
//		
//		try {
//			
//			update = mapper.readValue(request.getInputStream(), Reimbursement.class);
//			
//			if (update == null) {
//				response.setStatus(400);
//				return;
//			}
//			
//			updateStatus = rs.update(update);
//			
//			if (updateStatus == false) {
//				response.setStatus(400);
//				return;
//			}
//			
//		} catch (Exception e) {
//			response.setStatus(500);
//			System.out.println(e.getMessage());
//		}
//		
//	}
//}

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		j.info("FinancialManagerServlet.doPost(" + req + "," + resp + ")");
		System.out.println("IN THE DO POST");
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			System.out.println("IN THE TRY BLOCK");
			Reimbursement val = mapper.readValue(req.getInputStream(), Reimbursement.class);
			System.out.println(val);
			System.out.println("MAPPER VALUE");
			if(val == null) {
				System.out.println("JUMMPED INTO THE IF STATEMENT");
			//	log.info("input on update is null");
				resp.setStatus(400);
				System.out.println("400 STATUS");
				return;
				
			}
			Reimbursement reimb = new Reimbursement();
			System.out.println("PREPARING TO EXECUTE THE UPDATE");
			reimb = rs.update(val);
			
			System.out.println("YOU SCREWED THIS ONE UP");
			
			String reimbJson = mapper.writeValueAsString(reimb);
			PrintWriter out = resp.getWriter();
			System.out.println("JSON MAPPER");
			out.write(reimbJson);
			
		} catch (MismatchedInputException mie) {
			System.out.println("WRONG INPUTS");
			mie.printStackTrace();
			System.out.println(mie.getMessage());
	//		log.error(mie.getMessage());
			resp.setStatus(400);
			return;
		} catch (Exception e) {
			System.out.println("CAUGHT THE EXCEPTION");
			System.out.println(e.getMessage());
			resp.setStatus(500);
			return;
		}
	}
}