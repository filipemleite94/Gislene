package Integration;

import java.util.ArrayList;

public abstract class Type {
	private ArrayList<Property> atributes;
	private ArrayList<PropertyType> atributesType;
	private Category aboveCategory;
	
	public Type(Category category, ArrayList<String> values, ArrayList<PropertyType>atributesType){
		int i, size;
		ArrayList<PropertyType> aboveCategoryType;
		
		aboveCategory = category;
		this.atributesType = new ArrayList<PropertyType>(atributesType);
		aboveCategoryType = aboveCategory.getAllAtributesTypeCopy();
		size = aboveCategoryType.size();
		atributes = new ArrayList<Property>();
		
		for(i = 0; i<size; i++){
			atributes.add(new Property(values.get(i), aboveCategoryType.get(i)));
		}
	}
	
	public ArrayList<Property> getAllAtributesCopy(){
		ArrayList<Property> atributesCopy;
		atributesCopy = new ArrayList<Property>(atributes);
		atributesCopy.addAll(aboveCategory.getAllAtributesCopy());
		return atributesCopy;
	}
	
	public ArrayList<PropertyType> getAllAtributesType(){
		return new ArrayList<PropertyType>(atributesType);
	}
}
