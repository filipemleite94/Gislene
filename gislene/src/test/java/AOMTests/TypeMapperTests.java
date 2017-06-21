package AOMTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import AOM.AccountabilityType;
import AOM.PropertyType;
import AOM.TypePatternContainer;
import AOM.eClassMap;
import AOM.eTypePatternMapper;

public class TypeMapperTests {
	private final eTypePatternMapper propTypeMapper = 
			eTypePatternMapper.TypePatternMapperInstance;
	
	@Before
	public void setUp() throws Exception {
		propTypeMapper.clearMaps();
	}


	@Test
	public void testAddPropertyTypes() throws IOException, ClassNotFoundException {
		try{
			propTypeMapper.getPropertyType("foo");
		}catch(IOException e){
			assertEquals("Inexistent PropertyType", e.getMessage());
		}
		propTypeMapper.putPropertyType("foo", eClassMap.INSTANCE.booleanName);
		assertEquals("foo", propTypeMapper.getPropertyType("foo").getName());
		try{
			propTypeMapper.putPropertyType("foo", eClassMap.INSTANCE.booleanName);
		}catch(IOException e){
			assertEquals("Duplicate PropertyType name", e.getMessage());
		}
		try{
			propTypeMapper.putPropertyType("abc", "bool");
		}catch(ClassNotFoundException e){
			assertEquals("A classe bool nao eh prevista", e.getMessage());
		}
	}
	
	
	
	@Test
	public void testRemovePropertyType() throws ClassNotFoundException, IOException{
		PropertyType pType;
		TypePatternContainer cont = Mockito.mock(TypePatternContainer.class);
		propTypeMapper.putPropertyType("foo", eClassMap.INSTANCE.booleanName);
		pType = propTypeMapper.getPropertyType("foo");
		pType.addContainer(cont);
		propTypeMapper.removePropertyType("foo");
		Mockito.verify(cont, Mockito.times(1)).removePropertyType(pType);
		try{
			propTypeMapper.getPropertyType("foo");
		}catch(IOException e){
			assertEquals("Inexistent PropertyType", e.getMessage());
		}
	}
	
	@Test
	public void testAddAccountabilityTypes() throws IOException, ClassNotFoundException {
		try{
			propTypeMapper.getAccountabilityType("foo");
		}catch(IOException e){
			assertEquals("Inexistent AccountabilityType", e.getMessage());
		}
		propTypeMapper.putAccountabilityType("foo");
		assertEquals("foo", propTypeMapper.getAccountabilityType("foo").getName());
		try{
			propTypeMapper.putAccountabilityType("foo");
		}catch(IOException e){
			assertEquals("Duplicate AccountabilityType name", e.getMessage());
		}
	}

	@Test
	public void testRemoveAccountabilityType() throws ClassNotFoundException, IOException{
		AccountabilityType aType;
		TypePatternContainer cont = Mockito.mock(TypePatternContainer.class);
		propTypeMapper.putAccountabilityType("foo");
		aType = propTypeMapper.getAccountabilityType("foo");
		aType.addContainer(cont);
		propTypeMapper.removeAccountabilityType("foo");
		Mockito.verify(cont, Mockito.times(1)).removeAccountabilityType(aType);
		try{
			propTypeMapper.getAccountabilityType("foo");
		}catch(IOException e){
			assertEquals("Inexistent AccountabilityType", e.getMessage());
		}
	}
}
