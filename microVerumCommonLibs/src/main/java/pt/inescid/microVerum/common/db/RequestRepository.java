package pt.inescid.microVerum.common.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pt.inescid.microVerum.common.domain.Request;


public interface RequestRepository extends MongoRepository<Request, String> {
	public List<Request> findByUrl(String url);
	
	
}
