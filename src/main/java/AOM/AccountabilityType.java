package AOM;

import COMM.IStorableObject;

public class AccountabilityType extends TypePatternAbstract implements IStorableObject{
	private AccountabilityType reciprocal;
	
	public AccountabilityType(String nameVariable){
		super(nameVariable);
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
}
