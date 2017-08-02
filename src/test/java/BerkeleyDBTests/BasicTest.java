package BerkeleyDBTests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sleepycat.je.DatabaseException;

import BerkeleyBD.*;
import COMM.ICommManager;
import Proxies.*;

public class BasicTest {
	static ICommManager keyHandler;
	
	private static void purgeDirectory(File dir) {
	    for (File file: dir.listFiles()) {
	        if (file.isDirectory()) purgeDirectory(file);
	        file.delete();
	    }
	}
	
	@BeforeClass
	public static void setUp() throws Exception {
		File f = new File(".//Teste//");		
		if(!f.exists()){
			f.mkdir();
		}
		purgeDirectory(f);
		keyHandler = new KeyManager();
		keyHandler.InitiateComm(f, "Tests");
	}
	
	@Before
	public void updateSetUp() throws DatabaseException{
		keyHandler = new KeyManager();
		keyHandler.InitiateComm(new File(".//Teste//"), "Tests");
	}
	
	@After
	public void cleanUp() throws DatabaseException{
		keyHandler.cleanUpDatabase();
		keyHandler.terminateComm();
	}
	
	@Test
	public void setUpTests() throws DatabaseException{
		GenericAdm<PPropertyType> ptAdm = (GenericAdm<PPropertyType>) keyHandler.getIComm(PPropertyType.class);
		assertEquals(0,ptAdm.getAll().size());
		PPropertyType ppType = new PPropertyType();
		assertNotNull(ppType.getID());
		ppType.setID();
		ptAdm.persistInDatabase(ppType);
		assertEquals(ppType.getID(), ptAdm.getAll().get(0).getID());
	}
	
	@Test
	public void containsTest() throws DatabaseException{
		GenericAdm<PPropertyType> ptAdm = (GenericAdm<PPropertyType>) keyHandler.getIComm(PPropertyType.class);
		assertFalse(ptAdm.contains((long) 0));
		PPropertyType ppType = new PPropertyType();
		PPropertyType ppType2 = new PPropertyType();
		assertNotEquals(ppType.getID(), ppType2.getID());
		ptAdm.persistInDatabase(ppType);
		ptAdm.persistInDatabase(ppType2);
		assertTrue(ptAdm.contains(ppType.getID()));
		assertTrue(ptAdm.contains(ppType2.getID()));
	}
	
	@Test
	public void managementTest() throws DatabaseException{
		GenericAdm<PPropertyType> ptAdm = (GenericAdm<PPropertyType>) keyHandler.getIComm(PPropertyType.class);
		assertFalse(ptAdm.contains((long) 0));
		PPropertyType ppType = new PPropertyType();
		PPropertyType ppType2 = new PPropertyType();
		assertNotEquals(ppType.getID(), ppType2.getID());
		ptAdm.persistInDatabase(ppType);
		ptAdm.persistInDatabase(ppType2);
		ptAdm.deleteFromDB(ppType2.getID());
		assertFalse(ptAdm.contains(ppType2.getID()));
		ppType2 = new PPropertyType();
		assertFalse(ptAdm.contains(ppType2.getID()));
		ptAdm.persistInDatabase(ppType2);
		assertTrue(ptAdm.contains(ppType2.getID()));
		ptAdm.deleteEverything();
		assertFalse(ptAdm.contains(ppType.getID()));
		assertFalse(ptAdm.contains(ppType2.getID()));
	}
	
}
