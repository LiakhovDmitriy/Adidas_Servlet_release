package dimaliahov.MySQL;

import dimaliahov.model.Product;
import dimaliahov.service.DAOInterfase.ProductDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductDAO implements ProductDAO {
	private final static String SELECT_PRODUCT_BY_NAME = "SELECT * FROM `product` WHERE name = ?;";
	private final static String SELECT_PRODUCT_BY_ID = "SELECT * FROM `product` WHERE id = ?;";
	private final static String SET_PRODUCT = "INSERT INTO product (name, price, categoryID, rating, description, bigDescription, shortDescription, discount, photoUrlIcon, photoUrlBig1, photoUrlBig2, photoUrlBig3, photoUrlBig4) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String GET_PRODUCTS = "SELECT * FROM PRODUCT";
	private final static String GET_COUNT = "select count(*) from product";
	private final static String GET_PRODUCTS_BY_CATEGORY = "SELECT * FROM PRODUCT WHERE categoryID = ?";

	private MySQLDAOFactory ms;

	public MySQLProductDAO (MySQLDAOFactory ms) {
		this.ms = ms;
	}

	@Override
	public List<Product> getProducts () {
		List<Product> products = new ArrayList<Product>();

		Connection con = ms.getConnection();
		try (Statement ps = con.createStatement()) {

			ResultSet rs = ps.executeQuery(GET_PRODUCTS);
			while (rs.next()) {
				Product product = new Product()
						.setId(rs.getInt("id"))
						.setName(rs.getString("name"))
						.setPrice(rs.getInt("price"))
						.setCategoryID(rs.getInt("categoryID"))
						.setRating(rs.getInt("rating"))
						.setDescription(rs.getString("description"))
						.setBigDescription(rs.getString("bigDescription"))
						.setShortDescription(rs.getString("shortDescription"))
						.setDiscount(rs.getInt("discount"))
						.setPhotoUrlIcon(rs.getString("photoUrlIcon"))
						.setPhotoUrlBig1(rs.getString("photoUrlBig1"))
						.setPhotoUrlBig2(rs.getString("photoUrlBig2"))
						.setPhotoUrlBig3(rs.getString("photoUrlBig3"))
						.setPhotoUrlBig4(rs.getString("photoUrlBig4"));
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
	public List<Product> getProductsByCategoryId (String id) {
		List<Product> products = new ArrayList<Product>();

		Connection con = ms.getConnection();
		try (PreparedStatement ps = con.prepareStatement(GET_PRODUCTS_BY_CATEGORY)) {
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product()
						.setId(rs.getInt("id"))
						.setName(rs.getString("name"))
						.setPrice(rs.getInt("price"))
						.setCategoryID(rs.getInt("categoryID"))
						.setRating(rs.getInt("rating"))
						.setDescription(rs.getString("description"))
						.setBigDescription(rs.getString("bigDescription"))
						.setShortDescription(rs.getString("shortDescription"))
						.setDiscount(rs.getInt("discount"))
						.setPhotoUrlIcon(rs.getString("photoUrlIcon"))
						.setPhotoUrlBig1(rs.getString("photoUrlBig1"))
						.setPhotoUrlBig2(rs.getString("photoUrlBig2"))
						.setPhotoUrlBig3(rs.getString("photoUrlBig3"))
						.setPhotoUrlBig4(rs.getString("photoUrlBig4"));
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
	public Product getProductByName (String name) {
		Product product = new Product();
		product.setName(name);
		Connection con = ms.getConnection();
		try (PreparedStatement ps = con.prepareStatement(SELECT_PRODUCT_BY_NAME);) {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product.setPrice(rs.getInt("price"));
				product.setCategoryID(rs.getInt("categoryID"));
				product.setRating(rs.getInt("rating"));
				product.setDescription(rs.getString("description"));
				product.setBigDescription(rs.getString("bigDescription"));
				product.setShortDescription(rs.getString("shortDescription"));
				product.setDiscount(rs.getInt("discount"));
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
		return product;
	}

	@Override
	public Product getProductByID (int id) {
		Product product = new Product();
		product.setId(id);
		Connection con = ms.getConnection();
		try (PreparedStatement ps = con.prepareStatement(SELECT_PRODUCT_BY_ID);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setCategoryID(rs.getInt("categoryID"));
				product.setRating(rs.getInt("rating"));
				product.setDescription(rs.getString("description"));
				product.setBigDescription(rs.getString("bigDescription"));
				product.setShortDescription(rs.getString("shortDescription"));
				product.setDiscount(rs.getInt("discount"));
				product.setPhotoUrlIcon(rs.getString("photoUrlIcon"));
				product.setPhotoUrlBig1(rs.getString("photoUrlBig1"));
				product.setPhotoUrlBig2(rs.getString("photoUrlBig2"));
				product.setPhotoUrlBig3(rs.getString("photoUrlBig3"));
				product.setPhotoUrlBig4(rs.getString("photoUrlBig4"));
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
		return product;
	}

	@Override
	public boolean setProduct (Product product) {
		Connection con = ms.getConnection();
		try (PreparedStatement ps = con.prepareStatement(SET_PRODUCT)) {
			System.out.println(product);
			ps.setString(1, product.getName());
			ps.setString(2, String.valueOf(product.getPrice()));
			ps.setString(3, String.valueOf(product.getCategoryID()));
			ps.setString(4, String.valueOf(product.getRating()));
			ps.setString(5, product.getDescription());
			ps.setString(6, product.getBigDescription());
			ps.setString(7, product.getShortDescription());
			ps.setString(8, String.valueOf(product.getDiscount()));
			ps.setString(9, product.getPhotoUrlIcon());
			ps.setString(10, product.getPhotoUrlBig1());
			ps.setString(11, product.getPhotoUrlBig2());
			ps.setString(12, product.getPhotoUrlBig3());
			ps.setString(13, product.getPhotoUrlBig4());
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

	public int getCountAll () {
		Connection con = ms.getConnection();
		int count = 0;
		try (Statement s = con.createStatement();) {

			ResultSet rs = s.executeQuery(GET_COUNT);
			rs.next();
			count = rs.getInt(1);
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
		return count;
	}
}
