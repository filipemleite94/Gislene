package AOM;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public enum eValidator {
	INSTANCE;
	
	private HashMap<Class<?>, Method> casterMap;
	
	private eValidator(){
		Method[] mets = this.getClass().getMethods();
		casterMap = new HashMap<Class<?>, Method>();
		for(Method met:mets){
			if(met.isAnnotationPresent(aCaster.class)){
				aCaster anot = met.getAnnotation(aCaster.class);
				casterMap.put(anot.targetClass(), met);
			}
		}
	}
	
	//Função standardizada pro erro
	private IllegalArgumentException getStandardError(String input, Class<?> cla){
		return new IllegalArgumentException("Can't convert " + input + " to class " + cla.getName());
	}
	
	public Object cast(String input, Class<?> inputClass) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method met = casterMap.get(inputClass);
		return met.invoke(this, input);
	}
	
	@aCaster(targetClass = java.lang.Boolean.class)
	public boolean casterBoolean(String input){
		if(input.equals("true")){
			return true;
		}else{
			if(input.equals("false")){
				return false;
			}
		}
		throw this.getStandardError(input, java.lang.Boolean.class);
	}
}
