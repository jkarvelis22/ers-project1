package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.models.Credentials;
import com.revature.models.Principal;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.utils.Josh4J;
import com.revature.utils.JwtConfig;
import com.revature.utils.JwtGenerator;

@WebServlet("/auth")
//public class AuthServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
////	
//	
////	
public class LoginServlet extends HttpServlet {
	static Josh4J j = Josh4J.getInstance();

	private static final long serialVersionUID = 1L;
//	private static Logger log = Logger.getLogger(AuthServlet.class);
	
	private final UserService userService = new UserService();
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request,response);
//	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		j.info("LoginServlet.doPost(" + request + "," + response + ")");

//		ObjectMapper mapper = new ObjectMapper();
//		String[] credentials = null;
//		System.out.println(req.getInputStream());
//		System.out.println(mapper.readValue(req.getInputStream(), String[].class));
//		try {
//			credentials = mapper.readValue(req.getInputStream(), String[].class);
//			
//		} catch (MismatchedInputException mie) {
//		//log.error(mie.getMessage());
//			resp.setStatus(400);
//			return;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		//log.error(e.getMessage());
//			resp.setStatus(500);
//			return;
//		}
//	
//		if(credentials != null && credentials.length != 2) {
//		//log.warn("Invalid request, unexpected number of credential values");
//			resp.setStatus(400);
//			return;
//		}
//		
//		User user = userService.getByCredentials(credentials[0], credentials[1]);
//		
//		if(user == null) {
//	resp.setStatus(401);
//			return;
//	}
//	
//	resp.setStatus(200);
//		resp.addHeader(JwtConfig.HEADER, JwtConfig.PREFIX + JwtGenerator.createJwt(user));
//
//	}
//	}
//log.info("Request received by AuthServlet.doPost()");
		
		ObjectMapper mapper = new ObjectMapper();
		
		response.setContentType("application/json");
		
		try {
			j.info("LoginServlet.doPost(" + request + "," + response + ") : Variable == False :try catch block entered.");
			PrintWriter writer;
			User authUser;
			writer = response.getWriter();
			
			Credentials credentials = mapper.readValue(request.getInputStream(), Credentials.class);
			
			System.out.println(credentials.getUsername());
			System.out.println(credentials.getPassword());
			
			if(credentials == null || credentials.getUsername().equals("") || credentials.getPassword().equals("")) {
				j.info("LoginServlet.doPost(" + request + "," + response + ") : Variable == False :if block entered.");
				response.setStatus(400);
				j.info("LoginServlet.doPost(" + request + "," + response + ") : VALUE was returned");
				return;
			}
			
			authUser = userService.getByCredentials(credentials.getUsername(), credentials.getPassword());
			
			if(authUser == null) {
				j.info("LoginServlet.doPost(" + request + "," + response + ") : Variable == False : if block entered.");
				response.setStatus(401);
				j.info("LoginServlet.doPost(" + request + "," + response + ") : VALUE was returned");
				return;	
			}
			
			Principal principal = new Principal(authUser.getId(), authUser.getRole().getRoleName(), authUser.getUsername());
			writer.write(mapper.writeValueAsString(principal));
			
			/*
			 * If we wanted to do server-side session management, we would use the Servlet API's HttpSession interface
			 * to manage a client's session with our server:
			 * 
			 * 		HttpSession session = request.getSession();
			 * 
			 * Invalidating the session would require another endpoint that invalidates the session:
			 * 		
			 * 		session.invalidate();
			 */
			
			
			/*
			 * Add the token to the response within an Authorization header if storing the token in
			 * localStorage on the client-side (vulnerable to XSS)
			 */
			
			
		} catch (MismatchedInputException mie) {
		//	log.error(mie.getMessage());
			response.setStatus(400);
		} catch (Exception e) {
	//		log.error(e.getMessage());
			response.setStatus(500);
		}
	}

}
