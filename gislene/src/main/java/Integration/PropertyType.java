package Integration;

import java.io.IOException;

public class PropertyType {
	private String type;
	private String name;
	
	PropertyType(String type, String name) throws IOException{
		this.type = type;
		if(name.equals("int")||name.equals("boolean")||name.equals("char")||name.equals("float")||name.equals("string")||name.equals("geo")||name.equals("relacionamento")){
			this.name = name;
		}else{
			throw new IOException("Tipo de propriedade invalida");
		}
	}
	
	public String getType(){
		return type;
		
	}
	
	public String getName(){
		return name;
	}
}
