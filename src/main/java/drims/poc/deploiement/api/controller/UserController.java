package drims.poc.deploiement.api.controller;

import drims.poc.deploiement.api.model.User;
import drims.poc.deploiement.api.service.UserCrudService;

public class UserController extends AbstractRestCrudController {

	public UserController() {
		super(new UserCrudService(), User.class);
	}

}
