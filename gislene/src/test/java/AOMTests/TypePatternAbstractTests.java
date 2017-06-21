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

public class TypePatternAbstractTests {
	PropertyType pType;
	AccountabilityType aType;

	@Before
	public void setUp() throws ClassNotFoundException{
		pType = new PropertyType("foo", eClassMap.INSTANCE.stringName);
		aType = new AccountabilityType("foo");
	}
	
	@Test
	public void testBasicCreation(){
		assertTrue(pType.getName().equals("foo"));
		assertTrue(aType.getName().equals("foo"));
		assertEquals(eClassMap.INSTANCE.stringClass, pType.getPropertyClass());
		assertEquals("foo2", pType.getValue("foo2"));
		assertEquals(aType, aType.getReciprocal());
	}
	
	@Test
	public void testContainerManagement(){
		TypePatternContainer cont = Mockito.mock(TypePatternContainer.class);
		TypePatternContainer cont2 = Mockito.mock(TypePatternContainer.class);
		
		assertTrue(aType.addContainer(cont));
		assertTrue(pType.addContainer(cont));
		
		assertTrue(aType.removeContainer(cont));
		assertFalse(aType.removeContainer(cont));
		assertFalse(pType.removeContainer(cont2));
		assertTrue(pType.addContainer(cont2));
		assertTrue(aType.addContainer(cont2));
		
		Mockito.verify(cont, Mockito.never()).removeAccountabilityType(aType);
		Mockito.verify(cont2, Mockito.never()).removePropertyType(pType);
		
		aType.deleteType();
		pType.deleteType();
		
		Mockito.verify(cont, Mockito.never()).removeAccountabilityType(aType);
		Mockito.verify(cont2, Mockito.times(1)).removeAccountabilityType(aType);
		Mockito.verify(cont2, Mockito.times(1)).removePropertyType(pType);
		Mockito.verify(cont, Mockito.times(1)).removePropertyType(pType);
	}
	
	@Test
	public void testSetReciprocal(){
		AccountabilityType aType2 = new AccountabilityType("foo2");
		assertEquals(aType, aType.getReciprocal());
		aType.setReciprocal(aType2);
		assertEquals(aType2, aType.getReciprocal());
	}
}
