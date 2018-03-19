package pt.inescid.microVerum.Bubble.db;

import java.util.List;

import pt.inescid.microVerum.Bubble.domain.Request;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RequestRepository extends MongoRepository<Request, String> {
	public List<Request> findByUrl(String url);
	
	
}
