package drims.poc.deploiement.api;

import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import org.sql2o.Sql2o;

import drims.poc.deploiement.api.controller.IRestCrudController;
import drims.poc.deploiement.api.controller.UserController;
import spark.servlet.SparkApplication;

public class SparkApiFilter implements SparkApplication {

	@Override
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Sql2o sql2o = new Sql2o("jdbc:mysql://172.17.0.1:3306/api_db", "root", "needs-a-new-password-here");
		
		get("/", (request, response) -> "It works");
		get("/hello/:name", (request, response) -> "Hello " + request.params(":name"));
		prepareCrudController("users", new UserController(sql2o));
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
