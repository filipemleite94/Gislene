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
	
	@After
	public void cleanUp() throws DatabaseException{
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
}
