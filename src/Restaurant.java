import java.io.BufferedWriter;

/*
 * 
 * */
public class Restaurant{
	
	private String[] names;
	private Object[] fieldArray;
	private int[] fieldSizes = null;
	private int size = 0;
	private int currentNumber = 0;
	private static final int MAX_SIZE = 20;
	private static final int MAX_RESTAURANT = 5000;
	private static boolean COMPLETE = false;
	private String ID;
	private String name;
	private String cuisine; 
	private boolean hasID = false;
	private boolean hasName = false;
	private boolean hasGradeAverage = false;
	private boolean hasCuisine = false;
	private int gradeAverage = 0;
	private String highestGrade;
	private String highestScore;
	private int gradeSize = 0;
	private static final String GRADE_AVERAGE = "\"average_grade_score\":";
	private static final String HIGHEST_GRADE = "\"the_highest_grade\":";
	private static final String HIGHEST_SCORE = "\"the_highest_score\":";
	private static final String GRADE_COUNT = "\"grade_count\":";
	char[] stack;
	String[] subField;
	private GradeList restaurantGrades;
	
	int lengthOfSubField = 0;
	
	public Restaurant(String input){
		setFields(input);
		this.gradeAverage = restaurantGrades.getTotalScore()/this.gradeSize;
	}

	public GradeList getRestaurantGrades() {
		return restaurantGrades;
	}
	
	public int getFieldSize(int index) {
		return this.fieldSizes[index];
	}
	public int getGradeSize() {
		return gradeSize;
	}

