package AOM;

import java.util.HashSet;

public interface ITypePatternListener {
	public String getName();
	
	//Property receberá um valor default;
	public boolean addProperty(PropertyType pType);
	public boolean setProperty(PropertyType pType, String value);
	public boolean removeProperty(PropertyType pType);
	
	public boolean addAccountability(AccountabilityType aType);
	//public void setAccountabilityParent(AccountabilityType aType, TypePatternListener parent);
	public boolean setAccountabilityChild(AccountabilityType aType, ITypePatternListener child);
	public boolean removeAccountability(AccountabilityType aType);
	public boolean loseChild(AccountabilityType aType, ITypePatternListener lostChild);
	
	public boolean checkIfReciprocal(AccountabilityType accountabilityType);
	
	public HashSet<Property> getProperties();
	public HashSet<Accountability> getAccountabilities();
	public void addProperty(Property property);
	public void addAccountability(Accountability accountability);
	public TypePatternContainer getContainer();
	
	public void erase();
}
