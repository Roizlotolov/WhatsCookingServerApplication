package mongopac;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoSingaletonConnection {

	private static MongoClient mongoClient = null;
	
	public static MongoClient createConnctionManager() throws UnknownHostException{		
		return mongoClient = new MongoClient(
			//	new MongoClientURI("mongodb://localhost:27017"));
				new MongoClientURI("mongodb://roi:roi@ds053429.mongolab.com:53429/whats_cook_db"));
	}

	public static void closeConniction() {
		mongoClient.close();
	}
	
	

}
