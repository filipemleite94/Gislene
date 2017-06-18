package AOM;

import java.io.IOException;
import java.util.HashMap;

public enum ePropertyTypeMapper {
	PropTypeMapperInstance;
	
	HashMap<String, PropertyType> map;
	
	private ePropertyTypeMapper(){
		map = new HashMap<String, PropertyType>();
	}
	
	public PropertyType getPropertyType(String name) throws IOException{
		PropertyType propType;
		propType = map.get(name);
		if(propType == null){
			throw new IOException("Inexistent key");
		}
		return propType;
	}
	
	
}
