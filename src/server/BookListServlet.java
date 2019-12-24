package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.vo.Book;

/**
 * Servlet implementation class bookList
 */
@WebServlet(urlPatterns = { "/index" })
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookListServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		response.setContentType("text/html; charset=UTF-8");

		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT EMAIL,GEN_TIME,CHANGE_TIME,CONTENT " + " FROM BOOK" + " ORDER BY GEN_TIME ASC");

			ArrayList<Book> books = new ArrayList<Book>();
			while (rs.next()) {
				books.add(new Book().setEmail(rs.getString("EMAIL")).setGenTime(rs.getTimestamp("GEN_TIME"))
						.setChangeTime(rs.getTimestamp("CHANGE_TIME")).setContent(rs.getString("CONTENT")));
			}

			request.setAttribute("books", books);

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/BookListJSP.jsp");
			rd.include(request, response);
		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
