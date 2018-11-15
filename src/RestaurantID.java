/**The RestaurantID Class which extends Field. This class formats and stores information from specifically
 * formatted Strings.*/
public class RestaurantID{
	
	private String iD;
	/**Represents the number of filled fields in an instance of the RestaurantID class.*/
	private int size = 0;
	private static String NAME = "\"restaurant_id\":";
	
	/**The constructor of RestaurantID class which calls subFieldFormat() to set values.*/
	public RestaurantID(String input){
		subFieldFormat(input);
	}

	public String getNameOfField() {
		return NAME;
	}
	
	public String getiD() {
		return iD;
	}

	public void setID(String iD) {
		this.iD = iD;
		if(!iD.equals("")) {
			sizeUp();
		}
	}

	public int getSize() {
		return size;
	}
	
	/**Increments the value of size by 1*/
	private void sizeUp() {
		this.size = this.size + 1;
	}
	
	/**Takes a specifically formatted String and sets the values for iD
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
				setID(subFieldItem);
				}else {
					Restaurant.setCOMPLETE();
				}
			}else {
				Restaurant.setCOMPLETE();
			}
		}else {
			setID(null);
		}

	}
	
	/**Prints out a String version of an instance of the RestaurantID class.*/
	@Override
	public String toString() {
		if(!Restaurant.isCOMPLETE()) {
			return NAME + " \"" + getiD() + "\"";
		}else {
			return null;
		}
	}
}
