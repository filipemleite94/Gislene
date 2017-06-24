package AOM;

import COMM.IStorableObject;

public class AccountabilityType extends TypePatternAbstract implements IStorableObject{
	private AccountabilityType reciprocal;
	
	public AccountabilityType(String name){
		super(name);
		reciprocal = this;
		super.classe = this.getClass();
	}
	
	public AccountabilityType getReciprocal(){
		return reciprocal;
	}
	
	public void setReciprocal(AccountabilityType accountType){
		this.reciprocal = accountType;
	}
	
	public boolean validate(ITypePatternListener parent, ITypePatternListener child){
		return child.checkIfReciprocal(this);
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof AccountabilityType)||obj==null){
			return false;
		}
		else{
			return name.equals(((AccountabilityType) obj).getName());
		}
	}
	
	@Override
	public int hashCode(){
		return name.hashCode();
		
	}
}
