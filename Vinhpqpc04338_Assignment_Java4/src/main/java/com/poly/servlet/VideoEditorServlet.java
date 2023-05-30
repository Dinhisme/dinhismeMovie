package com.poly.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.VideoDao;
import com.poly.model.User;
import com.poly.model.Video;
import com.poly.utils.RenameFileAddLibrary;

/**
 * Servlet implementation class VideoEditorServlet
 */
@MultipartConfig
@WebServlet("/VideoEditorServlet")
public class VideoEditorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VideoEditorServlet() {
		super();
	}

	VideoDao vDao = new VideoDao();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		User user = (User) request.getSession().getAttribute("User");
		String page = request.getParameter("page");
		if (page == null) {
			page = "video_editor";
		}
		String action = request.getParameter("action");
		String videoId = request.getParameter("videoID");
		if (user == null) {
			page = "login";
		} else {
			if (action == null) {
				reset(request, response);
				request.setAttribute("active1", "active");
			} else {
				switch (action) {
				case "create": {
					createDataVideo(request, response);
					request.setAttribute("active2", "active");
					break;
				}
				case "edit": {
					loadDataVideoFormTable(videoId, request, response);
					request.setAttribute("active1", "active");
					break;
				}
				case "update": {
					updateDataVideo(request, response);
					request.setAttribute("active2", "active");
					break;
				}
				case "delete": {
					deleteVideo(request, response);
					request.setAttribute("active2", "active");
					break;
				}
				case "reset": {
					reset(request, response);
					request.setAttribute("active1", "active");
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + action);
				}
			}
			loadDataVideoEditor(request, response);
		}
		request.setAttribute("viewPage", page);
		request.getRequestDispatcher("views/index.jsp").forward(request, response);
	}

	private void loadDataVideoEditor(HttpServletRequest request, HttpServletResponse response) {
		List<Video> listVDE = vDao.findAll();
		request.setAttribute("listVideo", listVDE);
	}

	private void loadDataVideoFormTable(String id, HttpServletRequest request, HttpServletResponse response) {
		Video video = vDao.findByid(Integer.valueOf(id));
		request.setAttribute("videoEdit", video);
	}

	private void updateDataVideo(HttpServletRequest request, HttpServletResponse response) {
		try {
			Video video = new Video();
			String titleVideo = request.getParameter("title");
			int idVideo = Integer.valueOf(request.getParameter("idUpdate"));
			if (idVideo != 0) {
				Video vid = vDao.findByid(idVideo);
				BeanUtils.populate(video, request.getParameterMap());
				if (!request.getPart("img_one").getSubmittedFileName().equals("")) {
					File dirr = new File(request.getServletContext().getRealPath("views/poster"));
					if (!dirr.exists()) {
						dirr.mkdirs();
					}
					Part photo = request.getPart("img_one");
					File photoFile = new File(dirr, RenameFileAddLibrary.renameFile(titleVideo));
					photo.write(photoFile.getAbsolutePath());
					video.setPoster(RenameFileAddLibrary.renameFile(titleVideo));
				} else {
					video.setPoster(vid.getPoster());
				}
				if (!request.getPart("img_two").getSubmittedFileName().equals("")) {
					File dir = new File(request.getServletContext().getRealPath("views/thumbnail"));
					if (!dir.exists()) {
						dir.mkdirs();
					}
					Part photo = request.getPart("img_two");
					File photoFile = new File(dir, RenameFileAddLibrary.renameFile(titleVideo + "Thumb"));
					photo.write(photoFile.getAbsolutePath());
					video.setThumbnail(RenameFileAddLibrary.renameFile(titleVideo + "Thumb"));
				} else {
					video.setThumbnail(vid.getThumbnail());
				}
				video.setId(vid.getId());
				video.setViews(vid.getViews());
				video.setFavorite(vid.getFavorite());
				video.setShare(vid.getShare());
				vDao.update(video);
			}
			reset(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteVideo(HttpServletRequest request, HttpServletResponse response) {
		int idDelete = Integer.valueOf(request.getParameter("idDelete"));
		if (idDelete != 0) {
			try {
				vDao.remove(idDelete);
				reset(request, response);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void reset(HttpServletRequest request, HttpServletResponse response) {
		Video video = new Video();
		video.setPoster("poster.png");
		video.setThumbnail("br.png");
		request.setAttribute("videoEdit", video);
	}

	private void createDataVideo(HttpServletRequest request, HttpServletResponse response) {
		try {
			Video video = new Video();
			BeanUtils.populate(video, request.getParameterMap());
			String titleVideo = request.getParameter("title");
			if (!request.getPart("img_one").getSubmittedFileName().equals("")) {
				File dirr = new File(request.getServletContext().getRealPath("views/poster"));
				if (!dirr.exists()) {
					dirr.mkdirs();
				}
				Part photo = request.getPart("img_one");
				File photoFile = new File(dirr, RenameFileAddLibrary.renameFile(titleVideo));
				photo.write(photoFile.getAbsolutePath());
				video.setPoster(RenameFileAddLibrary.renameFile(titleVideo));
			} else {
				video.setPoster("poster.png");
			}
			if (!request.getPart("img_two").getSubmittedFileName().equals("")) {
				File dir = new File(request.getServletContext().getRealPath("views/thumbnail"));
				if (!dir.exists()) {
					dir.mkdirs();
				}
				Part photo = request.getPart("img_two");
				File photoFile = new File(dir, RenameFileAddLibrary.renameFile(titleVideo + "Thumb"));
				photo.write(photoFile.getAbsolutePath());
				video.setThumbnail(RenameFileAddLibrary.renameFile(titleVideo + "Thumb"));
			} else {
				video.setThumbnail("br.png");
			}

			video.setViews(0);
			video.setActive(true);
			vDao.create(video);
			reset(request, response);
			System.out.println("tao video thanh cong ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
