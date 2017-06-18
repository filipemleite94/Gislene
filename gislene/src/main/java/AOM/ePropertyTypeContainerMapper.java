package AOM;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public enum ePropertyTypeContainerMapper {
	CategoryMapperInstance(Category.class), TypeMapperInstance(Type.class);
	
	private final Class<? extends PropertyTypeContainer> classe;
	
	private final HashMap<String, PropertyTypeContainer> map;
	private final HashMap<PropertyType, ArrayList<PropertyTypeContainer>> PropertyTypeContainerMap;
	private final ePropertyTypeMapper propTypeMap;
	
	private ePropertyTypeContainerMapper(Class<? extends PropertyTypeContainer> classe){
		map = new HashMap<String, PropertyTypeContainer>();
		propTypeMap = ePropertyTypeMapper.PropTypeMapperInstance;
		this.classe = classe;
		PropertyTypeContainerMap = new HashMap<PropertyType, ArrayList<PropertyTypeContainer>>();
	}
	
	public PropertyTypeContainer getContainer(String name) throws IOException{
		PropertyTypeContainer cont;
		cont = map.get(name);
		if(cont == null){
			throw new IOException("Inexistent key");
		}
		return cont;
	}
	
	private ArrayList<PropertyTypeContainer> getPropertyTypeContainerCol(PropertyType pType){
		ArrayList<PropertyTypeContainer> pTypeContCol;
		pTypeContCol = PropertyTypeContainerMap.get(pType);
		if(pTypeContCol == null){
			pTypeContCol = new ArrayList<PropertyTypeContainer>();
			PropertyTypeContainerMap.put(pType, pTypeContCol);
		}
		return pTypeContCol;
	}
	
	public void addContainer(String name) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		if(map.get(name) != null){
			throw new IOException("Duplicate key");
		}
		map.put(name, this.classe.getConstructor(String.class).newInstance(name));
		return;
	}
	
	public void addPropertyType(String contKey, String propTypeKey) throws IOException{
		PropertyTypeContainer cont;
		PropertyType pType;
		ArrayList<PropertyTypeContainer> pTypeConCol;
		cont = getContainer(contKey);
		pType = propTypeMap.getPropertyType(propTypeKey);
		cont.addPropertyType(pType);
		pTypeConCol = getPropertyTypeContainerCol(pType);
		pTypeConCol.add(cont);
	}
	
	public void removePropertyType(String contKey, String propTypeKey) throws IOException{
		PropertyTypeContainer cont;
		PropertyType pType;
		ArrayList<PropertyTypeContainer> pTypeConCol;
		cont = getContainer(contKey);
		pType = propTypeMap.getPropertyType(propTypeKey);
		cont.removePropertyType(pType);
		pTypeConCol = getPropertyTypeContainerCol(pType);
		if(!pTypeConCol.remove(cont)){
			throw new IOException("Propriedade não estava prevista.");
		}
	}
	
	public void removeContainer(String contKey) throws IOException{
		PropertyTypeContainer cont;
		//Pegar todas as properties dele e operar a partir daqui.
		cont = getContainer(contKey);
		map.remove(contKey);
	}
}
