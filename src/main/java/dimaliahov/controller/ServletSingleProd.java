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
import java.util.List;

public class ServletSingleProd extends HttpServlet {

	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/single.jsp");

		String productID = req.getParameter("idProduct");

		DAOFactory daoFactoryProduct = DAOFactory.getInstance(1);
		ProductDAO productDAO = daoFactoryProduct.getProductDAO();
		Product product = productDAO.getProductByID(Integer.parseInt(productID));

		List<Product> productList = productDAO.getProducts();
		req.setAttribute("productList",productList);
		req.setAttribute("productForSinglPag",product);
		rd.forward(req, resp);
	}


}
