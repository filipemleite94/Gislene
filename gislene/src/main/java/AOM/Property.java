package AOM;

public class Property {
	private Object value;
	private final String name;
	private final Class<?> classe;
	private final PropertyType pType;
	
	public Property(PropertyType pType){
		this.pType = pType;
		classe = pType.getPropertyClass();
		value = pType.getValue("");
		name = pType.getName();
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
