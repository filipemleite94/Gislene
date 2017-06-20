package AOM;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;

public enum eTypePatternContainerMapper {
	CategoryMapperInstance(Category.class), TypeMapperInstance(Type.class);
	
	private final Class<? extends TypePatternContainer> classe;
	
	private HashMap<String, TypePatternContainer> map;
	private HashMap<PropertyType, HashSet<TypePatternContainer>> propertyTypeContainerMap;
	private HashMap<AccountabilityType, HashSet<TypePatternContainer>> accountabilityTypeContainerMap;
	private eTypePatternMapper typePatMap;
	
	private eTypePatternContainerMapper(Class<? extends TypePatternContainer> classe){
		map = new HashMap<String, TypePatternContainer>();
		typePatMap = eTypePatternMapper.TypePatternMapperInstance;
		this.classe = classe;
		propertyTypeContainerMap = new HashMap<PropertyType, HashSet<TypePatternContainer>>();
		accountabilityTypeContainerMap = new HashMap<AccountabilityType, HashSet<TypePatternContainer>>();
	}
	
	public TypePatternContainer getContainer(String name) throws IOException{
		TypePatternContainer cont;
		cont = map.get(name);
		if(cont == null){
			throw new IOException("Inexistent Container");
		}
		return cont;
	}
	
	private HashSet<TypePatternContainer> getPropertyTypeContainerCol(PropertyType pType){
		HashSet<TypePatternContainer> pTypeContCol;
		pTypeContCol = propertyTypeContainerMap.get(pType);
		if(pTypeContCol == null){
			pTypeContCol = new HashSet<TypePatternContainer>();
			propertyTypeContainerMap.put(pType, pTypeContCol);
		}
		return pTypeContCol;
	}
	
	private HashSet<TypePatternContainer> getAccountabilityTypeContainerCol(AccountabilityType aType){
		HashSet<TypePatternContainer> aTypeContCol;
		aTypeContCol = accountabilityTypeContainerMap.get(aType);
		if(aTypeContCol == null){
			aTypeContCol = new HashSet<TypePatternContainer>();
			accountabilityTypeContainerMap.put(aType, aTypeContCol);
		}
		return aTypeContCol;
	}
	
	public void addContainer(String name) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		if(map.get(name) != null){
			throw new IOException("Duplicate key");
		}
		map.put(name, this.classe.getConstructor(String.class).newInstance(name));
		return;
	}
	
	public void addPropertyType(String contKey, String propTypeKey) throws IOException{
		TypePatternContainer cont;
		PropertyType pType;
		HashSet<TypePatternContainer> pTypeConCol;
		cont = getContainer(contKey);
		pType = typePatMap.getPropertyType(propTypeKey);
		cont.addPropertyType(pType);
		pTypeConCol = getPropertyTypeContainerCol(pType);
		pTypeConCol.add(cont);
	}
	
	public void addAccountabilityType(String contKey, String accountTypeKey) throws IOException{
		TypePatternContainer cont;
		AccountabilityType aType;
		HashSet<TypePatternContainer> aTypeConCol;
		cont = getContainer(contKey);	
		aType = typePatMap.getAccountabilityType(accountTypeKey);
		cont.addAccountabilityType(aType);
		aTypeConCol = getAccountabilityTypeContainerCol(aType);
		aTypeConCol.add(cont);
	}
	
	public void removePropertyTypeFromContainer(String contKey, String propTypeKey) throws IOException{
		TypePatternContainer cont;
		PropertyType pType;
		HashSet<TypePatternContainer> pTypeConCol;
		cont = getContainer(contKey);
		pType = typePatMap.getPropertyType(propTypeKey);
		cont.removePropertyType(pType);
		pTypeConCol = getPropertyTypeContainerCol(pType);
		if(!pTypeConCol.remove(cont)){
			throw new IOException("Propriedade não estava prevista pra esse container");
		}
	}
	
	public void removeAccountabilityTypeFromContainer(String contKey, String propTypeKey) throws IOException{
		
	}
	
	public void removePropertyTypeFromAll(PropertyType pType) throws IOException{
		HashSet<TypePatternContainer> pTypeConCol = propertyTypeContainerMap.get(pType);
		if(pTypeConCol == null){
			return;
		}
		for(TypePatternContainer propCon : pTypeConCol){
			propCon.removePropertyType(pType);
		}
		pTypeConCol.clear();
		propertyTypeContainerMap.remove(pType);
	}
	
	public void removeAccountabilityTypeFromAll(AccountabilityType aType) throws IOException {
		HashSet<TypePatternContainer> aTypeConCol = propertyTypeContainerMap.get(aType);
		if(aTypeConCol == null){
			return;
		}
		for(TypePatternContainer accountCon:aTypeConCol){
			accountCon.removeAccountabilityType(aType);
		}
		aTypeConCol.clear();
		accountabilityTypeContainerMap.remove(aType);
	}
	
	public void removeContainer(String contKey) throws IOException{
		TypePatternContainer cont;
		cont = getContainer(contKey);
		cont.deleteContainer();
		map.remove(contKey);
	}
}
