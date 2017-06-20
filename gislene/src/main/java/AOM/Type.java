package AOM;

import java.io.IOException;

public class Type extends TypePatternContainer implements TypePatternListener {

	public Type(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addProperty(PropertyType pType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(PropertyType pType, Object value) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProperty(PropertyType pType) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAccountability(AccountabilityType aType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAccountabilityChild(AccountabilityType aType, TypePatternListener child) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAccountability(AccountabilityType aType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAsChild(Accountability account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeChildStatus(Accountability account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub
		
	}

}
