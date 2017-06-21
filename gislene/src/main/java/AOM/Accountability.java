package AOM;

import java.io.IOException;

public class Accountability {
	TypePatternListener parent;
	TypePatternListener child;
	String name;
	AccountabilityType accountType;
	
	public Accountability(AccountabilityType accountType, TypePatternListener parent){
		this.parent = parent;
		this.accountType = accountType;
		this.name = accountType.name;
		child = null;
	}
	
	public String getName(){
		return name;
	}
	
	public TypePatternListener getParent(){
		return parent;
	}
	
	public TypePatternListener getChild(){
		return child;
	}
	
	public boolean setChild(TypePatternListener child) throws IOException{
		if(accountType.validate(parent, child)){
			this.child = child;
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString(){
		return name + ": " + ((this.child==null)? "desconhecido":child.getName()); 
	}
}
