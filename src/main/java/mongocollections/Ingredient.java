package mongocollections;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

public class Ingredient {

	@Id
	@Property("_id")
	private ObjectId id;
	@Property("name")
	private String name;
	//@Property("quantity")
	private String quantity;
	//@Property("quantitytype")
	private String quantity_type;

	public String getQuantity_type() {
		return quantity_type;
	}

	public void setQuantity_type(String quantity_type) {
		this.quantity_type = quantity_type;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
