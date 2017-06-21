package AOM;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public abstract class TypePatternAbstract {
	protected String name;
	protected HashSet<TypePatternContainer> listenerContainer;
	protected Class<?> classe;
	
	public TypePatternAbstract(String name){
		this.name = name;
		this.classe = this.getClass();
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
