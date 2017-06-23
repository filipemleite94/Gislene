package AOM;

import COMM.IStorableObject;

public class Property implements IStorableObject {
	private Object value;
	private String name;
	private PropertyType pType;
	private Class<?> classe;
	
	public Property(PropertyType pType){
		classe = pType.getPropertyClass();
		value = pType.getValue("");
		name = pType.getNameVariable();
		this.pType = pType;
	}
	
	public Class<?> getClasse(){
		return classe;
	}
	
	public String getName(){
		return name;
	}
	
	public Object getValue(){
		return value;
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
	
	@Override
	public String toString(){
		return classe + " " + name + ": " + value; 
	}
}
