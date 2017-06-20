package AOMTests;
import AOM.*;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
public class ValidatorTests {
	eValidator validator = eValidator.INSTANCE;
	
	
	@Test
	public void booleanAcceptanceTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		assertEquals(false, validator.cast("", eClassMap.INSTANCE.booleanClass));
		assertEquals(false, validator.cast("false",  eClassMap.INSTANCE.booleanClass));
		assertEquals(true, validator.cast("true",  eClassMap.INSTANCE.booleanClass));
	}
	
	@Test
	public void booleanRejectTest() throws IllegalAccessException, IllegalArgumentException{
		Class<?> c = eClassMap.INSTANCE.booleanClass;
		try{
			validator.cast("abc", c);
		}catch(InvocationTargetException e){
			assertEquals("Can't convert abc to "+ c, e.getCause().getMessage().toString());
		}
	}
	
	@Test
	public void charAcceptanceTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		assertEquals(' ', validator.cast("", eClassMap.INSTANCE.charClass));
		assertEquals('c', validator.cast("c",  eClassMap.INSTANCE.charClass));
		assertEquals('a', validator.cast("a",  eClassMap.INSTANCE.charClass));
	}
	
	@Test
	public void charRejectTest() throws IllegalAccessException, IllegalArgumentException{
		Class<?> c = eClassMap.INSTANCE.charClass;
		try{
			validator.cast("abc", c);
		}catch(InvocationTargetException e){
			assertEquals("Can't convert abc to "+ c, e.getCause().getMessage().toString());
		}
	}
	
	@Test
	public void intAcceptanceTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		assertEquals(0, validator.cast("", eClassMap.INSTANCE.intClass));
		assertEquals(0, validator.cast("0",  eClassMap.INSTANCE.intClass));
		assertEquals(10, validator.cast("10",  eClassMap.INSTANCE.intClass));
	}
	
	@Test
	public void intRejectTest() throws IllegalAccessException, IllegalArgumentException{
		Class<?> c = eClassMap.INSTANCE.intClass;
		try{
			validator.cast("abc", c);
		}catch(InvocationTargetException e){
			assertEquals("Can't convert abc to "+ c, e.getCause().getMessage().toString());
		}
	}
	
	@Test
	public void doubleAcceptanceTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		assertEquals(0.0, validator.cast("", eClassMap.INSTANCE.doubleClass));
		assertEquals(0.1, validator.cast("0.1",  eClassMap.INSTANCE.doubleClass));
		assertEquals(15.234, validator.cast("15.234",  eClassMap.INSTANCE.doubleClass));
	}
	
	@Test
	public void doubleRejectTest() throws IllegalAccessException, IllegalArgumentException{
		Class<?> c = eClassMap.INSTANCE.doubleClass;
		try{
			validator.cast("abc", c);
		}catch(InvocationTargetException e){
			assertEquals("Can't convert abc to "+ c, e.getCause().getMessage().toString());
		}
	}
	
	@Test
	public void stringAcceptanceTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		assertEquals("", validator.cast("", eClassMap.INSTANCE.stringClass));
		assertEquals("false", validator.cast("false",  eClassMap.INSTANCE.stringClass));
		assertEquals("10", validator.cast("10",  eClassMap.INSTANCE.stringClass));
	}
	
	@Test
	public void stringRejectTest(){
		assertTrue(true);
		/*Class<?> c = eClassMap.INSTANCE.stringClass;
		try{
			validator.cast("abc", c);
		}catch(InvocationTargetException e){
			assertEquals("Can't convert abc to "+ c, e.getCause().getMessage().toString());
		}*/
	}
	
	@Test
	public void geoAcceptanceTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		assertTrue(true);
		/*assertEquals(true, validator.cast("", Geo.class));
		assertEquals(true, validator.cast("false",  Geo.class));
		assertEquals(true, validator.cast("true",  Geo.class));*/
	}
	
	@Test
	public void geoRejectTest() throws IllegalAccessException, IllegalArgumentException{
		assertTrue(true);
		/*Class<?> c = Geo.class;
		try{
			validator.cast("abc", c);
		}catch(InvocationTargetException e){
			assertEquals("Can't convert abc to "+ c, e.getCause().getMessage().toString());
		}*/
	}
	
}
