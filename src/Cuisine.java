/**The Cuisine Class which extends Field. This class formats and stores information from specifically
 * formatted Strings.*/
public class Cuisine{
	
	private String cuisineType;
	/**Represents the number of filled fields in an instance of the Cuisine class.*/
	private int size = 0;
	private static String NAME = "\"cuisine\":";
	
	/**The constructor of Cuisine class which calls subFieldFormat() to set values.*/
	public Cuisine(String input){
		subFieldFormat(input);
	}
	
	public String getNameOfField() {
		return NAME;
	}
	
	public int getSize() {
		return size;
	}
	
	/**Increments the value of size by 1*/
	private void sizeUp() {
		this.size = this.size + 1;
	}
	
	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
		if(!cuisineType.equals("")) {
			sizeUp();
		}
	}
	
	/**Prints out a String version of an instance of the Cuisine class.*/
	@Override
	public String toString() {
		if(!Restaurant.isCOMPLETE()) {
			return NAME + " \""+ getCuisineType() + "\"";
		}else {
			return null;
		}
	}

	/**Takes a specifically formatted String and sets the values for cuisineType
	 * @param String field - A specifically formatted String used to set values
	 * */

	protected void subFieldFormat(String field) {
		if(!Restaurant.isCOMPLETE()) {
			if(field.indexOf(':') != -1) {
				int indexOfColon = field.indexOf(':');
				String subFieldItem = field.substring(indexOfColon  + 1);
				if(subFieldItem.indexOf(':') == -1) {
				subFieldItem = subFieldItem.replaceFirst(" ", "");
				subFieldItem = subFieldItem.replace("\"", "");
				if(subFieldItem.replace(" ", "") != "")
					sizeUp();
				setCuisineType(subFieldItem);
				}else {
					Restaurant.setCOMPLETE();
				}
			}else {
				Restaurant.setCOMPLETE();
			}
		}else {

			setCuisineType(null);
		}

	}
}
