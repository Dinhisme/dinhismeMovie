package com.poly.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.dao.FavoriteDao;
import com.poly.dao.ShareDao;
import com.poly.dao.VideoDao;
import com.poly.model.Favorite;
import com.poly.model.Share;
import com.poly.model.User;
import com.poly.model.Video;

/**
 * Servlet implementation class WatchDetailServlet
 */
@WebServlet("/WatchDetailServlet")
public class WatchDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WatchDetailServlet() {
		super();
	}

	String page = null;
	boolean alreadyLike = false;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		page = request.getParameter("page");
		if (page == null) {
			page = "watch_detail";
		}
		String likeThisVideo = request.getParameter("likeThisVideo");
		if (likeThisVideo != null) {
			likeVideo(likeThisVideo, request, response);
		}
		String share = request.getParameter("action");
		if (share != null) {
			shareFriend(request, response);
		}
		loadDataWatchDetail(request, response);
		request.setAttribute("viewPage", page);
		request.getRequestDispatcher("views/index.jsp").forward(request, response);
	}

	private void loadDataWatchDetail(HttpServletRequest request, HttpServletResponse response) {
		VideoDao vdDao = new VideoDao();
		List<Video> listVideo = vdDao.findAll();
		alreadyLike = false;
		Video videoDetail = new Video();
		String idVideoDetail = request.getParameter("VideoID");
		// tang view
		videoDetail = vdDao.findByid(Integer.valueOf(idVideoDetail));
		videoDetail.setViews(videoDetail.getViews() + 1);
		vdDao.update(videoDetail);
		request.setAttribute("videoDetail", videoDetail);
		// lay like
		FavoriteDao fvDao = new FavoriteDao();
		Long likes = fvDao.getLikes(Integer.valueOf(idVideoDetail));
		// setlike
		User user = (User) request.getSession().getAttribute("User");
		if (user != null) {
			List<Video> listFvVideo = vdDao.findFvById(user.getId());
			for (Video vdVideo : listFvVideo) {
				if (vdVideo.getId() == Integer.valueOf(videoDetail.getId())) {
					alreadyLike = true;
				}
			}
		}
		request.setAttribute("alreadyLike", alreadyLike);
		request.setAttribute("likes", likes);
		request.setAttribute("listVideo", listVideo);
	}

	private void likeVideo(String likeThisVideo, HttpServletRequest request, HttpServletResponse response) {
		VideoDao vdDao = new VideoDao();
		User user = (User) request.getSession().getAttribute("User");
		int idLikeVideo = Integer.parseInt(likeThisVideo);
		if (user == null) {
			page = "login";
		} else {
			if (alreadyLike == true) {
				FavoriteDao fvDao = new FavoriteDao();
				Long idFv = fvDao.getIdFvToUnLike(user.getId(), idLikeVideo);
				fvDao.remove(idFv);
			} else {
				try {
					Date nowDate = new Date();
					Favorite favorite = new Favorite();
					FavoriteDao fvDao = new FavoriteDao();
					Video video = vdDao.findByid(idLikeVideo);
					favorite.setUser(user);
					favorite.setVideo(video);
					favorite.setLikeDate(nowDate);
					fvDao.create(favorite);
				} catch (Exception e) {
					System.out.println(e + " favorite");
				}
			}

		}
	}

	private void shareFriend(HttpServletRequest request, HttpServletResponse response) {
		String username = "vinhpqpc04338@fpt.edu.vn";
		String password = "gerjohwwyxmhpobp";
		String sendTo = request.getParameter("emailSend");
		String bodyText = request.getParameter("body");
		String subjectText = "You got a share form a friend at the Dinhisme website!!!";
		User user = (User) request.getSession().getAttribute("User");
		ShareDao dao = new ShareDao();
		Share share = new Share();
		String videoId = request.getParameter("VideoID");
		VideoDao vDao = new VideoDao();
		Video video = vDao.findByid(Integer.valueOf(videoId));
		share.setVideo(video);
		share.setUser(user);
		share.setEmails(sendTo);
		Date nowDate = new Date();
		share.setShareDate(nowDate);
		dao.create(share);
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
			MimeBodyPart contenPart = new MimeBodyPart();
			contenPart.setContent(bodyText, "text/html;charset=utf-8");
			message.setSubject(subjectText);
			message.setText(bodyText);
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(contenPart);
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println(e);
		}

	}

}
