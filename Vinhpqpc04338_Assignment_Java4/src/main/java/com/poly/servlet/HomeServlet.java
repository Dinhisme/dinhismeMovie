package com.poly.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.dao.VideoDao;
import com.poly.model.Video;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
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
			if (page.equalsIgnoreCase("home")) {
				loadDataVideo(request, response);
			}
			request.setAttribute("viewPage", page);
			request.getRequestDispatcher("views/index.jsp").forward(request, response);
		}
	}

	private void loadDataVideo(HttpServletRequest request, HttpServletResponse response) {
		VideoDao vdDao = new VideoDao();
		List<Video> listVideo = vdDao.findAll();
		int size = listVideo.size();
		request.setAttribute("listSize", size);
		request.setAttribute("listVideo", listVideo);
	}

}
