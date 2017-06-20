package AOM;

public class AccountabilityType {
	private String typeName;
	
	public AccountabilityType(String typeName){
		this.typeName = typeName;
	}
	
	public String getTypeName(){
		return this.typeName;
	}
	
	public boolean validate(TypePatternListener parent, TypePatternListener child){
		//Ainda não implementado
		return true;
	}
}
