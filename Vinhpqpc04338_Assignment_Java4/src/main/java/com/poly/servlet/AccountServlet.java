package com.poly.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.dao.UserDao;
import com.poly.model.User;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccountServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = request.getParameter("page");
		if (page.equalsIgnoreCase("account")) {
			editProfile(request, response);
		}
		request.setAttribute("viewPage", page);
		request.getRequestDispatcher("views/index.jsp").forward(request, response);
	}

	String editSuccess = "false";

	private void editProfile(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();
		String changePw = request.getParameter("changePassword");
		if (editSuccess.equals("false")) {
			changePw = request.getParameter("changePassword");
		} else {
			changePw = null;
		}
		request.setAttribute("clickchangePW", changePw);
		if (method.equalsIgnoreCase("POST")) {
			User user = (User) request.getSession().getAttribute("User");
			if (changePw == null) {
				user.setFullname(request.getParameter("txtUpdateUserName"));
				changeInfo(user, request, response);
			} else {
				if (user.getPassword().equals(request.getParameter("txtUserOldPassword"))) {
					request.setAttribute("clickchangePW", "false");
					request.setAttribute("newPass", "true");
					System.out.println("yo");
				} else {
					request.setAttribute("messagePassword", "Password is not correct!");
				}
				changePassword(user, request, response);
			}
		}
	}

	private void changePassword(User user, HttpServletRequest request, HttpServletResponse response) {
		String newPassword = request.getParameter("txtUpdateUserPassword");
		String reNewPassword = request.getParameter("txtUpdateUserRePassword");
		if (newPassword == null || reNewPassword == null) {
			return;
		} else {
			if (newPassword.equals(reNewPassword)) {
				user.setFullname(request.getParameter("txtUpdateUserName"));
				user.setPassword(newPassword);
				changeInfo(user, request, response);
				editSuccess = "true";
				request.setAttribute("clickchangePW", null);
				request.setAttribute("newPass", "false");
			} else {
				request.setAttribute("clickchangePW", "false");
				request.setAttribute("newPass", "true");
				System.out.println("cap nhat that bai");
				request.setAttribute("messageReNewPassword", "Repeat Password is not correct!");
			}
		}
	}

	private void changeInfo(User user, HttpServletRequest request, HttpServletResponse response) {
		UserDao dao = new UserDao();
		dao.update(user);
		request.getSession().setAttribute("User", user);
		System.out.println("cap nhat thanh cong");
	}
}
