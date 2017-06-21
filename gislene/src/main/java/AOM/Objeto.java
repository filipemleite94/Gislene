package AOM;

import java.io.IOException;

public class Objeto implements TypePatternListener {

	@Override
	public void addProperty(PropertyType pType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setProperty(PropertyType pType, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeProperty(PropertyType pType) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean checkIfReciprocal(AccountabilityType accountabilityType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub
		
	}
}
