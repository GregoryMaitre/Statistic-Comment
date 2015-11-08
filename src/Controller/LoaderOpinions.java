package Controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import Model.ResultOpinionExtractor;
import View.StatisticView;

public class LoaderOpinions implements ActionListener {
	
	private StatisticView statisticView;
	
	public LoaderOpinions(StatisticView statisticView) {
		this.statisticView = statisticView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Frame f = new Frame();
		FileDialog fileChooser = new FileDialog(f, "Load", FileDialog.LOAD);
		fileChooser.setVisible(true);
		File targetFile = new File(fileChooser.getDirectory() + fileChooser.getFile());
		
		if (fileChooser.getDirectory() == null) {
			return;
		}
		
		if (!targetFile.exists() || !targetFile.isFile()) {
			JOptionPane.showMessageDialog(null, "File invalid", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		boolean loaded = false;
		ResultOpinionExtractor resultOpinionExtractor = new ResultOpinionExtractor(targetFile);
		try {
			loaded = resultOpinionExtractor.load();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (loaded) {
			statisticView.setOpinionResult(resultOpinionExtractor);
		}
	}

}
