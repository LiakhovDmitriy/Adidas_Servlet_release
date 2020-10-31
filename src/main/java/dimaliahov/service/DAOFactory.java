package dimaliahov.service;

import dimaliahov.mySQL.MySQLDAOFactory;
import dimaliahov.service.DAOInterfase.CartDAO;
import dimaliahov.service.DAOInterfase.ProductDAO;
import dimaliahov.service.DAOInterfase.UserDAO;

public abstract class DAOFactory {

	public final static int MY_SQL = 1;

	public static DAOFactory getInstance (int sourceType) {
		switch (sourceType) {
			case MY_SQL:
				return new MySQLDAOFactory();
			default:
				return null;
		}
	}

	public abstract UserDAO getUserDAO ();

	public abstract CartDAO getCartDAO ();

	public abstract ProductDAO getProductDAO ();

}
