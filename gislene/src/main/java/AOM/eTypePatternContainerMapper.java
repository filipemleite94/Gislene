package AOM;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public enum eTypePatternContainerMapper {
	CategoryMapperInstance(Category.class), TypeMapperInstance(Type.class);
	
	private final Class<? extends TypePatternContainer> classe;
	
	private HashMap<String, TypePatternContainer> map;
	private eTypePatternMapper typePatMap = eTypePatternMapper.TypePatternMapperInstance;
	
	private eTypePatternContainerMapper(Class<? extends TypePatternContainer> classe){
		map = new HashMap<String, TypePatternContainer>();
		this.classe = classe;
	}
	
	public void  cleanMap(){
		map = new HashMap<String, TypePatternContainer>();
		typePatMap = eTypePatternMapper.TypePatternMapperInstance;
	}
	
	public TypePatternContainer getContainer(String name) throws IOException{
		TypePatternContainer cont;
		cont = map.get(name);
		if(cont == null){
			throw new IOException("Inexistent Container");
		}
		return cont;
	}
		
	public void addContainer(String name) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(map.get(name) != null){
			throw new IOException("Duplicate key");
		}
		map.put(name, this.classe.getConstructor(String.class).newInstance(name));
		return;
	}
	
	public boolean addPropertyType(String contKey, String propTypeKey) throws IOException{
		TypePatternContainer cont;
		PropertyType pType;
		cont = getContainer(contKey);
		pType = typePatMap.getPropertyType(propTypeKey);
		return cont.addPropertyType(pType);
	}
	
	public void addAccountabilityType(String contKey, String accountTypeKey) throws IOException{
		TypePatternContainer cont;
		AccountabilityType aType;
		cont = getContainer(contKey);
		aType = typePatMap.getAccountabilityType(accountTypeKey);
		cont.addAccountabilityType(aType);
	}
	
	public boolean removePropertyTypeFromContainer(String contKey, String propTypeKey) throws IOException{
		TypePatternContainer cont;
		PropertyType pType;
		cont = getContainer(contKey);
		pType = typePatMap.getPropertyType(propTypeKey);
		return cont.removePropertyType(pType);
	}
	
	public boolean removeAccountabilityTypeFromContainer(String contKey, String accountTypeKey) throws IOException{
		TypePatternContainer cont;
		AccountabilityType aType;
		cont = getContainer(contKey);
		aType = typePatMap.getAccountabilityType(accountTypeKey);
		return cont.removeAccountabilityType(aType);		
	}
	
	public void removeContainer(String contKey) throws IOException{
		TypePatternContainer cont;
		cont = getContainer(contKey);
		cont.deleteContainer();
		map.remove(contKey);
	}
}
