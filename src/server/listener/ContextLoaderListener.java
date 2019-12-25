package server.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import server.dao.BookDao;
import server.util.DBConnectionPool;

/**
 * Application Lifecycle Listener implementation class ContextLoaderListener
 *
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
	DBConnectionPool connPool;

	/**
	 * Default constructor.
	 */
	public ContextLoaderListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		connPool.closeAll();
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ServletContext application = sce.getServletContext();

			connPool = new DBConnectionPool(
					application.getInitParameter("driver"),
					application.getInitParameter("url"),
					application.getInitParameter("username"),
					application.getInitParameter("password"));
			
			BookDao bookDao = new BookDao();
			bookDao.setConnPool(connPool);

			application.setAttribute("bookDao", bookDao);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
