package AOMTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import AOM.PropertyType;
import AOM.TypePatternContainer;
import AOM.TypePatternListener;

public class PropertyTypeContainerTests {
	
	private class MockPropertyTypeContainer extends TypePatternContainer{

		public MockPropertyTypeContainer(String name) {
			super(name);
		}

	}
	
	private MockPropertyTypeContainer pTypeContainer;
	private String mockName;
	
	@Before
	public void setUp(){
		mockName = "Mock";
		pTypeContainer = new MockPropertyTypeContainer(mockName);
	}
	
	@Test
	public void testCreation() {
		 assertEquals("Mock", pTypeContainer.getName());
		 assertEquals(0,  pTypeContainer.getPropertyTypes().size());
	}
	
	@Test
	public void testManagingPropertiesType() throws IOException{
		PropertyType pType = Mockito.mock(PropertyType.class);
		PropertyType pType2 = Mockito.mock(PropertyType.class);
		pTypeContainer.addPropertyType(pType);
		assertEquals(true, pTypeContainer.getPropertyTypes().contains(pType));
		pTypeContainer.addPropertyType(pType2);
		assertEquals(true, pTypeContainer.getPropertyTypes().contains(pType2));
		assertEquals(2, pTypeContainer.getPropertyTypes().size());
		pTypeContainer.removePropertyType(pType);
		assertEquals(true, pTypeContainer.getPropertyTypes().contains(pType2));
		try{
			pTypeContainer.removePropertyType(pType);
		} catch(IOException e){
			assertEquals("Propriedade nao encontrada", e.getMessage());
		}
	}
	
	@Test
	public void testManagingListeneter() throws IOException{
		TypePatternListener listener = Mockito.mock(TypePatternListener.class);
		TypePatternListener listener2 = Mockito.mock(TypePatternListener.class);
		pTypeContainer.addListener(listener);
		try{
			pTypeContainer.addListener(listener);
		}catch(IOException e){
			assertEquals("Listener ja existe", e.getMessage());
		}
		assertEquals(true, pTypeContainer.getTypeListeners().contains(listener));
		pTypeContainer.addListener(listener2);
		assertEquals(true, pTypeContainer.getTypeListeners().contains(listener2));
		assertEquals(2, pTypeContainer.getTypeListeners().size());
		pTypeContainer.removeListener(listener);
		assertEquals(true, pTypeContainer.getTypeListeners().contains(listener2));
		try{
			pTypeContainer.removeListener(listener);
		} catch(IOException e){
			assertEquals("Listener nao encontrado", e.getMessage());
		}		
	}

	@Test
	public void testDeleteContainer() throws IOException{
		TypePatternListener listener = Mockito.mock(TypePatternListener.class);
		TypePatternListener listener2 = Mockito.mock(TypePatternListener.class);
		Mockito.verify(listener, Mockito.never()).erase();
		Mockito.verify(listener2, Mockito.never()).erase();
		pTypeContainer.addListener(listener);
		pTypeContainer.addListener(listener2);
		pTypeContainer.deleteContainer();
		assertEquals(0, pTypeContainer.getTypeListeners().size());
		assertEquals(0, pTypeContainer.getPropertyTypes().size());
		Mockito.verify(listener, Mockito.times(1)).erase();
		Mockito.verify(listener2, Mockito.times(1)).erase();
	}
	
	@Test
	public void testManagingPropertiesListenerVerifying() throws IOException{
		PropertyType pType = Mockito.mock(PropertyType.class);
		PropertyType pType2 = Mockito.mock(PropertyType.class);		
		TypePatternListener listener = Mockito.mock(TypePatternListener.class);
		TypePatternListener listener2 = Mockito.mock(TypePatternListener.class);
		pTypeContainer.addPropertyType(pType);
		pTypeContainer.addListener(listener);
		Mockito.verify(listener, Mockito.times(1)).addProperty(Mockito.isA(PropertyType.class));
		Mockito.verify(listener2, Mockito.never()).addProperty(Mockito.isA(PropertyType.class));
		pTypeContainer.addListener(listener2);
		pTypeContainer.addPropertyType(pType2);
		Mockito.verify(listener, Mockito.times(2)).addProperty(Mockito.isA(PropertyType.class));
		Mockito.verify(listener2, Mockito.times(2)).addProperty(Mockito.isA(PropertyType.class));
		pTypeContainer.removePropertyType(pType);
		Mockito.verify(listener, Mockito.times(1)).removeProperty(Mockito.isA(PropertyType.class));
		Mockito.verify(listener2, Mockito.times(1)).removeProperty(Mockito.isA(PropertyType.class));
		pTypeContainer.removeListener(listener);
		Mockito.verify(listener, Mockito.times(2)).removeProperty(Mockito.isA(PropertyType.class));
		Mockito.verify(listener2, Mockito.times(1)).removeProperty(Mockito.isA(PropertyType.class));
	}
}
