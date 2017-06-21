package AOMTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import AOM.AccountabilityType;
import AOM.PropertyType;
import AOM.TypePatternContainer;
import AOM.TypePatternListener;

public class TypePatternContainerTests {
	
	private class MockPropertyTypeContainer extends TypePatternContainer{
		public MockPropertyTypeContainer(String name) {
			super(name);
		}
	}
	
	private MockPropertyTypeContainer typeContainer;
	private String mockName;
	
	@Before
	public void setUp(){
		mockName = "Mock";
		typeContainer = new MockPropertyTypeContainer(mockName);
	}
	
	@Test
	public void testCreation() {
		 assertEquals("Mock", typeContainer.getName());
		 assertEquals(0,  typeContainer.getPropertyTypes().size());
		 assertEquals(0, typeContainer.getAccountabilityTypes().size());
		 assertEquals(0, typeContainer.getTypeListeners().size());
	}
	
	@Test
	public void testManagingPropertiesType(){
		PropertyType pType = Mockito.mock(PropertyType.class);
		PropertyType pType2 = Mockito.mock(PropertyType.class);
		typeContainer.addPropertyType(pType);
		assertTrue(typeContainer.getPropertyTypes().contains(pType));
		assertTrue(typeContainer.addPropertyType(pType2));
		assertTrue(typeContainer.getPropertyTypes().contains(pType2));
		assertEquals(2, typeContainer.getPropertyTypes().size());
		assertTrue(typeContainer.removePropertyType(pType));
		assertTrue(typeContainer.getPropertyTypes().contains(pType2));
		assertFalse(typeContainer.removePropertyType(pType));
	}
	
	@Test
	public void testManagingAccountabilityType(){
		AccountabilityType aType = Mockito.mock(AccountabilityType.class);
		AccountabilityType aType2 = Mockito.mock(AccountabilityType.class);
		assertTrue(typeContainer.addAccountabilityType(aType));
		assertTrue(typeContainer.getAccountabilityTypes().contains(aType));
		assertTrue(typeContainer.addAccountabilityType(aType2));
		assertTrue(typeContainer.getAccountabilityTypes().contains(aType2));
		assertEquals(2, typeContainer.getAccountabilityTypes().size());
		assertTrue(typeContainer.removeAccountabilityType(aType));
		assertTrue(typeContainer.getAccountabilityTypes().contains(aType2));
		assertFalse(typeContainer.removeAccountabilityType(aType));
	}
	
	@Test
	public void testManagingListeneters(){
		TypePatternListener listener = Mockito.mock(TypePatternListener.class);
		TypePatternListener listener2 = Mockito.mock(TypePatternListener.class);
		assertTrue(typeContainer.addListener(listener));
		assertFalse(typeContainer.addListener(listener));
		assertTrue(typeContainer.getTypeListeners().contains(listener));
		assertTrue(typeContainer.addListener(listener2));
		assertTrue(typeContainer.getTypeListeners().contains(listener2));
		assertEquals(2, typeContainer.getTypeListeners().size());
		assertTrue(typeContainer.removeListener(listener));
		assertTrue(typeContainer.getTypeListeners().contains(listener2));
		assertFalse(typeContainer.removeListener(listener));
	}

