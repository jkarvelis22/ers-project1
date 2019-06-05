//package com.revature.servlets;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.exc.MismatchedInputException;
//import com.revature.models.Principal;
//import com.revature.models.User;
//import com.revature.services.UserService;
//
//@WebServlet("/users")
//
//public class UserServlet extends HttpServlet {
//
////    private static final long serialVersionUID = 1L;
////    //   private static Logger log = Logger.getLogger(UserServlet.class);
////    private final UserService userService = new UserService();
////
////    @Override
////    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////resp.setContentType("application/json");
////
////        Principal principal = (Principal) req.getAttribute("principal");
////        String requestURI = req.getRequestURI();
////        ObjectMapper mapper = new ObjectMapper();
////
////   try {
////	   		PrintWriter out = resp.getWriter();
////	   		if(principal == null) {
////
//// //               log.warn("No principal attribute found on request");
////	   		resp.setStatus(401);
////	   		return;
//// }
////	   		if(requestURI.equals("/ers-project1/users") || requestURI.equals("/ers-project1/users/")) {
////	   			if (!principal.getRole().equalsIgnoreCase("manager")) {
////	   				User user = userService.getById(Integer.parseInt(principal.getId()));
////	   				String userJSON = mapper.writeValueAsString(user);
////	   				resp.setStatus(200);
////	   				out.write(userJSON);
////	   			} else {
////	   				List<User> users = userService.getAll();
////	   				String usersJSON = mapper.writeValueAsString(users);
////	   				resp.setStatus(200);
////	   				out.write(usersJSON);
////}
////	   			} else if (requestURI.contains("users/")) {
////
////             String[] fragments = requestURI.split("/");
////             String userId = fragments[3];
////
////       if (!principal.getRole().equalsIgnoreCase("manager") && !principal.getId().equalsIgnoreCase(userId)) {
////
////  //                  log.warn("Unauthorized access attempt made from origin: " + req.getLocalAddr());
////
////                    resp.setStatus(401);
////
////                    return;
////
////                }
////
////                    
////
////                User user = userService.getById(Integer.parseInt(userId));
////
////                String userJSON = mapper.writeValueAsString(user);
////
////                resp.setStatus(200);
////
////                out.write(userJSON);
////
////                    
////
////            } 
////
////        } catch (NumberFormatException nfe) {
////
//// //               log.error(nfe.getMessage());
////
////                resp.setStatus(400);
////
////        } catch (Exception e) {
////
////            e.printStackTrace();
////
////  //          log.error(e.getMessage());
////
////            resp.setStatus(500);
////
////        }
////
////    }
////
////    
////
////    @Override
////
////    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
////
//////        log.info("Request received by UserServlet.doPost()");
////
////        User newUser = null;
////
////        
////
////        ObjectMapper mapper = new ObjectMapper();
////
////        
////
////        try {
////
////            newUser = mapper.readValue(req.getInputStream(), User.class);
////
////        } catch (MismatchedInputException mie) {
////
//////            log.error(mie.getMessage());
////
////            resp.setStatus(400);
////
////            return;
////
////        } catch (Exception e) {
////
//// //           log.error(e.getMessage());
////
////            resp.setStatus(500);
////
////            return;
////
////        }
////
////            
////
////        UserService userService = new UserService();
////
////        List<User> users = userService.getAll();
////
////        for(User user: users) {
////
////            if(user.getUsername().equals(newUser.getUsername())) {
////
////                resp.setStatus(409);
////
////            }
////
////        }       
////
////        
////
////        newUser = userService.add(newUser);
////
////        
////
////        try {
////
////            String userJson = mapper.writeValueAsString(newUser);
////
////            PrintWriter out = resp.getWriter();
////
////            out.write(userJson);
////
////        } catch (Exception e) {
////
//// //           log.error(e.getMessage());
////
////            resp.setStatus(500);
////
////        }
////
////    }
////
////}
//	
//	
package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.models.Principal;
import com.revature.models.User;
import com.revature.services.UserService;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = LogManager.getLogger(UserServlet.class);
	
	private final UserService userService = new UserService();

//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//		log.info("Request received by UserServlet.doGet()");
//		response.setContentType("application/json");
//		
//		Principal principal = (Principal) request.getAttribute("principal");
//		PrintWriter writer = response.getWriter();
//		ObjectMapper mapper = new ObjectMapper();
//		
//		if(principal == null) {
//			log.warn("No principal attribute found on request");
//			response.setStatus(401);
//			return;
//		}
//		
//		if(!principal.getRole().equalsIgnoreCase("Manager")) {
//			log.warn("Unauthorized access attempt made");
//			response.setStatus(403);
//			return;
//		}
//		
//		List<User> users = userService.getAll();
//		String userJSON = mapper.writeValueAsString(users);
//		response.setStatus(200);
//		writer.write(userJSON);
		
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("application/json");
		Principal principal = (Principal) request.getAttribute("principal");
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();

		if(principal == null) {
			System.out.println("[WARN] - no principal attribute found on request");
			response.setStatus(401);
			return;
		}
		if(!principal.getRole().equalsIgnoreCase("Manager")) {
			System.out.println("[WARN] - unauthorized access attempt made");
			response.setStatus(403);
			return;
		}
		
		List<User> users = userService.getAll();
		String userJSON = mapper.writeValueAsString(users); //automatically makes user list into a json string
		response.setStatus(200);
		writer.write(userJSON);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		User newUser = null;
		
		try {
			newUser = mapper.readValue(request.getInputStream(), User.class);
		} catch (MismatchedInputException mie) {
			log.error(mie.getMessage());
			response.setStatus(400); // BAD REQUEST status code
			return;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(500); // INTERNAL SERVER ERROR status code
			return;
		}
		
		newUser = userService.add(newUser);
		
		try {
			String userJSON = mapper.writeValueAsString(newUser);
			PrintWriter writer = response.getWriter();
			writer.write(userJSON);
			response.setStatus(201); // CREATED status code
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setStatus(500);
		}
		
		
	}
}
