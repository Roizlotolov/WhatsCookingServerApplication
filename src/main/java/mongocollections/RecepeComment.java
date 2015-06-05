package mongocollections;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

public class RecepeComment {
	
	@Property("comment_text")
	private String comment_text;
	@Property("comment_time")
	private String comment_time;
	@Property("user_image_bitmap")
	private String user_image_bitmap;
	
	@Id
	@Property("_id")
	private ObjectId id;
	private String user_name;
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
	public String getUser_image_bitmap() {
		return user_image_bitmap;
	}
	public void setUser_image_bitmap(String user_image_bitmap) {
		this.user_image_bitmap = user_image_bitmap;
	}
	
	public String getUserName() {
		return user_name;
	}
	
	public void setUserName(String userName) {
		this.user_name = userName;
		
	}
	
	

}
