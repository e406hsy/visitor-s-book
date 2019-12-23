package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class bookList
 */
@WebServlet(urlPatterns = { "/index" })
public class bookList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public bookList() {
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
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"), // JDBC URL
					sc.getInitParameter("username"), // DBMS 사용자 아이디
					sc.getInitParameter("password")); // DBMS 사용자 암호
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * " + " FROM BOOK");

			out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
					+ "<title>방명록</title>\r\n" + "</head>\r\n" + "<body>\r\n");
			out.println("<h1>방명록</h1>");
			while (rs.next()) {
				out.println("<p>이메일 : " + rs.getString("EMAIL") + "</p>");
				out.println("<p>작성시간 : " + rs.getDate("GEN_TIME") + "</p>");
				if (rs.getDate("CHANGE_TIME") != null) {
					out.println("<p>수정시간 : " + rs.getDate("CHANGE_TIME") + "</p>");
				}
				out.println("<p>본문내용 : " + rs.getString("CONTENT") + "</p>");
				out.println("<br>");
			}
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
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		out.println("<h1>방명록 작성하기</h1>" + "<form action=\"saver\" method=\"post\">\r\n" + "<p>이메일</p>\r\n"
				+ "<input type=\"email\" name=\"email\" style=\"width:500px\">\r\n" + "<p>비밀번호</p>\r\n"
				+ "<input type=\"password\" name=\"password\" style=\"width:500px\">\r\n" + "<p>본문</p>\r\n"
				+ "<input type=\"text\" name=\"content\" style=\"width:500px; height:300px\"><br>\r\n"
				+ "<input type=\"submit\">\r\n" + "</form>\r\n" + "\r\n" + "</body>\r\n" + "</html>");
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
