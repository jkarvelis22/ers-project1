package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.utils.Josh4J;

@WebServlet("/employee")
	public class EmployeeServlet extends HttpServlet {
	static Josh4J j = Josh4J.getInstance();
	
	private static final long serialVersionUID = 1L;	
		private final ReimbursementService reimbService = new ReimbursementService();
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			j.info("EmployeeServlet.doGet(" + request + "," + response + ")");
			PrintWriter writer = response.getWriter();
//			User user = new User();
//			user.setId(5);
			String author = request.getParameter("author"); 
			int authorId = Integer.parseInt(author);
			List<Reimbursement> userReimb = reimbService.getByAuthor(authorId);
			ObjectMapper mapper = new ObjectMapper();
			
			String userReimbs = mapper.writeValueAsString(userReimb);
			System.out.println(userReimbs);
			writer.write(userReimbs);
		}
		
		@Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			j.info("EmployeeServlet.doPost(" + req + "," + resp + ")");
			System.out.println("ENTERED THE DOPOst");
			Reimbursement reimb = null;
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				j.info("EmployeeServlet.doPost(" + req + resp + ") : Variable == False :try catch block entered.");
						System.out.println("ENTERED THE TRY BLOCK");
				
				reimb = mapper.readValue(req.getInputStream(), Reimbursement.class);
				System.out.println("MAPPER READ VALUES");
				System.out.println(reimb);
			} catch (MismatchedInputException mie) {
				mie.printStackTrace();
				System.out.println(mie.getMessage());
			//	log.error(mie.getMessage());
				resp.setStatus(400);
				j.info("EmployeeServlet.doPost(" + req + "," + resp + ") : VALUE was returned");
				return;
			} catch (Exception e) {
				System.out.println(e.getMessage());;
				resp.setStatus(500);
				j.info("EmployeeServlet.doPost(" + req + "," + resp + ") : VALUE was returned");
				return;
			}
			System.out.println("GOING INTO REIMBSERVICE");
			reimb = reimbService.add(reimb);
			System.out.println("REIMBSERV METHOD COM{PLETE");
			try {
				String reimbJson = mapper.writeValueAsString(reimb);
				PrintWriter out = resp.getWriter();
				out.write(reimbJson);
				System.out.println("JSON WROTE BACK");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				resp.setStatus(500);
					
			
		}
		}
}

