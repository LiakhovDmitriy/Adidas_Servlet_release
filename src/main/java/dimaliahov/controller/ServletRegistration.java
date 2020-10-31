package dimaliahov.controller;

import dimaliahov.model.User;
import dimaliahov.service.DAOFactory;
import dimaliahov.service.DAOInterfase.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServletRegistration extends HttpServlet {
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getMethods(req, resp);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		postMethods(request, resp);

	}

	private void getMethods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/register.jsp");
		rd.forward(req, resp);
	}

	private void postMethods(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException{
		boolean showForm = true;
		boolean isError = false;
		ArrayList<String> error = new ArrayList<>();
		HttpSession httpSession = request.getSession();

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		if (login != null) {
			if (login.isEmpty()) {
				error.add("The 'login' is empty.");
				isError = true;
			}
			Pattern pattern = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher matcher = pattern.matcher(login);
			boolean loginBol = matcher.matches();
			if (!loginBol) {
				error.add("The 'login' is wrong. It should look like 'name@gmail.com'.");
				isError = true;
			}
			if (password.isEmpty()) {
				error.add("The 'password' is empty.");
				isError = true;
			}
			if (!password.isEmpty()) {
				Pattern patternP = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
				Matcher matcherP = patternP.matcher(password);
				boolean passwordBol = matcherP.matches();
				if (!passwordBol) {
					error.add("The 'password'at least 8 characters, contains at least one digit, contains at least one lower alpha char and one upper alpha char, no whitespace allowed in the entire string.");
					isError = true;
				}
			}
			if (name.isEmpty()) {
				error.add("The 'name' is empty.");
				isError = true;
			}
			Pattern patternN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z]).{3,}$");
			Matcher matcherN = patternN.matcher(name);
			boolean passwordName = matcherN.matches();
			if (!passwordName) {
				error.add("The 'Name' must be the correct. At least 3 characters, contains at least one lower alpha char and one upper alpha char, no whitespace allowed in the entire string");
				isError = true;
			}
			if (gender.isEmpty()) {
				error.add("The 'gender' is empty.");
				isError = true;
			}
			if (country.isEmpty()) {
				error.add("The 'country' is empty.");
				isError = true;
			}
			if (city.isEmpty()) {
				error.add("The 'City' is empty.");
				isError = true;
			}
			if (address.isEmpty()) {
				error.add("The 'address' is empty.");
				isError = true;
			}
			if (phone.isEmpty()) {
				error.add("The 'phone' is empty.");
				isError = true;
			}
			if (!isError) {
				User userPojo = new User();
				if (login != null) {
					userPojo.setName(name);
					userPojo.setLogin(login);
					userPojo.setPassword(password);
					userPojo.setGender(gender);
					userPojo.setAddress(address);
					userPojo.setCity(city);
					userPojo.setCountry(country);
					userPojo.setPhone(phone);

					DAOFactory daoFactory = DAOFactory.getInstance(1);
					UserDAO userDAO = daoFactory.getUserDAO();
					userDAO.setUser(userPojo);
					httpSession.setAttribute("userPojo", userPojo);
				}
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/index.jsp");
				request.setAttribute("login", login);
				rd.forward(request, resp);
				showForm = false;
			}
		}
		if (showForm) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/register.jsp");
			request.setAttribute("login", login);
			request.setAttribute("password", password);
			request.setAttribute("name", name);
			request.setAttribute("gender", gender);
			request.setAttribute("address", address);
			request.setAttribute("city", city);
			request.setAttribute("country", country);
			request.setAttribute("phone", phone);
			request.setAttribute("ERROR", error);
			rd.forward(request, resp);
		}
	}
}
