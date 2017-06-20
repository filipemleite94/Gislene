package AOM;

public class PropertyType extends TypePatternAbstract{
	private Class<?> propertyClass;
	private static eClassMap cMap = eClassMap.INSTANCE;
	private eValidator validator;
	
	PropertyType(String name, String typeName) throws ClassNotFoundException{
		super(name);
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
	
	public Class<?> getPropertyClass() throws ClassNotFoundException{
		return propertyClass;
	}
}
