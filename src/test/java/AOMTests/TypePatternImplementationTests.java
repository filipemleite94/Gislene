package AOMTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import AOM.Accountability;
import AOM.AccountabilityType;
import AOM.Property;
import AOM.PropertyType;
import AOM.ITypePatternListener;
import AOM.eClassMap;

public class TypePatternImplementationTests {
	String standardName = "foo";
	
	Property prop;
	String standardClassName = eClassMap.INSTANCE.booleanName;
	Class<?> standardClass;
	
	Accountability account;
	String parentName = "fooParent";
	String childName = "fooChild";
	ITypePatternListener mockParent;
	ITypePatternListener mockListenerAccept;
	ITypePatternListener mockListenerReject;
	
	@Before
	public void setUp() throws ClassNotFoundException{
		AccountabilityType aType;
		PropertyType pType;
		
		aType = new AccountabilityType(standardName);
		standardClass = eClassMap.INSTANCE.getClassGenerico(standardClassName);
		pType = new PropertyType(standardName, standardClassName);
		
		prop = new Property(pType);
		mockParent = Mockito.mock(ITypePatternListener.class);
		account = new Accountability(aType, mockParent);
		
		mockListenerReject = Mockito.mock(ITypePatternListener.class);
		Mockito.when(mockListenerReject.checkIfReciprocal(Mockito.isA(AccountabilityType.class))).thenReturn(false);
		
		mockListenerAccept = Mockito.mock(ITypePatternListener.class);
		Mockito.when(mockParent.getName()).thenReturn(parentName);
		Mockito.when(mockListenerAccept.getName()).thenReturn(childName);
		Mockito.when(mockListenerAccept.checkIfReciprocal(Mockito.isA(AccountabilityType.class))).thenReturn(true);
	}
	
	@Test
	public void creationTests(){
		assertEquals(standardName, account.getName());
		assertEquals(parentName, account.getParent().getName());
		assertEquals(null, account.getChild());
		
		assertEquals(standardName, prop.getName());
		assertEquals(standardClass, prop.getClasse());
		assertEquals(false, prop.getValue());
	}
	
	@Test
	public void accountabilityTests() throws IOException{
		assertFalse(account.setChild(mockListenerReject));
		assertEquals(null, account.getChild());
		assertTrue(account.setChild(mockListenerAccept));
		assertEquals(mockListenerAccept, account.getChild());
	}
	
	@Test
	public void propertyTests(){
		assertFalse(prop.setValue("asd"));
		assertEquals(false, prop.getValue());
		assertTrue(prop.setValue("true"));
		assertEquals(true, prop.getValue());
	}
	
}
