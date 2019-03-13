package drims.poc.deploiement.api.controller;

import drims.poc.deploiement.api.tools.JsonTools;
import spark.Request;
import spark.Response;

public class AbstractRestController {

	protected String response(Request request, Response response, int httpStatus, Object object) {
		response.type("application/json");
		response.status(httpStatus);
		return JsonTools.toJson(object);
	}
	
}
