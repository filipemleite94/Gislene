package gislene;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

public class testesBD {
	
	GPSManager DBM;
	
	@Before
	public void init () {
		
	}
	
	@Test
	public void testInsertSQLeEditar() throws SQLException {
		GPSManager DBM = new GPSManager("objetos3.fdb");
		DBM.CreateDatabase("objetos3");
		DBM.deleteEVERYTHINGSqlData();
		DBM.InsertSQL("IDsql1", "SQLCode1");
	    ResultSet resultSet = DBM.getSQLresultset();
	    boolean achou = false;
	    while(resultSet.next() && !achou){
	    	if(resultSet.getString("IDsql").equals("IDsql1")&&resultSet.getString("SQLcd").equals("SQLCode1")){
	    		assertTrue(true);
	    		achou = true;
	    	}
	    }
	    if(!achou)
	    	assertTrue(false);
	    DBM.EditSQL("IDsql2", "SQLCode2");
	    resultSet = DBM.getSQLresultset();
	    achou = false;
	    while(resultSet.next() && !achou){
	    	if(resultSet.getString("IDsql").equals("IDsql2")&&resultSet.getString("SQLcd").equals("SQLCode2")){
	    		assertTrue(true);
	    		achou = true;
	    	}
	    }
	    if(!achou)
	    	assertTrue(false);
	}
	@Test
	public void testInserirObjetoEDeletar () throws SQLException{
		//COMO SIMULAR ENTRADA DE USUARIO		
		String data = "objeto1\n123";
	 	InputStream stdin = System.in;
	 	System.setIn(new ByteArrayInputStream(data.getBytes()));
	 	Scanner scanner = new Scanner(System.in);
	 	System.setIn(stdin);
		 
		GPSManager DBM = new GPSManager("objetos.fdb");
		DBM.CreateDatabase("objetos");
		DBM.deleteEVERYTHINGSObjetos();

        DBM.InsertObjetoFromUserInput(scanner);
        ResultSet resultSet = DBM.getRegisteredObjects();
        while(resultSet.next()){
	    	assertTrue(resultSet.getString("Nome").equals("objeto1"));
	    	assertTrue(resultSet.getString("Coordenadas").equals("123"));
	    }
        data = "objeto1";
	 	stdin = System.in;
	 	System.setIn(new ByteArrayInputStream(data.getBytes()));
	 	scanner = new Scanner(System.in);
	 	System.setIn(stdin);
	 	DBM.DeleteObjectFromUserInput(scanner);
	 	resultSet = DBM.getRegisteredObjects();
	 	while(resultSet.next()){
	    	assertFalse(resultSet.getString("Nome").equals("objeto1"));
	    }
		//DBM.Show_RegisteredObjects ();
	}
}