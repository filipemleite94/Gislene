package AOMTests;
import AOM.*;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
public class ValidatorTests {
	eValidator validator = eValidator.INSTANCE;
	
	
	@Test
	public void booleanAcceptanceTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		assertEquals(false, validator.cast("false",  eClassMap.INSTANCE.booleanClass));
		assertEquals(true, validator.cast("true",  eClassMap.INSTANCE.booleanClass));
	}
	
	@Test
	public void booleanRejectTest() throws IllegalAccessException, IllegalArgumentException{
		try{
			validator.cast("abc", eClassMap.INSTANCE.booleanClass);
		}catch(InvocationTargetException e){
			assertEquals("Can't convert abc to "+eClassMap.INSTANCE.booleanClass, e.getCause().getMessage().toString());
		}
	}
}
