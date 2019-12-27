package server;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.dao.BookDao;
import server.vo.Book;

/**
 * Servlet implementation class modifier
 */
@WebServlet("/modifyDB")
public class modifier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public modifier() {
		super();
		// TODO Auto-generated constructor stub
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		String content = request.getParameter("content");

		try {
			ServletContext application = this.getServletContext();
			BookDao bookDao = (BookDao) application.getAttribute("bookDao");

			Book book = new Book().setId(id).setPassword(password).setContent(content);

			String passwordFromDB = bookDao.getPassword(book);

			if (password.contentEquals(passwordFromDB)) {
				if (bookDao.modify(book) == 0)
					System.out.println("fail to modify");
			} else {
				System.out.println("wrong password");
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}

		response.sendRedirect("index");
	}

}
