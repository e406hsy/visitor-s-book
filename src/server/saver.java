package server;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.dao.BookDao;
import server.vo.Book;

/**
 * Servlet implementation class saver
 */
@WebServlet("/saver")
@MultipartConfig
public class saver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public saver() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String content = request.getParameter("content");

		Pattern emailPattern = Pattern.compile("^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]+)$");
		Matcher emailMatcher = emailPattern.matcher(email);
		if (emailMatcher.find()) {
			try {
				ServletContext application = this.getServletContext();
				BookDao bookDao = (BookDao) application.getAttribute("bookDao");

				Book book = new Book().setEmail(email).setPassword(password).setContent(content);

				if (bookDao.insert(book) == 0) {
					System.out.println("fail to insert");
				}

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		response.sendRedirect("index");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index");
	}

}
