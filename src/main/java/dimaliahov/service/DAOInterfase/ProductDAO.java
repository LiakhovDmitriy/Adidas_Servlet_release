package dimaliahov.service.DAOInterfase;

import dimaliahov.model.Product;

import java.util.List;

public interface ProductDAO {

	List<Product> getProductsByCategoryId (String id);

	List<Product> getProducts ();

	Product getProductByName (String name);

	Product getProductByID (int id);

	boolean setProduct (Product product);

	int getCountAll ();

}
