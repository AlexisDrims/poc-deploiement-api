package drims.poc.deploiement.api.controller;

import drims.poc.deploiement.api.service.ICrudService;
import drims.poc.deploiement.api.tools.JsonTools;
import spark.Request;
import spark.Response;

public abstract class AbstractRestCrudController<T> extends AbstractRestController implements IRestCrudController {

	private ICrudService<T> service;
	private Class<T> clazz;
	
	public AbstractRestCrudController(ICrudService<T> service, Class<T> clazz) {
		this.service = service;
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public Object create(Request request, Response response) {
		final T object = (T) JsonTools.fromJson(request.body(), clazz);
		final T newObject = service.insert(object);
		return response(request, response, 201, newObject);
	}

	public Object findById(Request request, Response response) {
		final Long id = Long.valueOf(request.params(":id"));
		final T object = service.findById(id);
		return response(request, response, 200, object);
	}
	
	@SuppressWarnings("unchecked")
	public Object updateById(Request request, Response response) {
		final Long id = Long.valueOf(request.params(":id"));
		final T object = (T) JsonTools.fromJson(request.body(), clazz);
		final T updatedObject = service.updateById(id, object);
		return response(request, response, 200, updatedObject);
	}
	
	public Object deleteById(Request request, Response response) {
		final Long id = Long.valueOf(request.params(":id"));
		boolean res = service.deleteById(id);
		return response(request, response, 200, res);
	}
}
