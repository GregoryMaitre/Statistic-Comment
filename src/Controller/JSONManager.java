package Controller;


import java.util.ArrayList;

import com.google.gson.Gson;

import Model.Comment;
import Model.Opinion;

public class JSONManager {
	
	private static JSONManager instance = null;
	private Gson gson;
	private JSONManager() {
		gson = new Gson();
	}
	
	public static JSONManager getInstance() {
		if (instance == null) {
			instance = new JSONManager();
		}
		
		return instance;
	}
	
	public Comment toComment(String json) {
		return gson.fromJson(json, Comment.class);
	}
	
	public String toJson(Comment comment) {
		return gson.toJson(comment);
	}
	
	public String toJson(ArrayList<Comment> comments) {
		return gson.toJson(comments);
	}
	
	public Opinion[] toOpinions(String json) {
		return gson.fromJson(json, Opinion[].class);
	}
}
