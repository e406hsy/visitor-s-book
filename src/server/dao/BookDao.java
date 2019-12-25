package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import server.vo.Book;

public class BookDao {
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public int insert(Book book) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection
					.prepareStatement("INSERT INTO BOOK(EMAIL,PASSWORD,CONTENT,GEN_TIME)" + " VALUES (?,?,?,NOW())");
			stmt.setString(1, book.getEmail());
			stmt.setString(2, book.getPassword());
			stmt.setString(3, book.getContent());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
		}
	}

	public List<Book> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT EMAIL,GEN_TIME,CHANGE_TIME,CONTENT " + " FROM BOOK" + " ORDER BY GEN_TIME ASC");

			ArrayList<Book> books = new ArrayList<Book>();
			while (rs.next()) {
				books.add(new Book().setEmail(rs.getString("EMAIL")).setGenTime(rs.getTimestamp("GEN_TIME"))
						.setChangeTime(rs.getTimestamp("CHANGE_TIME")).setContent(rs.getString("CONTENT")));
			}
			return books;
		} catch (Exception e) {
			throw e;
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
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
		}
	}
}
