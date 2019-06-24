package project.bookplanet.managers;

import java.util.List;

import project.bookplanet.constants.Gender;
import project.bookplanet.constants.UserType;
import project.bookplanet.dao.UserDao;
import project.bookplanet.entities.User;
import project.bookplanet.util.StringUtil;

public class UserManager {
	private static UserManager instance = new UserManager();
	private static UserDao dao = new UserDao();

	private UserManager() {
	}

	public static UserManager getInstance() {
		return instance;

	}

	public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender,
			UserType userType) {
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstname(firstName);
		user.setLastname(lastName);
		user.setGender(gender);
		user.setUserType(userType);
		
		return user;

	}
	
	public List<User> getUsers() {
		return dao.getUser();
	}

	public User getUser(long userId) {
		// TODO Auto-generated method stub
		return dao.getUser(userId);
	}

	public long authenticate(String email, String password) {
		return dao.authenticate(email, StringUtil.encodePassword(password));
	}

	/*public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender,
			UserType userType) {
		// TODO Auto-generated method stub
		return null;
	}*/
}
