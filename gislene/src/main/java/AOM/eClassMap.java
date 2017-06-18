package AOM;

import java.util.HashMap;

public enum eClassMap {
	INSTANCE;
	
	private final HashMap<String, Class<?>> classMap;
	
	public String booleanName = "boolean";
	public String charName = "char";
	public String intName = "int";
	public String doubleName = "real";
	public String stringName = "string";
	public String geoName = "geo";
	
	public final Class<?> booleanClass = java.lang.Boolean.class;
	public final Class<?> charClass = java.lang.Character.class;
	public final Class<?> intClass = java.lang.Integer.class;
	public final Class<?> doubleClass = java.lang.Double.class;
	public final Class<?> stringClass = java.lang.String.class;
	public final Class<?> geoClass = Geo.class;
	
	private eClassMap(){
		classMap = new HashMap<String, Class<?>>();
		classMap.put(booleanName, booleanClass);
		classMap.put(charName, charClass);
		classMap.put(intName, intClass);
		classMap.put(doubleName, doubleClass);
		classMap.put(stringName, stringClass);
		classMap.put(geoName, geoClass);
	}
	
	public Class<?> getClassGenerico(String className) throws ClassNotFoundException{
		Class<?> objectClass = classMap.get(className);
		if(objectClass == null){
			throw new ClassNotFoundException("A classe " + className + " nao eh prevista");
		}
		return objectClass;
	}

}
