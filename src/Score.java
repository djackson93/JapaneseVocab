//Dakota Jackson

import java.text.SimpleDateFormat;
import java.util.Date;

public class Score {
	
	private int score;
	private String name;
	
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	Date date = new Date();
	
	public Score(int score) {
		
		this.name = "null";
		this.score = score;
	}
	
	public Score(String name, int Score) {
		
		this.name = name;
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name + " - " + score + "    DATE: " + formatter.format(date);
	}
	
}
