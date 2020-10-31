package dimaliahov.controller;

import dimaliahov.model.User;
import dimaliahov.service.DAOFactory;
import dimaliahov.service.DAOInterfase.CartDAO;
import dimaliahov.service.DAOInterfase.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletLogin extends HttpServlet {
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getMethods(req, resp);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		postMethods(request, resp);
	}

	private void getMethods (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/login.jsp");
		rd.forward(req, resp);
	}

	private void postMethods (HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String login = request.getParameter("f1");
		String password = request.getParameter("f2");
		String logout = request.getParameter("logout");
		HttpSession session = request.getSession();
		if (logout != null) {
			session.removeAttribute("login");
			session.removeAttribute("password");
			session.removeAttribute("userPojo");
			session.removeAttribute("role");

			request.removeAttribute("login");
			request.removeAttribute("password");
			request.removeAttribute("error");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/login.jsp");
			rd.forward(request, resp);
		}
		StringBuilder stringBuilder = new StringBuilder();
		if (login.equals("") && password.equals("")) {
			stringBuilder.append("Please input value of login and password first");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/login.jsp");
			request.setAttribute("error", stringBuilder);
			rd.forward(request, resp);
		} else {
			DAOFactory daoFactory = DAOFactory.getInstance(1);
			UserDAO userDAO = daoFactory.getUserDAO();
			User user;
			user = userDAO.getUser(login, password);
			session.setAttribute("userPojo", user);
			CartDAO cartDAO = daoFactory.getCartDAO();
			session.setAttribute("countCart", cartDAO.getCountProductByUserId(user.getId()));
			{
				session.setAttribute("login", login);
				session.setAttribute("password", password);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/login.jsp");
				String roleAccess = user.getRole();
				session.setAttribute("role", roleAccess);
				rd.forward(request, resp);
			}
		}
	}
}
