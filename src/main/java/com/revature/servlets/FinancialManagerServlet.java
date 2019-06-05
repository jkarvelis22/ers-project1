package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class FinancialManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ReimbursementService rs = new ReimbursementService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// populate the page with all the tickets that exist in the data base
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		List<Reimbursement> allReimbs = rs.getAll();
		String reimbJSON = mapper.writeValueAsString(allReimbs);
		writer.write(reimbJSON);
		// principle object would be a roleId and userId
		// create a new user object and populate with the two values
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// update the selected ticket to approved/denied
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement update = null;
		boolean updateStatus = false;
		System.out.println("Start of doPost");
		
		try {
			
			update = mapper.readValue(request.getInputStream(), Reimbursement.class);
			System.out.println("MAPPING UPDATE");
			if (update == null) {
				response.setStatus(400);
				return;
			}
			
			System.out.println("PASSED NULL CHECK");
					
		} catch (Exception e) {
			response.setStatus(500);
			System.out.println(e.getMessage());
			System.out.println("500 IS NO GOOD");
		}
		
	}
}