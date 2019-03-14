package drims.poc.deploiement.api.controller;

import org.sql2o.Sql2o;

import drims.poc.deploiement.api.model.User;
import drims.poc.deploiement.api.service.UserCrudService;

public class UserController extends AbstractRestCrudController {

	public UserController(Sql2o sql2o) {
		super(new UserCrudService(sql2o), User.class);
	}

}
