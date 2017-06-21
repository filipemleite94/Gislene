package AOM;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public abstract class TypePatternContainer {
	protected final String name;
	protected final HashSet<PropertyType> tiposDePropriedades;
	protected final HashSet<AccountabilityType> tiposDeAccountability;
	protected HashSet<TypePatternListener> listenerCollection;
	
	public TypePatternContainer(String name){
		this.name = name;
		tiposDePropriedades = new HashSet<PropertyType>();
		tiposDeAccountability = new HashSet<AccountabilityType>();
		listenerCollection = new HashSet<TypePatternListener>();	
	}
	
	public String getName(){
		return name;
	}
	
	public boolean addPropertyType(PropertyType propertyType){
		if(tiposDePropriedades.add(propertyType)){
			for(TypePatternListener pTypeListener:listenerCollection){
				pTypeListener.addProperty(propertyType);
			}
			propertyType.addContainer(this);
			return true;
		}
		return false;
	}
	
	public boolean removePropertyType(PropertyType propertyType){
		if(tiposDePropriedades.remove(propertyType)){		
			for(TypePatternListener pTypeListener:listenerCollection){
				pTypeListener.removeProperty(propertyType);
			}
			return true;
		}
		return false;
	}

	public boolean addAccountabilityType(AccountabilityType accountabilityType){
		if(tiposDeAccountability.add(accountabilityType)){
			for(TypePatternListener pTypeListener:listenerCollection){
				pTypeListener.addAccountability(accountabilityType);
			}
			accountabilityType.addContainer(this);
			return true;
		}
		return false;
	}
	
	public boolean removeAccountabilityType(AccountabilityType accountabilityType){
		if(tiposDeAccountability.remove(accountabilityType)){
			for(TypePatternListener typeListener:listenerCollection){
				typeListener.removeAccountability(accountabilityType);
			}
			return true;
		}
		return false;
	}
	
	public boolean addListener(TypePatternListener typeListener){
		if(listenerCollection.add(typeListener)){
			for(PropertyType pType:tiposDePropriedades){
				typeListener.addProperty(pType);
			}
			for(AccountabilityType aType:tiposDeAccountability){
				typeListener.addAccountability(aType);
			}
			return true;
		}
		return false;
	}
	
	public boolean removeListener(TypePatternListener typeListener){
		if(listenerCollection.remove(typeListener)){
			for(PropertyType pType:tiposDePropriedades){
				typeListener.removeProperty(pType);
			}
			for(AccountabilityType aType:tiposDeAccountability){
				typeListener.removeAccountability(aType);
			}
			return true;
		}
		return false;
	}
	
	public HashSet<PropertyType> getPropertyTypes(){
		return new HashSet<PropertyType>(tiposDePropriedades);
	}
	
	public HashSet<AccountabilityType> getAccountabilityTypes(){
		return new HashSet<AccountabilityType>(tiposDeAccountability);
	}
	
	public HashSet<TypePatternListener> getTypeListeners(){
		return new HashSet<TypePatternListener>(listenerCollection);
	}
	
	public void deleteContainer(){
		Iterator<TypePatternListener> iterator = listenerCollection.iterator();
		while(iterator.hasNext()){
			iterator.next().erase();
			iterator.remove();
		}
		
		Iterator<PropertyType> propIterator = tiposDePropriedades.iterator();
		while(propIterator.hasNext()){
			propIterator.next().removeContainer(this);
			propIterator.remove();
		}
		
		Iterator<AccountabilityType> accountType = tiposDeAccountability.iterator();
		while(accountType.hasNext()){
			accountType.next().removeContainer(this);
			accountType.remove();
		}
		
		tiposDePropriedades.clear();
		tiposDeAccountability.clear();
	}
}
