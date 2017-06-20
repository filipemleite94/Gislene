package AOM;

import java.io.IOException;

public class Accountability {
	TypePatternListener parent;
	TypePatternListener child;
	AccountabilityType accountType;
	
	public Accountability(AccountabilityType accountType, TypePatternListener parent){
		this.parent = parent;
		this.accountType = accountType;
		child = null;
	}
	
	public void setChild(TypePatternListener child) throws IOException{
		if(accountType.validate(parent, parent)){
			this.child = child;
		}
		throw new IOException("Impossível de fazer a Accountability");
	}
}
