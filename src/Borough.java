/**The Borough Class which extends Field. This class formats and stores information from specifically
 * formatted Strings.*/
public class Borough{
	
	private String boroughName;
	/**Represents the number of filled fields in an instance of the Borough class.*/
	private int size = 0;
	private static String NAME = "\"borough\":";

	/**The constructor of Borough class which calls subFieldFormat() to set values.*/
	public Borough(String input){
		//String addressHolder = addressBuilder(input);
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
	public String getBoroughName() {
		return boroughName;
	}

	public void setBoroughName(String buroughName) {
		this.boroughName = buroughName;
		if(!buroughName.equals("")) {
			sizeUp();
		}
	}
	
	/**Prints out a String version of an instance of the Borough class.*/
	@Override
	public String toString() {
		if(!Restaurant.isCOMPLETE()) {
			return NAME + " \"" + getBoroughName() + "\"";
		}else {
			return null;
		}
	}

	/**Takes a specifically formatted String and sets the values for borough
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
					setBoroughName(subFieldItem);
				}else {
					Restaurant.setCOMPLETE();
				}
			}else {
				Restaurant.setCOMPLETE();
			}
		}else {

			setBoroughName(null);
		}

	}

}
