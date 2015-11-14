package Model;

import java.util.ArrayList;
import java.util.List;

public class Evaluation {

	private ResultsClassifyComments resultsClassifyComments;
	private ResultOpinionExtractor resultOpinionExtractor;

	private ArrayList<Integer> positivePositive;
	private ArrayList<Integer> positiveNegative;
	private ArrayList<Integer> positiveNeutral;
	private ArrayList<Integer> negativePositive;
	private ArrayList<Integer> negativeNegative;
	private ArrayList<Integer> negativeNeutral;
	private ArrayList<Integer> neutralPositive;
	private ArrayList<Integer> neutralNegative;
	private ArrayList<Integer> neutralNeutral;

	public Evaluation(ResultsClassifyComments resultsClassifyComments, ResultOpinionExtractor resultOpinionExtractor) {
		this.resultsClassifyComments = resultsClassifyComments;
		this.resultOpinionExtractor = resultOpinionExtractor;
		positivePositive = new ArrayList<Integer>();
		positiveNegative = new ArrayList<Integer>();
		positiveNeutral = new ArrayList<Integer>();
		negativePositive = new ArrayList<Integer>();
		negativeNegative = new ArrayList<Integer>();
		negativeNeutral = new ArrayList<Integer>();
		neutralPositive = new ArrayList<Integer>();
		neutralNegative = new ArrayList<Integer>();
		neutralNeutral = new ArrayList<Integer>();
	}

	public void eval() {
		for (int id : resultOpinionExtractor.getCommentsID()) {
			Comment comment = resultsClassifyComments.get(id);
			Opinion opinion = resultOpinionExtractor.get(id);

			if (comment == null) {
				continue;
			}

			switch (comment.getOpinionValue()) {
			case NEGATIVE:
				switch (opinion.getOpinionValue()) {
				case NEGATIVE:
					negativeNegative.add(id);
					break;
				case NEUTRAL:
					negativeNeutral.add(id);
					break;
				case POSITIVE:
					negativePositive.add(id);
					break;
				default:
					break;

				}
				break;
			case NEUTRAL:
				switch (opinion.getOpinionValue()) {
				case NEGATIVE:
					neutralNegative.add(id);
					break;
				case NEUTRAL:
					neutralNeutral.add(id);
					break;
				case POSITIVE:
					neutralPositive.add(id);
					break;
				default:
					break;

				}
				break;
			case POSITIVE:
				switch (opinion.getOpinionValue()) {
				case NEGATIVE:
					positiveNegative.add(id);
					break;
				case NEUTRAL:
					positiveNeutral.add(id);
					break;
				case POSITIVE:
					positivePositive.add(id);
					break;
				default:
					break;

				}
				break;
			default:
				break;
			}
		}
	}

	public List<String[]> getResults() {
		ArrayList<String[]> result = new ArrayList<String[]>();

		double allOpinionsSize = positivePositive.size() + positiveNeutral.size() + positiveNegative.size()
				+ neutralNegative.size() + neutralNeutral.size() + neutralNegative.size() + negativeNegative.size()
				+ negativeNeutral.size() + negativePositive.size();

		allOpinionsSize /= 100.0;

		System.out.println(allOpinionsSize);

		String[] header = { "Classify\\Opinion", "Positive", "Neutral", "Negative" };
		String[] res1 = { "Positive", Double.toString((positivePositive.size() / allOpinionsSize)) + " %",
				Double.toString((positiveNeutral.size() / allOpinionsSize)) + " %",
				Double.toString((positiveNegative.size() / allOpinionsSize)) + " %" };
		String[] res2 = { "Neutral", Double.toString((neutralPositive.size() / allOpinionsSize)) + " %",
				Double.toString((neutralNeutral.size() / allOpinionsSize)) + " %",
				Double.toString((neutralNegative.size() / allOpinionsSize)) + " %" };
		String[] res3 = { "Negative", Double.toString((negativePositive.size() / allOpinionsSize)) + " %",
				Double.toString((negativeNeutral.size() / allOpinionsSize)) + " %",
				Double.toString((negativeNegative.size() / allOpinionsSize)) + " %" };

		result.add(header);
		result.add(res1);
		result.add(res2);
		result.add(res3);
		return result;
	}

	public List<String[]> getResultsWithoutNeutral() {
		ArrayList<String[]> result = new ArrayList<String[]>();

		double allOpinionsSize = positivePositive.size() + positiveNegative.size() + negativeNegative.size()
				+ negativePositive.size();

		allOpinionsSize /= 100.0;

		System.out.println(allOpinionsSize);

		String[] header = { "Classify\\Opinion", "Positive", "Negative" };
		String[] res1 = { "Positive", Double.toString((positivePositive.size() / allOpinionsSize)) + " %",
				Double.toString((positiveNegative.size() / allOpinionsSize)) + " %" };
		String[] res3 = { "Negative", Double.toString((negativePositive.size() / allOpinionsSize)) + " %",
				Double.toString((negativeNegative.size() / allOpinionsSize)) + " %" };

		result.add(header);
		result.add(res1);
		result.add(res3);
		return result;
	}

	public List<String[]> getFalsePositive() {
		ArrayList<String[]> comments = new ArrayList<String[]>();
		String[] header = { "ID", "Comment" };
		comments.add(header);
		for (int id : negativePositive) {
			Comment comment = resultsClassifyComments.get(id);
			String[] res = {Integer.toString(comment.id), comment.message};
			comments.add(res);
		}
		return comments;
	}
}
