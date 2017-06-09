package Integration;

public class Property {
	private String value;
	private String name;
	private String type;
	
	Property(String value, PropertyType PropType){
		this.value = value;
		this.name = PropType.getName();
		this.type = PropType.getType();
	}
	
	public String getValue(){
		return value;
	}
	
	public String getName(){
		return name;
	}
	
	public String getType(){
		return type;
	}
}
