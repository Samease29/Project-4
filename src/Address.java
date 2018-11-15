/**The Address Class which extends Field. This class formats and stores information from specifically
 * formatted Strings.*/
public class Address{
	
	private String building;
	private String coord;
	private String street;
	private String zipcode;
	/**Represents the number of filled fields in an instance of the Address class.*/
	private int size = 0;
	private static final String NAME = "\"address\":";
	private static final String NAME_OF_BUILDING = "\"building\":";
	private static final String NAME_OF_COORD = "\"coord\":";
	private static final String NAME_OF_STREET = "\"street\":";
	private static final String NAME_OF_ZIPCODE = "\"zipcode\":";
	
	/**The constructor of Address class which calls subFieldFormat() to set values.*/
	public Address(String input){
		subFieldFormat(input);
	}
	
	public String getNameOfField() {
		return NAME;
	}
	
	public int getSize() {
		return size;
	}
	
	/**Increments the value of size by 1.*/
	private void sizeUp() {
		this.size = this.size + 1;
	}
	
	public String getBuilding() {
		return building;
	}
	
	/**Takes a specifically formatted String input to set the value of the building field.
	 * @param String building - A specifically formatted String that is used to set building*/
	public void setBuilding(String building) {
		if(!Restaurant.isCOMPLETE()) {
			int buildingStart = 0;
			int buildingFinish = 0;
			int start = building.indexOf("building");
			try {
				String nameStart = building.substring(start);
				start = nameStart.indexOf(" ");
				nameStart = nameStart.substring(start);
				buildingStart = nameStart.indexOf('"');
				buildingStart++;
				buildingFinish = nameStart.indexOf(',');
				buildingFinish --;
				if(buildingStart > buildingFinish) {
					Restaurant.setCOMPLETE();
					this.building = null;
				}else if(buildingStart == buildingFinish){
					this.building = "";
				}else {
					nameStart = nameStart.substring(buildingStart, buildingFinish);
					this.building = nameStart;
					sizeUp();
				}
			}catch(IndexOutOfBoundsException e) {
				Restaurant.setCOMPLETE();
			}
		}else {
			this.building = null;
		}
	}
	
	public String getCoord() {
		return coord;
	}
	
	/**Takes a specifically formatted String input to set the value of the coord field.
	 * @param String coord - A specifically formatted String that is used to set coord*/
	public void setCoord(String coord) {
		if(!Restaurant.isCOMPLETE()) {
			int start = coord.indexOf("coord");
			try {
				String nameStart = coord.substring(start);
				start = nameStart.indexOf("[");
				start++;
				int finish = nameStart.indexOf("]");
				if(start > finish) {
					Restaurant.setCOMPLETE();
					this.coord = null;
					setBuilding("");
				}else {
					nameStart = nameStart.substring(start, finish);
					this.coord = nameStart;
					if(!nameStart.equals("[]")) {
						sizeUp();
					}
				}
			}catch(IndexOutOfBoundsException e) {
				Restaurant.setCOMPLETE();
			}
		}else {
			this.coord = null;
			setBuilding("");
		}
	}
	
	public String getStreet() {
		return street;
	}
	
	/**Takes a specifically formatted String input to set the value of the street field.
	 * @param String street - A specifically formatted String that is used to set street*/
	public void setStreet(String street) {
		if(!Restaurant.isCOMPLETE()) {
			int streetStart = 0;
			int streetFinish = 0;
			int start = street.indexOf("street");
			try {
				String nameStart = street.substring(start);
				start = nameStart.indexOf(" ");
				nameStart = nameStart.substring(start);
				streetStart = nameStart.indexOf('"');
				streetStart++;
				streetFinish = nameStart.indexOf(',');
				streetFinish --;
				if(streetStart > streetFinish) {
					Restaurant.setCOMPLETE();
					this.street = null;
					setCoord("");
				}else if(streetStart == streetFinish){
					this.street = "";
				}else {
					nameStart = nameStart.substring(streetStart, streetFinish);
					this.street = nameStart;
						sizeUp();
				}
			}catch(IndexOutOfBoundsException e) {
				Restaurant.setCOMPLETE();
			}
		}else {
			this.street = null;
			setCoord("");
		}
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	/**Takes a specifically formatted String input to set the value of the zipcode field.
	 * @param String zipcode - A specifically formatted String that is used to set zipcode*/
	public void setZipcode(String zipcode) {
		if(!Restaurant.isCOMPLETE()) {
			int zipcodeStart = 0;
			int zipcodeFinish = 0;
			int start = zipcode.indexOf("zipcode");
			try {
				String nameStart = zipcode.substring(start);
				start = nameStart.indexOf(" ");
				nameStart = nameStart.substring(start);
				zipcodeStart = nameStart.indexOf('"');
				zipcodeStart++;
				zipcodeFinish = nameStart.indexOf('}');
				zipcodeFinish--;
				if(zipcodeStart > zipcodeFinish) {
					System.out.println("Here");
					Restaurant.setCOMPLETE();
					this.zipcode = null;
					setStreet("");
				}else if(zipcodeStart == zipcodeFinish) {
					this.zipcode = "";
				}else {
					nameStart = nameStart.substring(zipcodeStart, zipcodeFinish);
					int zipcodeAsInt = Integer.parseInt(nameStart);
					this.zipcode = zipcodeAsInt + "";
				}
			}catch(IndexOutOfBoundsException e) {
				Restaurant.setCOMPLETE();
			}catch(NumberFormatException e) {
				Restaurant.setCOMPLETE();
			}
		}else {
			this.zipcode = null;
			setStreet("");
		}
	}
	
	/**Prints out a String version of an instance of the Address class.*/
	@Override
	public String toString() {
		if(!Restaurant.isCOMPLETE()) {
			if(getBuilding() == null) {
				this.building = "";
			}
			if(getCoord() == null) {
				this.coord = "";
			}
			if(getStreet() == null) {
				this.street = "";
			}
			if(getZipcode() == null) {
				this.zipcode = "";
			}
			return NAME + " {" + NAME_OF_BUILDING + " \"" + getBuilding() + "\", " + NAME_OF_COORD + " [" + getCoord() + "], " + NAME_OF_STREET + " \"" + getStreet() 
			+ "\", " + NAME_OF_ZIPCODE + " \"" + getZipcode() + "\"}";
		}else {
			return null;
		}
	}

	/**Takes a specifically formatted String and sets the values for building,
	 * coord, street, and zipcode.
	 * @param String field - A specifically formatted String used to set values
	 * */
	protected void subFieldFormat(String field) {
		setBuilding(field);
		setCoord(field);
		setStreet(field);
		setZipcode(field);
	}
}
