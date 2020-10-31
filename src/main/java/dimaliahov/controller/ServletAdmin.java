package dimaliahov.controller;

import dimaliahov.model.Product;
import dimaliahov.service.DAOFactory;
import dimaliahov.service.DAOInterfase.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ServletAdmin extends HttpServlet {

	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getMethods(req, resp);
	}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		postMethods(request, resp);
	}

	private void getMethods (HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/admin.jsp");
		rd.forward(request, resp);
	}

	private void postMethods (HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		boolean showForm = true;
		boolean isError = false;

		ArrayList<String> error = new ArrayList<>();

		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String categoryID = request.getParameter("categoryID");
		String rating = request.getParameter("rating");
		String description = request.getParameter("description");
		String shortDescription = request.getParameter("shortDescription");
		String discount = request.getParameter("discount");

		if (name != null) {
			if (name.isEmpty()) {
				error.add("The 'name' is empty.");
				isError = true;
			}
			if (price.isEmpty()) {
				error.add("The 'price' is empty.");
				isError = true;
			}
			if (categoryID.isEmpty()) {
				error.add("The 'categoryID' is empty.");
				isError = true;
			}
			if (rating.isEmpty()) {
				error.add("The 'rating' is empty.");
				isError = true;
			}
			if (description.isEmpty()) {
				error.add("The 'description' is empty.");
				isError = true;
			}
			if (shortDescription.isEmpty()) {
				error.add("The 'shortDescription' is empty.");
				isError = true;
			}
			if (!isError) {
				Product product = new Product();
				product.setName(name);
				product.setPrice(Integer.parseInt(price));
				product.setCategoryID(Integer.parseInt(categoryID));
				product.setRating(Integer.parseInt(rating));
				product.setDescription(description);
				product.setShortDescription(shortDescription);
				product.setDiscount(Integer.parseInt(discount));

				DAOFactory daoFactoryProduct = DAOFactory.getInstance(1);
				ProductDAO productDAO = daoFactoryProduct.getProductDAO();
				productDAO.setProduct(product);

				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/shop.jsp");
				rd.forward(request, resp);
				showForm = false;
			}
		}
		if (showForm) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/admin.jsp");
			request.setAttribute("name", name);
			request.setAttribute("price", price);
			request.setAttribute("categoryID", categoryID);
			request.setAttribute("rating", rating);
			request.setAttribute("description", description);
			request.setAttribute("shortDescription", shortDescription);
			request.setAttribute("discount", discount);
			request.setAttribute("ERROR", error);
			rd.forward(request, resp);
		}
	}

}
