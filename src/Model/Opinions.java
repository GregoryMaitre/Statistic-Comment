package Model;

import java.util.HashMap;
import java.util.Set;

public class Opinions {
	private HashMap<Integer, Opinion> opinions;
	
	public Opinions() {
		opinions = new HashMap<Integer, Opinion>();
	}
	
	public Opinion get(int id) {
		return opinions.get(id);
	}
	
	public int size() {
		return opinions.keySet().size();
	}

	public void add(Opinion opinion) {
		opinions.put(opinion.comment_id, opinion);
	}

	public Set<Integer> getCommentsID() {
		return opinions.keySet();
	}
}
