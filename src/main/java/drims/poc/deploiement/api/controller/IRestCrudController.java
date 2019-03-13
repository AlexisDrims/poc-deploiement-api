package drims.poc.deploiement.api.controller;

import spark.Request;
import spark.Response;

public interface IRestCrudController {

	public Object create(Request request, Response response);

	public Object findById(Request request, Response response);
	
	public Object updateById(Request request, Response response);
	
	public Object deleteById(Request request, Response response);
	
}
