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
import java.util.List;

public class ServletHome extends HttpServlet {
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/index.jsp");

		DAOFactory daoFactory = DAOFactory.getInstance(1);
		ProductDAO productDAO = daoFactory.getProductDAO();

		List<Product> productList = productDAO.getProducts();
		List<Product> productListHomeP = new ArrayList<Product>();

		for (int i = 0; i < productList.size(); i++) {
			Product p = productList.get(i);
			productListHomeP.add(p);
			if (i == 5) {
				break;
			}
		}

		req.setAttribute("productListHomeP", productListHomeP);

		rd.forward(req, resp);
	}
}
