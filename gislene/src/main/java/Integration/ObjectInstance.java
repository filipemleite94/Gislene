package Integration;

import java.util.ArrayList;

public abstract class ObjectInstance {
	private ArrayList<Property> atributes;
	private Type aboveType;
	
	public ObjectInstance(Type Type, ArrayList<String> values){
		int i, size;
		ArrayList<PropertyType> propType;
		
		this.aboveType = Type;
		atributes = new ArrayList<Property>();
		propType = Type.getAllAtributesType();
		
		size = values.size();
		for(i = 0; i<size; i++){
			atributes.add(new Property(values.get(i), propType.get(i)));
		}
	}
	
	public ArrayList<Property> getAllAtributesCopy(){
		ArrayList<Property> atributesCopy;
		atributesCopy = new ArrayList<Property>(atributes);
		atributesCopy.addAll(aboveType.getAllAtributesCopy());
		return atributesCopy;
	}
}
