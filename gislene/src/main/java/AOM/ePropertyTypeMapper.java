package AOM;

import java.io.IOException;
import java.util.HashMap;

public enum ePropertyTypeMapper {
	PropTypeMapperInstance;
	
	HashMap<String, PropertyType> map;
	ePropertyTypeContainerMapper TypeMapper;
	ePropertyTypeContainerMapper CategoryMapper;
	
	private ePropertyTypeMapper(){
		map = new HashMap<String, PropertyType>();
		TypeMapper = ePropertyTypeContainerMapper.TypeMapperInstance;
		CategoryMapper = ePropertyTypeContainerMapper.CategoryMapperInstance;
	}
	
	public void clearMap(){
		map = new HashMap<String, PropertyType>();
	}
	
	public void putPropertyType(String name, String typeName) throws IOException, ClassNotFoundException{
		if(map.get(name)!=null){
			throw new IOException("Duplicate PropertyType name");
		}
		map.put(name, new PropertyType(name, typeName));
	}
	
	public PropertyType getPropertyType(String name) throws IOException{
		PropertyType propType;
		propType = map.get(name);
		if(propType == null){
			throw new IOException("Inexistent PropertyType");
		}
		return propType;
	}
	
	public void removePropertyType(String name) throws IOException{
		PropertyType propType;
		propType = getPropertyType(name);
		TypeMapper.removePropertyTypeFromAll(propType);
		CategoryMapper.removePropertyTypeFromAll(propType);
		map.remove(name);
	}
}
