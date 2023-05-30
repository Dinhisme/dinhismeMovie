package com.poly.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.model.User;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReportServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			page = "login";
		} else {
			if (action == null) {
				request.setAttribute("active1", "active");
			} else {
				switch (action) {
				case "tab1": {
					request.setAttribute("active1", "active");
					break;
				}
				case "tab2": {
					request.setAttribute("active2", "active");
					break;
				}
				case "tab3": {
					request.setAttribute("active3", "active");
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + action);
				}
			}
			callProcSTATISTICAL(request, response);
			callProcFILTER_VIDEO_FAV_USER(request, response);
			callProcFILTER_USER_SHARE(request, response);
		}
		request.setAttribute("viewPage", page);
		request.getRequestDispatcher("/views/index.jsp").forward(request, response);
	}

	String firstSet = null;

	private void callProcSTATISTICAL(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("DinhismeMovie");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			StoredProcedureQuery query = em.createStoredProcedureQuery("STATISTICAL");
			em.getTransaction().commit();
			List<Object[]> listx = query.getResultList();
			for (Object[] ojObject : listx) {
				firstSet = String.valueOf(ojObject[1]);
				break;
			}
			request.setAttribute("videosProcStatistical", listx);
			em.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void callProcFILTER_VIDEO_FAV_USER(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("DinhismeMovie");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			String titleVd = request.getParameter("titleVdTab2");
			if (titleVd == null) {
				titleVd = firstSet;
			}
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery("favoriteByUser");
			query.setParameter("titleVideo", titleVd);
			em.getTransaction().commit();
			List<Object[]> listx = query.getResultList();
			request.setAttribute("videosProcFilterVideo", listx);
			em.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void callProcFILTER_USER_SHARE(HttpServletRequest request, HttpServletResponse response) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("DinhismeMovie");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			String titleVd = request.getParameter("titleVdTab3");
			if (titleVd == null) {
				titleVd = firstSet;
			}
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery("userShare");
			query.setParameter("titleVideo", titleVd);
			em.getTransaction().commit();
			List<Object[]> listx = query.getResultList();
			request.setAttribute("videosProcShare", listx);
			em.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
