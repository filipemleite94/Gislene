package AOM;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public enum eValidator {
	INSTANCE;
	
	private HashMap<Class<?>, Method> casterMap;
	private HashMap<Class<?>, Method> defaultCasterMap;
	
	private eValidator(){
		aCaster casterAnot;
		aDefaultCaster defaultCasterAnot;
		
		Method[] mets = this.getClass().getMethods();
		
		casterMap = new HashMap<Class<?>, Method>();
		defaultCasterMap = new HashMap<Class<?>, Method>();
		
		for(Method met:mets){
			if(met.isAnnotationPresent(aCaster.class)){
				casterAnot = met.getAnnotation(aCaster.class);
				casterMap.put(casterAnot.targetClass(), met);
			}else{
				if(met.isAnnotationPresent(aDefaultCaster.class)){
					defaultCasterAnot = met.getAnnotation(aDefaultCaster.class);
					defaultCasterMap.put(defaultCasterAnot.targetClass(), met);
				}
			}
		}
	}
	
	//Função standardizada pro erro
	private IllegalArgumentException getStandardError(String input, Class<?> cla){
		return new IllegalArgumentException("Can't convert " + input + " to class " + cla.getName());
	}
	
	public Object cast(String input, Class<?> inputClass) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method met; 
		if(input == ""){
			met  = defaultCasterMap.get(inputClass);
			return met.invoke(this);
		}
		met = casterMap.get(inputClass);
		return met.invoke(this, input);
	}
	
//--------------------------------Implementações Dos Casters--------------
	
	@aCaster(targetClass = java.lang.Boolean.class)
	public java.lang.Boolean casterBoolean(String input){
		if(input.equals("true")){
			return true;
		}else{
			if(input.equals("false")){
				return false;
			}
		}
		throw this.getStandardError(input, java.lang.Boolean.class);
	}
	
	@aDefaultCaster(targetClass = java.lang.Boolean.class)
	public java.lang.Boolean defaultCasterBoolean(){
		return false;
	}
	
	@aCaster(targetClass = java.lang.Character.class)
	public java.lang.Character casterCharacter(String input){
		if(input.length() == 1){
			return input.charAt(0);
		}
		throw this.getStandardError(input, java.lang.Character.class);
	}
	
	@aDefaultCaster(targetClass = java.lang.Character.class)
	public java.lang.Character defaultCasterCharacter(){
		return ' ';
	}
	
	@aCaster(targetClass = java.lang.Integer.class)
	public java.lang.Integer casterInteger(String input){
		try{
			return Integer.parseInt(input);
		}catch (NumberFormatException e){
			throw this.getStandardError(input, java.lang.Integer.class);
		}
	}
	
	@aDefaultCaster(targetClass = java.lang.Integer.class)
	public java.lang.Integer defaultCasterInteger(){
		return 0;
	}
	
	@aCaster(targetClass = java.lang.Double.class)
	public java.lang.Double casterDouble(String input){
		try{
			return Double.parseDouble(input);
		}catch(Exception e){
			throw this.getStandardError(input, java.lang.Double.class);
		}
	}
	
	@aDefaultCaster(targetClass = java.lang.Double.class)
	public java.lang.Double defaultCasterDouble(){
		return 0.0;
	}
	
	@aCaster(targetClass = java.lang.String.class)
	public java.lang.String casterString(String input){
		return input;
	}
	
	@aDefaultCaster(targetClass = java.lang.String.class)
	public java.lang.String defaultCasterString(){
		return "";
	}
	
	@aCaster(targetClass = Geo.class)
	public Geo casterGeo(String input){
		try{
			return new Geo(input);
		}catch(IOException e){
			throw this.getStandardError(input, Geo.class);
		}
	}
	
	@aDefaultCaster(targetClass = Geo.class)
	public Geo defaultCasterGeo(){
		return new Geo();
	}
	
	
}
