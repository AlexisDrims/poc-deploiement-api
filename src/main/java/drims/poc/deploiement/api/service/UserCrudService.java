package drims.poc.deploiement.api.service;

import drims.poc.deploiement.api.model.User;

public class UserCrudService implements ICrudService<User> {

	@Override
	public User insert(User object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(id);
		user.setEmail(id.toString() + "@email.fr");
		return user;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User updateById(Long id, User object) {
		// TODO Auto-generated method stub
		return null;
	}


}
