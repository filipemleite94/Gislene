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
	
	public void addPropertyType(PropertyType propertyType) throws IOException{
		tiposDePropriedades.add(propertyType);
		for(TypePatternListener pTypeListener:listenerCollection){
			pTypeListener.addProperty(propertyType);
		}
	}
	
	public void removePropertyType(PropertyType propertyType) throws IOException{
		if(!tiposDePropriedades.remove(propertyType)){
			throw new IOException("Propriedade nao encontrada");
		}
		for(TypePatternListener pTypeListener:listenerCollection){
			pTypeListener.removeProperty(propertyType);
		}
	}

	public void addAccountabilityType(AccountabilityType accountabilityType) throws IOException{
		tiposDeAccountability.add(accountabilityType);
		for(TypePatternListener pTypeListener:listenerCollection){
			pTypeListener.addAccountability(accountabilityType);
		}
	}
	
	public void removeAccountabilityType(AccountabilityType accountabilityType) throws IOException{
		if(!tiposDePropriedades.remove(accountabilityType)){
			throw new IOException("Accountability nao encontrada");
		}
		for(TypePatternListener typeListener:listenerCollection){
			typeListener.removeAccountability(accountabilityType);
		}
	}
	
	public void addListener(TypePatternListener typeListener) throws IOException{
		if(listenerCollection.contains(typeListener)){
			throw new IOException("Listener ja existe");
		}
		listenerCollection.add(typeListener);
		for(PropertyType pType:tiposDePropriedades){
			typeListener.addProperty(pType);
		}
		for(AccountabilityType aType:tiposDeAccountability){
			typeListener.addAccountability(aType);
		}
	}
	
	public void removeListener(TypePatternListener typeListener) throws IOException{
		if(!listenerCollection.remove(typeListener)){
			throw new IOException("Listener nao encontrado");
		}
		for(PropertyType pType:tiposDePropriedades){
			typeListener.removeProperty(pType);
		}
		for(AccountabilityType aType:tiposDeAccountability){
			typeListener.addAccountability(aType);
		}
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
		tiposDePropriedades.clear();
		tiposDeAccountability.clear();
	}
}
