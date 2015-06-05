package mongocollections;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("recipes")
public class Recepe {
	@Id
	@Property("_id")
	private ObjectId id;
	@Property("recipeName")
	private String recipeName; 
	@Property("directions")
	private String directions;
		
	private List<Ingredient> ingredients ;
	
	private List<RecepeComment> comments;
	
	@Property("author")
	private String author;
	@Property("imageurl")
	private String imageurl;
	@Property("source")
	private String source;
	@Property("type")
	private String type;
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	public List<RecepeComment>getComments() {
		if(comments == null)
		{
			comments = new ArrayList<RecepeComment>();
		}
		return comments;
	}

	public void setComments(List<RecepeComment> comments) {
		this.comments = comments;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	
}
