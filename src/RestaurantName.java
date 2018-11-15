/**The RestaurantName Class which extends Field. This class formats and stores information from specifically
 * formatted Strings.*/
public class RestaurantName{
	
	private String name;
	/**Represents the number of filled fields in an instance of the RestaurantName class.*/
	private int size = 0;
	private static String NAME = "\"name\":";
	
	/**The constructor of RestaurantName class which calls subFieldFormat() to set values.*/
	public RestaurantName(String input) {
		subFieldFormat(input);
	}
	
	public String getNameOfField() {
		return  NAME;
	}
	
	public int getSize() {
		return size;
	}
	
	/**Increments the value of size by 1*/
	private void sizeUp() {
		this.size = this.size + 1;
	}
	
	public void setName(String name) {
		this.name = name;
		if(!name.equals("")) {
			sizeUp();
		}
	}
	
	public String getName() {
		return name;
	}
	
	/**Takes a specifically formatted String and sets the values for name
	 * @param String field - A specifically formatted String used to set values
	 * */

	
	protected void subFieldFormat(String field) {
		if(!Restaurant.isCOMPLETE()) {
			if(field.indexOf(':') != -1) {
				int indexOfColon = field.indexOf(':');
				String subFieldItem = field.substring(indexOfColon  + 1);
					subFieldItem = subFieldItem.replaceFirst(" ", "");
					subFieldItem = subFieldItem.replace("\"", "");
					if(subFieldItem.replace(" ", "") != "")
						sizeUp();
					setName(subFieldItem);
			}else {
				Restaurant.setCOMPLETE();
			}
		}else {

			setName(null);
		}

	}
	
	/**Prints out a String version of an instance of the RestaurantName class.*/
	@Override
	public String toString() {
		if(!Restaurant.isCOMPLETE()) {
			return NAME + " \"" + getName() + "\"";
		}else {
			return null;
		}
	}

}
