package AOM;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import COMM.IStorableObject;

@SuppressWarnings(value = { "unused" })
public class Type extends TypePatternContainer implements ITypePatternListener, IStorableObject {
	
	private HashMap<PropertyType, Property> properties;
	private HashMap<AccountabilityType, Accountability> accountabilities;
	
	private Category categoria;
	
	public Type(String name, Category categoria) {
		super(name);
		this.categoria = categoria;
		properties = new HashMap<PropertyType, Property>();
		accountabilities = new HashMap<AccountabilityType, Accountability>();
	}
	
	@Override
	public boolean addProperty(PropertyType pType) {
		if(properties.containsKey(pType)){
			return false;
		}
		properties.put(pType, new Property(pType));
		return true;
	}

	@Override
	public boolean setProperty(PropertyType pType, String value) {
		Property prop = properties.get(pType);
		return (prop==null)? false: prop.setValue(value);
	}

	@Override
	public boolean removeProperty(PropertyType pType) {
		return properties.remove(pType) != null;
	}

	@Override
	public boolean addAccountability(AccountabilityType aType) {
		if(accountabilities.containsKey(aType)){
			accountabilities.put(aType, new Accountability(aType, this));
			return false;
		}
		return true;
	}

	@Override
	public boolean setAccountabilityChild(AccountabilityType aType, ITypePatternListener child) {
		Accountability account = accountabilities.get(aType);
		return (account==null)? false:account.setChild(child);
	}

	@Override
	public boolean removeAccountability(AccountabilityType aType) {
		return accountabilities.get(aType)!=null;
	}
	
	@Override
	public boolean loseChild(AccountabilityType aType, ITypePatternListener lostChild){
		Accountability account = accountabilities.get(aType);
		return (account==null)? false: account.setChild(null);
	}

	@Override
	public boolean checkIfReciprocal(AccountabilityType accountabilityType) {
		return accountabilities.containsKey(accountabilityType);
	}

	@Override
	public void erase() {
		properties = null;
		Iterator<Accountability> iterator = accountabilities.values().iterator();
		while(iterator.hasNext()){
			iterator.next().erase();
			iterator.remove();
		}
		accountabilities = null;
	}
	
	@Override
	public HashSet<Property> getProperties() {
		return new HashSet<Property>(properties.values());
	}
	
	@Override
	public HashSet<Accountability> getAccountabilities() {
		return new HashSet<Accountability>(accountabilities.values());
	}
	
	@Override
	public void addProperty(Property property) {
		properties.put(property.getPropertyType(), property);
	}

	@Override
	public void addAccountability(Accountability accountability) {
		accountabilities.put(accountability.getAccountabilityType(), accountability);
	}
	
	@Override
	public TypePatternContainer getContainer() {
		return categoria;
	}
}
