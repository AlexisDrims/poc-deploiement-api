package drims.poc.deploiement.api.service;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class AbstractService {
	
	private Sql2o sql2o;
	
	public AbstractService(Sql2o sql2o) {
		this.sql2o = sql2o;
	}
	
	protected Connection open() {
		return sql2o.open();
	}
	

	protected void close(Connection connection) {
		connection.close();
	}

}
