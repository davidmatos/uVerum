package pt.inescid.microverum.microVerumAgentProxy.log;

import java.util.HashMap;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import pt.inescid.microVerum.common.domain.Request;
import pt.inescid.microverum.microVerumAgentProxy.config.MicroVerumAgentConstants;

public class MicroVerumMongoClient {

	static String mongoAddress;
	static int mongoPort;

	private MongoClient mongoClient;

	private static MicroVerumMongoClient instance = null;

	public static MicroVerumMongoClient getInstance() {
		if (instance == null) {
			instance = new MicroVerumMongoClient(mongoAddress, mongoPort);
		}
		return instance;
	}

	public MicroVerumMongoClient(String address, int port) {
		mongoAddress = address;
		mongoPort = port;

		mongoClient = new MongoClient();

	}

	public void writeRequest(Request request) {

		MongoCollection<Document> collection = mongoClient
				.getDatabase(MicroVerumAgentConstants.MICRO_VERUM_LOG_DATABASE_NAME)
				.getCollection(MicroVerumAgentConstants.MICRO_VERUM_LOG_COLLECTION_NAME);

		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("id", request.id);
		map.put("ts", request.ts);
		map.put("url", request.url);
		map.put("request", request.request);
		map.put("from", request.from);
		map.put("to", request.to);
		map.put("globalId", request.globalId);
		map.put("method", request.method);
		map.put("query", request.query);
		
		Document document = new Document(map);
		
		collection.insertOne(document);

		
	}

}
