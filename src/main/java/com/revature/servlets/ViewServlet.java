package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.utils.Josh4J;
import com.revature.utils.RequestViewHelper;


public class ViewServlet extends HttpServlet {
	static Josh4J j = Josh4J.getInstance();

	private static final long serialVersionUID = 2L;
	//private static Logger log = Logger.getLogger(ViewServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		j.info("ViewServlet.doGet(" + req + "," + resp + ")");
	//	log.info("We are in ViewSerlet");
		
		String nextView = RequestViewHelper.process(req);

		if (nextView != null) {
			j.info("ViewServlet.doGet(" + req + "," + resp + ") : Variable == False : if block entered.");
			try {
				j.info("ViewServlet.doPost(" + req + "," + resp + ") : Variable == False : try catch block entered.");
				req.getRequestDispatcher(nextView).forward(req, resp);
			} catch (Exception e) {
			//	log.error(e.getMessage());
				//server error
				resp.setStatus(500);
			}
		} else {
			j.info("RegistrationServlet.doPost(" + req + "," + resp + ") : Variable == False : else statement entered.");
			resp.setStatus(401);
		}

	}

}
