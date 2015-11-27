package Controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.opencsv.CSVWriter;

import Common.Constants;
import Model.Evaluation;
import Model.ResultOpinionExtractor;
import View.StatisticView;

public class LoaderAllOpinions implements ActionListener {

	private StatisticView statisticView;
	private Evaluation evaluation;

	public LoaderAllOpinions(StatisticView statisticView) {
		this.statisticView = statisticView;
	}
	
	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		File results = new File(Constants.RESULTS);
		results.mkdir();
		File data = new File(Constants.DATA);
		ArrayList<String[]> mainResults = new ArrayList<String[]>();
		for (File file : data.listFiles()) {
			String[] result = new String[2];
			mainResults.add(result);
			String fileName = Constants.RESULTS + File.separator + file.getName().split("\\.")[0] + ".csv";
			result[0] = file.getName().split("\\.")[0];

			boolean loaded = false;
			ResultOpinionExtractor resultOpinionExtractor = new ResultOpinionExtractor(file);
			try {
				loaded = resultOpinionExtractor.load();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (loaded) {
				statisticView.setOpinionResult(resultOpinionExtractor);
				CSVWriter writer = null;
				try {
					writer = new CSVWriter(new FileWriter(fileName), ';');

					writer.writeAll(evaluation.getResults());
					result[1] = evaluation.getMainResults();

				} catch (IOException err) {
					// TODO Auto-generated catch block
					err.printStackTrace();
				} finally {
					if (writer != null) {
						try {
							writer.close();
						} catch (IOException err) {
						}
					}
				}
			}
		}
		
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(Constants.RESULTS + File.separator + Constants.RESULTS + ".csv"), ';');

			writer.writeAll(mainResults);

		} catch (IOException err) {
			// TODO Auto-generated catch block
			err.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException err) {
				}
			}
		}

	}

}
