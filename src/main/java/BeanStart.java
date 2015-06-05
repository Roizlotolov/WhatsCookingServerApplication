import mongocollections.Ingredient;
import mongocollections.Recepe;
import mongocollections.RecepeComment;
import mongopac.MongoRunner;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.util.JSON;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.WhatsCookingConsts;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static spark.Spark.before;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;

public class BeanStart {

	private static ObjectMapper mapper = new ObjectMapper();
	private static JsonFactory jsonFactory = mapper.getJsonFactory();

	// allow CORS
	private static void enableCORS(final String origin, final String methods,
			final String headers) {
		before(new Filter() {

			public void handle(Request request, Response response) {
				response.header("Access-Control-Allow-Origin", origin);

			}
		});
	};

	public static void main(String[] args) throws IOException {

		MongoRunner.init();	
		setPort(Integer.valueOf(5000));
		staticFileLocation("/WhatsCook");
		enableCORS("*", "*", "*");

		// search for Ingredients
				post("/getIngredients", new Route() {

					public Object handle(Request request, Response response) {
						// String string = JSON.serialize(MongoRunner.getIngredientsInDB());
						return MongoRunner.getIngredientsString();
					}
				});


		// Quantity Units"
		get("/getQuantityUnits", new Route() {

			public Object handle(Request request, Response response) {
				String string = JSON.serialize(MongoRunner.getUnitsInDB());
				return JSON.serialize(MongoRunner.getUnitsInDB());
			}
		});

		// search for getRecepieById
		post("/getRecepieById", new Route() {

			public Object handle(Request request, Response response)
					throws NoSuchObjectException {
				String recepeID = request.queryParams(WhatsCookingConsts.JSON_ID);
				return MongoRunner.getMorphia().toDBObject(
						(MongoRunner.findRecepeById(recepeID)));
			}
		});

	
		// search for Recepies
		post("/getRecepiesByIngredients", new Route() {

			public Object handle(Request request, Response response) {
				List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
				JsonParser jp = null;
				List<DBObject> recepies = null;
				try {
					String ingredientsString = request
							.queryParams(WhatsCookingConsts.INGREDIENTS_IN_RECIPES);
					JSONArray array = new JSONArray(ingredientsString);
					recepies = MongoRunner
							.getRecepiesByIngeredients(array);

				} catch (Exception e) {
					System.out.println("error " + e.getMessage());
				}
				
				return recepies; 

			}
		});

		// Save Recepies
		post("/saveRecipe", new Route() {

			public Object handle(Request request, Response response) {
				List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
				JsonParser jp = null;
				JSONObject jsonObject = null;
				try {
					jsonObject = MongoRunner.saveComments(request
							.queryParams(WhatsCookingConsts.USER_COMMENTS));

				} catch (Exception e) {
					System.out.println("error " + e.getMessage());
				}

				return jsonObject;

			}
		});

	}
}
