package dimaliahov.MySQL;

import dimaliahov.service.DAOFactory;
import dimaliahov.service.DAOInterfase.CartDAO;
import dimaliahov.service.DAOInterfase.ProductDAO;
import dimaliahov.service.DAOInterfase.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {

	public MySQLDAOFactory () {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e){
			e.printStackTrace();
		} catch (IllegalAccessException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	public Connection getConnection () {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/itea_db?" + "user=root&password=password&serverTimezone=UTC");
		} catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	@Override
	public UserDAO getUserDAO () {
		return new MySQLUserDAO(this);
	}

	@Override
	public CartDAO getCartDAO () {
		return new MySQLCartDAO(this);
	}

	@Override
	public ProductDAO getProductDAO () {
		return new MySQLProductDAO(this);
	}
}
