package AOMTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import AOM.PropertyType;
import AOM.PropertyTypeContainer;
import AOM.PropertyTypeListener;

public class PropertyTypeContainerTests {
	
	private class MockPropertyTypeContainer extends PropertyTypeContainer{

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
		 assertEquals(0,  pTypeContainer.getPropertiesType().size());
	}
	
	@Test
	public void testManagingPropertiesType() throws IOException{
		PropertyType pType = Mockito.mock(PropertyType.class);
		PropertyType pType2 = Mockito.mock(PropertyType.class);
		pTypeContainer.addPropertyType(pType);
		assertEquals(pType, pTypeContainer.getPropertiesType().get(0));
		pTypeContainer.addPropertyType(pType2);
		assertEquals(pType2, pTypeContainer.getPropertiesType().get(1));
		assertEquals(2, pTypeContainer.getPropertiesType().size());
		pTypeContainer.removePropertyType(pType);
		assertEquals(pType2, pTypeContainer.getPropertiesType().get(0));
		try{
			pTypeContainer.removePropertyType(pType);
		} catch(IOException e){
			assertEquals("Propriedade nao encontrada", e.getMessage());
		}
	}
	
	@Test
	public void testManagingListeneter() throws IOException{
		PropertyTypeListener listener = Mockito.mock(PropertyTypeListener.class);
		PropertyTypeListener listener2 = Mockito.mock(PropertyTypeListener.class);
		pTypeContainer.addListener(listener);
		assertEquals(listener, pTypeContainer.getPropertyTypeListeners().get(0));
		pTypeContainer.addListener(listener2);
		assertEquals(listener2, pTypeContainer.getPropertyTypeListeners().get(1));
		assertEquals(2, pTypeContainer.getPropertyTypeListeners().size());
		pTypeContainer.removeListener(listener);
		assertEquals(listener2, pTypeContainer.getPropertyTypeListeners().get(0));
		try{
			pTypeContainer.removeListener(listener);
		} catch(IOException e){
			assertEquals("Listener nao encontrado", e.getMessage());
		}		
	}

	@Test
	public void testDeleteContainer(){
		PropertyTypeListener listener = Mockito.mock(PropertyTypeListener.class);
		PropertyTypeListener listener2 = Mockito.mock(PropertyTypeListener.class);
		Mockito.verify(listener, Mockito.never()).erase();
		Mockito.verify(listener2, Mockito.never()).erase();
		pTypeContainer.addListener(listener);
		pTypeContainer.addListener(listener2);
		pTypeContainer.deleteContainer();
		assertEquals(0, pTypeContainer.getPropertyTypeListeners().size());
		assertEquals(0, pTypeContainer.getPropertiesType().size());
		Mockito.verify(listener, Mockito.times(1)).erase();
		Mockito.verify(listener2, Mockito.times(1)).erase();
	}
	
	@Test
	public void testManagingPropertiesListenerVerifying() throws IOException{
		PropertyType pType = Mockito.mock(PropertyType.class);
		PropertyType pType2 = Mockito.mock(PropertyType.class);		
		PropertyTypeListener listener = Mockito.mock(PropertyTypeListener.class);
		PropertyTypeListener listener2 = Mockito.mock(PropertyTypeListener.class);
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
