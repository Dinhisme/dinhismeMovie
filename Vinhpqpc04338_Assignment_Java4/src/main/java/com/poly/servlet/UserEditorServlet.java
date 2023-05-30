package com.poly.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.UserDao;
import com.poly.model.User;

/**
 * Servlet implementation class UserEditorServlet
 */
@WebServlet("/UserEditorServlet")
public class UserEditorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserEditorServlet() {
		super();

	}

	UserDao uDao = new UserDao();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		User user = (User) request.getSession().getAttribute("User");
		String page = request.getParameter("page");
		if (page == null) {
			page = "user_editor";
		}
		String action = request.getParameter("action");
		String userId = request.getParameter("userID");
		if (user == null) {
			page = "login";
		} else {
			if (action == null) {
				request.setAttribute("active1", "active");
			} else {
				switch (action) {
				case "edit": {
					loadDataUserFormTable(userId, request, response);
					request.setAttribute("active1", "active");
					break;
				}
				case "update": {
					updateDataUser(request, response);
					request.setAttribute("active2", "active");
					break;
				}
				case "delete": {
					deleteUser(request, response);
					request.setAttribute("active2", "active");
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + action);
				}
			}
			loadDataUserEditor(request, response);
		}
		request.setAttribute("viewPage", page);
		request.getRequestDispatcher("views/index.jsp").forward(request, response);
	}

	private void loadDataUserEditor(HttpServletRequest request, HttpServletResponse response) {
		List<User> listUsers = uDao.findAll();
		request.setAttribute("listUsers", listUsers);
	}

	private void loadDataUserFormTable(String id, HttpServletRequest request, HttpServletResponse response) {
		User user = uDao.findByid(id);
		request.setAttribute("userEdit", user);
	}

	private void updateDataUser(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			uDao.update(user);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		String idDelete = request.getParameter("id");
		try {
			uDao.remove(idDelete);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
