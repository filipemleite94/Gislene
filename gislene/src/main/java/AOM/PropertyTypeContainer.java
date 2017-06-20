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
	
	public void addPropertyType(PropertyType propertyType) throws IOException{
		tiposDePropriedades.add(propertyType);
		for(PropertyTypeListener pTypeListener:listenerCollection){
			pTypeListener.addProperty(propertyType);
		}
	}
	
	public void removePropertyType(PropertyType propertyType) throws IOException{
		if(!tiposDePropriedades.remove(propertyType)){
			throw new IOException("Propriedade nao encontrada");
		}
		for(PropertyTypeListener pTypeListener:listenerCollection){
			pTypeListener.removeProperty(propertyType);
		}
	}
	
	public void addListener(PropertyTypeListener pTypeListener){
		listenerCollection.add(pTypeListener);
		for(PropertyType pType:tiposDePropriedades){
			pTypeListener.addProperty(pType);
		}
	}
	
	public void removeListener(PropertyTypeListener pTypeListener) throws IOException{
		if(!listenerCollection.remove(pTypeListener)){
			throw new IOException("Listener nao encontrado");
		}
		for(PropertyType pType:tiposDePropriedades){
			pTypeListener.removeProperty(pType);
		}
	}
	
	public ArrayList<PropertyType> getPropertiesType(){
		return new ArrayList<PropertyType>(tiposDePropriedades);
	}
	
	public ArrayList<PropertyTypeListener> getPropertyTypeListeners(){
		return new ArrayList<PropertyTypeListener>(listenerCollection);
	}
	
	public void deleteContainer(){
		while(listenerCollection.size()!=0){
			listenerCollection.get(0).erase();
			listenerCollection.remove(0);
		}
		tiposDePropriedades.clear();
	}
}
