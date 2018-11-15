import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**The Address Class. This class formats and stores information from specifically
 * formatted Strings and keeps track of certain values from those Strings*/
public class Grade{
	
	private String grade = null;
	private String score = null;
	private String date = null;
	/**Represents the number of grades, scores, and dates in Grade*/
	private int size = 0;
	private static char HIGHEST_GRADE = 0;
	private char gradeValue = 0;
	private static int HIGHEST_SCORE = 0;
	private int scoreValue = 0;
	private static String INPUT;
	private static String START = "\"$date\"";
	private static final String NAME = "{\"date\":";
	private static final String NAME_OF_DATE = "{\"$date\":";
	private static final String NAME_OF_GRADE = "\"grade\":";
	private static final String NAME_OF_SCORE = "\"score\":";
	
	/**The constructor of Grade class which calls setters to set values for date, grade, and score*/
	public Grade(String input) {
		this.setDate(input);
		this.setGrade(input);
		this.setScore(input);
	}
	
	/**The constructor of Grade used solely to set and access INPUT and START*/
	public Grade(String input, int difference) {
		setInput(input);
		setStart(getStart());
	}
	public static String getInput() {
		return Grade.INPUT;
	}

	public static void setInput(String input) {
		Grade.INPUT = input;
	}

	public static String getStart() {
		return Grade.START;
	}
	
	public static void setStart(String start) {
		Grade.START = start;
	}
	public int getSize() {
		return size;
	}
	
	public static void reset_HIGHEST_GRADE() {
		HIGHEST_GRADE = 0;
	}
	
	public static void reset_HIGHEST_SCORE() {
		HIGHEST_SCORE = 0;
	}
	
	public char getGradeValue() {
		return this.gradeValue;
	}
	
	public int getScoreValue() {
		return this.scoreValue;
	}
	/**Increments the value of size by 1.*/
	private void sizeUp() {
		this.size = this.size + 1;
	}
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		if(!Restaurant.isCOMPLETE()) {
			int start = grade.indexOf(getStart());
			grade = grade.substring(start);
			start = grade.indexOf(" ");
			String gradeStart = grade;
			int finish = gradeStart.indexOf(",");
			try {
				gradeStart = gradeStart.substring(start, finish);
				gradeStart = gradeStart.replaceAll(" ", "");
				gradeStart = gradeStart.replaceAll("\"", "");
				gradeStart =gradeStart.replaceAll(",", "");
				this.grade = gradeStart;
				if(HIGHEST_GRADE == 0) {
					HIGHEST_GRADE = this.grade.charAt(0);
					this.gradeValue = HIGHEST_GRADE;
				}else if((int)HIGHEST_GRADE > (int)(this.grade.charAt(0))) {
					HIGHEST_GRADE = this.grade.charAt(0);
					this.gradeValue = HIGHEST_GRADE;
				}
				setStart("\"score\"");
				if(!gradeStart.equals("")) {
					sizeUp();
				}
			}catch(IndexOutOfBoundsException e){
				Restaurant.setCOMPLETE();
			}
		}else {
			this.grade = null;
		}
	}
	
	public String getScore() {
		return score;
	}
	public void setScore(String score) {//Returns incorrect values for Highest Score
		if(!Restaurant.isCOMPLETE()) {
			int start = score.indexOf(Grade.getStart());
			score = score.substring(start);
			start = score.indexOf(" ");
			String scoreStart = score;
			String input = score;
			int finish = scoreStart.indexOf("}");
			start++;
			try {
				scoreStart = scoreStart.substring(start, finish);
				int scoreAsNumber = Integer.parseInt(scoreStart);
				this.score = scoreAsNumber + "";
				if(HIGHEST_SCORE == 0) {
					//System.out.println(Main.getLINE_NUMBER() + ": " + this.score);
					HIGHEST_SCORE = Integer.parseInt(this.score);
				}else if(HIGHEST_SCORE < Integer.parseInt(this.score)) {
					//System.out.println(Main.getLINE_NUMBER() + ": " + this.score);
					HIGHEST_SCORE = Integer.parseInt(this.score);
				}
				GradeList.setTotalScore(Integer.parseInt(this.score));
				finish += 2;
				input = input.substring(finish);
				setInput(input);
				setStart("\"$date\"");
				if(!this.score.equals("")) {
					sizeUp();
				}
			}catch(NumberFormatException e) {
				Restaurant.setCOMPLETE();
			}catch(IndexOutOfBoundsException e) {
				Restaurant.setCOMPLETE();
			}
		}else {
			this.score = null;
			setGrade("");
		}
	}
	
	public static char getHIGHEST_GRADE() {
		return HIGHEST_GRADE;
	}

	public static Integer getHIGHEST_SCORE() {
		return HIGHEST_SCORE;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		if(!Restaurant.isCOMPLETE()) {
			int start = date.indexOf(Grade.getStart());
			date = date.substring(start);
			start = date.indexOf(" ");
			String dateStart = date.substring(start);
			int finish = dateStart.indexOf(",");
			finish--;
			dateStart = dateStart.substring(1, finish);
			try {
				long parsedDate = Long.valueOf(dateStart);
				Date finalDate = new Date(parsedDate);
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				this.date = df.format(finalDate);
				setStart("\"grade\"");
				sizeUp();
				
			}catch(NumberFormatException e) {
				Restaurant.setCOMPLETE();
			}catch(IndexOutOfBoundsException e) {
				Restaurant.setCOMPLETE();
			}
		}else {
			this.date = null;
			setScore("");
		}
	}
	
	/**Prints out a String version of an instance of the Grade class.*/
	@Override
	public String toString() {
		if(!Restaurant.isCOMPLETE()) {
			if(getGrade() == null) {
				this.grade = "";
			}
			if(getDate() == null) {
				this.date = "";
			}
			if(getScore() == null) {
				this.score = "0";
			}
			return NAME + " " + NAME_OF_DATE + " " + getDate() + "}, " + NAME_OF_GRADE + " \"" + getGrade() + "\", " + NAME_OF_SCORE + " \"" + getScore() + "}";
		}else {
			return null;
		}
	}
}
