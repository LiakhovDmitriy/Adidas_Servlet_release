package dimaliahov.controller;

import dimaliahov.model.Product;
import dimaliahov.service.DAOFactory;
import dimaliahov.service.DAOInterfase.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ServletAllProduct extends HttpServlet {

	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getMethods(req, resp);
	}

	private void getMethods (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/shop.jsp");

		String categoryId = req.getParameter("categoryId");

		DAOFactory daoFactoryProduct = DAOFactory.getInstance(1);
		ProductDAO productDAO = daoFactoryProduct.getProductDAO();
		List<Product> products;

		if (categoryId != null) {
			products = productDAO.getProductsByCategoryId(categoryId);
		} else {
			products = productDAO.getProducts();
		}
		int c = productDAO.getCountAll();
		HttpSession session = req.getSession();
		session.setAttribute("prodList", products);
		session.setAttribute("count", c);
		rd.forward(req, resp);
	}


}
