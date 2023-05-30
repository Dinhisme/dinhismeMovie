package com.poly.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		if (page == null || page.equalsIgnoreCase("")) {
			request.getSession().setAttribute("User", null);
			request.getSession().setAttribute("userNameType", null);
			request.setAttribute("viewPage", "about");
			request.getRequestDispatcher("views/index.jsp").forward(request, response);
		} else {
			request.setAttribute("viewPage", page);
			request.getRequestDispatcher("views/index.jsp").forward(request, response);
		}
	}

}