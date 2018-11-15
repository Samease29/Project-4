/**The GradeList Class which extends Field. This class formats and stores information from specifically
 * formatted Strings and makes an array of Grades out of it.*/
public class GradeList{
	
	private Grade[] grades;
	private Grade grade = null;
	private int highestGrade = 0;
	private String highestScore;
	/**Represents the number of Grades in GradeList*/
	private int size = 0;
	private static int GRADE_SUM = 0;
	private int totalScore = 0;
	private static final String NAME = "\"grades\":";
	
	public GradeList(String input){
		Grade.reset_HIGHEST_GRADE();
		Grade.reset_HIGHEST_SCORE();
		subFieldFormat(input);
		this.setFinalScoreTotal();
	}
	
	public int addGrades(Grade[] one, int sizeOfOne, Grade[] two, int sizeOfTwo) {
		Grade[] toReturn = new Grade[sizeOfOne + sizeOfTwo];
		for(int i = 0; i < sizeOfOne; i++) {
			toReturn[i] = one[i];
			if(this.highestGrade < one[i].getGradeValue()) {
				this.highestGrade = one[i].getGradeValue();
			}
			if(Integer.parseInt(this.highestScore) < one[i].getScoreValue()) {
				this.highestScore = one[i].getScoreValue() + "";
			}
		}
		for(int i = sizeOfOne; i < sizeOfOne + sizeOfTwo; i++) {
			toReturn[i] = two[i - sizeOfOne];
			if(this.highestGrade < two[i - sizeOfOne].getGradeValue()) {
				this.highestGrade = two[i - sizeOfOne].getGradeValue();
			}
			if(Integer.parseInt(this.highestScore) < two[i - sizeOfOne].getScoreValue()) {
				this.highestScore = two[i - sizeOfOne].getScoreValue() + "";
			}
		}
		
		this.grades = toReturn;
		this.size = grades.length;
		return this.size;
	}
	public String getNameOfField() {
		return NAME;
	}
	
	/**Gives Grade[] grades an initial null field*/
	private void initializeGrades() {
		this.grades = new Grade[1];
	}
	public Grade[] getGrades() {
		return grades;
	}
	
	/**Gives Grade[] grades a number of filled fields no greater than necessary to hold all Grades produced.
	 * @param Grade grade - The grade which is added to Grade[] grades*/
	public void setGrades(Grade grade) {
		if(getGrade() != null) {
			Grade[] temporary = new Grade[getSize() + 1];
			temporary[getSize()] = getGrade();
			for(int i = 0; i < getSize(); i++) {
				temporary[i] = getGrades()[i];
			}
			sizeUp();
			this.grades = temporary;
			setHighestGrade();
			setHighestScore();
		}else {
			Restaurant.setCOMPLETE();
		}
	}
	private void setGrade(String input) {
		this.grade = new Grade(input);
	}
	private void setGrade(String input, int difference) {
		this.grade = new Grade(input, 0);
	}
	private Grade getGrade() {
		return grade;
	}
	public int getSize() {
		return size;
	}
	public static void resetGradeSum() {
		GRADE_SUM = 0;
	}
	
	public static void setTotalScore(int gradeSum) {
		GRADE_SUM = GRADE_SUM + gradeSum;
	}
	
	public void setFinalScoreTotal() {
		this.totalScore = GRADE_SUM;
		GRADE_SUM = 0;
	}
	public int getTotalScore() {
		return totalScore;
	}
	/**Prints out a String version of an instance of the GradeList class.*/
	@Override
	public String toString() {
		String toReturn = NAME + " [";
		for(int i = 0; i < getSize(); i++) {
			if(i != getSize() - 1 || getSize() == 1) {
				toReturn += getGrades()[i].toString() + ", ";
			}else {
				toReturn += getGrades()[i].toString() + "]";
			}
		}
		return toReturn;
	}

	/**Takes a specifically formatted String and sets the values of Grades for Grade[] grades.
	 * @param String field - A specifically formatted String used to set values
	 * */
	protected void subFieldFormat(String field) {
		initializeGrades();
		setGrade(field, 0);
		while(Grade.getInput().indexOf(Grade.getStart()) != -1 && !Restaurant.isCOMPLETE()) {
			setGrade(Grade.getInput());
			if(getGrade() == null) {
				Restaurant.setCOMPLETE();
			}else {
				setGrades(getGrade());
			}
		}
		
	}
	public char getHighestGrade() {
		return (char)highestGrade;
	}
	public void setHighestGrade() {
		this.highestGrade = Grade.getHIGHEST_GRADE();
	}
	public String getHighestScore() {
		return highestScore;
	}
	public void setHighestScore() {
		this.highestScore = Grade.getHIGHEST_SCORE() + "";
	}
	
	/**Increments the value of size by 1.*/
	private void sizeUp() {
		this.size = this.size + 1;
	}
	
}
