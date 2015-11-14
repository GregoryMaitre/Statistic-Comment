package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Controller.JSONManager;

public class ResultOpinionExtractor {
	private File resultOpinions;
	private Opinions opinions;
	public ResultOpinionExtractor(File opinions) {
		resultOpinions = opinions;
		this.opinions = new Opinions();
	}
	
	public boolean load() throws FileNotFoundException {
		boolean done = false;
		
		if (resultOpinions.isFile()) {
			
			StringBuilder json = new StringBuilder();
			Scanner fileStream = new Scanner(new FileInputStream(resultOpinions));
			while (fileStream.hasNext()) {
				json.append(fileStream.nextLine());
			}
			fileStream.close();
			Opinion[] opinions = JSONManager.getInstance().toOpinions(json.toString());
			
			for (Opinion opinion : opinions) {
				this.opinions.add(opinion);
			}
			
			done = true;
		}
				
		return done;
	}
	
	public ArrayList<Integer> getCommentsID() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.addAll(opinions.getCommentsID());
		Collections.sort(result);
		return result;
	}
	
	public Opinion get(int id) {
		return opinions.get(id);
	}
}
