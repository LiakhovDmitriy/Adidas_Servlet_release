package dimaliahov.service.DAOInterfase;

import dimaliahov.model.User;

public interface UserDAO {

	User getUser (String login, String password);

	void setUser (User user);


}
