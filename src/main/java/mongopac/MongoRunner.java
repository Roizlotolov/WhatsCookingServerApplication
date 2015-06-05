package mongopac;

import java.net.UnknownHostException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mongocollections.Recepe;
import mongocollections.RecepeComment;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.CriteriaContainerImpl;
import org.mongodb.morphia.query.Query;

import utils.WhatsCookingConsts;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class MongoRunner {
	private static MongoClient mongoClient = null;
	private static Datastore cookDatastore = null;
	private static String DB_NAME = "whats_cook_db";
	private static Morphia morphia = null;
	private static String ingredientsString = null;

	public static Morphia getMorphia() {
		return morphia;
	}

	public static void init() {
		try {
			mongoClient = MongoSingaletonConnection.createConnctionManager();
			morphia = new Morphia();
			cookDatastore = morphia.createDatastore(mongoClient, DB_NAME);
			morphia.mapPackage("mongocollections");
			ingredientsString = JSON
					.serialize(MongoRunner.getIngredientsInDB());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	public static String getIngredientsString() {
		return ingredientsString;
	}

	public static MongoDatabase getDB(String nameDB) {
		MongoDatabase mongoDatabase = mongoClient.getDatabase(nameDB);
		return mongoDatabase;

	}

	public static List<DBObject> getRecepiesByIngeredients(JSONArray ingredients) {
		Query<Recepe> q = cookDatastore.createQuery(Recepe.class);
		List<CriteriaContainerImpl> containers = new ArrayList<CriteriaContainerImpl>();
		CriteriaContainerImpl[] criteriaContainerImpls;

		for (int i = 0; i < ingredients.length(); i++) {
			JSONObject jsonObject = new JSONObject(ingredients.getString(i));
			CriteriaContainerImpl criteriaContainer = q.criteria(
					WhatsCookingConsts.SEARCH_INGREDIENTS_BY_NAME).contains(jsonObject.getString(WhatsCookingConsts.JSON_NAME));
			containers.add(criteriaContainer);

			if (!jsonObject.isNull(WhatsCookingConsts.QUANTITY)
					&& jsonObject.getString(WhatsCookingConsts.QUANTITY).compareTo("") != 0) {

				CriteriaContainerImpl criteriaContainer1 = q.criteria(
						WhatsCookingConsts.SEARCH_INGREDIENTS_BY_QUANTITY).equal(
						Integer.valueOf(jsonObject.getString(WhatsCookingConsts.QUANTITY)));
				containers.add(criteriaContainer1);
			}

		}
		
		System.out.println("Searching In Mongo Your Reccepies..... ");
		Iterator<Recepe> recepiesIterator = q.fetch();
		List<DBObject> recepes = new ArrayList<DBObject>();

		while (recepiesIterator.hasNext()) {
			Recepe recepe = recepiesIterator.next();
			recepes.add(getMorphia().toDBObject(recepe));
			System.out.println("Mongo Found Recepe: " + recepe.getRecipeName());
		}

		return recepes;

	}

	public static JSONObject saveComments(String quartParams) {

		JSONObject jsonObject = new JSONObject(quartParams);
		String commentID = jsonObject.getString(WhatsCookingConsts.COMMENT_ID);
		String recepeID = jsonObject.getString(WhatsCookingConsts.JSON_ID);
		String comment_text = jsonObject.getString(WhatsCookingConsts.COMMENT_TEXT);
		String comment_time = jsonObject.getString(WhatsCookingConsts.COMMENT_TIME);
		String userName = jsonObject.getString(WhatsCookingConsts.USER_NAME);
		String user_image_bitmap = jsonObject.getString(WhatsCookingConsts.USER_IMAGE_BITMAP);
		RecepeComment commentRes = null;
		boolean isCommentExist = false;
		try {
			Recepe recepe = findRecepeById(recepeID);
			RecepeComment recepeComment = isCommentExsit(recepe, commentID);

			if (recepeComment != null) {
				recepeComment.setComment_text(comment_text);

				cookDatastore.save(recepe);
			} else {
				RecepeComment comment = new RecepeComment();
				comment.setId(new ObjectId(commentID));
				comment.setComment_text(comment_text);
				comment.setComment_time(comment_time);
				comment.setUserName(userName);
				comment.setUser_image_bitmap(user_image_bitmap);
				recepe.getComments().add(comment);
				cookDatastore.save(recepe);

			}

		} catch (NoSuchObjectException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	private static RecepeComment isCommentExsit(Recepe recepe, String commentID) {
		RecepeComment isCommentExsit = null;
		for (RecepeComment currComment : recepe.getComments()) {
			if (currComment.getId().toString().compareTo(commentID) == 0) {
				isCommentExsit = currComment;
				break;
			}
		}

		return isCommentExsit;
	}

	public static Recepe findRecepeById(String id) throws NoSuchObjectException {
		if (!ObjectId.isValid(id)) {
			throw new NoSuchObjectException(id);
		}

		ObjectId oid = new ObjectId(id);
		Recepe m = cookDatastore.find(Recepe.class)
				.field(WhatsCookingConsts.JSON_ID).equal(oid).get();
		if (m == null) {
			throw new NoSuchObjectException(id);
		}
		return m;
	}

	public static RecepeComment findRecepeCommentById(String id)
			throws NoSuchObjectException {
		if (!ObjectId.isValid(id)) {
			throw new NoSuchObjectException(id);
		}

		ObjectId oid = new ObjectId(id);
		RecepeComment m = cookDatastore.find(RecepeComment.class)
				.field(WhatsCookingConsts.JSON_ID).equal(oid).get();
		if (m == null) {
			throw new NoSuchObjectException(id);
		}
		return m;
	}

	public static FindIterable<Document> getIngredientsInDB() {
		System.out.println("Mongo Sending You Ingredients System");
		FindIterable<Document> findIterable = MongoRunner.getDB(DB_NAME)
				.getCollection(WhatsCookingConsts.INGREDIENTS_ROI).find()
				.sort(new BasicDBObject(WhatsCookingConsts.JSON_NAME, -1));
		return findIterable;
	}

	public static List<DBObject> convertArrayToJson(List<Recepe> list) {
		List<DBObject> dbObjects = new ArrayList<DBObject>();
		for (Recepe t : list) {
			dbObjects.add((DBObject) morphia.toDBObject(t));
		}

		return dbObjects;
	}

	public static FindIterable<Document> getUnitsInDB() {
		System.out.println("Mongo Sending You Units System");
		return MongoRunner.getDB(DB_NAME)
				.getCollection(WhatsCookingConsts.MEASURABLE_UNITS).find();
	}

}
