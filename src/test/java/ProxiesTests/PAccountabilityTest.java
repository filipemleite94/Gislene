package ProxiesTests;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import AOM.Accountability;
import AOM.AccountabilityType;
import AOM.ITypePatternListener;
import AOM.Objeto;
import AOM.Type;
import COMM.aProxyClassMap;
import Proxies.PAccountability;

public class PAccountabilityTest {
	private static Accountability account;
	private static ITypePatternListener parent;
	private static ITypePatternListener child;
	private static AccountabilityType aType;
	
	@BeforeClass
	public static void setUp() throws IOException{
		parent = Mockito.mock(Objeto.class);
		child = Mockito.mock(Type.class);
		aType = new AccountabilityType("TypeTest");
		
		account = new Accountability(aType, parent);
		account.setChild(child);
		aProxyClassMap.accountabilityTypeMap.stage(aType);
		aProxyClassMap.objetoMap.stage((Objeto) parent);
		aProxyClassMap.typeMap.stage((Type)child);
	}
	
	@Test
	public void creationTest() throws IOException{
		PAccountability paccount = (PAccountability) aProxyClassMap.getNewProxy(aType);
		
	}
	
}
