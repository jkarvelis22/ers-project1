package com.revature.utils;



import javax.servlet.http.HttpServletRequest;

import com.revature.models.Principal;

public class RequestViewHelper {
	
//	private static Logger log = Logger.getLogger(RequestViewHelper.class);
	
	private RequestViewHelper() {
		super();
	}
	
	public static String process(HttpServletRequest request) {
		
		switch(request.getRequestURI()) {
		
		case "/ers-project1/login.view":
	//		log.info("Getting login.html");
			return "partials/login.html";
		
		case "/ers-project1/register.view":
	//		log.info("Fetching register.html");
			return "partials/register.html";
		
		case "/ers-project1/employee.view":
			
			Principal principalEmployee = (Principal) request.getAttribute("principal");
			
			if(principalEmployee == null) {
	//			log.warn("No principal attribute found on request object");
				return null;
			}
			
	//		log.info("Fetching employee.html");
			return "partials/employee.html";
		
		case "/ers-project1/managers.view":
			
			Principal principalManager = (Principal) request.getAttribute("principal");
			
			if(principalManager == null) {
	//			log.warn("No principal attribute found on request object");
				return null;
			}
			
//			log.info("Fetching managers.html");
			return "partials/managers.html";
			
		case "/ers-project1.view":
			
			Principal principalReimbursement = (Principal) request.getAttribute("principal");
			
			if(principalReimbursement == null) {
	//			log.warn("No principal attribute found on request object");
				return null;
			}
			
	//		log.info("Fetching reimbursement.html");
			return "partials/reimbursement.html";
			
		default: 
//			log.info("Invalid view requested");
			return null;
		}
	}

}
