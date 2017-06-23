package AOM;

import java.util.HashSet;
import java.util.Iterator;

public abstract class TypePatternContainer {
	protected String name;
	protected HashSet<PropertyType> tiposDePropriedades;
	protected HashSet<AccountabilityType> tiposDeAccountability;
	protected HashSet<ITypePatternListener> listenerCollection;
	
	public TypePatternContainer(String name){
		this.name = name;
		tiposDePropriedades = new HashSet<PropertyType>();
		tiposDeAccountability = new HashSet<AccountabilityType>();
		listenerCollection = new HashSet<ITypePatternListener>();	
	}
	
	public String getName(){
		return name;
	}
	
	public boolean addPropertyType(PropertyType propertyType){
		if(tiposDePropriedades.add(propertyType)){
			for(ITypePatternListener pTypeListener:listenerCollection){
				pTypeListener.addProperty(propertyType);
			}
			propertyType.addContainer(this);
			return true;
		}
		return false;
	}
	
	public boolean removePropertyType(PropertyType propertyType){
		if(tiposDePropriedades.remove(propertyType)){		
			for(ITypePatternListener pTypeListener:listenerCollection){
				pTypeListener.removeProperty(propertyType);
			}
			return true;
		}
		return false;
	}

	public boolean addAccountabilityType(AccountabilityType accountabilityType){
		if(tiposDeAccountability.add(accountabilityType)){
			for(ITypePatternListener pTypeListener:listenerCollection){
				pTypeListener.addAccountability(accountabilityType);
			}
			accountabilityType.addContainer(this);
			return true;
		}
		return false;
	}
	
	public boolean removeAccountabilityType(AccountabilityType accountabilityType){
		if(tiposDeAccountability.remove(accountabilityType)){
			for(ITypePatternListener typeListener:listenerCollection){
				typeListener.removeAccountability(accountabilityType);
			}
			return true;
		}
		return false;
	}
	
	public boolean addListener(ITypePatternListener typeListener){
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
	
	public boolean removeListener(ITypePatternListener typeListener){
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
	
	public HashSet<ITypePatternListener> getTypeListeners(){
		return new HashSet<ITypePatternListener>(listenerCollection);
	}
	
	public void deleteContainer(){
		Iterator<ITypePatternListener> iterator = listenerCollection.iterator();
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
