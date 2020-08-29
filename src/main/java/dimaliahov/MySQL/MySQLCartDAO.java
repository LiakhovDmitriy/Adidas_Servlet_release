package dimaliahov.MySQL;

import dimaliahov.model.Product;
import dimaliahov.model.ProductIdAndCount;
import dimaliahov.service.DAOFactory;
import dimaliahov.service.DAOInterfase.CartDAO;
import dimaliahov.service.DAOInterfase.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLCartDAO implements CartDAO {

	private final static String GET_PRODUCTS_BY_USER_ID = "SELECT * FROM shoppingcart WHERE userId = ?;";
	private final static String GET_PRODUCTS_BY_PRODUCT_ID = "SELECT * FROM shoppingcart WHERE userId = ? AND productId = ?;";
	private final static String SET_PRODUCT_TO_CART = "INSERT INTO shoppingcart (userId, productId, count) VALUES (?, ?, ?);";
	private final static String SET_COUNT_ON_PRODUCT = "UPDATE shoppingcart SET count = ? WHERE userId = ? AND productId = ?;";
	private final static String REMOVE_PRODUCT_TO_CART = "DELETE FROM shoppingcart WHERE userId = ? AND productId = ? LIMIT 1;";

	private MySQLDAOFactory ms;

	public MySQLCartDAO (MySQLDAOFactory ms) {
		this.ms = ms;
	}


	@Override
	public boolean addProductToCart (int userId, int productId, int count) {
		Connection con = ms.getConnection();
		try (PreparedStatement ps = con.prepareStatement(SET_PRODUCT_TO_CART)) {
			ps.setString(1, String.valueOf(userId));
			ps.setString(2, String.valueOf(productId));
			ps.setString(3, String.valueOf(count));
			int rows = ps.executeUpdate();

		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return true;
	}


	@Override
	public boolean changeCountOnProduct (int userId, int productId, int count) {
		Connection con = ms.getConnection();
		try (PreparedStatement ps = con.prepareStatement(SET_COUNT_ON_PRODUCT)) {
			ps.setString(1, String.valueOf(count));
			ps.setString(2, String.valueOf(userId));
			ps.setString(3, String.valueOf(productId));
			int rows = ps.executeUpdate();

		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return true;
	}


	@Override
	public List<Product> getProductsByUserId (int userId) {
		List<Product> products = new ArrayList<Product>();

		Connection con = ms.getConnection();
		try (PreparedStatement ps = con.prepareStatement(GET_PRODUCTS_BY_USER_ID)) {
			ps.setString(1, String.valueOf(userId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("productId");

				DAOFactory daoFactoryProduct = DAOFactory.getInstance(1);
				ProductDAO productDAO = daoFactoryProduct.getProductDAO();

				Product product = productDAO.getProductByID(id);
				products.add(product);
			}

			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return products;
	}

	@Override
	public List<ProductIdAndCount> getProductsAndCount (int userId) {
		List<ProductIdAndCount> list = new ArrayList<>();

		Connection con = ms.getConnection();
		try (PreparedStatement ps = con.prepareStatement(GET_PRODUCTS_BY_USER_ID)) {
			ps.setString(1, String.valueOf(userId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("productId");
				int count = rs.getInt("count");

				ProductIdAndCount prod = new ProductIdAndCount(id, count);
				list.add(prod);
			}

			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean removeProductFromCartById (int userId, int productId) {
		Connection con = ms.getConnection();
		try (PreparedStatement ps = con.prepareStatement(REMOVE_PRODUCT_TO_CART)) {
			ps.setString(1, String.valueOf(userId));
			ps.setString(2, String.valueOf(productId));
			int rows = ps.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean removeAllProductFromUser (int userId) {
		return false;
	}

	@Override
	public int getCountProductByUserId (int userId) {
		List<Product> products = new ArrayList<Product>();

		Connection con = ms.getConnection();
		try (PreparedStatement ps = con.prepareStatement(GET_PRODUCTS_BY_USER_ID)) {
			ps.setString(1, String.valueOf(userId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("productId");

				DAOFactory daoFactoryProduct = DAOFactory.getInstance(1);
				ProductDAO productDAO = daoFactoryProduct.getProductDAO();

				Product product = productDAO.getProductByID(id);
				products.add(product);
			}

			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return products.size();
	}

	@Override
	public Product getProductByProductIdAndUserId (int userId, int productId) {
		Connection con = ms.getConnection();
		Product p = new Product();
		try (PreparedStatement ps = con.prepareStatement(GET_PRODUCTS_BY_PRODUCT_ID)) {
			ps.setString(1, String.valueOf(userId));
			ps.setString(2, String.valueOf(productId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DAOFactory daoFactoryProduct = DAOFactory.getInstance(1);
				ProductDAO productDAO = daoFactoryProduct.getProductDAO();

				p = productDAO.getProductByID(rs.getInt("productId"));

			}

			rs.close();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return p;
	}
}
