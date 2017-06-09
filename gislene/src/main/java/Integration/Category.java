package Integration;

import java.util.ArrayList;

public abstract class Category {
	public ArrayList<Property> atributes;
	public ArrayList<PropertyType> atributesType;
	
	public Category(ArrayList<Property> atributes, ArrayList<PropertyType> atributesType){
		this.atributes = new ArrayList<Property>(atributes);
		this.atributesType = new ArrayList<PropertyType>(atributesType);
	}
	
	public ArrayList<Property> getAllAtributesCopy() {
		return new ArrayList<Property>(atributes);
	}
	
	public ArrayList<PropertyType> getAllAtributesTypeCopy(){
		return new ArrayList<PropertyType>(atributesType);
	}
}
