package Model;

import java.util.HashMap;
import java.util.Set;

public class Comments {
	private HashMap<Integer, Comment> comments;
	
	public Comments() {
		comments = new HashMap<Integer, Comment>();
	}
	
	public Comment get(int id) {
		return comments.get(id);
	}
	
	public int size() {
		return comments.keySet().size();
	}

	public void add(Comment comment) {
		comments.put(comment.id, comment);
	}

	public Set<Integer> getCommentsID() {
		return comments.keySet();
	}
}
