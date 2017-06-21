package AOMTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import AOM.AccountabilityType;
import AOM.PropertyType;
import AOM.TypePatternContainer;
import AOM.eClassMap;
import AOM.eTypePatternMapper;

public class TypeMapperTests {
	private static eTypePatternMapper propTypeMapper = 
			eTypePatternMapper.TypePatternMapperInstance;
	
	@Before
	public void setUp(){
		propTypeMapper.clearMaps();
	}

	@AfterClass
	public static void cleanUp(){
		propTypeMapper.clearMaps();
	}

	@Test
	public void testAddPropertyTypes() throws IOException, ClassNotFoundException {
		String msg = null;
		try{
			propTypeMapper.getPropertyType("foo");
		}catch(IOException e){
			msg = e.getMessage();
		}
		assertEquals("Inexistent PropertyType", msg);
		msg = null;
		propTypeMapper.putPropertyType("foo", eClassMap.INSTANCE.booleanName);
		assertEquals("foo", propTypeMapper.getPropertyType("foo").getName());
		try{
			propTypeMapper.putPropertyType("foo", eClassMap.INSTANCE.booleanName);
		}catch(IOException e){
			msg = e.getMessage();
		}
		assertEquals("Duplicate PropertyType name", msg);
		msg = null;
		try{
			propTypeMapper.putPropertyType("abc", "bool");
		}catch(ClassNotFoundException e){
			msg = e.getMessage();
		}
		assertEquals("A classe bool nao eh prevista", msg);
		msg = null;
	}
	
	
	
	@Test
	public void testRemovePropertyType() throws ClassNotFoundException, IOException{
		PropertyType pType;
		String msg = null;
		TypePatternContainer cont = Mockito.mock(TypePatternContainer.class);
		propTypeMapper.putPropertyType("foo", eClassMap.INSTANCE.booleanName);
		pType = propTypeMapper.getPropertyType("foo");
		pType.addContainer(cont);
		propTypeMapper.removePropertyType("foo");
		Mockito.verify(cont, Mockito.times(1)).removePropertyType(pType);
		try{
			propTypeMapper.getPropertyType("foo");
		}catch(IOException e){
			msg = e.getMessage();			
		}
		assertEquals("Inexistent PropertyType", msg);
		msg = null;
	}
	
	@Test
	public void testAddAccountabilityTypes() throws IOException, ClassNotFoundException {
		String msg = null;
		try{
			propTypeMapper.getAccountabilityType("foo");
		}catch(IOException e){
			msg = e.getMessage();
		}
		assertEquals("Inexistent AccountabilityType", msg);
		msg = null;
		propTypeMapper.putAccountabilityType("foo");
		assertEquals("foo", propTypeMapper.getAccountabilityType("foo").getName());
		try{
			propTypeMapper.putAccountabilityType("foo");
		}catch(IOException e){
			msg = e.getMessage();
		}
		assertEquals("Duplicate AccountabilityType name", msg);
		msg = null;
	}

	@Test
	public void testRemoveAccountabilityType() throws ClassNotFoundException, IOException{
		AccountabilityType aType;
		TypePatternContainer cont = Mockito.mock(TypePatternContainer.class);
		String msg = null;
		propTypeMapper.putAccountabilityType("foo");
		aType = propTypeMapper.getAccountabilityType("foo");
		aType.addContainer(cont);
		propTypeMapper.removeAccountabilityType("foo");
		Mockito.verify(cont, Mockito.times(1)).removeAccountabilityType(aType);
		try{
			propTypeMapper.getAccountabilityType("foo");
		}catch(IOException e){
			msg = e.getMessage();
		}
		assertEquals("Inexistent AccountabilityType", msg);
		msg = null;
	}
}
