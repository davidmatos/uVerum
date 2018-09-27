package pt.inescid.microverum.microVerumAgentProxy.log;

import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

public class MicroVerumMongoClient {
	
	
	static String mongoAddress;
	static int mongoPort;
	
	private MongoClient mongoClient;

	static MicroVerumMongoClient instance = null;

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

	
	
	public void writeRequest() {
		MongoCollection<Document> collection = mongoClient.getDatabase("test").getCollection("JavaDriver");

		
		 Document document = new Document("a", 0).append("createddate", new Date());
         collection.insertOne(document);
	}
	
}
