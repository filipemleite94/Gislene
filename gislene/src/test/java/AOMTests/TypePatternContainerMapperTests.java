package AOMTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import AOM.AccountabilityType;
import AOM.PropertyType;
import AOM.eClassMap;
import AOM.eTypePatternContainerMapper;
import AOM.eTypePatternMapper;

public class TypePatternContainerMapperTests {
	private static eTypePatternContainerMapper typeContMap = eTypePatternContainerMapper.TypeMapperInstance;
	private static eTypePatternMapper typeMap = eTypePatternMapper.TypePatternMapperInstance;
	private PropertyType pType;
	private AccountabilityType aType;
	private String standardName = "foo";
	
	@Before
	public void setUp() throws Exception {
		typeContMap.cleanMap();
		typeMap.clearMaps();
		typeMap.putPropertyType(standardName, eClassMap.INSTANCE.stringName);
		typeMap.putAccountabilityType(standardName);
		pType = typeMap.getPropertyType(standardName);
		aType = typeMap.getAccountabilityType(standardName);
	}
	
	@AfterClass
	public static void cleanUp(){
		typeContMap.cleanMap();
		typeMap.clearMaps();		
	}
	
	@Test
	public void testManagingContainers() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException{
		String msg = null;
		try{
			typeContMap.removeContainer("foo");
		}catch(IOException e){
			msg = e.getMessage();
		}
		assertEquals("Inexistent Container", msg);
		msg = null;
		typeContMap.addContainer("foo");
		try{
			typeContMap.addContainer("foo");
		}catch(IOException e){
			msg = e.getMessage();
		}
		assertEquals("Duplicate key", msg);
		msg = null;
		typeContMap.removeContainer("foo");
		typeContMap.addContainer("foo");
	}
	
	@Test
	public void testManagingAccountabilityType() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		String msg = null;
		try {
			typeContMap.addAccountabilityType("foo", standardName);
		} catch (IOException e) {
			msg = e.getMessage();
		}
		assertEquals("Inexistent Container", msg);
		msg = null;
		typeContMap.addContainer("foo");
		typeContMap.addAccountabilityType("foo", standardName);
		assertTrue(typeContMap.getContainer("foo").getAccountabilityTypes().contains(aType));
		typeContMap.removeAccountabilityTypeFromContainer("foo", standardName);
		assertFalse(typeContMap.getContainer("foo").getAccountabilityTypes().contains(aType));
		assertFalse(typeContMap.removeAccountabilityTypeFromContainer("foo", standardName));
	}
	
	@Test
	public void testManagingPropertyType() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		String msg = null;
		try {
			typeContMap.addPropertyType("foo", standardName);
		} catch (IOException e) {
			msg = e.getMessage();
		}
		assertEquals("Inexistent Container", msg);
		msg = null;
		typeContMap.addContainer("foo");
		typeContMap.addPropertyType("foo", standardName);
		assertTrue(typeContMap.getContainer("foo").getPropertyTypes().contains(pType));
		typeContMap.removePropertyTypeFromContainer("foo", standardName);
		assertFalse(typeContMap.getContainer("foo").getPropertyTypes().contains(pType));
		assertFalse(typeContMap.removePropertyTypeFromContainer("foo", standardName));
	}
}
