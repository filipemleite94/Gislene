package AOM;

import java.io.IOException;
import java.util.HashMap;

public enum eTypePatternMapper {
	TypePatternMapperInstance;
	
	HashMap<String, PropertyType> typeMap;
	HashMap<String, AccountabilityType> accountMap;
	eTypePatternContainerMapper TypeMapper;
	eTypePatternContainerMapper CategoryMapper;
	
	private eTypePatternMapper(){
		typeMap = new HashMap<String, PropertyType>();
		accountMap = new HashMap<String, AccountabilityType>();
		TypeMapper = eTypePatternContainerMapper.TypeMapperInstance;
		CategoryMapper = eTypePatternContainerMapper.CategoryMapperInstance;
	}
	
	public void clearMaps(){
		typeMap = new HashMap<String, PropertyType>();
		accountMap = new HashMap<String, AccountabilityType>();
	}
	
	public void putPropertyType(String name, String typeName) throws IOException, ClassNotFoundException{
		if(typeMap.get(name)!=null){
			throw new IOException("Duplicate PropertyType name");
		}
		typeMap.put(name, new PropertyType(name, typeName));
	}
	
	public void putAccountabilityType(String name) throws IOException{
		if(accountMap.get(name)!=null){
			throw new IOException("Duplicate AccountabilityType name");
		}
		accountMap.put(name, new AccountabilityType(name));
	}
	
	public PropertyType getPropertyType(String name) throws IOException{
		PropertyType propType;
		propType = typeMap.get(name);
		if(propType == null){
			throw new IOException("Inexistent PropertyType");
		}
		return propType;
	}
	
	public AccountabilityType getAccountabilityType(String name) throws IOException{
		AccountabilityType accountType;
		accountType = accountMap.get(name);
		if(accountType == null){
			throw new IOException("Inexistent AccountabilityType");
		}
		return accountType;
	}
	
	public void removePropertyType(String name) throws IOException{
		PropertyType propType;
		propType = getPropertyType(name);
		TypeMapper.removePropertyTypeFromAll(propType);
		CategoryMapper.removePropertyTypeFromAll(propType);
		typeMap.remove(name);
	}
	
	public void removeAccountabilityType(String name) throws IOException{
		AccountabilityType accountType;
		accountType = getAccountabilityType(name);
		TypeMapper.removeAccountabilityTypeFromAll(accountType);
		CategoryMapper.removeAccountabilityTypeFromAll(accountType);
	}
}
