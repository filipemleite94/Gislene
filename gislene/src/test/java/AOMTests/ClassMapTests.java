package AOMTests;

import static org.junit.Assert.*;

import org.junit.Test;

import AOM.eClassMap;

public class ClassMapTests {
	eClassMap classMap = eClassMap.INSTANCE;
	
	@Test
	public void testAcceptance() throws ClassNotFoundException {
		assertEquals(classMap.getClassGenerico(classMap.booleanName), classMap.booleanClass);
		assertEquals(classMap.getClassGenerico(classMap.charName), classMap.charClass);
		assertEquals(classMap.getClassGenerico(classMap.intName), classMap.intClass);
		assertEquals(classMap.getClassGenerico(classMap.doubleName), classMap.doubleClass);
		assertEquals(classMap.getClassGenerico(classMap.stringName), classMap.stringClass);
		assertEquals(classMap.getClassGenerico(classMap.geoName), classMap.geoClass);
	}
	
	@Test
	public void testRegect(){
		try{
			classMap.getClassGenerico("bool");
		}catch(ClassNotFoundException e){
			assertEquals("A classe bool nao eh prevista", e.getMessage());
		}
	}
	
	@Test
	public void testInvertedAcceptance() throws ClassNotFoundException {
		assertEquals(classMap.getNameClassGenerico(classMap.booleanClass), classMap.booleanName);
		assertEquals(classMap.getNameClassGenerico(classMap.charClass), classMap.charName);
		assertEquals(classMap.getNameClassGenerico(classMap.intClass), classMap.intName);
		assertEquals(classMap.getNameClassGenerico(classMap.doubleClass), classMap.doubleName);
		assertEquals(classMap.getNameClassGenerico(classMap.stringClass), classMap.stringName);
		assertEquals(classMap.getNameClassGenerico(classMap.geoClass), classMap.geoName);
	}
	
	@Test
	public void testInvertedRegect(){
		try{
			classMap.getNameClassGenerico(Object.class);
		}catch(ClassNotFoundException e){
			assertEquals("A classe Object nao eh prevista", e.getMessage());
		}
	}

}
