package Integration;

public class PropertyType {
	private String type;
	private String name;
	
	PropertyType(String type, String name){
		this.type = type;
		//checkName(name);
		this.name = name;
	}
	
	public String getType(){
		return type;
		
	}
	
	public String getName(){
		return name;
	}
}
