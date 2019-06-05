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
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementInputs;
import com.revature.models.ReimbursementType;
import com.revature.services.ReimbursementService;

@WebServlet("/reimbursements")
public class ReimbursementServlet extends HttpServlet {
	
//    private static final long serialVersionUID = 1L;
// //   private static Logger log = Logger.getLogger(ReimbursementServlet.class);
//    
//    private final ReimbursementService reimService = new ReimbursementService();
//    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//        Principal principal = (Principal) request.getAttribute("principal");
//        
//        String requestURI = request.getRequestURI();
//        ObjectMapper mapper = new ObjectMapper();
//        
//        try {
//            PrintWriter out = response.getWriter();
//            
//            if(principal != null) {
////                log.warn("No principal attribute found on request");
//                response.setStatus(401);
//                System.out.println("Error----401");
//                return;
//            }
//            
//            if(requestURI.equals("ers-project1/reimbursements") || requestURI.equals("/ers-project1/reimbursements/")) {
//                System.out.println("");
//                if (!principal.getRole().equalsIgnoreCase("manager")) {
//                    List<Reimbursement> reimbursement = reimService.getByAuthor((principal.getId()));
//                    String reimJSON = mapper.writeValueAsString(reimbursement);
//                    response.setStatus(200);
//                    out.write(reimJSON);
//                } else {
//                    List<Reimbursement> reim = reimService.getAll();
//                    String reimJSON = mapper.writeValueAsString(reim);
//                    response.setStatus(200);
//                    out.write(reimJSON);
//                }   
//            } 
//            
//        } catch (NumberFormatException nfe) {
// //               log.error(nfe.getMessage());
//                response.setStatus(400);
//        } catch (Exception e) {
//            e.printStackTrace();
// //           log.error(e.getMessage());
//            response.setStatus(500);
//        }
//    }
//    
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//  //      log.info("Request received by ReimbursementServlet.doPost()");
//        Reimbursement someReimbursement = null;
//        
//        resp.setContentType("application/json");
//        Principal principal = (Principal) req.getAttribute("principal");
//        
//        ObjectMapper mapper = new ObjectMapper();
//        
//        try {
//            someReimbursement = mapper.readValue(req.getInputStream(), Reimbursement.class);
//        } catch (MismatchedInputException mie) {
////            log.error(mie.getMessage());
//            resp.setStatus(400);
//            return;
//        } catch (Exception e) {
////            log.error(e.getMessage());
//            resp.setStatus(500);
//            return;
//        }
//        
//        if(someReimbursement.getReimbStatus().getReimbStatusName().equals("pending")) {
//            
//        	someReimbursement.setAuthor((principal.getId()));
//        	
//        	someReimbursement = reimService.add(someReimbursement);
//        } else {
//        	
//        	someReimbursement.setResolver((principal.getId()));
//        	
//        }
//        
//        try {
//            String reimJson = mapper.writeValueAsString(someReimbursement);
//            PrintWriter out = resp.getWriter();
//            out.write(reimJson);
//        } catch (Exception e) {
// //           log.error(e.getMessage());
//            resp.setStatus(500);
//        }
//    }
//    
//}
	
		private static final long serialVersionUID = 1L;	
		private final ReimbursementService reimbService = new ReimbursementService();
		
		//method 3) from ReimbursementService for retrieving all tickets
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
		//Print writer deals with response things
			PrintWriter writer = response.getWriter();
			ObjectMapper mapper = new ObjectMapper();
			
			response.setContentType("application/json");
			String requestURI = request.getRequestURI();
			System.out.println(requestURI);
			
			List<Reimbursement> reimbursement  = reimbService.getAll();
			String userJSON = mapper.writeValueAsString(reimbursement);
			response.setStatus(200);
			writer.write(userJSON);
		}	

		// NEEDS TO BE TESTED
		//method 1) from ReimbursementService for registering a new ticket 
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
			//Need to create an object mapper 
			ObjectMapper map = new ObjectMapper();
			//Request the content type-JSON
			ReimbursementInputs reimb = map.readValue(request.getInputStream(), ReimbursementInputs.class);
			Reimbursement r = new Reimbursement();
			ReimbursementType rs = new ReimbursementType();
			r.setAmount(reimb.getAmount());
			r.setDescription(reimb.getDescription());
			r.setAuthor(reimb.getAuthor());
			rs.setReimbTypeId(reimb.getTypeId());
			r.setReimbType(rs);
			reimbService.add(r);
			
			response.setStatus(200);
			//Request the Service layer
			
			//get the Attribute
			
			//Status codes
		}

	}