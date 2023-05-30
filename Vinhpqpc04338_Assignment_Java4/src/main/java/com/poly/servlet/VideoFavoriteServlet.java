package com.poly.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.dao.FavoriteDao;
import com.poly.dao.VideoDao;
import com.poly.model.User;
import com.poly.model.Video;

/**
 * Servlet implementation class VideoFavorite
 */
@WebServlet("/VideoFavoriteServlet")
public class VideoFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VideoFavoriteServlet() {
		super();
	}

	String page = "";
	VideoDao vdDao = new VideoDao();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("User");
		String page = request.getParameter("page");
		String unlikeThisVideo = request.getParameter("unlikeThisVideo");
		System.out.println(unlikeThisVideo);
		if (user == null) {
			page = "login";
		} else {
			if (page.equalsIgnoreCase("video_favorite") && unlikeThisVideo != null) {
				unlikeVideo(unlikeThisVideo, user, request, response);
			}
			if (page.equalsIgnoreCase("video_favorite") || unlikeThisVideo == null) {
				loadDataFavorite(user, request, response);
			}
		}
		request.setAttribute("viewPage", page);
		request.getRequestDispatcher("views/index.jsp").forward(request, response);
	}

	private void loadDataFavorite(User user, HttpServletRequest request, HttpServletResponse response) {
		List<Video> listFvVideo = vdDao.findFvById(user.getId());
		request.setAttribute("listFvVideo", listFvVideo);

	}

	private void unlikeVideo(String unlikeThisVideo, User user, HttpServletRequest request,
			HttpServletResponse response) {
		int idLikeVideo = Integer.parseInt(unlikeThisVideo);
		FavoriteDao fvDao = new FavoriteDao();
		Long idFv = fvDao.getIdFvToUnLike(user.getId(), idLikeVideo);
		fvDao.remove(idFv);
	}

}
