package View;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Common.Constants;
import Controller.LoaderAllOpinions;
import Controller.LoaderOpinions;
import Controller.SaveFalsePositive;
import Controller.SaveMetaData;
import Model.Comment;
import Model.Evaluation;
import Model.Opinion;
import Model.ResultOpinionExtractor;
import Model.ResultsClassifyComments;

public class StatisticView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ResultsClassifyComments resultsClassifyComments;
	private ResultOpinionExtractor resultOpinionExtractor;
	private DefaultTableModel tableModel;
	private Evaluation evaluation;
	private SaveMetaData saveMetaData;
	private SaveFalsePositive saveFalsePositive;
	private LoaderAllOpinions loaderAll;

	/**
	 * Create the frame.
	 * @param resultsClassifyComments 
	 */
	public StatisticView(ResultsClassifyComments resultsClassifyComments) {
		this.resultsClassifyComments = resultsClassifyComments;
		LoaderOpinions loaderOpinions = new LoaderOpinions(this);
		saveMetaData = new SaveMetaData();
		saveFalsePositive = new SaveFalsePositive();
		loaderAll = new LoaderAllOpinions(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Statistic-Comments");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLoadOpinion = new JMenuItem("Load Opinion");
		mntmLoadOpinion.addActionListener(loaderOpinions);
		mnFile.add(mntmLoadOpinion);
		
		JMenuItem mntmExportMetadata = new JMenuItem("Export MetaData");
		mntmExportMetadata.addActionListener(saveMetaData);
		mnFile.add(mntmExportMetadata);
		
		JMenuItem mntmExportFalsePositive = new JMenuItem("Export False Positive");
		mntmExportFalsePositive.addActionListener(saveFalsePositive);
		mnFile.add(mntmExportFalsePositive);
		
		JMenuItem mntmLoadAndExport = new JMenuItem("Load and Export all");
		mntmLoadAndExport.addActionListener(loaderAll);
		mnFile.add(mntmLoadAndExport);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		
		setVisible(true);
	}

	public void setOpinionResult(ResultOpinionExtractor resultOpinionExtractor) {
		this.resultOpinionExtractor = resultOpinionExtractor;
		this.evaluation = new Evaluation(resultsClassifyComments, resultOpinionExtractor);
		evaluation.eval();
		saveMetaData.setEvaluation(evaluation);
		saveFalsePositive.setEvaluation(evaluation);
		loaderAll.setEvaluation(evaluation);
		printResults();
	}

	private void printResults() {
		tableModel.setColumnCount(0);
		tableModel.getDataVector().removeAllElements();
		tableModel.fireTableDataChanged();
		
		Vector<String> header = new Vector<String>();
		for (String headerName : Constants.HEADERS) {
			header.add(headerName);
		}
		
		Vector<Vector<String>> dataList = new Vector<Vector<String>>();
		
		for (int id : resultOpinionExtractor.getCommentsID()) {
			Vector<String> datas = new Vector<String>();
			
			Comment comment = resultsClassifyComments.get(id);
			Opinion opinion = resultOpinionExtractor.get(id);
			
			if (comment == null) {
				continue;
			}
			
			datas.add(Integer.toString(id));
			datas.add(comment.getOpinionValue().toString());
			datas.add(opinion.getOpinionValue().toString());
			datas.add((comment.getOpinionValue().equals(opinion.getOpinionValue())) ? "Correct" : "Incorrect");
			
			dataList.add(datas);
		}
			
		tableModel.setDataVector(dataList, header);
		
	}
}
