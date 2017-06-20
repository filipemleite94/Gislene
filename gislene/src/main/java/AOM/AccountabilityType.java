package AOM;

import java.util.HashSet;

public class AccountabilityType extends TypePatternAbstract{
	private AccountabilityType reciprocal;
	
	public AccountabilityType(String name){
		super(name);
		reciprocal = this;
	}
	
	public void setReciprocal(AccountabilityType accountType){
		this.reciprocal = accountType;
	}
	
	public boolean validate(TypePatternListener parent, TypePatternListener child){
		//Ainda não implementado
		return true;
	}
}
