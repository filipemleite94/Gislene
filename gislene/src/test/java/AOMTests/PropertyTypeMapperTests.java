package AOMTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import AOM.eClassMap;
import AOM.eTypePatternMapper;

public class PropertyTypeMapperTests {
	private final eTypePatternMapper propTypeMapper = 
			eTypePatternMapper.TypePatternMapperInstance;
	
	@Before
	public void setUp() throws Exception {
		propTypeMapper.clearMaps();
	}

	@Test
	public void testManagePropertyTypes() throws IOException, ClassNotFoundException {
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
	public void testRemoveProperty() throws ClassNotFoundException, IOException{
		propTypeMapper.putPropertyType("foo", eClassMap.INSTANCE.booleanName);
		propTypeMapper.removePropertyType("foo");
		try{
			propTypeMapper.getPropertyType("foo");
		}catch(IOException e){
			assertEquals("Inexistent PropertyType", e.getMessage());
		}
	}

}
