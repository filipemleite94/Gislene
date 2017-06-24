package AOM;

import java.util.HashSet;
import java.util.Iterator;

public abstract class TypePatternAbstract {
	protected final String name;
	protected HashSet<TypePatternContainer> listenerContainer;
	protected Class<?> classe;
	
	public TypePatternAbstract(String name){
		this.name = name;
		listenerContainer = new HashSet<TypePatternContainer>();
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean addContainer(TypePatternContainer container){
		if(listenerContainer.add(container)){
			return true;
		}
		return false;
	}
	
	public boolean removeContainer(TypePatternContainer container){
		if(listenerContainer.remove(container)){
			return true;
		}
		return false;
	}
	
	public void deleteType(){
		Iterator<TypePatternContainer> iterator = listenerContainer.iterator();
		if(this.getClass()==PropertyType.class){
			while(iterator.hasNext()){
				iterator.next().removePropertyType((PropertyType)this);
			}		
		}else{
			if(this.getClass() == AccountabilityType.class){
				while(iterator.hasNext()){
					iterator.next().removeAccountabilityType((AccountabilityType)this);
				}	
			}
		}
		listenerContainer.clear();
		return;
	}
}