	@Test
	public void testDeleteContainer() throws IOException{
		TypePatternListener listener = Mockito.mock(TypePatternListener.class);
		TypePatternListener listener2 = Mockito.mock(TypePatternListener.class);
		PropertyType pType = Mockito.mock(PropertyType.class);
		AccountabilityType aType = Mockito.mock(AccountabilityType.class);
		
		Mockito.verify(listener, Mockito.never()).erase();
		Mockito.verify(listener2, Mockito.never()).erase();
		Mockito.verify(aType, Mockito.never()).removeContainer(typeContainer);
		Mockito.verify(pType, Mockito.never()).removeContainer(typeContainer);
		
		typeContainer.addPropertyType(pType);
		typeContainer.addAccountabilityType(aType);
		typeContainer.addListener(listener);
		typeContainer.addListener(listener2);
		typeContainer.deleteContainer();
		
		assertEquals(0, typeContainer.getTypeListeners().size());
		assertEquals(0, typeContainer.getPropertyTypes().size());
		assertEquals(0, typeContainer.getAccountabilityTypes().size());
		
		Mockito.verify(listener, Mockito.times(1)).erase();
		Mockito.verify(listener2, Mockito.times(1)).erase();
		Mockito.verify(pType, Mockito.times(1)).removeContainer(typeContainer);
		Mockito.verify(aType, Mockito.times(1)).removeContainer(typeContainer);
	}
	
	@Test
	public void testManagingPropertyListenerVerifying() throws IOException{
		PropertyType pType = Mockito.mock(PropertyType.class);
		PropertyType pType2 = Mockito.mock(PropertyType.class);		
		TypePatternListener listener = Mockito.mock(TypePatternListener.class);
		TypePatternListener listener2 = Mockito.mock(TypePatternListener.class);
		typeContainer.addPropertyType(pType);
		typeContainer.addListener(listener);
		Mockito.verify(listener, Mockito.times(1)).addProperty(Mockito.isA(PropertyType.class));
		Mockito.verify(listener2, Mockito.never()).addProperty(Mockito.isA(PropertyType.class));
		typeContainer.addListener(listener2);
		typeContainer.addPropertyType(pType2);
		Mockito.verify(listener, Mockito.times(2)).addProperty(Mockito.isA(PropertyType.class));
		Mockito.verify(listener2, Mockito.times(2)).addProperty(Mockito.isA(PropertyType.class));
		typeContainer.removePropertyType(pType);
		Mockito.verify(listener, Mockito.times(1)).removeProperty(Mockito.isA(PropertyType.class));
		Mockito.verify(listener2, Mockito.times(1)).removeProperty(Mockito.isA(PropertyType.class));
		typeContainer.removeListener(listener);
		Mockito.verify(listener, Mockito.times(2)).removeProperty(Mockito.isA(PropertyType.class));
		Mockito.verify(listener2, Mockito.times(1)).removeProperty(Mockito.isA(PropertyType.class));
	}
	
	@Test
	public void testManagingAccountabilityTypeListenerVerifying() throws IOException{
		AccountabilityType aType = Mockito.mock(AccountabilityType.class);
		AccountabilityType aType2 = Mockito.mock(AccountabilityType.class);		
		TypePatternListener listener = Mockito.mock(TypePatternListener.class);
		TypePatternListener listener2 = Mockito.mock(TypePatternListener.class);
		typeContainer.addAccountabilityType(aType);
		typeContainer.addListener(listener);
		Mockito.verify(listener, Mockito.times(1)).addAccountability(Mockito.isA(AccountabilityType.class));
		Mockito.verify(listener2, Mockito.never()).addAccountability(Mockito.isA(AccountabilityType.class));
		typeContainer.addListener(listener2);
		typeContainer.addAccountabilityType(aType2);
		Mockito.verify(listener, Mockito.times(2)).addAccountability(Mockito.isA(AccountabilityType.class));
		Mockito.verify(listener2, Mockito.times(2)).addAccountability(Mockito.isA(AccountabilityType.class));
		typeContainer.removeAccountabilityType(aType);
		Mockito.verify(listener, Mockito.times(1)).removeAccountability(Mockito.isA(AccountabilityType.class));
		Mockito.verify(listener2, Mockito.times(1)).removeAccountability(Mockito.isA(AccountabilityType.class));
		typeContainer.removeListener(listener);
		Mockito.verify(listener, Mockito.times(2)).removeAccountability(Mockito.isA(AccountabilityType.class));
		Mockito.verify(listener2, Mockito.times(1)).removeAccountability(Mockito.isA(AccountabilityType.class));
	}
	
}
