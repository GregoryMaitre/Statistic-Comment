package Model;

public class Opinion {
	public int id;
	public int topic_id;
	public String subject;
	public String language;
	public int comment_id;
	public int opinion_value;
	public int relevance;
	public int nb_opinions;

	@Override
	public String toString() {
		return "Opinion [id=" + id + ", topic_id=" + topic_id + ", subject=" + subject + ", language=" + language
				+ ", comment_id=" + comment_id + ", opinion_value=" + opinion_value + ", relevance=" + relevance
				+ ", nb_opinions=" + nb_opinions + "]";
	}
	
	public OpinionValue getOpinionValue() {
		String result = "";
		if (opinion_value < 0) {
			result = "Commentaire negatif";
		} else if (opinion_value == 0) {
			result = "Commentaire neutre";
		} else {
			result = "Commentaire positif";
		}
		return OpinionValue.toOpinionValue(result);
	}
}
