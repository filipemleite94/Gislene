package AOM;

import COMM.IStorableObject;

public class Accountability implements IStorableObject {
	ITypePatternListener parent;
	ITypePatternListener child;
	String name;
	AccountabilityType accountType;
	Accountability reciprocal;
	
	public Accountability(AccountabilityType accountType, ITypePatternListener parent){
		this.parent = parent;
		this.accountType = accountType;
		this.name = accountType.name;
		child = null;
	}
	
	public String getName(){
		return name;
	}
	
	public ITypePatternListener getParent(){
		return parent;
	}
	
	public ITypePatternListener getChild(){
		return child;
	}
	
	public boolean setChild(ITypePatternListener child){
		if(accountType.validate(parent, child)){
			this.child = child;
			return true;
		}else{
			return false;
		}
	}
	
	public void erase(){
		child.loseChild(accountType, parent);
	}
	
	@Override
	public String toString(){
		return name + ": " + ((this.child==null)? "desconhecido":child.getName()); 
	}
}
