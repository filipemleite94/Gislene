package AOM;

import java.util.HashSet;
import java.util.Iterator;

public abstract class TypePatternAbstract {
	
	protected String nameVariable;
	protected HashSet<TypePatternContainer> listenerContainer;
	Class<?> classe;
	
	public TypePatternAbstract(String nameVariable){
		this.nameVariable = nameVariable;
		listenerContainer = new HashSet<TypePatternContainer>();
	}
	
	public String getNameVariable(){
		return this.nameVariable;
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
		if(classe==PropertyType.class){
			while(iterator.hasNext()){
				iterator.next().removePropertyType((PropertyType)this);
			}		
		}else{
			if(classe == AccountabilityType.class){
				while(iterator.hasNext()){
					iterator.next().removeAccountabilityType((AccountabilityType)this);
				}	
			}
		}
		listenerContainer.clear();
		return;
	}
}
