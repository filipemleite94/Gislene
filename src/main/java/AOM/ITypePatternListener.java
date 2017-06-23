package AOM;

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
	
	public void erase();
}
