package AOM;

public class Property {
	private Object value;
	private Class<?> classes;
	private final PropertyType pType;
	
	public Property(PropertyType pType) throws ClassNotFoundException{
		this.pType = pType;
		//classes = Class.forName(this.pType.getType());
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
