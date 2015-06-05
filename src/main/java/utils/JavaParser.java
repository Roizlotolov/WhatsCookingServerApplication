package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class JavaParser {

	public static void pasreJsonFromFile(String filename) {
		try {
			URI uri = null;
			uri = JavaParser.class.getResource(filename).toURI();
			File f = new File(uri);
			if (f.exists()) {
				InputStream is;
				String jsonTxt = null;

				is = new FileInputStream(f);
				jsonTxt = IOUtils.toString(is);
				System.out.println(jsonTxt);
				JSONObject json = new JSONObject(jsonTxt);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	

	/*public static void getRecepiesByIngeredients() {
	MongoDatabase recepiesDatabase = getDB("Recepies");
	BasicDBObject eleMatch = new BasicDBObject();
	eleMatch.put("name", "beet");
	BasicDBObject up = new BasicDBObject();
	up.put("$elemMatch", eleMatch);
	BasicDBObject query = new BasicDBObject();
	query.put("ingredients", up);

	Iterator<Document> itr = recepiesDatabase.getCollection("recepies")
			.find(query).iterator();
	while (itr.hasNext()) {
		Document element = itr.next();
		System.out.println(element + " ");

	}
	System.out.println("------------------------------");
}
*/

}
