package AOM;

import COMM.IStorableObject;

public class PropertyType extends TypePatternAbstract implements IStorableObject{
	private static eClassMap cMap = eClassMap.INSTANCE;
	private eValidator validator;
	private String typeName;
	private Class<?> classe;
	
	public PropertyType(String nameVariable, String typeName) throws ClassNotFoundException{
		super(nameVariable);
		this.typeName = typeName;
		this.validator = eValidator.INSTANCE;
		classe = cMap.getClassGenerico(typeName);
		super.classe = this.getClass();
		
	}
	
	public String getClassName(){
		return typeName;
	}
	
	public Object getValue(String value){
		Object tempValue;
		Class<?> propertyClass;
		try{
			propertyClass = cMap.getClassGenerico(typeName);
			tempValue = validator.cast(value, propertyClass);
		}catch(Exception E){
			return null;
		}
		return tempValue;
	}
	
	public Class<?> getPropertyClass(){
		return classe;
	}
}
