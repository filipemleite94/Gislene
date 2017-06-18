package AOM;

import java.lang.reflect.InvocationTargetException;

public class PropertyType {
	private String name;
	private Class<?> propertyClass;
	private static eClassMap cMap = eClassMap.INSTANCE;
	private eValidator validator;
	
	PropertyType(String name, String typeName) throws ClassNotFoundException{
		this.name = name;
		this.propertyClass = cMap.getClassGenerico(typeName);
		this.validator = eValidator.INSTANCE;
	}
	
	Object getValue(String value){
		Object tempValue;
		try{
			tempValue = validator.cast(value, propertyClass);
		}catch(Exception E){
			return null;
		}
		return tempValue;
	}

	public String getName() {
		return name;
	}
	
	public Class<?> getPropertyClass() throws ClassNotFoundException{
		return propertyClass;
	}
}
