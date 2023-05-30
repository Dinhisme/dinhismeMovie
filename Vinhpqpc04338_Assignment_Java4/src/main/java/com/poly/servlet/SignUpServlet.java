package com.poly.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.UserDao;
import com.poly.model.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = request.getParameter("page");
		if (page.equalsIgnoreCase("signup")) {
			signUp(request, response);
		}
		request.setAttribute("viewPage", page);
		request.getRequestDispatcher("views/index.jsp").forward(request, response);
	}

	private void signUp(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			try {
				User user = new User();
				BeanUtils.populate(user, request.getParameterMap());
				String rePass = request.getParameter("RePassword");
				if (user.getPassword().equalsIgnoreCase(rePass)) {
					UserDao dao = new UserDao();
					dao.create(user);
					request.getSession().setAttribute("userSignUp", null);
				} else {
					request.setAttribute("messageRepass", "Repeat password is not correct");
					request.getSession().setAttribute("userSignUp", user);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
