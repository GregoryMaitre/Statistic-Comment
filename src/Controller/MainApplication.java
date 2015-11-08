package Controller;

import java.io.IOException;

import Common.Constants;
import Model.ResultsClassifyComments;
import View.StatisticView;

public class MainApplication {
	public static void main(String[] args) throws IOException {
		
		ResultsClassifyComments resultsClassifyComments = new ResultsClassifyComments(Constants.COMMENTS_PATH);
		if (resultsClassifyComments.load()) {
			new StatisticView(resultsClassifyComments);
		}
	}
}
