package drims.poc.deploiement.api;

import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import drims.poc.deploiement.api.controller.IRestCrudController;
import drims.poc.deploiement.api.controller.UserController;
import spark.servlet.SparkApplication;

public class SparkApiFilter implements SparkApplication {

	@Override
	public void init() {
		get("/", (request, response) -> "It works");
		get("/hello/:name", (request, response) -> "Hello " + request.params(":name"));
		prepareCrudController("users", new UserController());
	}
	
	protected void prepareCrudController(String path, IRestCrudController controller) {
		before((req, res) -> {
			res.header("Access-Control-Allow-Headers", "Authorization, Content-Type");
			res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			res.header("Access-Control-Allow-Origin", "*");
		});
		options("/*", (req, res) -> "");

		post("/" + path, (req, res) -> controller.create(req, res));
		get("/" + path + "/:id", (req, res) -> controller.findById(req, res));
		put("/" + path + "/:id", (req, res) -> controller.updateById(req, res));
		delete("/" + path + " /:id", (req, res) -> controller.deleteById(req, res));

		exception(Exception.class, (e, req, res) -> {
			String message = e.getClass().getName() + ": " + e.getMessage();
			res.type("application/json");
			res.status(500);
			res.body(message);
		});
	}

}
