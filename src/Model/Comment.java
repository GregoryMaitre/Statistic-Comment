package Model;

import java.util.HashMap;

public class Comment {
	public int id;
	public int article_id;
	public String message;
	public String author;

	public String disqus_parent;
	public String disqus_id;
	public String status;
	public int statusParsed;
	public String owner;
	
	//Controversy
	public String label;
	public String label_pro;
	public String label_con;
	
	//Article title
	public String title;
	
	public HashMap<String, String> questionAnswer = new HashMap<String, String>();
	public String aspect;
	public String opinion;

	public Comment() {}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", article_id=" + article_id + ", message=" + message + ", author=" + author
				+ ", disqus_parent=" + disqus_parent + ", disqus_id=" + disqus_id + ", status=" + status
				+ ", statusParsed=" + statusParsed + ", owner=" + owner + "]";
	}
	
	public void setAnswer(String question, String answer) {
		questionAnswer.put(question, answer);
	}
	
//	public void save() {
//		String path = Constants.COMMENTS_PATH + "/";
//		for (int i = 0; i < Questions.QUESTIONS.length; i++) {
//			path += questionAnswer.get(Questions.QUESTIONS[i]) + "/";
//		}
//		new File(path).mkdirs();
//		path += "comment_" + id + ".json";
//		File file = new File(path);
//		if (file.exists() && (file.isFile() || !file.isDirectory())) {
//			file.delete();
//		}
//		
//		JSONManager jsonManager = new JSONManager();
//		String commentString = jsonManager.toJson(this);
//		
//		BufferedWriter output = null;
//		try {
//			output = new BufferedWriter(new FileWriter(path));
//			output.write(commentString);
//			
//		} catch (IOException e) {
//			System.err.println("Unable to save the comment!");
//		} finally {
//			if (output != null) {
//				try {
//					output.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	public void setOpinion(String aspect, String opinion) {
//		this.aspect = aspect;
//		this.opinion = opinion;
//	}
	
	public OpinionValue getOpinionValue() {
		return OpinionValue.toOpinionValue(questionAnswer.get("Est-ce un commentaire positif?"));
	}
}