	public void setGradeSize() {
		this.gradeSize = restaurantGrades.getSize();
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	
	public boolean isHasID() {
		return hasID;
	}

	public void setHasID() {
		this.hasID = true;
	}
	
	public boolean isHasCuisine() {
		return hasCuisine;
	}

	public void setHasCuisine() {
		this.hasCuisine = true;
	}
	
	public boolean isHasName() {
		return hasName;
	}

	public void setHasName() {
		this.hasName = true;
	}

	public boolean isHasGradeAverage() {
		return hasGradeAverage;
	}

	public void setHasGradeAverage() {
		this.hasGradeAverage = true;
	}
	
	public int getID() {
		return Integer.parseInt(ID);
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Restaurant() {
	}
	
	public static int getMAX_RESTAURANT() {
		return MAX_RESTAURANT;
	}

	public static int getMAX_SIZE() {
		return MAX_SIZE;
	}
	
	public static boolean isCOMPLETE() {
		return COMPLETE;
	}

	public static void setCOMPLETE() {
		if(!isCOMPLETE()) {
			COMPLETE = true;
			System.out.println("The restaurant entry at line " //+ Main.getLINE_NUMBER() 
			+ " is not valid.");
		}
	}

	public static void resetCOMPLETE() {
		COMPLETE = false;
	}

	public int getActualSize() {
		if(hasGradeAverage) 
			return this.size + 4;
		return this.size;
		
	}
	public int getSize() {
		return size;
	}
	
	private void setSize(String[] fields) {
		this.size = fields.length;
	}

	private void setFieldArraySize() {
		this.fieldArray = new Object[getSize()];
		this.names = new String[getSize()];
		this.fieldSizes = new int[getSize()];
	}
	
	public Object[] getFieldArray() {
		return fieldArray;
	}

	public void setFieldArray(String[] fields) {
		for(int i = 0; i < getSize(); i++) {
			subFieldFormat(fields[i]);
		}
	}

	@Override
	public String toString() {
		if(!isCOMPLETE()) {
			String toReturn = "{";
			for(int i = 0; i < getSize(); i++) {
				if(i != getSize() -1)
					toReturn = toReturn + fieldArray[i].toString() + ", ";
				if(i == getSize() - 1) 
					toReturn = toReturn + fieldArray[i];
			}
			if(getGradeSize() != 0)
				toReturn = toReturn + ", " + GRADE_COUNT + " \"" + getGradeSize() + "\"";
			if(getHighestGrade() != null)
				toReturn = toReturn + ", " + HIGHEST_GRADE + " \"" + getHighestGrade() + "\"";
			if(getHighestScore() != null)
				toReturn = toReturn + ", " + HIGHEST_SCORE + " " + getHighestScore();
			if(getGradeAverage() != 0)
				toReturn = toReturn + ", " + GRADE_AVERAGE + " \"" + getGradeAverage() + "\"";
			toReturn = toReturn + "}";

			return toReturn;
		}else {
			return null;
		}
	}

	void setFields(String input){
		if(!isCOMPLETE()) {
			String[] fields = getField(input);
			setSize(fields);
			setFieldArraySize();
			setFieldArray(fields);
		}
		}
		
protected void subFieldFormat(String field) {
		if(field.indexOf("\"name\"") != -1) {
			RestaurantName nameOfRestaurant = new RestaurantName(field);
			this.name = nameOfRestaurant.getName();
			fieldArray[currentNumber] = nameOfRestaurant;
			if(!this.name.equals("")) {
				this.hasName = true;
				names[currentNumber] = "name";
				fieldSizes[currentNumber] = nameOfRestaurant.getSize();
			}
			currentNumber++;
		}else if(field.indexOf("\"restaurant_id\"") != -1) { 
			RestaurantID idOfRestaurant = new RestaurantID(field);
			if(idOfRestaurant.getiD() == null) {
				Restaurant.setCOMPLETE();
			}else {
				this.ID = idOfRestaurant.getiD();
				fieldArray[currentNumber] = idOfRestaurant;
				this.hasID = true;
				names[currentNumber] = "id";
				fieldSizes[currentNumber] = idOfRestaurant.getSize();
			}
			currentNumber++;
		}else if(field.indexOf("\"cuisine\":") != -1) {
			Cuisine cuisineOfRestaurant = new Cuisine(field);
			this.cuisine = cuisineOfRestaurant.getCuisineType();
			fieldArray[currentNumber] = cuisineOfRestaurant;
			if(!this.cuisine.equals("")) {
				this.hasCuisine = true;
				names[currentNumber] = "cuisine";
				fieldSizes[currentNumber] = cuisineOfRestaurant.getSize();
			}
			currentNumber++;
		}else if(field.indexOf("\"address\":") != -1) {
			Address addressOfRestaurant = new Address(field);
			fieldArray[currentNumber] = addressOfRestaurant;
			if(addressOfRestaurant.getSize() != 0) {
				names[currentNumber] = "address";
				fieldSizes[currentNumber] = addressOfRestaurant.getSize();
			}
			currentNumber++;
		}else if(field.indexOf("\"borough\":") != -1) {
			Borough boroughOfRestaurant = new Borough(field);
			fieldArray[currentNumber] = boroughOfRestaurant;
			if(boroughOfRestaurant.getSize() != 0) {
				names[currentNumber] = "borough";
				fieldSizes[currentNumber] = boroughOfRestaurant.getSize();
			}
			currentNumber++;
		}else if(field.indexOf("\"grades\":") != -1) {
			GradeList gradesOfRestaurant = new GradeList(field);
			fieldArray[currentNumber] = gradesOfRestaurant;
			this.highestGrade = gradesOfRestaurant.getHighestGrade() + "";
			this.highestScore = gradesOfRestaurant.getHighestScore() + "";
			int totalScore = gradesOfRestaurant.getTotalScore();
			if(totalScore != 0) {
				setGradeAverage(totalScore / gradesOfRestaurant.getSize());
				this.restaurantGrades = gradesOfRestaurant;
				setGradeSize();
				setHasGradeAverage();
				GradeList.resetGradeSum();
				names[currentNumber] = "grades";
				fieldSizes[currentNumber] = gradesOfRestaurant.getSize();
			}
			currentNumber++;
		}else {
			fieldArray[currentNumber] = field;
			String[] returning = findName(field);
			names[currentNumber] = returning[0];
			fieldSizes[currentNumber] = Integer.parseInt(returning[0]);
			currentNumber++;
		}
	}

	private String[] findName(String field) {
		String[] toReturn = new String[2];
		int count = 0;
		int fieldNumber = 0;
		boolean nameFound = false;
		initializeStack();
		int i = 0;
		while(i < field.length() - 1 && !Restaurant.isCOMPLETE()) {
			if(count == stack.length) {
				increaseStack(stack);
			}
			if(field.charAt(i) == '{') {
				stack[count] = field.charAt(i);
				count++;
			}else if(field.charAt(i) == '}' && stack[count -1] == '{') {
				count--;
			}else if(field.charAt(i) == '[') {
				stack[count] = field.charAt(i);
				count++;
			}else if(field.charAt(i) == ']' && stack[count -1] == '[') {
				count--;
			}
			if(field.charAt(i) == ':' && !nameFound) {
				nameFound = true;
				String justName = field.substring(0, i);
				int start = justName.indexOf('"');
				start++;
				int finish = i - 1;
				justName.substring(start, finish);
				toReturn[0] = justName;
			}
			if(field.charAt(i) == ',' && count == 0) {
				fieldNumber++;
			}
			i++;
		}
		destroyStack();
		toReturn[1] = fieldNumber + "";
		return toReturn;
	}
	public int getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(int currentNumber) {
		this.currentNumber = currentNumber;
	}

	public int getGradeAverage() {
		return gradeAverage;
	}

	public void setGradeAverage(int gradeAverage) {
		this.gradeAverage = gradeAverage;
		setHasGradeAverage();
	}

	public String getHighestGrade() {
		return highestGrade;
	}

	public void setHighestGrade(String highestGrade) {
		this.highestGrade = highestGrade;
	}

	public String getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(String highestScore) {
		this.highestScore = highestScore;
	}

	public int compareRestaurants(Restaurant a, String wayToCompare) {
		int comparison = 0;
		if(wayToCompare.equalsIgnoreCase("id")) {
			comparison = this.getID() - a.getID() ;
		}else if(wayToCompare.equalsIgnoreCase("name")) {
			comparison = this.getName().compareTo(a.getName());
		}else if(wayToCompare.equalsIgnoreCase("averagegradescore")) {
			comparison = -1 * this.getGradeAverage() - a.getGradeAverage();
		}else if(wayToCompare.equalsIgnoreCase("cuisine")) {
			comparison = this.getCuisine().compareTo(a.getCuisine());
		}
		if(comparison < 0)
			return -1;
		else if(comparison > 0)
			return 1;
		else
			return 0;
	}
	
	public boolean matchesComparison(String wayToCompare, String userResponse) {
		if(wayToCompare.equals("id") && (this.getID() + "").equalsIgnoreCase(userResponse))
			return true;
		if(wayToCompare.equals("name") && this.getName().equalsIgnoreCase(userResponse))
			return true;
		if(wayToCompare.equals("cuisine") && this.getCuisine().equalsIgnoreCase(userResponse))
			return true;
		if(wayToCompare.equals("averagegradescore") && this.getGradeAverage() == Integer.parseInt(userResponse))
			return true;
		return false;
	}
	
	protected String[] getField(String input) {
		int quoteCounter = 0;
		int fieldNumber = 0;
		int count = 0;
		int fieldStart = 1;
		int fieldEnd = 0;
		String[] fields = new String[Restaurant.getMAX_SIZE()];
		initializeStack();
		int i = 0;
		while(i < input.length() && !Restaurant.isCOMPLETE()) {
			if(count == stack.length) {
				increaseStack(stack);
			}
			if(input.charAt(i) == '{') {
				stack[count] = input.charAt(i);
				count++;
			}else if(input.charAt(i) == '}' && stack[count -1] == '{') {
				count--;
			}else if(input.charAt(i) == '[') {
				stack[count] = input.charAt(i);
				count++;
			}else if(input.charAt(i) == ']' && stack[count -1] == '[') {
				count--;
			}else if(input.charAt(i) == '"' && quoteCounter == 0) {
				quoteCounter++;
				stack[count] = input.charAt(i);
				count++;
			}else if(input.charAt(i) == '"' && quoteCounter == 1 && stack[count -1] == '"') {
				quoteCounter--;
				count--;
			}else if(input.charAt(i) == '"' && quoteCounter == 1 || input.charAt(i) == '}' || input.charAt(i) == ']') {
				Restaurant.setCOMPLETE();
			}
			if(input.charAt(i)  == ',' && count == 1 && stack[count-1] == '{') {
				fieldEnd = i;
				String field = input.substring(fieldStart, fieldEnd);
				fields[fieldNumber] = field;
				fieldNumber++;
				fieldStart = fieldEnd + 2;
			} 
			if(input.charAt(i) == '}' && count == 0) {
				fieldEnd = i;
				String field = input.substring(fieldStart, fieldEnd);
				fields[fieldNumber] = field;
				fieldNumber++;
			}
			i++;
		}
		destroyStack();
		String[] toReturn = new String[fieldNumber];
		for(int j = 0; j < fieldNumber; j++) {
			toReturn[j] = fields[j];
		}
		
		return toReturn;
		
	}
	
	protected void initializeStack() {
		stack = new char[100];
	}
	protected void increaseStack(char[] stack) {
		char[] toReturn = new char[stack.length + 50];
		for(int i = 0; i < stack.length; i++) {
			toReturn[i] = stack[i];
		}
		stack = toReturn;
	}
	
	protected void destroyStack() {
		stack = new char[1];
	}
}
