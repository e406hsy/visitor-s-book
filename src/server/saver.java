package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class saver
 */
@WebServlet("/saver")
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String content = request.getParameter("content");

		// 클라이언트로 출력하기 위해 준비한다.
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html><body>");
		out.println("<h1>방명록 작성 결과</h1>");
		out.println("email : " + email + "<br>");
		out.println("password : " + password + "<br>");
		out.println("content : " + content + "<br>");

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"), // JDBC URL
					sc.getInitParameter("username"), // DBMS 사용자 아이디
					sc.getInitParameter("password")); // DBMS 사용자 암호
			stmt = conn.prepareStatement("INSERT INTO BOOK(EMAIL,PASSWORD,CONTENT,GEN_TIME)" + " VALUES (?,?,?,NOW())");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			stmt.setString(3, request.getParameter("content"));
			stmt.executeUpdate();
			out.println("성공적으로 등록되었습니다.<br>");

		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
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
		out.println("</body></html>");
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
