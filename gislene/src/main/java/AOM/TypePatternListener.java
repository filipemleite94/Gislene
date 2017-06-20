package AOM;

import java.io.IOException;

public interface TypePatternListener {
	//Property receberá um valor default;
	public void addProperty(PropertyType pType);
	public void setProperty(PropertyType pType, Object value) throws IOException;
	public void removeProperty(PropertyType pType) throws IOException;
	
	public void addAccountability(AccountabilityType aType);
	//public void setAccountabilityParent(AccountabilityType aType, TypePatternListener parent);
	public void setAccountabilityChild(AccountabilityType aType, TypePatternListener child);
	public void removeAccountability(AccountabilityType aType);
	
	public void setAsChild(Accountability account);
	public void removeChildStatus(Accountability account);
	
	public void erase();
}
