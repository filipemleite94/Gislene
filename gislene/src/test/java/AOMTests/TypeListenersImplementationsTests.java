package AOMTests;

import AOM.AccountabilityType;
import AOM.Category;
import AOM.Geo;
import AOM.Objeto;
import AOM.PropertyType;
import AOM.Type;
import AOM.eClassMap;
import AOM.eValidator;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TypeListenersImplementationsTests {
	private Objeto objeto;
	private Type type;
	private String pos;
	private String standardString;
	private eClassMap classMap = eClassMap.INSTANCE;
	
	@Before
	public void setUp() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException{
		pos = ((Geo.class).cast(eValidator.INSTANCE.cast("", Geo.class))).getPointsString();
		standardString = "foo";
		type = new Type(standardString, Mockito.mock(Category.class));
		objeto = new Objeto(standardString, pos, type);
		type.addListener(objeto);
	}
	
	@Test
	public void creationTests() throws IOException{
		assertEquals(pos, objeto.getGeo().getPointsString());
		assertEquals(standardString, objeto.getName());
		assertEquals(standardString, type.getName());
		assertTrue(type.getTypeListeners().contains(objeto));
	}
	
	@Test
	public void manageProperties() throws ClassNotFoundException{
		PropertyType pType = new PropertyType("fooP", classMap.stringName);
		assertFalse(objeto.removeProperty(pType));
		assertTrue(type.addPropertyType(pType));
		assertTrue(objeto.removeProperty(pType));
		assertTrue(objeto.addProperty(pType));
		assertTrue(type.removePropertyType(pType));
		assertFalse(objeto.removeProperty(pType));
	}
	
	@Test
	public void manageAccountability(){
		AccountabilityType aType = new AccountabilityType("fooP");
		assertFalse(objeto.removeAccountability(aType));
		assertTrue(type.addAccountabilityType(aType));
		assertTrue(objeto.removeAccountability(aType));
		assertTrue(objeto.addAccountability(aType));
		assertTrue(type.removeAccountabilityType(aType));
		assertFalse(objeto.removeAccountability(aType));
	}
}
