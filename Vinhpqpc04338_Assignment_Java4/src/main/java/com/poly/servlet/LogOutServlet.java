package com.poly.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogOutServlet
 */
@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogOutServlet() {
		super();
	}

	String page = "";

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		page = request.getParameter("page");
		if (page.equalsIgnoreCase("logout")) {
			logOut(request, response);
		}
		request.setAttribute("viewPage", page);
		request.getRequestDispatcher("views/index.jsp").forward(request, response);
	}

	private void logOut(HttpServletRequest request, HttpServletResponse response) {
		page = "about";
		request.getSession().setAttribute("User", null);
	}

}
