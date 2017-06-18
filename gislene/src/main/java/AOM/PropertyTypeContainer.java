package AOM;

import java.io.IOException;
import java.util.ArrayList;

public abstract class PropertyTypeContainer {
	protected final String name;
	protected final ArrayList<PropertyType> tiposDePropriedades;
	protected ArrayList<PropertyTypeListener> listenerCollection;
	
	public PropertyTypeContainer(String name){
		this.name = name;
		tiposDePropriedades = new ArrayList<PropertyType>();
		listenerCollection = new ArrayList<PropertyTypeListener>();
	}
	
	public String getName(){
		return name;
	}
	
	public void addPropertyType(PropertyType propertyType){
		tiposDePropriedades.add(propertyType);
	}
	
	public void removePropertyType(PropertyType propertyType) throws IOException{
		if(!tiposDePropriedades.remove(propertyType)){
			throw new IOException("Propriedade não encontrada");
		}
	}
	
	public void deleteContainer(){
		while(listenerCollection.size()!=0){
			listenerCollection.get(0).erase();
			listenerCollection.remove(0);
		}
	}
}
