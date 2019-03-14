package drims.poc.deploiement.api.service;

import java.util.Random;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import drims.poc.deploiement.api.model.User;

public class UserCrudService extends AbstractService implements ICrudService<User> {

	public UserCrudService(Sql2o sql2o) {
		super(sql2o);
	}

	@Override
	public User insert(User object) {
		Long id = new Random().nextLong();
		Connection connection = open();
		try {
			connection.createQuery("insert into users (email, password) values (:email, :password)").
				addParameter("id", id).
				addParameter("email", object.getEmail()).
				addParameter("password", object.getPassword()).executeUpdate();
			
			return findById(id);
		} finally {
			close(connection);
		}
	}

	@Override
	public User findById(Long id) {
		Connection connection = open();
		try {
			User user = connection.createQuery("select * from users where id = :id").addParameter("id", id).executeAndFetchFirst(User.class);
			return user;
		} finally {
			close(connection);
		}
	}

	@Override
	public boolean deleteById(Long id) {
		Connection connection = open();
		try {
			connection.createQuery("delete users where id = :id").addParameter("id", id).executeUpdate();
			return true;
		} finally {
			close(connection);
		}
	}

	@Override
	public User updateById(Long id, User object) {
		Connection connection = open();
		try {
			connection.createQuery("update users set email = :email, password = :password) values where id = :id").
				addParameter("id", id).
				addParameter("email", object.getEmail()).
				addParameter("password", object.getPassword()).executeUpdate();
			return findById(id);
		} finally {
			close(connection);
		}
	}


}
