package drims.poc.deploiement.api.service;

public interface ICrudService<T> {

	public T insert(T object);
	
	public T findById(Long id);
	
	public boolean deleteById(Long id);
	
	public T updateById(Long id, T object);
	
}
