package com.poly.servlet;

import java.io.IOException;
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

import com.poly.dao.UserDao;
import com.poly.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogInServlet() {
		super();

	}

	String page = "";

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		page = request.getParameter("page");
		String action = request.getParameter("action");
		if (action != null) {
			sendNewPassword(request, response);
		}
		if (page.equalsIgnoreCase("login")) {
			logIn(request, response);
		}
		if (page.equalsIgnoreCase("home")) {

		} else {
			request.setAttribute("viewPage", page);
			request.getRequestDispatcher("views/index.jsp").forward(request, response);
		}
	}

	private void logIn(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			String id = request.getParameter("txtUsername");
			request.getSession().setAttribute("userNameType", id);
			String pw = request.getParameter("txtPassword");
			try {
				UserDao dao = new UserDao();
				User user = dao.findByid(id);
				if (!user.getPassword().equals(pw)) {
					request.setAttribute("messagePassword", "Password is not correct!");
				} else {
					page = "home";
					request.getSession().setAttribute("User", user);
					response.sendRedirect("./HomeServlet?page=home");
				}
			} catch (Exception e) {
				request.setAttribute("messageUsername", "Username is not correct or doesn't exist!");
			}
		}
	}

	private void sendNewPassword(HttpServletRequest request, HttpServletResponse response) {
		String username = "vinhpqpc04338@fpt.edu.vn";
		String password = "gerjohwwyxmhpobp";
		String sendTo = request.getParameter("emailSend");
		String rdnm = String.valueOf(Math.floor(Math.random() * 100000));
		String bodyText = rdnm;
		String subjectText = "Your new password at the Dinhisme website!!!";
		UserDao dao = new UserDao();
		List<User> list = dao.findAll();
		String idUserUpdatePassword = "";
		for (User us : list) {
			if (us.getEmail().equals(sendTo)) {
				idUserUpdatePassword = us.getId();
			}
		}
		if (!idUserUpdatePassword.equals("")) {
			User user = dao.findByid(idUserUpdatePassword);
			user.setPassword(rdnm);
			dao.update(user);
		}
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
