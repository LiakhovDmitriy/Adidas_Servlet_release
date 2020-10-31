package dimaliahov.service.DAOInterfase;

import dimaliahov.model.Product;
import dimaliahov.model.ProductIdAndCount;

import java.util.List;

public interface CartDAO {

	void addProductToCart (int userId, int productId, int amount);

	void removeProductFromCartById (int userId, int productId);

	int getCountProductByUserId (int userId);

	List<ProductIdAndCount> getProductsAndCount (int userId);

	void changeCountOnProduct (int userId, int productId, int count);

	Product getProductByProductIdAndUserId (int userId, int productId);

}
