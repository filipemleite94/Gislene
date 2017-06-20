package AOM;

import java.lang.reflect.InvocationTargetException;

public class Property {
	private Object value;
	private Class<?> classe;
	private final PropertyType pType;
	
	public Property(PropertyType pType) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		this.pType = pType;
		classe = pType.getPropertyClass();
		value = pType.getValue("");
	}
	
	public boolean setValue(String valueString){
		Object tempValue;
		tempValue = pType.getValue(valueString);
		if(tempValue==null){
			return false;
		}
		this.value = tempValue;
		return true;
	}
}
