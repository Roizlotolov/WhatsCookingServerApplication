package tests;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import mongocollections.Ingredient;
import mongopac.MongoSingaletonConnection;

import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TestDatabase {
	private static MongoClient mongoClient = null;
	private static Datastore cookDatastore = null;
	private static Morphia morphia = null;

	public static void main(String[] args) {
		try {
			mongoClient = MongoSingaletonConnection.createConnctionManager();
			MongoDatabase ingredientsDatabase = getDB("Cook");
			morphia = new Morphia();
			cookDatastore = morphia.createDatastore(mongoClient, "Cook");
			morphia.mapPackage("mongocollections");

			printCollection(ingredientsDatabase.getCollection("vegetables"),
					"vegetables");
			printCollection(ingredientsDatabase.getCollection("meat"), "meat");
			printCollection(ingredientsDatabase.getCollection("sauce"), "sauce");
			printCollection(ingredientsDatabase.getCollection("spices"),
					"spices");
			printCollection(ingredientsDatabase.getCollection("vegetables"),
					"vegetables");
	
			Ingredient beet = new Ingredient();
		//	beet.setId(3013);
			beet.setName("beet");
			
			Ingredient carrot = new Ingredient();
			//carrot.setId(3026);
			carrot.setName("carrot");
			
			List<Ingredient> ingredients = Arrays.asList(beet, carrot);
			
		} catch (UnknownHostException e) {
			MongoSingaletonConnection.closeConniction();
		} finally {
			MongoSingaletonConnection.closeConniction();
		}
	}
	
	public static MongoDatabase getDB(String nameDB) {
		MongoDatabase mongoDatabase = mongoClient.getDatabase(nameDB);
		return mongoDatabase;

	}

	public static void printCollection(
			MongoCollection<Document> mongoCollection, String name) {
		{
			System.out.println("------" + name + "------");
			Iterator<Document> itr = mongoCollection.find().iterator();
			while (itr.hasNext()) {
				Document element = itr.next();
				System.out.println(element + " ");

			}
			System.out.println("------------------------------");
		}
	}

}
