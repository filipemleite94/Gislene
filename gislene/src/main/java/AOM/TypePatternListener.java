package AOM;

import java.io.IOException;

public interface TypePatternListener {
	public String getName();
	
	//Property receberá um valor default;
	public void addProperty(PropertyType pType);
	public boolean setProperty(PropertyType pType, Object value);
	public boolean removeProperty(PropertyType pType);
	
	public void addAccountability(AccountabilityType aType);
	//public void setAccountabilityParent(AccountabilityType aType, TypePatternListener parent);
	public void setAccountabilityChild(AccountabilityType aType, TypePatternListener child);
	public void removeAccountability(AccountabilityType aType);
	
	public boolean checkIfReciprocal(AccountabilityType accountabilityType);
	
	public void erase();
}
