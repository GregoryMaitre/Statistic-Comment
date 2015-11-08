package Controller;


import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

import Model.Evaluation;

public class SaveMetaData implements ActionListener {

	private Evaluation evaluation;
	
	public SaveMetaData() {
	}
	
	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
	
	public void save() {
		Frame f = new Frame();
		FileDialog fileChooser = new FileDialog(f, "Save", FileDialog.SAVE);
		fileChooser.setVisible(true);
		String name = fileChooser.getDirectory() + fileChooser.getFile() + ".csv";
		
		if (fileChooser.getFile() == null) {
			return;
		}
		

		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(name), ';');

			
			writer.writeAll(evaluation.getResultsWithoutNeutral());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (evaluation != null) {
			save();
		}
	}

}
