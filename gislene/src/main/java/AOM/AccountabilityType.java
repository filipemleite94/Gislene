package AOM;

public class AccountabilityType extends TypePatternAbstract{
	private AccountabilityType reciprocal;
	
	public AccountabilityType(String name){
		super(name);
		reciprocal = this;
	}
	
	public AccountabilityType getReciprocal(){
		return reciprocal;
	}
	
	public void setReciprocal(AccountabilityType accountType){
		this.reciprocal = accountType;
	}
	
	public boolean validate(TypePatternListener parent, TypePatternListener child){
		return child.checkIfReciprocal(this);
	}
}
